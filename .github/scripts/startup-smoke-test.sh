#!/usr/bin/env bash
set -u
mkdir -p test-output
adb wait-for-device
adb shell getprop ro.build.version.release > test-output/android-version.txt 2>&1
adb shell getprop ro.build.version.sdk > test-output/android-sdk.txt 2>&1
adb install -r /tmp/the-daily-shop.apk > test-output/install.txt 2>&1
INSTALL_STATUS=$?
adb logcat -c
PID_STATUS=1
LAUNCH_STATUS=1
SCREENSHOT_STATUS=1
UI_STATUS=1
if [ "$INSTALL_STATUS" -eq 0 ]; then
  adb shell monkey -p com.hazem.dailystore -c android.intent.category.LAUNCHER 1 > test-output/launch.txt 2>&1
  LAUNCH_STATUS=$?
  sleep 12
  adb shell pidof com.hazem.dailystore > test-output/app-pid.txt 2>&1
  PID_STATUS=$?
  adb shell dumpsys activity activities > test-output/activity-state.txt 2>&1
  adb shell dumpsys window windows > test-output/window-state-before-wait.txt 2>&1
  adb exec-out screencap -p > test-output/startup-screen-before-wait.png 2> test-output/screenshot-error.txt
  SCREENSHOT_STATUS=$?
  adb shell uiautomator dump /sdcard/window-before-wait.xml > test-output/uiautomator-before-wait.txt 2>&1
  adb pull /sdcard/window-before-wait.xml test-output/window-before-wait.xml >> test-output/uiautomator-before-wait.txt 2>&1

  # If the emulator displays Android's System UI ANR dialog, choose Wait once.
  if adb shell uiautomator dump /sdcard/window-check.xml >/dev/null 2>&1 && adb pull /sdcard/window-check.xml /tmp/window-check.xml >/dev/null 2>&1; then
    if grep -q "System UI isn't responding" /tmp/window-check.xml; then
      echo "System UI ANR dialog detected; selecting Wait (not Close app)." > test-output/system-ui-anr-action.txt
      adb shell input tap 540 1060 >> test-output/system-ui-anr-action.txt 2>&1
      sleep 15
    else
      echo "No System UI ANR dialog detected at first check." > test-output/system-ui-anr-action.txt
    fi
  fi

  adb exec-out screencap -p > test-output/startup-screen-after-wait.png 2>> test-output/screenshot-error.txt
  adb shell uiautomator dump /sdcard/window-after-wait.xml > test-output/uiautomator-after-wait.txt 2>&1
  adb pull /sdcard/window-after-wait.xml test-output/window-after-wait.xml >> test-output/uiautomator-after-wait.txt 2>&1
  adb shell dumpsys activity activities > test-output/activity-state-after-wait.txt 2>&1
  adb shell dumpsys window windows > test-output/window-state-after-wait.txt 2>&1
else
  echo "Install failed; launch skipped." > test-output/launch.txt
fi
adb shell pidof com.hazem.dailystore > test-output/app-pid-final.txt 2>&1
adb logcat -d -v threadtime > test-output/logcat-full.txt 2>&1
adb logcat -d -v threadtime AndroidRuntime:E System.err:E libc:F DEBUG:F '*:S' > test-output/crash-logcat.txt 2>&1
adb shell dumpsys activity lastanr > test-output/last-anr.txt 2>&1
printf 'install_status=%s\nlaunch_command_status=%s\nprocess_alive_status=%s\nscreenshot_status=%s\n' "$INSTALL_STATUS" "$LAUNCH_STATUS" "$PID_STATUS" "$SCREENSHOT_STATUS" > test-output/result.txt
cat test-output/result.txt
if [ "$INSTALL_STATUS" -ne 0 ] || [ "$LAUNCH_STATUS" -ne 0 ] || [ "$PID_STATUS" -ne 0 ] || [ "$SCREENSHOT_STATUS" -ne 0 ]; then
  echo "Startup smoke test failed; inspect test-output logs."
  exit 1
fi
echo "Startup smoke test passed; post-wait screen and ANR diagnostics captured."
