package zone.clanker.gort.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import zone.clanker.gort.components.*
import zone.clanker.gort.theme.Gort

private val sampleMarkdown = """
# Gort Design System

A **neobrutalist** UI component library for *Compose Multiplatform*.

## Features

- **52 components** — buttons, inputs, data, navigation, and more
- Zero dependency on Material 3
- Pure Compose Foundation
- Cross-platform: Android, Desktop, WASM, iOS

### Getting Started

1. Add the dependency to your `build.gradle.kts`
2. Wrap your app in `GortTheme { }`
3. Start using components
4. Customize with `GortColorScheme.fromSeed()`

```kotlin
// Quick start
GortTheme {
    Button(onClick = { }) {
        BasicText("Hello Gort")
    }
}
```

> "We don't do rounded corners here." — The Gort Gazette

## Supported Platforms

| Platform | Status | Notes |
|----------|--------|-------|
| Android  | ✅ Ready | API 21+ |
| Desktop  | ✅ Ready | JVM target |
| WASM     | ✅ Ready | Browser |
| iOS      | 🚧 WIP  | arm64 + simulator |
| macOS    | 🚧 WIP  | arm64 |

## Task List

- [x] Core components
- [x] Theme system
- [x] Catalog app
- [ ] Chart components
- [ ] Documentation site
- [ ] Maven Central publishing

> [!NOTE]
> Gort uses Compose Foundation only — no Material 3 dependency.

> [!TIP]
> Use `GortColorScheme.fromSeed()` to generate a full palette from a single color.

> [!WARNING]
> API may change before v1.0 release.

> [!CAUTION]
> Do not mix Gort components with Material 3 — they have conflicting theme systems.

## Typography

Gort ships with **four typography factories**:

- `GortTypography()` — default system fonts
- `GortTypography.monospace()` — hacker vibes
- `GortTypography.editorial()` — newspaper feel
- `GortTypography.sansSerif()` — clean modern

### Code Example

```kotlin
val theme = GortColorScheme.fromSeed(
    seed = Color(0xFFFF6B6B),
    isDark = false,
)
```

## Links & Images

Check out [Gort on GitHub](https://github.com/ClankerGuru/gort) or visit https://clanker.zone for more.

![Gort Logo](https://clanker.zone/gort-icon.svg)

## Strikethrough

~~Material 3 is required~~ — actually, Gort has zero Material dependency!

## Nested Quotes

> First level quote
> > Nested quote inside
> > > Even deeper nesting
>
> Back to first level

## Color Swatches

Theme colors: `#FF6B6B` for coral, `#4ECDC4` for teal, `#2C3E50` for dark blue.

---

Built with ❤️ by **Clanker Labs** · [clanker.zone](https://clanker.zone)
""".trimIndent()

@Composable
fun MarkdownScreen() {
    val colors = Gort.colors
    val spacing = Gort.spacing
    val typography = Gort.typography

    var selectedTab by remember { mutableStateOf(0) }
    var markdownText by remember { mutableStateOf(sampleMarkdown) }

    SectionTitle("Markdown Renderer")

    // Edit / Preview toggle
    SegmentedControl(
        options = listOf("Preview", "Edit"),
        selectedIndex = selectedTab,
        onSelect = { selectedTab = it },
    )

    Spacer(modifier = Modifier.height(spacing.md))

    // Main content area
    when (selectedTab) {
        0 -> {
            // Preview mode
            MarkdownRenderer(
                markdown = markdownText,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        1 -> {
            // Edit mode — editable text area with monospace font
            BasicTextField(
                value = markdownText,
                onValueChange = { markdownText = it },
                textStyle = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    color = colors.onSurface,
                    fontSize = typography.body.fontSize,
                    lineHeight = typography.body.lineHeight,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 400.dp)
                    .border(Gort.borders.default, colors.border)
                    .background(colors.surface)
                    .padding(spacing.md),
            )
        }
    }

    Spacer(modifier = Modifier.height(spacing.xl))

    // Individual feature showcases
    ShowcaseSection("Individual Features")

    ComponentShowcase(
        name = "Tables",
        description = "Markdown tables rendered with the Table composable.",
        code = """| Platform | Status |
|----------|--------|
| Android  | ✅ Ready |
| Desktop  | ✅ Ready |""",
    ) {
        MarkdownRenderer(
            """
| Platform | Status | Notes |
|----------|--------|-------|
| Android  | ✅ Ready | API 21+ |
| Desktop  | ✅ Ready | JVM target |
| WASM     | ✅ Ready | Browser |
            """.trimIndent()
        )
    }

    ComponentShowcase(
        name = "Callouts",
        description = "GitHub-style callout blocks for notes, tips, warnings, and cautions.",
        code = """> [!NOTE]
> This is a note callout.""",
    ) {
        MarkdownRenderer(
            """
> [!NOTE]
> Gort uses Compose Foundation only — no Material 3 dependency.

> [!TIP]
> Use `GortColorScheme.fromSeed()` to generate a full palette from a single color.

> [!WARNING]
> API may change before v1.0 release.

> [!CAUTION]
> Do not mix Gort components with Material 3 — they have conflicting theme systems.
            """.trimIndent()
        )
    }

    ComponentShowcase(
        name = "Task Lists",
        description = "Checkbox-style task lists parsed from markdown.",
        code = """- [x] Completed task
- [ ] Pending task""",
    ) {
        MarkdownRenderer(
            """
- [x] Core components
- [x] Theme system
- [x] Catalog app
- [ ] Chart components
- [ ] Documentation site
- [ ] Maven Central publishing
            """.trimIndent()
        )
    }
}
