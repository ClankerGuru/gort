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
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zone.clanker.gort.theme.Gort

@Composable
fun Checkbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val colors = Gort.colors
    val shape = Gort.corners.small
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()

    val borderColor = when {
        isFocused -> colors.primary
        isHovered -> colors.primary.copy(alpha = 0.6f)
        else -> colors.border
    }

    val focusModifier = if (isFocused) {
        Modifier.border(3.dp, colors.primary, shape)
    } else Modifier

    Box(
        modifier = modifier
            .then(focusModifier)
            .size(24.dp)
            .clip(shape)
            .background(if (checked) colors.primary else colors.surface, shape)
            .border(Gort.borders.thick, borderColor, shape)
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
            ) { onCheckedChange(!checked) },
        contentAlignment = Alignment.Center,
    ) {
        if (checked) {
            BasicText(
                text = "✓",
                style = TextStyle(
                    color = colors.onPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                    fontFamily = FontFamily.Monospace,
                ),
            )
        }
    }
}
