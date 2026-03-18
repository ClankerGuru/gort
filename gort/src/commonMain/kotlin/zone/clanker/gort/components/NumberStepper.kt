package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

@Composable
fun NumberStepper(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    min: Int = Int.MIN_VALUE,
    max: Int = Int.MAX_VALUE,
    step: Int = 1,
) {
    val colors = Gort.colors
    val shape = Gort.corners.default

    Row(
        modifier = modifier
            .border(Gort.borders.default, colors.border, shape)
            .clip(shape),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Minus button
        Box(
            modifier = Modifier
                .clickable(enabled = value > min) { onValueChange(value - step) }
                .background(if (value > min) colors.surface else colors.background)
                .padding(horizontal = Gort.spacing.md, vertical = Gort.spacing.sm),
            contentAlignment = Alignment.Center,
        ) {
            BasicText(
                text = "−",
                style = Gort.typography.title.copy(
                    color = if (value > min) colors.onSurface else colors.onSurface.copy(alpha = 0.3f),
                ),
            )
        }

        // Value display
        Box(
            modifier = Modifier
                .defaultMinSize(minWidth = 48.dp)
                .border(width = Gort.borders.thin, color = colors.border)
                .background(colors.surface)
                .padding(horizontal = Gort.spacing.md, vertical = Gort.spacing.sm),
            contentAlignment = Alignment.Center,
        ) {
            BasicText(
                text = value.toString(),
                style = Gort.typography.body.copy(color = colors.onSurface),
            )
        }

        // Plus button
        Box(
            modifier = Modifier
                .clickable(enabled = value < max) { onValueChange(value + step) }
                .background(if (value < max) colors.surface else colors.background)
                .padding(horizontal = Gort.spacing.md, vertical = Gort.spacing.sm),
            contentAlignment = Alignment.Center,
        ) {
            BasicText(
                text = "+",
                style = Gort.typography.title.copy(
                    color = if (value < max) colors.onSurface else colors.onSurface.copy(alpha = 0.3f),
                ),
            )
        }
    }
}
