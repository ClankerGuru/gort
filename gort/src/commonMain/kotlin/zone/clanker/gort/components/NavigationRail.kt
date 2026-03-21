package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

data class GortNavItem(
    val label: String,
    val icon: String = "",
)

@Composable
fun GortNavigationRail(
    items: List<GortNavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    railWidth: Dp = 200.dp,
) {
    val colors = Gort.colors

    Row(modifier = modifier.fillMaxHeight()) {
        Column(
            modifier = Modifier
                .width(railWidth)
                .fillMaxHeight()
                .background(colors.surface)
                .padding(vertical = Gort.spacing.sm),
            verticalArrangement = Arrangement.spacedBy(Gort.spacing.xs),
        ) {
            items.forEachIndexed { index, item ->
                val selected = index == selectedIndex
                val shape = Gort.corners.small
                val interactionSource = remember { MutableInteractionSource() }
                val isHovered by interactionSource.collectIsHoveredAsState()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Gort.spacing.sm)
                        .clip(shape)
                        .background(
                            when {
                                selected -> colors.primaryContainer
                                isHovered -> colors.primaryContainer.copy(alpha = 0.3f)
                                else -> colors.surface
                            },
                            shape,
                        )
                        .then(
                            if (selected) Modifier.border(Gort.borders.normal, colors.border, shape)
                            else Modifier
                        )
                        .hoverable(interactionSource)
                        .pointerHoverIcon(PointerIcon.Hand)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                        ) { onItemSelected(index) }
                        .padding(horizontal = Gort.spacing.md, vertical = Gort.spacing.sm),
                ) {
                    BasicText(
                        text = if (item.icon.isNotEmpty()) "${item.icon}  ${item.label}" else item.label,
                        style = Gort.typography.labelLarge.copy(
                            color = if (selected) colors.onPrimaryContainer else colors.onSurface,
                        ),
                    )
                }
            }
        }
        GortDivider(vertical = true, thickness = Gort.borders.heavy)
    }
}
