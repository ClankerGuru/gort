package zone.clanker.gort.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.*
import zone.clanker.gort.components.*
import zone.clanker.gort.theme.Gort

@Composable
fun ButtonsScreen() {
    ShowcaseSection("Buttons")

    ComponentShowcase(
        name = "Button",
        description = "Primary action trigger with neobrutalist shadow. Supports primary, secondary, outline, danger, and disabled variants.",
        code = """Button(onClick = {}, variant = ButtonVariant.Primary) {
    BasicText("Primary")
}""",
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(Gort.spacing.sm)) {
            ButtonVariantRow("Primary", ButtonVariant.Primary, Gort.colors.onPrimary)
            ButtonVariantRow("Secondary", ButtonVariant.Secondary, Gort.colors.onSecondary)
            ButtonVariantRow("Outline", ButtonVariant.Outline, Gort.colors.onSurface)
            ButtonVariantRow("Danger", ButtonVariant.Danger, Gort.colors.onError)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Gort.spacing.md),
            ) {
                BasicText(
                    text = "Disabled",
                    style = Gort.typography.label.copy(color = Gort.colors.onSurface.copy(alpha = 0.6f)),
                    modifier = Modifier.width(80.dp),
                )
                Button(onClick = {}, enabled = false) {
                    BasicText("Disabled", style = Gort.typography.label.copy(color = Gort.colors.onSurface))
                }
            }
        }
    }

    ShowcaseSection("Icon Buttons")

    ComponentShowcase(
        name = "IconButton",
        description = "Compact icon-only button for toolbar actions and inline controls.",
        code = """IconButton(onClick = {}) {
    Image(Lucide.Pencil, contentDescription = "Edit")
}""",
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(Gort.spacing.sm)) {
            IconButton(onClick = {}) { Image(Lucide.Pencil, contentDescription = "Edit", colorFilter = ColorFilter.tint(Gort.colors.onSurface), modifier = Modifier.size(20.dp)) }
            IconButton(onClick = {}) { Image(Lucide.Trash2, contentDescription = "Delete", colorFilter = ColorFilter.tint(Gort.colors.onSurface), modifier = Modifier.size(20.dp)) }
            IconButton(onClick = {}) { Image(Lucide.Star, contentDescription = "Favorite", colorFilter = ColorFilter.tint(Gort.colors.onSurface), modifier = Modifier.size(20.dp)) }
        }
    }

    ShowcaseSection("Selection Controls")

    ComponentShowcase(
        name = "SegmentedControl",
        description = "Mutually exclusive option selector for switching between related views or modes.",
        code = """SegmentedControl(
    options = listOf("Day", "Week", "Month"),
    selectedIndex = selected,
    onSelect = { selected = it },
)""",
    ) {
        var segmentIndex by remember { mutableIntStateOf(0) }
        SegmentedControl(
            options = listOf("Day", "Week", "Month", "Year"),
            selectedIndex = segmentIndex,
            onSelect = { segmentIndex = it },
        )
    }

    ComponentShowcase(
        name = "Chip",
        description = "Compact labels for tags, filters, or categories.",
        code = """Chip(label = "Kotlin")""",
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(Gort.spacing.sm)) {
            Chip(label = "Kotlin")
            Chip(label = "Java")
            Chip(label = "Scala")
        }
    }
}

@Composable
private fun ButtonVariantRow(label: String, variant: ButtonVariant, textColor: androidx.compose.ui.graphics.Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Gort.spacing.md),
    ) {
        BasicText(
            text = label,
            style = Gort.typography.label.copy(color = Gort.colors.onSurface.copy(alpha = 0.6f)),
            modifier = Modifier.width(80.dp),
        )
        Button(onClick = {}, variant = variant) {
            BasicText(label, style = Gort.typography.label.copy(color = textColor))
        }
    }
}
