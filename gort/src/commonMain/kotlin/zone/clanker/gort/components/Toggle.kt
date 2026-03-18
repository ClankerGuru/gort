package zone.clanker.gort.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun Toggle(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val colors = Gort.colors
    val anim = Gort.animation
    val trackShape = RoundedCornerShape(4.dp)
    val thumbShape = RoundedCornerShape(2.dp)
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()

    val thumbOffset by animateDpAsState(
        targetValue = if (checked) 20.dp else 0.dp,
        animationSpec = tween(anim.durationMedium),
    )
    val trackColor = if (checked) colors.primary else colors.surface
    val borderColor = when {
        isFocused -> colors.primary
        isHovered -> colors.primary.copy(alpha = 0.6f)
        else -> colors.border
    }

    val focusModifier = if (isFocused) {
        Modifier.border(3.dp, colors.primary, trackShape)
    } else Modifier

    Box(
        modifier = modifier
            .then(focusModifier)
            .size(width = 48.dp, height = 28.dp)
            .clip(trackShape)
            .background(trackColor, trackShape)
            .border(Gort.borders.thick, borderColor, trackShape)
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
            ) { onCheckedChange(!checked) }
            .padding(4.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Box(
            modifier = Modifier
                .offset(x = thumbOffset)
                .size(18.dp)
                .clip(thumbShape)
                .background(if (checked) colors.onPrimary else colors.border, thumbShape)
                .border(Gort.borders.normal, colors.border, thumbShape),
        )
    }
}
