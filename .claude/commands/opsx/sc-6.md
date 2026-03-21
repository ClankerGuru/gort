---
name: "OPSX: sc-6"
description: "Clean up `CatalogApp.kt` imports. After all extractions, remove every import that is no longer used in the file. The remaining file should be ~60 lines containing only `CatalogApp()` and its imports (~10-12 imports for Compose layout, Gort theme, GortScaffold, WindowSize). Do NOT modify any other file. Verify: `./gradlew :catalog:compileCommonMainKotlinMetadata` and `./gradlew opsx-verify` (import count should be under 30). (split-catalog-app)"
category: Task
tags: [task, split-catalog-app]
---

⬜ **sc-6**: Clean up `CatalogApp.kt` imports. After all extractions, remove every import that is no longer used in the file. The remaining file should be ~60 lines containing only `CatalogApp()` and its imports (~10-12 imports for Compose layout, Gort theme, GortScaffold, WindowSize). Do NOT modify any other file. Verify: `./gradlew :catalog:compileCommonMainKotlinMetadata` and `./gradlew opsx-verify` (import count should be under 30).

**Proposal:** split-catalog-app

## Context

Read these files before starting:
- `opsx/changes/split-catalog-app/proposal.md` — what & why
- `opsx/changes/split-catalog-app/design.md` — how
- `opsx/changes/split-catalog-app/tasks.md` — all tasks & progress

## Implementation

1. Read the context files above
2. Implement this task: **Clean up `CatalogApp.kt` imports. After all extractions, remove every import that is no longer used in the file. The remaining file should be ~60 lines containing only `CatalogApp()` and its imports (~10-12 imports for Compose layout, Gort theme, GortScaffold, WindowSize). Do NOT modify any other file. Verify: `./gradlew :catalog:compileCommonMainKotlinMetadata` and `./gradlew opsx-verify` (import count should be under 30).**
3. When complete, mark done: `./gradlew opsx-sc-6 --set=done`

## ⚠️ Reconciliation Warning

This task references symbols not found in the codebase:
- **`Clean`** — not found. Did you mean: `l`, `c`, `a`?
- **`After`** — not found. Did you mean: `r`, `a`?
- **`Compose`** — not found. Did you mean: `s`, `c`, `m`?
- **`NOT`** — not found. Did you mean: `annotated`, `Notification`, `none`?
- **`Verify`** — not found. Did you mean: `r`, `i`, `y`?

Review and update this task if the referenced code has changed.

