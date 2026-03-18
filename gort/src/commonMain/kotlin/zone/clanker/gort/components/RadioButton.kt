package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
fun RadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val colors = Gort.colors
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()

    val borderColor = when {
        isFocused -> colors.primary
        isHovered -> colors.primary.copy(alpha = 0.6f)
        else -> colors.border
    }

    val focusModifier = if (isFocused) {
        Modifier.border(3.dp, colors.primary, CircleShape)
    } else Modifier

    Box(
        modifier = modifier
            .then(focusModifier)
            .size(24.dp)
            .clip(CircleShape)
            .background(colors.surface, CircleShape)
            .border(Gort.borders.thick, borderColor, CircleShape)
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        if (selected) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(colors.primary, CircleShape),
            )
        }
    }
}
