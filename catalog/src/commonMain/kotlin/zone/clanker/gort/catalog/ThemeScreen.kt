package zone.clanker.gort.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

@Composable
fun ThemeScreen() {
    SectionTitle("🎨 Theme")
    ComponentLabel("Current color palette")

    val colors = Gort.colors
    val swatches = listOf(
        "Primary" to colors.primary,
        "Secondary" to colors.secondary,
        "Tertiary" to colors.tertiary,
        "Accent" to colors.accent,
        "Background" to colors.background,
        "Surface" to colors.surface,
        "Error" to colors.error,
        "Success" to colors.success,
        "Warning" to colors.warning,
    )

    @Composable
    fun ColorSwatch(label: String, color: androidx.compose.ui.graphics.Color) {
        Row(
            modifier = Modifier.padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(color)
                    .border(Gort.borders.thin, colors.border),
            )
            BasicText(
                text = label,
                style = Gort.typography.body.copy(color = colors.onSurface),
            )
        }
    }

    swatches.forEach { (label, color) ->
        ColorSwatch(label, color)
    }

    Spacer(modifier = Modifier.height(16.dp))
    ComponentLabel("Typography styles")
    BasicText("Display", style = Gort.typography.display.copy(color = colors.onSurface))
    BasicText("Headline", style = Gort.typography.headline.copy(color = colors.onSurface))
    BasicText("Title", style = Gort.typography.title.copy(color = colors.onSurface))
    BasicText("Body", style = Gort.typography.body.copy(color = colors.onSurface))
    BasicText("Label", style = Gort.typography.label.copy(color = colors.onSurface))
    BasicText("Code", style = Gort.typography.code.copy(color = colors.onSurface))
}
