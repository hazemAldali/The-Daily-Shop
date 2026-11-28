#!/usr/bin/env bash
set -euo pipefail
mkdir -p test-output
adb wait-for-device
adb shell getprop ro.build.version.release > test-output/android-version.txt
adb shell getprop ro.build.version.sdk > test-output/android-sdk.txt
adb install -r /tmp/the-daily-shop.apk > test-output/install.txt 2>&1
adb shell pm clear com.hazem.dailystore > test-output/pm-clear.txt 2>&1
adb logcat -c
adb shell monkey -p com.hazem.dailystore -c android.intent.category.LAUNCHER 1 > test-output/launch.txt 2>&1
sleep 14

dump_ui() {
  local name="$1"
  adb shell uiautomator dump "/sdcard/${name}.xml" > "test-output/uiautomator-${name}.txt" 2>&1
  adb pull "/sdcard/${name}.xml" "test-output/${name}.xml" >> "test-output/uiautomator-${name}.txt" 2>&1
}
bounds_for() {
  local xml="$1" kind="$2" needle="$3" index="${4:-0}"
  python3 - "$xml" "$kind" "$needle" "$index" <<'PY'
import sys, xml.etree.ElementTree as ET
path, kind, needle, index = sys.argv[1], sys.argv[2], sys.argv[3], int(sys.argv[4])
root = ET.parse(path).getroot()
matches = []
for node in root.iter('node'):
    if node.attrib.get('class') == kind and needle in node.attrib.get('text',''):
        b = node.attrib.get('bounds','')
        import re
        m = re.fullmatch(r'\[(\d+),(\d+)\]\[(\d+),(\d+)\]', b)
        if m:
            x1,y1,x2,y2 = map(int,m.groups())
            matches.append(f"{(x1+x2)//2} {(y1+y2)//2}")
if len(matches) <= index:
    sys.exit(2)
print(matches[index])
PY
}
tap_bounds() {
  local pair="$1"; read -r x y <<< "$pair"; adb shell input tap "$x" "$y"
}
type_text() { adb shell input text "$1"; }
SMOKE_SUFFIX="$(date +%s)"
SMOKE_USERNAME="smoke${SMOKE_SUFFIX}"
SMOKE_PASSWORD="TdsSmoke${SMOKE_SUFFIX}X9"

# Clean first-run state must show the bootstrap form.
dump_ui first-run
grep -q "إعداد المتجر لأول مرة" test-output/first-run.xml
FIRST_FIELDS="$(python3 - <<'PY'
import xml.etree.ElementTree as ET
r=ET.parse('test-output/first-run.xml').getroot()
print(sum(1 for n in r.iter('node') if n.attrib.get('class')=='android.widget.EditText'))
PY
)"
test "$FIRST_FIELDS" -eq 4

# Create an emulator-only manager account with non-production credentials.
tap_bounds "$(bounds_for test-output/first-run.xml android.widget.EditText 'اسم المدير')"
type_text "SmokeManager"
adb shell input keyevent 61
type_text "$SMOKE_USERNAME"
adb shell input keyevent 61
type_text "$SMOKE_PASSWORD"
adb shell input keyevent 61
type_text "$SMOKE_PASSWORD"
adb shell input keyevent 4
sleep 2
tap_bounds "$(bounds_for test-output/first-run.xml android.widget.Button 'إنشاء حساب المدير')"
sleep 8
dump_ui after-admin-create
grep -q "تسجيل الدخول" test-output/after-admin-create.xml
grep -q "دخول" test-output/after-admin-create.xml
echo "admin_creation=passed" > test-output/auth-test-result.txt

login_with_test_account() {
  local xml="$1" stage="$2"
  local fields
  fields="$(python3 - "$xml" <<'PY'
import sys, xml.etree.ElementTree as ET
r=ET.parse(sys.argv[1]).getroot()
nodes=[n for n in r.iter('node') if n.attrib.get('class')=='android.widget.EditText']
print(len(nodes))
PY
)"
  test "$fields" -ge 2
  tap_bounds "$(bounds_for "$xml" android.widget.EditText '')"
  type_text "$SMOKE_USERNAME"
  adb shell input keyevent 61
  type_text "$SMOKE_PASSWORD"
  adb shell input keyevent 4
  sleep 2
  tap_bounds "$(bounds_for "$xml" android.widget.Button 'دخول')"
  sleep 10
  dump_ui "$stage"
  if grep -q 'text="تسجيل الدخول"\|text="إعداد المتجر لأول مرة"' "test-output/$stage.xml"; then
    echo "Login did not leave the login/bootstrap screen at $stage" >&2
    return 1
  fi
}
login_with_test_account test-output/after-admin-create.xml after-first-login
echo "login_after_creation=passed" >> test-output/auth-test-result.txt
adb exec-out screencap -p > test-output/after-first-login.png

# Relaunch without clearing app data. Either a restored authenticated POS session
# or a login screen is acceptable; the account must remain and authenticate again.
adb shell am force-stop com.hazem.dailystore
adb shell monkey -p com.hazem.dailystore -c android.intent.category.LAUNCHER 1 > test-output/relaunch.txt 2>&1
sleep 12
dump_ui after-restart
if grep -q 'text="إعداد المتجر لأول مرة"' test-output/after-restart.xml; then
  echo "Admin bootstrap reappeared after restart" >&2
  exit 1
fi
if grep -q 'text="تسجيل الدخول"' test-output/after-restart.xml; then
  login_with_test_account test-output/after-restart.xml after-restart-login
  echo "session_restore=not_restored_but_account_login_after_restart=passed" >> test-output/auth-test-result.txt
else
  echo "session_restore=passed" >> test-output/auth-test-result.txt
fi
# Primary-device designation is the next required stage after authentication.
# This runs only in the disposable emulator database.
if grep -q 'text="اعتماد هذا الهاتف جهازاً رئيسياً"' test-output/after-restart.xml; then
  tap_bounds "$(bounds_for test-output/after-restart.xml android.widget.Button 'اعتماد هذا الهاتف جهازاً رئيسياً')"
  sleep 12
  dump_ui after-primary-designation
  python3 - <<'PY'
import xml.etree.ElementTree as ET, sys
r=ET.parse('test-output/after-primary-designation.xml').getroot()
texts=[n.attrib.get('text','') for n in r.iter('node')]
all_text='\\n'.join(texts)
if 'الدور: رئيسي' not in all_text:
    print(all_text)
    sys.exit('Primary designation did not produce a confirmed primary-role state')
PY
  echo "primary_designation=passed" >> test-output/auth-test-result.txt
else
  echo "Primary designation action was not present on authenticated device page" >&2
  exit 1
fi

# Confirm that primary role survives a process restart.
adb shell am force-stop com.hazem.dailystore
adb shell monkey -p com.hazem.dailystore -c android.intent.category.LAUNCHER 1 > test-output/relaunch-primary.txt 2>&1
sleep 12
dump_ui after-primary-restart
python3 - <<'PY'
import xml.etree.ElementTree as ET, sys
r=ET.parse('test-output/after-primary-restart.xml').getroot()
texts=[n.attrib.get('text','') for n in r.iter('node')]
all_text='\\n'.join(texts)
if 'الدور: رئيسي' not in all_text:
    print(all_text)
    sys.exit('Primary role did not persist after process restart')
PY
echo "primary_role_persistence=passed" >> test-output/auth-test-result.txt
adb exec-out screencap -p > test-output/after-primary-restart.png
adb shell pidof com.hazem.dailystore > test-output/app-pid-final.txt
adb logcat -d -v threadtime > test-output/logcat-full.txt 2>&1
adb logcat -d -v threadtime AndroidRuntime:E System.err:E libc:F DEBUG:F '*:S' > test-output/crash-logcat.txt 2>&1
adb shell dumpsys activity lastanr > test-output/last-anr.txt 2>&1
printf 'install=passed\nfirst_run_setup=passed\nadmin_creation=passed\nlogin_after_creation=passed\nrestart_account_persistence=passed\n' > test-output/result.txt
cat test-output/result.txt
cat test-output/auth-test-result.txt
