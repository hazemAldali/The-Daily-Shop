#!/usr/bin/env bash
set -euo pipefail
APK="${1:-recovery/app-release.apk}"
OUT="${2:-recovery/decompiled}"
if [[ ! -f "$APK" ]]; then
  echo "Missing APK: $APK" >&2
  exit 2
fi
mkdir -p "$OUT"
JADX_VERSION="1.5.7"
JADX_ZIP="/tmp/jadx-${JADX_VERSION}.zip"
curl --fail --location --retry 3 "https://github.com/skylot/jadx/releases/download/v${JADX_VERSION}/jadx-${JADX_VERSION}.zip" -o "$JADX_ZIP"
mkdir -p /tmp/jadx
unzip -q -o "$JADX_ZIP" -d /tmp/jadx
chmod +x /tmp/jadx/bin/jadx

# JADX may return exit code 3 after producing usable sources for classes it
# could not fully reconstruct. Preserve its complete output and report status.
set +e
/tmp/jadx/bin/jadx --show-bad-code --escape-unicode -d "$OUT" "$APK" 2>&1 | tee "$OUT/jadx.log"
JADX_STATUS=${PIPESTATUS[0]}
set -e

JAVA_COUNT=$(find "$OUT/sources" -type f -name '*.java' 2>/dev/null | wc -l | tr -d ' ')
RESOURCE_COUNT=$(find "$OUT/resources" -type f 2>/dev/null | wc -l | tr -d ' ')
{
  echo "JADX_VERSION=$JADX_VERSION"
  echo "JADX_EXIT_CODE=$JADX_STATUS"
  echo "JAVA_SOURCE_FILES=$JAVA_COUNT"
  echo "RESOURCE_FILES=$RESOURCE_COUNT"
  if [[ "$JADX_STATUS" -eq 0 ]]; then
    echo "RECOVERY_STATUS=COMPLETE"
  elif [[ "$JAVA_COUNT" -gt 0 ]]; then
    echo "RECOVERY_STATUS=PARTIAL_WITH_DECOMPILATION_ERRORS"
  else
    echo "RECOVERY_STATUS=FAILED_NO_SOURCE_OUTPUT"
  fi
} | tee "$OUT/RECOVERY_STATUS.txt"

if [[ "$JAVA_COUNT" -eq 0 ]]; then
  echo "JADX produced no Java source files." >&2
  exit 3
fi

if [[ "$JADX_STATUS" -ne 0 ]]; then
  echo "::warning::JADX returned $JADX_STATUS after producing $JAVA_COUNT Java files; preserving partial recovery output."
fi
