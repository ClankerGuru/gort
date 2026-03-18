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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
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
        val minusEnabled = value > min
        val minusInteraction = remember { MutableInteractionSource() }
        val isMinusHovered by minusInteraction.collectIsHoveredAsState()

        Box(
            modifier = Modifier
                .hoverable(minusInteraction)
                .pointerHoverIcon(if (minusEnabled) PointerIcon.Hand else PointerIcon.Default)
                .clickable(enabled = minusEnabled) { onValueChange(value - step) }
                .background(
                    when {
                        isMinusHovered && minusEnabled -> colors.primaryContainer.copy(alpha = 0.3f)
                        !minusEnabled -> colors.background
                        else -> colors.surface
                    },
                )
                .padding(horizontal = Gort.spacing.md, vertical = Gort.spacing.sm),
            contentAlignment = Alignment.Center,
        ) {
            BasicText(
                text = "−",
                style = Gort.typography.title.copy(
                    color = if (minusEnabled) colors.onSurface else colors.onSurface.copy(alpha = 0.3f),
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
        val plusEnabled = value < max
        val plusInteraction = remember { MutableInteractionSource() }
        val isPlusHovered by plusInteraction.collectIsHoveredAsState()

        Box(
            modifier = Modifier
                .hoverable(plusInteraction)
                .pointerHoverIcon(if (plusEnabled) PointerIcon.Hand else PointerIcon.Default)
                .clickable(enabled = plusEnabled) { onValueChange(value + step) }
                .background(
                    when {
                        isPlusHovered && plusEnabled -> colors.primaryContainer.copy(alpha = 0.3f)
                        !plusEnabled -> colors.background
                        else -> colors.surface
                    },
                )
                .padding(horizontal = Gort.spacing.md, vertical = Gort.spacing.sm),
            contentAlignment = Alignment.Center,
        ) {
            BasicText(
                text = "+",
                style = Gort.typography.title.copy(
                    color = if (plusEnabled) colors.onSurface else colors.onSurface.copy(alpha = 0.3f),
                ),
            )
        }
    }
}
