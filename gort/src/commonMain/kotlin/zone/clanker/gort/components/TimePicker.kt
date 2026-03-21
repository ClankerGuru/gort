package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import zone.clanker.gort.foundation.Surface
import zone.clanker.gort.theme.Gort

data class GortTime(val hour: Int, val minute: Int)

@Composable
fun TimePicker(
    selectedTime: GortTime,
    onTimeChange: (GortTime) -> Unit,
    modifier: Modifier = Modifier,
    is24Hour: Boolean = true,
) {
    val colors = Gort.colors
    val spacing = Gort.spacing

    Surface(
        modifier = modifier,
        color = colors.surface,
        borderColor = colors.border,
    ) {
        Row(
            modifier = Modifier.padding(spacing.md),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            TimeWheel(
                value = selectedTime.hour,
                max = if (is24Hour) 23 else 12,
                onValueChange = { onTimeChange(selectedTime.copy(hour = it)) },
            )

            BasicText(
                text = ":",
                style = Gort.typography.display.copy(color = colors.onSurface),
                modifier = Modifier.padding(horizontal = spacing.sm),
            )

            TimeWheel(
                value = selectedTime.minute,
                max = 59,
                step = 5,
                onValueChange = { onTimeChange(selectedTime.copy(minute = it)) },
            )
        }
    }
}

@Composable
private fun TimeWheel(
    value: Int,
    max: Int,
    onValueChange: (Int) -> Unit,
    step: Int = 1,
) {
    val colors = Gort.colors

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val upInteraction = remember { MutableInteractionSource() }
        val isUpHovered by upInteraction.collectIsHoveredAsState()

        BasicText(
            text = "▲",
            style = Gort.typography.body.copy(
                color = if (isUpHovered) colors.primary else colors.onSurface,
            ),
            modifier = Modifier
                .hoverable(upInteraction)
                .pointerHoverIcon(PointerIcon.Hand)
                .clickable {
                    onValueChange(if (value + step > max) 0 else value + step)
                },
        )

        Box(
            modifier = Modifier
                .defaultMinSize(minWidth = 56.dp, minHeight = 48.dp)
                .border(Gort.borders.default, colors.border, Gort.corners.small)
                .background(colors.surface, Gort.corners.small)
                .padding(Gort.spacing.sm),
            contentAlignment = Alignment.Center,
        ) {
            BasicText(
                text = value.toString().padStart(2, '0'),
                style = Gort.typography.display.copy(color = colors.onSurface),
            )
        }

        val downInteraction = remember { MutableInteractionSource() }
        val isDownHovered by downInteraction.collectIsHoveredAsState()

        BasicText(
            text = "▼",
            style = Gort.typography.body.copy(
                color = if (isDownHovered) colors.primary else colors.onSurface,
            ),
            modifier = Modifier
                .hoverable(downInteraction)
                .pointerHoverIcon(PointerIcon.Hand)
                .clickable {
                    onValueChange(if (value - step < 0) max else value - step)
                },
        )
    }
}
