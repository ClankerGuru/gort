package zone.clanker.gort.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.*
import zone.clanker.gort.components.*
import zone.clanker.gort.theme.Gort

@Composable
fun ButtonsScreen() {
    ShowcaseSection("Buttons")

    var variant by remember { mutableStateOf(ButtonVariant.Primary) }

    ComponentShowcase(
        name = "Button",
        description = "Primary action trigger with neobrutalist shadow. Supports primary, secondary, outline, and danger variants.",
        code = """Button(onClick = {}, variant = ButtonVariant.${variant.name}) {
    BasicText("Click Me")
}""",
        controls = {
            var segIdx by remember { mutableIntStateOf(0) }
            SegmentedControl(
                options = listOf("Primary", "Secondary", "Outline", "Danger"),
                selectedIndex = segIdx,
                onSelect = {
                    segIdx = it
                    variant = ButtonVariant.entries[it]
                },
            )
        },
    ) {
        Button(onClick = {}, variant = variant) {
            val textColor = when (variant) {
                ButtonVariant.Primary -> Gort.colors.onPrimary
                ButtonVariant.Secondary -> Gort.colors.onSecondary
                ButtonVariant.Outline -> Gort.colors.onSurface
                ButtonVariant.Danger -> Gort.colors.onError
            }
            BasicText("Click Me", style = Gort.typography.label.copy(color = textColor))
        }
    }

    ComponentShowcase(
        name = "Button (Disabled)",
        description = "Disabled buttons are visually muted and non-interactive.",
        code = """Button(onClick = {}, enabled = false) {
    BasicText("Disabled")
}""",
    ) {
        Button(onClick = {}, enabled = false) {
            BasicText("Disabled", style = Gort.typography.label.copy(color = Gort.colors.onSurface))
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
