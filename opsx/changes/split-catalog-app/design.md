# Design: Split CatalogApp.kt

## Source File
`catalog/src/commonMain/kotlin/zone/clanker/gort/catalog/CatalogApp.kt` (281 lines)

## Current Structure (by line range)
| Lines | Symbol | Visibility | Description |
|-------|--------|-----------|-------------|
| 1–26 | — | — | Package + 26 imports |
| 28–37 | `CatalogSection` | public enum | 9 entries with label + icon |
| 39–98 | `CatalogApp()` | public @Composable | Theme state, GortScaffold, layout switching |
| 100–137 | `Masthead()` | private @Composable | Header bar with title + dark/light toggle |
| 139–183 | `NavRail()` | private @Composable | Desktop side navigation, iterates CatalogSection.entries |
| 185–225 | `BottomNavBar()` | private @Composable | Mobile bottom navigation, iterates CatalogSection.entries |
| 227–260 | `CatalogContent()` | private @Composable | when(section) dispatch to screen composables |
| 262–268 | `SectionTitle()` | public @Composable | Utility: headline text |
| 270–277 | `ComponentLabel()` | public @Composable | Utility: label text |

## Target Files
All in `catalog/src/commonMain/kotlin/zone/clanker/gort/catalog/`:

| New File | Contains | Visibility |
|----------|----------|-----------|
| `CatalogSection.kt` | `CatalogSection` enum | public |
| `CatalogMasthead.kt` | `Masthead()` | internal |
| `CatalogNavigation.kt` | `NavRail()`, `BottomNavBar()` | internal |
| `CatalogContent.kt` | `CatalogContent()` | internal |
| `CatalogComponents.kt` | `SectionTitle()`, `ComponentLabel()` | public |
| `CatalogApp.kt` | `CatalogApp()` only | public (unchanged) |

## Rules
- Same package `zone.clanker.gort.catalog` — no import needed between files
- `private` → `internal` only for functions called from `CatalogApp.kt`
- Each new file includes ONLY the imports it needs
- No behavior changes. No build file changes. No config changes.
