---
name: "OPSX: sc-3"
description: "Create `catalog/src/commonMain/kotlin/zone/clanker/gort/catalog/CatalogNavigation.kt` containing `NavRail()` (lines 139–183) and `BottomNavBar()` (lines 185–225) from `CatalogApp.kt`. Change both from `private fun` to `internal fun`. The file needs: `package zone.clanker.gort.catalog`, imports for `Composable`, `Column`, `Row`, `Box`, `Spacer`, `Modifier`, `fillMaxWidth`, `fillMaxHeight`, `width`, `height`, `size`, `background`, `padding`, `clickable`, `Arrangement`, `Alignment`, `Image`, `ColorFilter`, `BasicText`, `rememberScrollState`, `verticalScroll`, `dp`, `sp`, `Gort`. Then delete lines 139–225 from `CatalogApp.kt`. Do NOT modify any other file. Verify: `./gradlew :catalog:compileCommonMainKotlinMetadata`. (split-catalog-app)"
category: Task
tags: [task, split-catalog-app]
---

⬜ **sc-3**: Create `catalog/src/commonMain/kotlin/zone/clanker/gort/catalog/CatalogNavigation.kt` containing `NavRail()` (lines 139–183) and `BottomNavBar()` (lines 185–225) from `CatalogApp.kt`. Change both from `private fun` to `internal fun`. The file needs: `package zone.clanker.gort.catalog`, imports for `Composable`, `Column`, `Row`, `Box`, `Spacer`, `Modifier`, `fillMaxWidth`, `fillMaxHeight`, `width`, `height`, `size`, `background`, `padding`, `clickable`, `Arrangement`, `Alignment`, `Image`, `ColorFilter`, `BasicText`, `rememberScrollState`, `verticalScroll`, `dp`, `sp`, `Gort`. Then delete lines 139–225 from `CatalogApp.kt`. Do NOT modify any other file. Verify: `./gradlew :catalog:compileCommonMainKotlinMetadata`.

**Proposal:** split-catalog-app

## Context

Read these files before starting:
- `opsx/changes/split-catalog-app/proposal.md` — what & why
- `opsx/changes/split-catalog-app/design.md` — how
- `opsx/changes/split-catalog-app/tasks.md` — all tasks & progress

## Implementation

1. Read the context files above
2. Implement this task: **Create `catalog/src/commonMain/kotlin/zone/clanker/gort/catalog/CatalogNavigation.kt` containing `NavRail()` (lines 139–183) and `BottomNavBar()` (lines 185–225) from `CatalogApp.kt`. Change both from `private fun` to `internal fun`. The file needs: `package zone.clanker.gort.catalog`, imports for `Composable`, `Column`, `Row`, `Box`, `Spacer`, `Modifier`, `fillMaxWidth`, `fillMaxHeight`, `width`, `height`, `size`, `background`, `padding`, `clickable`, `Arrangement`, `Alignment`, `Image`, `ColorFilter`, `BasicText`, `rememberScrollState`, `verticalScroll`, `dp`, `sp`, `Gort`. Then delete lines 139–225 from `CatalogApp.kt`. Do NOT modify any other file. Verify: `./gradlew :catalog:compileCommonMainKotlinMetadata`.**
3. When complete, mark done: `./gradlew opsx-sc-3 --set=done`

## ⚠️ Reconciliation Warning

This task references symbols not found in the codebase:
- **`CatalogNavigation`** — not found. Did you mean: `g`, `l`, `c`?
- **`Change`** — not found. Did you mean: `g`, `h`, `c`?
- **`Composable`** — not found. Did you mean: `b`, `s`, `l`?
- **`Column`** — not found. Did you mean: `l`, `c`, `m`?
- **`Row`** — not found. Did you mean: `r`, `ButtonVariantRow`, `TreeNodeRow`?
- **`Box`** — not found. Did you mean: `b`, `x`, `Checkbox`?
- **`Spacer`** — not found. Did you mean: `r`, `s`, `c`?
- **`Modifier`** — not found. Did you mean: `r`, `m`, `focusModifier`?
- **`Arrangement`** — not found. Did you mean: `r`, `g`, `m`?
- **`Alignment`** — not found. Did you mean: `g`, `l`, `m`?
- **`Image`** — not found. Did you mean: `g`, `m`, `a`?
- **`ColorFilter`** — not found. Did you mean: `color`, `r`, `l`?
- **`BasicText`** — not found. Did you mean: `b`, `s`, `c`?
- **`Then`** — not found. Did you mean: `h`, `theme`, `thin`?
- **`NOT`** — not found. Did you mean: `annotated`, `Notification`, `none`?
- **`Verify`** — not found. Did you mean: `r`, `i`, `y`?

Review and update this task if the referenced code has changed.

