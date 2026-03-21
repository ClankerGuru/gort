package zone.clanker.gort.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
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
    val borderWidth = Gort.borders.default
    val haptic = LocalHapticFeedback.current

    SubcomposeLayout(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .border(borderWidth, colors.border, shape)
            .clip(shape),
    ) { constraints ->
        // Measure all segments to find widths
        val segmentPlaceables = options.mapIndexed { index, label ->
            val isSelected = index == selectedIndex
            subcompose("segment_$index") {
                SegmentItem(
                    label = label,
                    isSelected = isSelected,
                    onClick = {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onSelect(index)
                    },
                )
            }.first().measure(Constraints(maxWidth = constraints.maxWidth / options.size.coerceAtLeast(1)))
        }

        // Measure dividers
        val dividerPlaceables = (0 until options.size - 1).map { idx ->
            subcompose("div_$idx") {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(borderWidth)
                        .background(colors.border),
                )
            }.first().measure(Constraints(
                minHeight = segmentPlaceables.maxOfOrNull { it.height } ?: 0,
                maxHeight = segmentPlaceables.maxOfOrNull { it.height } ?: constraints.maxHeight,
            ))
        }

        // Calculate indicator position
        val segmentWidths = segmentPlaceables.map { it.width }
        val totalWidth = segmentWidths.sum() + dividerPlaceables.sumOf { it.width }
        val maxHeight = segmentPlaceables.maxOfOrNull { it.height } ?: 0

        // Measure animated indicator
        val indicatorOffset = segmentWidths.take(selectedIndex).sum() +
            dividerPlaceables.take(selectedIndex).sumOf { it.width }
        val indicatorWidth = segmentWidths.getOrElse(selectedIndex) { 0 }

        val indicatorPlaceable = subcompose("indicator") {
            val animatedOffset by animateDpAsState(
                targetValue = with(LocalDensity.current) { indicatorOffset.toDp() },
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium,
                ),
            )
            Box(
                modifier = Modifier
                    .width(with(LocalDensity.current) { indicatorWidth.toDp() })
                    .height(with(LocalDensity.current) { maxHeight.toDp() })
                    .offset(x = animatedOffset)
                    .background(colors.primary),
            )
        }.first().measure(Constraints(maxWidth = totalWidth, maxHeight = maxHeight))

        layout(totalWidth, maxHeight) {
            // Place indicator behind
            indicatorPlaceable.placeRelative(0, 0)

            // Place segments and dividers
            var x = 0
            segmentPlaceables.forEachIndexed { index, placeable ->
                placeable.placeRelative(x, 0)
                x += placeable.width
                if (index < dividerPlaceables.size) {
                    dividerPlaceables[index].placeRelative(x, 0)
                    x += dividerPlaceables[index].width
                }
            }
        }
    }
}

@Composable
private fun SegmentItem(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val colors = Gort.colors
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
            .clickable(onClick = onClick)
            .background(
                when {
                    isSelected -> androidx.compose.ui.graphics.Color.Transparent // indicator handles bg
                    isHovered -> colors.primaryContainer.copy(alpha = 0.3f)
                    else -> colors.surface
                },
            )
            .padding(horizontal = Gort.spacing.md, vertical = Gort.spacing.sm),
    )
}
