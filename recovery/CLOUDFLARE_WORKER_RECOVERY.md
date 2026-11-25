# Cloudflare Worker recovery — separate workstream

## Known deployed service
- Worker endpoint previously used by the Android app: https://the-daily-shop-relay.hazem-syrian.workers.dev
- The Android client expects pairing, response polling, and WebSocket relay routes under /v1/.

## Evidence available
- The endpoint and route names are present in the prior debugging record.
- Prior observations included a successful pairing request (HTTP 200) but 403 responses while reading the pairing response and connecting to the WebSocket endpoint.
- The GitLab development branch tree contains no Worker source, wrangler.toml, package.json, or deployment bundle.

## Recovery boundary
The Android APK contains the client-side networking code, not the deployed Cloudflare Worker source. Recreating the Worker from route names alone would risk breaking authentication, certificate validation, replay protection, pairing state, and message sequencing.

## Required recovery inputs
One of:
1. The original Worker source directory or ZIP backup.
2. A Cloudflare Workers deployment/source export.
3. Authorized Cloudflare account access that exposes the deployed Worker version/source.

Do not put API tokens, account IDs that are secret, private keys, device credentials, or signing material in this repository. Do not replace the live Worker until the recovered source has been reviewed and tested in a separate staging Worker.
