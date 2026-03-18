package zone.clanker.gort.catalog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import zone.clanker.gort.components.*
import zone.clanker.gort.theme.Gort

@Composable
fun ButtonsScreen() {
    SectionTitle("Buttons")

    ComponentLabel("Primary")
    Button(onClick = {}) {
        BasicText("Primary Button", style = Gort.typography.label.copy(color = Gort.colors.onPrimary))
    }

    ComponentLabel("Secondary")
    Button(onClick = {}, variant = ButtonVariant.Secondary) {
        BasicText("Secondary", style = Gort.typography.label.copy(color = Gort.colors.onSecondary))
    }

    ComponentLabel("Outline")
    Button(onClick = {}, variant = ButtonVariant.Outline) {
        BasicText("Outline", style = Gort.typography.label.copy(color = Gort.colors.onSurface))
    }

    ComponentLabel("Danger")
    Button(onClick = {}, variant = ButtonVariant.Danger) {
        BasicText("Danger", style = Gort.typography.label.copy(color = Gort.colors.onError))
    }

    ComponentLabel("Disabled")
    Button(onClick = {}, enabled = false) {
        BasicText("Disabled", style = Gort.typography.label.copy(color = Gort.colors.onSurface))
    }

    SectionTitle("Icon Button")
    Row(horizontalArrangement = Arrangement.spacedBy(Gort.spacing.sm)) {
        IconButton(onClick = {}) {
            BasicText("✏️", style = Gort.typography.body)
        }
        IconButton(onClick = {}) {
            BasicText("🗑️", style = Gort.typography.body)
        }
        IconButton(onClick = {}) {
            BasicText("⭐", style = Gort.typography.body)
        }
    }

    SectionTitle("Segmented Control")
    var segmentIndex by remember { mutableIntStateOf(0) }
    SegmentedControl(
        options = listOf("Day", "Week", "Month", "Year"),
        selectedIndex = segmentIndex,
        onSelect = { segmentIndex = it },
    )

    SectionTitle("Chip")
    Row(horizontalArrangement = Arrangement.spacedBy(Gort.spacing.sm)) {
        Chip(label = "Kotlin")
        Chip(label = "Java")
        Chip(label = "Scala")
    }
}
