package zone.clanker.gort.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import zone.clanker.gort.foundation.GortSurface
import zone.clanker.gort.theme.Gort
import zone.clanker.gort.theme.GortShadowSize

enum class GortButtonStyle {
    Primary,
    Secondary,
    Tertiary,
    Outlined,
}

@Composable
fun GortButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: GortButtonStyle = GortButtonStyle.Primary,
    enabled: Boolean = true,
    shape: Shape = Gort.corners.default,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val colors = Gort.colors
    val shadow = Gort.shadows.medium

    val (backgroundColor, contentColor) = when (style) {
        GortButtonStyle.Primary -> colors.primary to colors.onPrimary
        GortButtonStyle.Secondary -> colors.secondary to colors.onSecondary
        GortButtonStyle.Tertiary -> colors.tertiary to colors.onTertiary
        GortButtonStyle.Outlined -> colors.surface to colors.onSurface
    }

    // When pressed, shadow shrinks to zero — button "pushes down"
    val shadowOffsetX by animateDpAsState(
        targetValue = if (isPressed) shadow.offsetX * 0.25f else shadow.offsetX,
        animationSpec = tween(durationMillis = if (isPressed) 80 else 120),
    )
    val shadowOffsetY by animateDpAsState(
        targetValue = if (isPressed) shadow.offsetY * 0.25f else shadow.offsetY,
        animationSpec = tween(durationMillis = if (isPressed) 80 else 120),
    )

    GortSurface(
        modifier = modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            enabled = enabled,
            onClick = onClick,
        ),
        color = if (enabled) backgroundColor else colors.surface,
        borderColor = colors.border,
        shadowColor = colors.shadow,
        shadow = GortShadowSize(shadowOffsetX, shadowOffsetY),
        shape = shape,
    ) {
        Box(
            modifier = Modifier.padding(
                horizontal = Gort.spacing.md,
                vertical = Gort.spacing.sm,
            ),
            contentAlignment = Alignment.Center,
        ) {
            content()
        }
    }
}
