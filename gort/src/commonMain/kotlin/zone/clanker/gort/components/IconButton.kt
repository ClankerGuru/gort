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
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import zone.clanker.gort.foundation.Surface
import zone.clanker.gort.theme.Gort
import zone.clanker.gort.theme.GortShadowSize

@Composable
fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = Gort.colors.surface,
    size: Dp = 48.dp,
    shape: Shape = Gort.corners.default,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val shadow = Gort.shadows.medium
    val anim = Gort.animation
    val colors = Gort.colors

    val offsetX by animateDpAsState(
        targetValue = when {
            isPressed -> shadow.offsetX * 0.25f
            isHovered -> shadow.offsetX + 2.dp
            else -> shadow.offsetX
        },
        animationSpec = tween(if (isPressed) anim.durationFast else anim.durationMedium),
    )
    val offsetY by animateDpAsState(
        targetValue = when {
            isPressed -> shadow.offsetY * 0.25f
            isHovered -> shadow.offsetY + 2.dp
            else -> shadow.offsetY
        },
        animationSpec = tween(if (isPressed) anim.durationFast else anim.durationMedium),
    )

    val focusModifier = if (isFocused) {
        Modifier.border(3.dp, colors.primary, shape)
    } else Modifier

    Surface(
        modifier = modifier
            .defaultMinSize(minWidth = 48.dp, minHeight = 48.dp)
            .size(size)
            .then(focusModifier)
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick,
            ),
        color = color,
        borderColor = colors.border,
        shadowColor = colors.shadow,
        shadow = GortShadowSize(offsetX, offsetY),
        shape = shape,
    ) {
        Box(
            modifier = Modifier.padding(Gort.spacing.xs),
            contentAlignment = Alignment.Center,
        ) {
            content()
        }
    }
}
