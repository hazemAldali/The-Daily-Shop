#!/usr/bin/env bash
set -u
mkdir -p test-output
adb wait-for-device
adb shell getprop ro.build.version.release > test-output/android-version.txt 2>&1
adb install -r /tmp/the-daily-shop.apk > test-output/install.txt 2>&1
INSTALL_STATUS=$?
adb logcat -c
PID_STATUS=1
LAUNCH_STATUS=1
SCREENSHOT_STATUS=1
if [ "$INSTALL_STATUS" -eq 0 ]; then
  adb shell monkey -p com.hazem.dailystore -c android.intent.category.LAUNCHER 1 > test-output/launch.txt 2>&1
  LAUNCH_STATUS=$?
  sleep 20
  adb shell pidof com.hazem.dailystore > test-output/app-pid.txt 2>&1
  PID_STATUS=$?
  adb shell dumpsys activity activities > test-output/activity-state.txt 2>&1
  adb shell dumpsys window windows > test-output/window-state.txt 2>&1
  adb exec-out screencap -p > test-output/startup-screen.png 2> test-output/screenshot-error.txt
  SCREENSHOT_STATUS=$?
  adb shell uiautomator dump /sdcard/window.xml > test-output/uiautomator-dump.txt 2>&1
  adb pull /sdcard/window.xml test-output/window.xml >> test-output/uiautomator-dump.txt 2>&1
else
  echo "Install failed; launch skipped." > test-output/launch.txt
fi
adb logcat -d -v threadtime > test-output/logcat-full.txt 2>&1
adb logcat -d -v threadtime AndroidRuntime:E System.err:E libc:F DEBUG:F '*:S' > test-output/crash-logcat.txt 2>&1
printf 'install_status=%s\nlaunch_command_status=%s\nprocess_alive_status=%s\nscreenshot_status=%s\n' "$INSTALL_STATUS" "$LAUNCH_STATUS" "$PID_STATUS" "$SCREENSHOT_STATUS" > test-output/result.txt
cat test-output/result.txt
if [ "$INSTALL_STATUS" -ne 0 ] || [ "$LAUNCH_STATUS" -ne 0 ] || [ "$PID_STATUS" -ne 0 ]; then
  echo "Startup smoke test failed; inspect test-output logs."
  exit 1
fi
echo "Startup smoke test passed: app process remained alive for 20 seconds."
