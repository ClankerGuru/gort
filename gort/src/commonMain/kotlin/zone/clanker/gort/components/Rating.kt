package zone.clanker.gort.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import zone.clanker.gort.theme.Gort

@Composable
fun Rating(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    max: Int = 5,
    filledIcon: String = "★",
    emptyIcon: String = "☆",
) {
    val colors = Gort.colors
    var hoveredIndex by remember { mutableIntStateOf(-1) }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Gort.spacing.xs),
    ) {
        repeat(max) { index ->
            val interactionSource = remember { MutableInteractionSource() }
            val isHovered by interactionSource.collectIsHoveredAsState()

            if (isHovered) hoveredIndex = index
            else if (hoveredIndex == index) hoveredIndex = -1

            val isFilled = if (hoveredIndex >= 0) index <= hoveredIndex else index < value
            BasicText(
                text = if (isFilled) filledIcon else emptyIcon,
                style = Gort.typography.headline.copy(
                    color = if (isFilled) colors.warning else colors.onSurface.copy(alpha = 0.3f),
                ),
                modifier = Modifier
                    .hoverable(interactionSource)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .clickable { onValueChange(index + 1) },
            )
        }
    }
}
