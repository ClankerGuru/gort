# Fix Signing Pattern

## Tasks

- [ ] `fs-1` agent:github retries:2 — Fix the signing configuration in `icons/build.gradle.kts` to match exactly the pattern used in `gort/build.gradle.kts`. Currently `:icons` uses `useInMemoryPgpKeys(signingKey, signingPassword)` with `SIGNING_KEY`/`SIGNING_PASSWORD` env vars. It should use `useGpgCmd()` with `GPG_PASSPHRASE` env var instead — exactly like `:gort`. Look at `gort/build.gradle.kts` lines 89-100 for the reference pattern: `signing { val gpgPassphrase = ...; if (gpgPassphrase != null) { useGpgCmd() }; sign(publishing.publications) }` and the `tasks.withType<Sign>` guard. Copy that exact block into `icons/build.gradle.kts`, replacing the current signing block. File: `icons/build.gradle.kts` (modify), `gort/build.gradle.kts` (read-only reference). Verify: `grep -A10 'signing {' icons/build.gradle.kts` shows `useGpgCmd()`.
