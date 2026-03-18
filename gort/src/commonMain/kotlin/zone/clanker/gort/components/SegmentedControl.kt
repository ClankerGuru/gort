package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import zone.clanker.gort.theme.Gort

@Composable
fun SegmentedControl(
    options: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = Gort.colors
    val shape = Gort.corners.default

    Row(
        modifier = modifier
            .border(Gort.borders.default, colors.border, shape)
            .clip(shape),
    ) {
        options.forEachIndexed { index, label ->
            val isSelected = index == selectedIndex
            val interactionSource = remember { MutableInteractionSource() }
            val isHovered by interactionSource.collectIsHoveredAsState()

            BasicText(
                text = label,
                style = Gort.typography.label.copy(
                    color = if (isSelected) colors.onPrimary else colors.onSurface,
                ),
                modifier = Modifier
                    .hoverable(interactionSource)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .clickable { onSelect(index) }
                    .background(
                        when {
                            isSelected -> colors.primary
                            isHovered -> colors.primaryContainer.copy(alpha = 0.3f)
                            else -> colors.surface
                        },
                    )
                    .padding(horizontal = Gort.spacing.md, vertical = Gort.spacing.sm)
                    .then(
                        if (index < options.lastIndex)
                            Modifier.border(
                                width = Gort.borders.thin,
                                color = colors.border,
                            )
                        else Modifier
                    ),
            )
        }
    }
}
