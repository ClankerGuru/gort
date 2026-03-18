package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import zone.clanker.gort.theme.Gort

@Composable
fun Chip(
    label: String,
    modifier: Modifier = Modifier,
    color: Color = Gort.colors.primaryContainer,
    contentColor: Color = Gort.colors.onPrimaryContainer,
    shape: Shape = Gort.corners.small,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val colors = Gort.colors

    val bgColor = if (isHovered) colors.primary.copy(alpha = 0.15f).compositeOver(color) else color

    Box(
        modifier = modifier
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .clip(shape)
            .background(bgColor, shape)
            .border(Gort.borders.normal, colors.border, shape)
            .padding(horizontal = Gort.spacing.sm, vertical = Gort.spacing.xs),
    ) {
        BasicText(
            text = label,
            style = Gort.typography.labelMedium.copy(color = contentColor),
        )
    }
}

/**
 * Composite a color over another (simple alpha blend approximation).
 */
private fun Color.compositeOver(background: Color): Color {
    val a = this.alpha
    return Color(
        red = this.red * a + background.red * (1 - a),
        green = this.green * a + background.green * (1 - a),
        blue = this.blue * a + background.blue * (1 - a),
        alpha = 1f,
    )
}
