---
name: "OPSX: sc-1"
description: "Create `catalog/src/commonMain/kotlin/zone/clanker/gort/catalog/CatalogSection.kt` containing the `CatalogSection` enum (lines 28–37 of `CatalogApp.kt`). The file needs: `package zone.clanker.gort.catalog`, `import androidx.compose.ui.graphics.vector.ImageVector`, `import com.composables.icons.lucide.*`. Then delete lines 28–37 from `CatalogApp.kt` and remove the now-unused `ImageVector` import from `CatalogApp.kt`. Do NOT modify any other file. Verify: `./gradlew :catalog:compileCommonMainKotlinMetadata`. (split-catalog-app)"
category: Task
tags: [task, split-catalog-app]
---

✅ **sc-1**: Create `catalog/src/commonMain/kotlin/zone/clanker/gort/catalog/CatalogSection.kt` containing the `CatalogSection` enum (lines 28–37 of `CatalogApp.kt`). The file needs: `package zone.clanker.gort.catalog`, `import androidx.compose.ui.graphics.vector.ImageVector`, `import com.composables.icons.lucide.*`. Then delete lines 28–37 from `CatalogApp.kt` and remove the now-unused `ImageVector` import from `CatalogApp.kt`. Do NOT modify any other file. Verify: `./gradlew :catalog:compileCommonMainKotlinMetadata`.

**Proposal:** split-catalog-app

## Context

Read these files before starting:
- `opsx/changes/split-catalog-app/proposal.md` — what & why
- `opsx/changes/split-catalog-app/design.md` — how
- `opsx/changes/split-catalog-app/tasks.md` — all tasks & progress

## Implementation

1. Read the context files above
2. Implement this task: **Create `catalog/src/commonMain/kotlin/zone/clanker/gort/catalog/CatalogSection.kt` containing the `CatalogSection` enum (lines 28–37 of `CatalogApp.kt`). The file needs: `package zone.clanker.gort.catalog`, `import androidx.compose.ui.graphics.vector.ImageVector`, `import com.composables.icons.lucide.*`. Then delete lines 28–37 from `CatalogApp.kt` and remove the now-unused `ImageVector` import from `CatalogApp.kt`. Do NOT modify any other file. Verify: `./gradlew :catalog:compileCommonMainKotlinMetadata`.**
3. When complete, mark done: `./gradlew opsx-sc-1 --set=done`

