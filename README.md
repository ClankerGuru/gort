# GORT

[![🤖 clanker](https://img.shields.io/badge/🤖-clanker-black?style=flat-square)](https://github.com/ClankerGuru) [![Kotlin](https://img.shields.io/badge/Kotlin-2.3-7F52FF?style=flat-square&logo=kotlin&logoColor=white)](https://kotlinlang.org) [![Compose](https://img.shields.io/badge/Compose-1.10-4285F4?style=flat-square&logo=jetpackcompose&logoColor=white)](https://www.jetbrains.com/compose-multiplatform/) [![CI](https://github.com/ClankerGuru/gort/actions/workflows/ci.yml/badge.svg)](https://github.com/ClankerGuru/gort/actions/workflows/ci.yml) [![Maven Central](https://img.shields.io/maven-central/v/zone.clanker/gort?label=Maven%20Central&style=flat-square)](https://central.sonatype.com/artifact/zone.clanker/gort) [![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg?style=flat-square)](LICENSE)

> *A neobrutalist design system for Compose Multiplatform.*

**Bold. Loud. Unapologetic.**

Gort is an opinionated UI component library that replaces Material 3 with thick borders, hard offset shadows, flat colors, and monospace type. **52 components.** Pure Compose Foundation — zero Material dependency. Every platform Compose supports.

Named after the 8-foot robot from *The Day the Earth Stood Still* (1951).

---

## Install

```kotlin
// build.gradle.kts
dependencies {
    implementation("zone.clanker:gort:<version>")
}
```

---

## Quick Start

```kotlin
GortTheme {
    Button(onClick = { }) {
        Text("LAUNCH")
    }
}
```

---

## Custom Theme

Swap the colors. Keep the structure. It still looks like Gort.

```kotlin
GortTheme(
    colors = GortColors.light(
        primary = Color(0xFF8B5CF6),   // your brand
        secondary = Color(0xFF10B981),
    )
) {
    // All components now use your palette
    // but stay neobrutalist
}
```

---

## Components (52)

### Core Input
| Component | Description |
|-----------|-------------|
| `Button` | Primary, secondary, tertiary, outlined — shadow-press animation |
| `IconButton` | Compact icon-only button |
| `TextField` | Thick-bordered text input |
| `TextArea` | Multi-line text input |
| `Toggle` | Mechanical on/off switch |
| `Checkbox` | Chunky checkmark |
| `RadioButton` | Exclusive selection |
| `Slider` | Range input with track + thumb |
| `NumberStepper` | Increment/decrement numeric input |
| `ChoiceGroup` | Single/multi select option group |
| `Rating` | Star rating input |
| `Dropdown` | Select menu with popup |
| `DatePicker` | Date selection |
| `TimePicker` | Time selection |
| `ColorPicker` | Color selection with palette |

### Display
| Component | Description |
|-----------|-------------|
| `Card` | Bordered card with hard offset shadow |
| `Badge` | Notification indicator |
| `Tag` | Categorical label |
| `Chip` | Removable tag/filter |
| `Avatar` | User identity with fallback |
| `Divider` | Thick horizontal rule |
| `Stamp` | Rotated approval/status mark |
| `Skeleton` | Loading placeholder |
| `Code` | Code block with syntax highlighting |
| `InlineCode` | Inline code span |
| `Kbd` | Keyboard shortcut display |
| `MarkdownRenderer` | Markdown to Compose UI |

### Feedback
| Component | Description |
|-----------|-------------|
| `Dialog` | Modal with shadow |
| `Snackbar` | Inline notification bar |
| `Toast` | Temporary popup notification |
| `Tooltip` | Hover info popup |
| `Banner` | Full-width alert strip |
| `Callout` | Highlighted info block |
| `Notification` | Rich notification card |
| `ProgressBar` | Determinate/indeterminate progress |

### Navigation
| Component | Description |
|-----------|-------------|
| `TopBar` | App bar with thick bottom border |
| `NavigationRail` | Side navigation |
| `Tabs` | Tab strip |
| `Breadcrumb` | Path navigation |
| `Pagination` | Page navigation controls |
| `SegmentedControl` | Toggle between views |

### Data & Compound
| Component | Description |
|-----------|-------------|
| `Table` | Data table with borders |
| `ListItem` | Structured list row |
| `Timeline` | Vertical event timeline |
| `Stepper` | Multi-step progress |
| `Accordion` | Collapsible sections |
| `FileTree` | Hierarchical file browser |
| `CommandPalette` | Searchable command menu |
| `FormField` | Label + input + error wrapper |
| `Widget` | Dashboard card container |
| `Calendar` | Monthly calendar view |
| `Marquee` | Scrolling text ticker |

### Chat
| Component | Description |
|-----------|-------------|
| `ChatBubble` | Message bubble (sent/received) |
| `ChatInput` | Message composer with actions |

### Foundation
| Component | Description |
|-----------|-------------|
| `Surface` | Base layer with border + hard offset shadow |
| `DrawSurface` | Freeform drawing canvas |
| `ColorPalette` | Preset color swatch picker |

---

## Theme System

Eight customizable token groups:

| Token | Class | What it controls |
|-------|-------|-----------------|
| Colors | `GortColors` | Primary, secondary, surface, error, text |
| Typography | `GortTypography` | Display, headline, title, body, label (shorthand aliases) |
| Borders | `GortBorders` | Width and color for all bordered components |
| Shadows | `GortShadows` | Hard offset shadow size and color |
| Corners | `GortCorners` | Border radius across components |
| Spacing | `GortSpacing` | Consistent padding/margin scale |
| Animation | `GortAnimation` | Duration and easing for press/hover/transitions |

### Design Tokens (Defaults)

| Token | Default |
|-------|---------|
| Border | 3dp solid |
| Shadow | 4dp × 4dp hard offset |
| Corners | 4dp radius |
| Font | JetBrains Mono |
| Press animation | 80ms snap (shadow collapses on press) |

---

## Accessibility

One-line toggle to OpenDyslexic for dyslexia-friendly reading:

```kotlin
GortTheme(
    typography = GortTypography.openDyslexic()
) {
    // All text now uses OpenDyslexic font
}
```

---

## Platforms

| Platform | Target | Status |
|----------|--------|--------|
| Android | `androidTarget()` | ✅ |
| Desktop | `jvm("desktop")` | ✅ |
| Web (WASM) | `wasmJs { browser() }` | ✅ |
| Web (JS) | `js { browser(); nodejs() }` | ✅ |
| iOS | `iosArm64()`, `iosSimulatorArm64()` | ✅ |
| macOS | `macosArm64()` | ✅ |

**6 targets.** All from a single codebase. No platform-specific code required.

---

## Catalog App

The `:catalog` module is a live showcase of every component. Runs on Desktop and Web (WASM):

```bash
./gradlew :catalog:run              # Desktop
./gradlew :catalog:wasmJsBrowserRun # Web
```

8 screens: Buttons, Inputs, Display, Navigation, Feedback, Compound, Data, Chat — with dark/light toggle and sidebar navigation.

---

## Philosophy

Material 3 is fine. It's also everywhere. Every Compose app looks the same.

Gort is the first **fun, opinionated alternative to Material 3 for Compose Multiplatform**:

- **Pure Foundation** — depends only on `compose.foundation`, `compose.runtime`, `compose.ui`
- **No Material** — not a theme on top of Material. No Material dependency at all.
- **Shadow-press is the signature** — interactive components have a hard offset shadow that collapses on press. That's the feel.
- **Extensible, not flexible** — you swap colors, but the structural style (borders, shadows, typography) stays. That's the point.
- **No prefix** — it's `Button`, not `GortButton`. Package namespace handles disambiguation.

---

## Dependencies

```
compose.foundation
compose.runtime
compose.ui
```

That's it. No Material 3. No third-party libraries.

---

## License

[MIT](LICENSE) © [ClankerGuru](https://github.com/ClankerGuru)
