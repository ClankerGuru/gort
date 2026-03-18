package zone.clanker.gort.catalog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import zone.clanker.gort.components.*
import zone.clanker.gort.theme.Gort

@Composable
fun DisplayScreen() {
    SectionTitle("Card")
    Card(modifier = Modifier.fillMaxWidth()) {
        BasicText(
            text = "This is a card with a hard offset shadow.",
            style = Gort.typography.body.copy(color = Gort.colors.onSurface),
            modifier = Modifier.padding(Gort.spacing.md),
        )
    }

    SectionTitle("Badge")
    Row(horizontalArrangement = Arrangement.spacedBy(Gort.spacing.md)) {
        Badge(text = "5")
        Badge(text = "99")
        Badge(text = "0")
    }

    SectionTitle("Tag")
    Row(horizontalArrangement = Arrangement.spacedBy(Gort.spacing.sm)) {
        Tag(text = "Bug", color = Gort.colors.error)
        Tag(text = "Feature", color = Gort.colors.success, textColor = Gort.colors.onSuccess)
        Tag(text = "WIP", color = Gort.colors.warning, textColor = Gort.colors.onWarning)
    }

    SectionTitle("Avatar")
    Row(horizontalArrangement = Arrangement.spacedBy(Gort.spacing.sm)) {
        GortAvatar(text = "XL")
        GortAvatar(text = "GR")
        GortAvatar(text = "AI")
    }

    SectionTitle("Divider")
    Divider()

    SectionTitle("Code Block")
    Code(
        code = "fun main() {\n    println(\"Hello, Gort!\")\n}",
        language = "kotlin",
        onCopy = {},
    )

    SectionTitle("Inline Code")
    Row(horizontalArrangement = Arrangement.spacedBy(Gort.spacing.sm)) {
        BasicText("Use ", style = Gort.typography.body.copy(color = Gort.colors.onSurface))
        InlineCode("./gradlew build")
        BasicText(" to compile", style = Gort.typography.body.copy(color = Gort.colors.onSurface))
    }

    SectionTitle("Kbd")
    Kbd("Ctrl", "Shift", "P")

    SectionTitle("Callout")
    Callout(type = CalloutType.Info, title = "Note") {
        BasicText("This is an informational callout.", style = Gort.typography.body.copy(color = Gort.colors.onSurface))
    }
    Callout(type = CalloutType.Warning, title = "Warning") {
        BasicText("Be careful with this operation.", style = Gort.typography.body.copy(color = Gort.colors.onSurface))
    }
    Callout(type = CalloutType.Danger, title = "Danger") {
        BasicText("This action cannot be undone.", style = Gort.typography.body.copy(color = Gort.colors.onSurface))
    }

    SectionTitle("Stamp")
    StampOverlay(text = "DRAFT") {
        Card(modifier = Modifier.fillMaxWidth()) {
            BasicText(
                text = "This document is still in progress.",
                style = Gort.typography.body.copy(color = Gort.colors.onSurface),
                modifier = Modifier.padding(Gort.spacing.lg),
            )
        }
    }

    SectionTitle("Skeleton")
    SkeletonCard()

    SectionTitle("Progress Bar")
    GortProgressBar(progress = 0.65f)

    SectionTitle("Marquee")
    Marquee(text = "BREAKING NEWS — Gort design system now available for Compose Multiplatform — All 50 components shipping today")

    SectionTitle("Widget")
    Widget(title = "Monthly Revenue", subtitle = "Last 30 days") {
        BasicText("$12,450", style = Gort.typography.display.copy(color = Gort.colors.primary))
    }

    SectionTitle("Color Palette")
    ColorPalette(
        colors = listOf(
            "Primary" to Gort.colors.primary,
            "Secondary" to Gort.colors.secondary,
            "Tertiary" to Gort.colors.tertiary,
            "Accent" to Gort.colors.accent,
            "Error" to Gort.colors.error,
            "Success" to Gort.colors.success,
        ),
    )

    SectionTitle("Markdown")
    MarkdownRenderer("""
# Hello Gort

This is **bold** and *italic* and `inline code`.

> A blockquote for emphasis.

- Item one
- Item two
- Item three

```kotlin
fun main() {
    println("Gort!")
}
```

---

### That's it!
    """.trimIndent())
}
