#!/usr/bin/env bash
set -euo pipefail
APK="${1:-recovery/input/app-release.apk}"
OUT="${2:-recovery/decompiled}"
if [[ ! -f "$APK" ]]; then
  echo "Missing APK: $APK" >&2
  exit 2
fi
mkdir -p "$OUT"
# Pin the JADX version for reproducible decompilation.
JADX_VERSION="1.5.7"
JADX_ZIP="/tmp/jadx-${JADX_VERSION}.zip"
curl --fail --location --retry 3 "https://github.com/skylot/jadx/releases/download/v${JADX_VERSION}/jadx-${JADX_VERSION}.zip" -o "$JADX_ZIP"
mkdir -p /tmp/jadx
unzip -q -o "$JADX_ZIP" -d /tmp/jadx
chmod +x /tmp/jadx/bin/jadx
/tmp/jadx/bin/jadx --show-bad-code --escape-unicode -d "$OUT" "$APK"
