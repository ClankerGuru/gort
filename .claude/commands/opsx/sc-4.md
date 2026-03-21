---
name: "OPSX: sc-4"
description: "Create `catalog/src/commonMain/kotlin/zone/clanker/gort/catalog/CatalogContent.kt` containing the `CatalogContent()` composable (lines 227–260 of `CatalogApp.kt`). Change `private fun` to `internal fun`. The file needs: `package zone.clanker.gort.catalog`, imports for `Composable`, `Box`, `Column`, `Modifier`, `fillMaxHeight`, `widthIn`, `padding`, `Alignment`, `Arrangement`, `rememberScrollState`, `verticalScroll`, `dp`, `Gort`, `GortColors`, `GortTypography`. Then delete lines 227–260 from `CatalogApp.kt` and remove the now-unused screen imports (`ThemeScreen`, `ButtonsScreen`, `InputsScreen`, `DisplayScreen`, `NavigationScreen`, `FeedbackScreen`, `CompoundScreen`, `DataScreen`, `ChatScreen`) from `CatalogApp.kt`. Do NOT modify any other file. Verify: `./gradlew :catalog:compileCommonMainKotlinMetadata`. (split-catalog-app)"
category: Task
tags: [task, split-catalog-app]
---

⬜ **sc-4**: Create `catalog/src/commonMain/kotlin/zone/clanker/gort/catalog/CatalogContent.kt` containing the `CatalogContent()` composable (lines 227–260 of `CatalogApp.kt`). Change `private fun` to `internal fun`. The file needs: `package zone.clanker.gort.catalog`, imports for `Composable`, `Box`, `Column`, `Modifier`, `fillMaxHeight`, `widthIn`, `padding`, `Alignment`, `Arrangement`, `rememberScrollState`, `verticalScroll`, `dp`, `Gort`, `GortColors`, `GortTypography`. Then delete lines 227–260 from `CatalogApp.kt` and remove the now-unused screen imports (`ThemeScreen`, `ButtonsScreen`, `InputsScreen`, `DisplayScreen`, `NavigationScreen`, `FeedbackScreen`, `CompoundScreen`, `DataScreen`, `ChatScreen`) from `CatalogApp.kt`. Do NOT modify any other file. Verify: `./gradlew :catalog:compileCommonMainKotlinMetadata`.

**Proposal:** split-catalog-app

## Context

Read these files before starting:
- `opsx/changes/split-catalog-app/proposal.md` — what & why
- `opsx/changes/split-catalog-app/design.md` — how
- `opsx/changes/split-catalog-app/tasks.md` — all tasks & progress

## Implementation

1. Read the context files above
2. Implement this task: **Create `catalog/src/commonMain/kotlin/zone/clanker/gort/catalog/CatalogContent.kt` containing the `CatalogContent()` composable (lines 227–260 of `CatalogApp.kt`). Change `private fun` to `internal fun`. The file needs: `package zone.clanker.gort.catalog`, imports for `Composable`, `Box`, `Column`, `Modifier`, `fillMaxHeight`, `widthIn`, `padding`, `Alignment`, `Arrangement`, `rememberScrollState`, `verticalScroll`, `dp`, `Gort`, `GortColors`, `GortTypography`. Then delete lines 227–260 from `CatalogApp.kt` and remove the now-unused screen imports (`ThemeScreen`, `ButtonsScreen`, `InputsScreen`, `DisplayScreen`, `NavigationScreen`, `FeedbackScreen`, `CompoundScreen`, `DataScreen`, `ChatScreen`) from `CatalogApp.kt`. Do NOT modify any other file. Verify: `./gradlew :catalog:compileCommonMainKotlinMetadata`.**
3. When complete, mark done: `./gradlew opsx-sc-4 --set=done`

## ⚠️ Reconciliation Warning

This task references symbols not found in the codebase:
- **`Change`** — not found. Did you mean: `g`, `h`, `c`?
- **`Composable`** — not found. Did you mean: `b`, `s`, `l`?
- **`Box`** — not found. Did you mean: `b`, `x`, `Checkbox`?
- **`Column`** — not found. Did you mean: `l`, `c`, `m`?
- **`Modifier`** — not found. Did you mean: `r`, `m`, `focusModifier`?
- **`Alignment`** — not found. Did you mean: `g`, `l`, `m`?
- **`Arrangement`** — not found. Did you mean: `r`, `g`, `m`?
- **`Then`** — not found. Did you mean: `h`, `theme`, `thin`?
- **`NOT`** — not found. Did you mean: `annotated`, `Notification`, `none`?
- **`Verify`** — not found. Did you mean: `r`, `i`, `y`?

Review and update this task if the referenced code has changed.

