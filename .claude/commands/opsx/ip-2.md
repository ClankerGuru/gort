---
name: "OPSX: ip-2"
description: "Update `:gort` module to depend on published `zone.clanker:gort-icons` instead of `project(\":icons\")`. In `gort/build.gradle.kts`, change `api(project(\":icons\"))` to `api(\"zone.clanker:gort-icons:$version\")` where version comes from the project version. Add `gort-icons` to the version catalog in `gradle/libs.versions.toml` as `gortIcons = { module = \"zone.clanker:gort-icons\", version = \"0.1.0\" }` and reference it as `api(libs.gortIcons)`. File: `gort/build.gradle.kts`, `gradle/libs.versions.toml`. Verify: `./gradlew :gort:dependencies` shows `zone.clanker:gort-icons`. (icons-publishing)"
category: Task
tags: [task, icons-publishing]
---

⛔ **ip-2**: Update `:gort` module to depend on published `zone.clanker:gort-icons` instead of `project(":icons")`. In `gort/build.gradle.kts`, change `api(project(":icons"))` to `api("zone.clanker:gort-icons:$version")` where version comes from the project version. Add `gort-icons` to the version catalog in `gradle/libs.versions.toml` as `gortIcons = { module = "zone.clanker:gort-icons", version = "0.1.0" }` and reference it as `api(libs.gortIcons)`. File: `gort/build.gradle.kts`, `gradle/libs.versions.toml`. Verify: `./gradlew :gort:dependencies` shows `zone.clanker:gort-icons`.

**Proposal:** icons-publishing

## Context

Read these files before starting:
- `opsx/changes/icons-publishing/proposal.md` — what & why
- `opsx/changes/icons-publishing/design.md` — how
- `opsx/changes/icons-publishing/tasks.md` — all tasks & progress

## Implementation

1. Read the context files above
2. Implement this task: **Update `:gort` module to depend on published `zone.clanker:gort-icons` instead of `project(":icons")`. In `gort/build.gradle.kts`, change `api(project(":icons"))` to `api("zone.clanker:gort-icons:$version")` where version comes from the project version. Add `gort-icons` to the version catalog in `gradle/libs.versions.toml` as `gortIcons = { module = "zone.clanker:gort-icons", version = "0.1.0" }` and reference it as `api(libs.gortIcons)`. File: `gort/build.gradle.kts`, `gradle/libs.versions.toml`. Verify: `./gradlew :gort:dependencies` shows `zone.clanker:gort-icons`.**
3. When complete, mark done: `./gradlew opsx-ip-2 --set=done`

## ⚠️ Reconciliation Warning

This task references symbols not found in the codebase:
- **`File`** — not found. Did you mean: `l`, `i`, `FileTree`?
- **`Verify`** — not found. Did you mean: `r`, `i`, `y`?

Review and update this task if the referenced code has changed.

