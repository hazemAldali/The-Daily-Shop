# Binary recovery artifact inventory — 2026-11-25

The original APK was retrieved from the prior GitLab development branch into the isolated recovery workspace and verified before extraction.

## Original APK
- File: app-release.apk
- Size: 19,587,400 bytes
- SHA-256: 1ecb5c388ba7cf8f67fe6aa23c903f15c55d8d818f43f960515005b14a744ab0

## Extracted DEX
- classes.dex: 10,075,620 bytes
- classes2.dex: 2,320 bytes
- classes3.dex: 6,191,932 bytes
- DEX archive: dex-files-2026-11-25.zip
- Archive size: 6,096,181 bytes
- Archive SHA-256: a8a904af11f6651b2a5897f3bba3da7f97c0ceaf96f2794f8e207c7e8e3ad12d

## Extracted binary resources
Includes resources.arsc, AndroidManifest.xml, res/, assets/, and native libraries under lib/.
- Archive: binary-resources-2026-11-25.zip
- Archive size: 7,504,488 bytes
- Archive SHA-256: dfbf83e2108038b0112e1291d863ba2f1be25466a3cfd8e7d12c6d9e697e4040

## Existing class skeleton archive
- Source: GitLab development branch, class_skeletons.zip
- Size: 171,839 bytes
- SHA-256: b4cdd7a4d395116881dde7395babbf71e7e1a54a3bd6398322f105e8589f340c

## Important transfer status
The files above have been extracted and verified in the recovery workspace. They have NOT yet been committed as binary objects to this GitHub repository. GitHub connector access currently exposes text-file create/fetch and Git blob APIs, but does not provide a direct binary-file upload or release-asset upload operation. Do not claim these binary artifacts are already stored in GitHub.

## JADX status
JADX 1.5.7 could not be downloaded in the isolated execution environment because outbound DNS/network access is unavailable. No DEX decompilation has been run in this pass. The DEX files are preserved intact and ready for JADX.

## Android Gradle status
No original Gradle wrapper, settings.gradle, build.gradle, Kotlin/Java source tree, or test tree was found in the GitLab development branch. The APK alone cannot be rebuilt into the original application. A decompiled source tree and dependency reconstruction are prerequisites.

## Cloudflare Worker status
No Worker source, wrangler.toml, package.json, or deployment project files were present in the GitLab development branch tree. The Worker is not embedded as source in the Android APK. Recovery requires a separate source backup, deployment bundle, or Cloudflare account/API access. Do not invent Worker source or claim it has been restored.
