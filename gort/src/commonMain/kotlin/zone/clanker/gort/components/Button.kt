package zone.clanker.gort.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import zone.clanker.gort.foundation.Surface
import zone.clanker.gort.theme.Gort
import zone.clanker.gort.theme.GortShadowSize

enum class ButtonVariant {
    Primary,
    Secondary,
    Outline,
    Danger,
}

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: ButtonVariant = ButtonVariant.Primary,
    enabled: Boolean = true,
    shape: Shape = Gort.corners.default,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val colors = Gort.colors
    val shadow = Gort.shadows.medium
    val anim = Gort.animation

    val (backgroundColor, _) = when (variant) {
        ButtonVariant.Primary -> colors.primary to colors.onPrimary
        ButtonVariant.Secondary -> colors.secondary to colors.onSecondary
        ButtonVariant.Outline -> colors.surface to colors.onSurface
        ButtonVariant.Danger -> colors.error to colors.onError
    }

    val shadowOffsetX by animateDpAsState(
        targetValue = when {
            isPressed -> shadow.offsetX * 0.25f
            isHovered -> shadow.offsetX + 2.dp
            else -> shadow.offsetX
        },
        animationSpec = tween(durationMillis = if (isPressed) anim.durationFast else anim.durationMedium),
    )
    val shadowOffsetY by animateDpAsState(
        targetValue = when {
            isPressed -> shadow.offsetY * 0.25f
            isHovered -> shadow.offsetY + 2.dp
            else -> shadow.offsetY
        },
        animationSpec = tween(durationMillis = if (isPressed) anim.durationFast else anim.durationMedium),
    )

    val focusModifier = if (isFocused) {
        Modifier.border(3.dp, colors.primary, shape)
    } else Modifier

    Surface(
        modifier = modifier
            .then(focusModifier)
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable(
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
