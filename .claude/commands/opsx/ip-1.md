---
name: "OPSX: ip-1"
description: "Update `:icons` module `build.gradle.kts` to publish as `zone.clanker:gort-icons` artifact. Change the publishing coordinates in the POM to use `artifactId = \"gort-icons\"`, `groupId = \"zone.clanker\"`. Remove the `signing` block that uses `useGpgCmd()` and replace with the same pattern as `:gort` module. Ensure the `maven-publish` and `signing` plugins are applied. File: `icons/build.gradle.kts`. Verify: `./gradlew :icons:tasks --group=publishing` shows publish tasks. (icons-publishing)"
category: Task
tags: [task, icons-publishing]
---

✅ **ip-1**: Update `:icons` module `build.gradle.kts` to publish as `zone.clanker:gort-icons` artifact. Change the publishing coordinates in the POM to use `artifactId = "gort-icons"`, `groupId = "zone.clanker"`. Remove the `signing` block that uses `useGpgCmd()` and replace with the same pattern as `:gort` module. Ensure the `maven-publish` and `signing` plugins are applied. File: `icons/build.gradle.kts`. Verify: `./gradlew :icons:tasks --group=publishing` shows publish tasks.

**Proposal:** icons-publishing

## Context

Read these files before starting:
- `opsx/changes/icons-publishing/proposal.md` — what & why
- `opsx/changes/icons-publishing/design.md` — how
- `opsx/changes/icons-publishing/tasks.md` — all tasks & progress

## Implementation

1. Read the context files above
2. Implement this task: **Update `:icons` module `build.gradle.kts` to publish as `zone.clanker:gort-icons` artifact. Change the publishing coordinates in the POM to use `artifactId = "gort-icons"`, `groupId = "zone.clanker"`. Remove the `signing` block that uses `useGpgCmd()` and replace with the same pattern as `:gort` module. Ensure the `maven-publish` and `signing` plugins are applied. File: `icons/build.gradle.kts`. Verify: `./gradlew :icons:tasks --group=publishing` shows publish tasks.**
3. When complete, mark done: `./gradlew opsx-ip-1 --set=done`

