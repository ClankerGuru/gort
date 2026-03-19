package zone.clanker.gort.catalog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import zone.clanker.gort.components.*
import zone.clanker.gort.theme.Gort

@Composable
fun DisplayScreen() {
    ShowcaseSection("Containers")

    ComponentShowcase(
        name = "Card",
        description = "Content container with neobrutalist hard-offset shadow and border.",
        code = """Card(modifier = Modifier.fillMaxWidth()) {
    BasicText("Card content")
}""",
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            BasicText(
                text = "This is a card with a hard offset shadow.",
                style = Gort.typography.body.copy(color = Gort.colors.onSurface),
                modifier = Modifier.padding(Gort.spacing.md),
            )
        }
    }

    ComponentShowcase(
        name = "Widget",
        description = "Dashboard-style metric card with title, subtitle, and prominent content.",
        code = """Widget(title = "Revenue", subtitle = "Last 30 days") {
    BasicText("$12,450")
}""",
    ) {
        Widget(title = "Monthly Revenue", subtitle = "Last 30 days") {
            BasicText("$12,450", style = Gort.typography.display.copy(color = Gort.colors.primary))
        }
    }

    ShowcaseSection("Labels & Indicators")

    ComponentShowcase(
        name = "Badge",
        description = "Small numeric indicator for counts, notifications, or status.",
        code = """Badge(text = "5")""",
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(Gort.spacing.md)) {
            Badge(text = "5")
            Badge(text = "99")
            Badge(text = "0")
        }
    }

    ComponentShowcase(
        name = "Tag",
        description = "Colored label for categorization — bugs, features, status indicators.",
        code = """Tag(text = "Bug", color = Gort.colors.error)""",
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(Gort.spacing.sm)) {
            Tag(text = "Bug", color = Gort.colors.error)
            Tag(text = "Feature", color = Gort.colors.success, textColor = Gort.colors.onSuccess)
            Tag(text = "WIP", color = Gort.colors.warning, textColor = Gort.colors.onWarning)
        }
    }

    ComponentShowcase(
        name = "Avatar",
        description = "Circular user identity indicator with initials.",
        code = """GortAvatar(text = "XL")""",
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(Gort.spacing.sm)) {
            GortAvatar(text = "XL")
            GortAvatar(text = "GR")
            GortAvatar(text = "AI")
        }
    }

    ComponentShowcase(
        name = "Stamp",
        description = "Rotated watermark overlay on content — DRAFT, APPROVED, CLASSIFIED.",
        code = """StampOverlay(text = "DRAFT") {
    Card { BasicText("Document content") }
}""",
    ) {
        StampOverlay(text = "DRAFT") {
            Card(modifier = Modifier.fillMaxWidth()) {
                BasicText(
                    text = "This document is still in progress.",
                    style = Gort.typography.body.copy(color = Gort.colors.onSurface),
                    modifier = Modifier.padding(Gort.spacing.lg),
                )
            }
        }
    }

    ShowcaseSection("Code & Keyboard")

    ComponentShowcase(
        name = "Code Block",
        description = "Syntax-highlighted code display with optional language label and copy button.",
        code = """Code(
    code = "fun main() { println(\"Hello!\") }",
    language = "kotlin",
    onCopy = {},
)""",
    ) {
        Code(
            code = "fun main() {\n    println(\"Hello, Gort!\")\n}",
            language = "kotlin",
            onCopy = {},
        )
    }

    ComponentShowcase(
        name = "Inline Code",
        description = "Monospace inline code span for referencing symbols, commands, or values within text.",
        code = """InlineCode("./gradlew build")""",
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(Gort.spacing.sm)) {
            BasicText("Use ", style = Gort.typography.body.copy(color = Gort.colors.onSurface))
            InlineCode("./gradlew build")
            BasicText(" to compile", style = Gort.typography.body.copy(color = Gort.colors.onSurface))
        }
    }

    ComponentShowcase(
        name = "Kbd",
        description = "Keyboard shortcut display showing key combinations in a visual key-cap style.",
        code = """Kbd("Ctrl", "Shift", "P")""",
    ) {
        Kbd("Ctrl", "Shift", "P")
    }

    ShowcaseSection("Feedback & Content")

    ComponentShowcase(
        name = "Callout",
        description = "Highlighted information block for tips, warnings, or danger notices.",
        code = """Callout(type = CalloutType.Info, title = "Note") {
    BasicText("Informational callout.")
}""",
        controls = {
            var typeIdx by remember { mutableIntStateOf(0) }
            SegmentedControl(
                options = listOf("Info", "Warning", "Danger"),
                selectedIndex = typeIdx,
                onSelect = { typeIdx = it },
            )
        },
    ) {
        Callout(type = CalloutType.Info, title = "Note") {
            BasicText("This is an informational callout.", style = Gort.typography.body.copy(color = Gort.colors.onSurface))
        }
        Callout(type = CalloutType.Warning, title = "Warning") {
            BasicText("Be careful with this operation.", style = Gort.typography.body.copy(color = Gort.colors.onSurface))
        }
        Callout(type = CalloutType.Danger, title = "Danger") {
            BasicText("This action cannot be undone.", style = Gort.typography.body.copy(color = Gort.colors.onSurface))
        }
    }

    ShowcaseSection("Loading & Progress")

    ComponentShowcase(
        name = "Skeleton",
        description = "Placeholder shimmer animation shown while content is loading.",
        code = """SkeletonCard()""",
    ) {
        SkeletonCard()
    }

    ComponentShowcase(
        name = "ProgressBar",
        description = "Determinate progress indicator showing completion percentage.",
        code = """GortProgressBar(progress = 0.65f)""",
    ) {
        var progress by remember { mutableFloatStateOf(0.65f) }
        GortProgressBar(progress = progress)
        GortSlider(value = progress, onValueChange = { progress = it })
    }

    ComponentShowcase(
        name = "Marquee",
        description = "Scrolling text ticker for news headlines, alerts, or announcements.",
        code = """Marquee(text = "BREAKING NEWS — …")""",
    ) {
        Marquee(text = "BREAKING NEWS — Gort design system now available for Compose Multiplatform — All 50 components shipping today")
    }

    ShowcaseSection("Color & Layout")

    ComponentShowcase(
        name = "ColorPalette",
        description = "Named color swatch grid for displaying and documenting theme colors.",
        code = """ColorPalette(
    colors = listOf("Primary" to Gort.colors.primary, …),
)""",
    ) {
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
    }

    ComponentShowcase(
        name = "Divider",
        description = "Thin horizontal rule for visually separating content sections.",
        code = """Divider()""",
    ) {
        Divider()
    }

    ComponentShowcase(
        name = "Markdown",
        description = "Rich text renderer supporting headings, bold, italic, code, blockquotes, and lists.",
        code = """MarkdownRenderer("# Hello\\n**Bold** and *italic*")""",
    ) {
        MarkdownRenderer("""
# Hello Gort

This is **bold** and *italic* and `inline code`.

> A blockquote for emphasis.

- Item one
- Item two
        """.trimIndent())
    }
}
