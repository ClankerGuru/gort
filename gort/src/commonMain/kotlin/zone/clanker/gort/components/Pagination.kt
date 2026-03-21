package zone.clanker.gort.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

@Composable
fun Pagination(
    currentPage: Int,
    totalPages: Int,
    onPageChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = Gort.spacing
    val haptic = LocalHapticFeedback.current

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing.xs),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PaginationButton(
            text = "←",
            enabled = currentPage > 1,
            selected = false,
            onClick = {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onPageChange(currentPage - 1)
            },
        )

        val range = paginationRange(currentPage, totalPages)
        range.forEach { page ->
            if (page == -1) {
                BasicText(
                    text = "…",
                    style = Gort.typography.body.copy(color = Gort.colors.onSurface),
                    modifier = Modifier.padding(horizontal = spacing.xs),
                )
            } else {
                PaginationButton(
                    text = page.toString(),
                    enabled = true,
                    selected = page == currentPage,
                    onClick = {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onPageChange(page)
                    },
                )
            }
        }

        PaginationButton(
            text = "→",
            enabled = currentPage < totalPages,
            selected = false,
            onClick = {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onPageChange(currentPage + 1)
            },
        )
    }
}

@Composable
private fun PaginationButton(
    text: String,
    enabled: Boolean,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val colors = Gort.colors
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val bgColor by animateColorAsState(
        targetValue = when {
            selected -> colors.primary
            isHovered && enabled -> colors.primaryContainer.copy(alpha = 0.3f)
            !enabled -> colors.background
            else -> colors.surface
        },
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium,
        ),
    )

    val borderColor by animateColorAsState(
        targetValue = when {
            selected -> colors.primary
            isHovered && enabled -> colors.primary.copy(alpha = 0.6f)
            else -> colors.border
        },
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium,
        ),
    )

    Box(
        modifier = Modifier
            .defaultMinSize(minWidth = 36.dp, minHeight = 36.dp)
            .border(
                if (selected) Gort.borders.thick else Gort.borders.default,
                borderColor,
                Gort.corners.small,
            )
            .background(bgColor, Gort.corners.small)
            .hoverable(interactionSource)
            .pointerHoverIcon(if (enabled) PointerIcon.Hand else PointerIcon.Default)
            .clickable(enabled = enabled, onClick = onClick)
            .padding(Gort.spacing.xs),
        contentAlignment = Alignment.Center,
    ) {
        BasicText(
            text = text,
            style = Gort.typography.label.copy(
                color = when {
                    selected -> colors.onPrimary
                    !enabled -> colors.onSurface.copy(alpha = 0.3f)
                    else -> colors.onSurface
                },
            ),
        )
    }
}

private fun paginationRange(current: Int, total: Int): List<Int> {
    if (total <= 7) return (1..total).toList()
    return buildList {
        add(1)
        if (current > 3) add(-1)
        val start = maxOf(2, current - 1)
        val end = minOf(total - 1, current + 1)
        addAll(start..end)
        if (current < total - 2) add(-1)
        add(total)
    }
}
