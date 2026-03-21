package zone.clanker.gort.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import zone.clanker.gort.icons.lucide.*
import zone.clanker.gort.theme.Gort

enum class TimelineDirection { Down, Up }

data class TimelineItem(
    val title: String,
    val description: String = "",
    val timestamp: String = "",
    val color: Color? = null,
    val icon: ImageVector? = null,
    val content: (@Composable () -> Unit)? = null,
)

@Composable
fun Timeline(
    items: List<TimelineItem>,
    modifier: Modifier = Modifier,
    direction: TimelineDirection = TimelineDirection.Down,
    maxItems: Int = 7,
    onExpand: (() -> Unit)? = null,
    horizontal: Boolean = false,
) {
    val colors = Gort.colors
    val spacing = Gort.spacing
    val haptic = LocalHapticFeedback.current

    var currentDirection by remember { mutableStateOf(direction) }
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }

    val orderedItems = if (currentDirection == TimelineDirection.Up) items.reversed() else items
    val visibleItems = if (expanded) orderedItems else orderedItems.take(maxItems)
    val canExpand = orderedItems.size > maxItems

    if (horizontal) {
        // Horizontal mode
        Column(modifier = modifier) {
            // Node strip
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                visibleItems.forEachIndexed { index, item ->
                    val nodeColor = item.color ?: colors.primary
                    val isSelected = index == selectedIndex

                    Column(
                        modifier = Modifier
                            .clickable {
                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                selectedIndex = index
                            }
                            .padding(horizontal = spacing.xs),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(if (isSelected) 20.dp else 14.dp)
                                .zIndex(1f)
                                .border(Gort.borders.default, colors.border, Gort.corners.default)
                                .background(nodeColor, Gort.corners.default),
                            contentAlignment = Alignment.Center,
                        ) {
                            if (item.icon != null) {
                                Image(
                                    imageVector = item.icon,
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(colors.onPrimary),
                                    modifier = Modifier.size(10.dp),
                                )
                            }
                        }
                        Spacer(Modifier.height(2.dp))
                        BasicText(
                            text = item.title,
                            style = Gort.typography.labelSmall.copy(
                                color = if (isSelected) colors.primary else colors.onSurface.copy(alpha = 0.6f),
                            ),
                        )
                    }
                }
            }

            // Detail card
            Spacer(Modifier.height(spacing.sm))
            AnimatedContent(
                targetState = selectedIndex,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
            ) { idx ->
                val item = visibleItems.getOrNull(idx) ?: return@AnimatedContent
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.fillMaxWidth().padding(spacing.md)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            BasicText(
                                text = item.title,
                                style = Gort.typography.title.copy(color = colors.onSurface),
                            )
                            if (item.timestamp.isNotEmpty()) {
                                BasicText(
                                    text = item.timestamp,
                                    style = Gort.typography.label.copy(color = colors.onSurface.copy(alpha = 0.5f)),
                                )
                            }
                        }
                        if (item.description.isNotEmpty()) {
                            Spacer(Modifier.height(spacing.xs))
                            BasicText(
                                text = item.description,
                                style = Gort.typography.body.copy(color = colors.onSurface.copy(alpha = 0.7f)),
                            )
                        }
                        item.content?.invoke()
                    }
                }
            }
        }
    } else {
        // Vertical mode
        Column(modifier = modifier) {
            // Direction toggle
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Image(
                    imageVector = if (currentDirection == TimelineDirection.Down) Lucide.ArrowDown else Lucide.ArrowUp,
                    contentDescription = "Toggle direction",
                    colorFilter = ColorFilter.tint(colors.onSurface.copy(alpha = 0.5f)),
                    modifier = Modifier
                        .size(18.dp)
                        .clickable {
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            currentDirection = if (currentDirection == TimelineDirection.Down) TimelineDirection.Up else TimelineDirection.Down
                        },
                )
            }

            visibleItems.forEachIndexed { index, item ->
                val nodeColor = item.color ?: colors.primary
                val isLast = index == visibleItems.lastIndex

                Row(modifier = Modifier.fillMaxWidth()) {
                    // Left: node + connector
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.width(32.dp),
                    ) {
                        // Connector above
                        if (index > 0) {
                            Box(
                                modifier = Modifier
                                    .width(Gort.borders.default)
                                    .height(8.dp)
                                    .background(colors.border),
                            )
                        }
                        // Node
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .zIndex(1f)
                                .border(Gort.borders.default, colors.border, Gort.corners.default)
                                .background(nodeColor, Gort.corners.default),
                            contentAlignment = Alignment.Center,
                        ) {
                            if (item.icon != null) {
                                Image(
                                    imageVector = item.icon,
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(colors.onPrimary),
                                    modifier = Modifier.size(10.dp),
                                )
                            }
                        }
                        // Connector below
                        if (!isLast) {
                            Box(
                                modifier = Modifier
                                    .width(Gort.borders.default)
                                    .height(40.dp)
                                    .background(colors.border),
                            )
                        }
                    }

                    // Right: content
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = spacing.sm, bottom = if (isLast) 0.dp else spacing.sm),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            BasicText(
                                text = item.title,
                                style = Gort.typography.title.copy(color = colors.onSurface),
                            )
                            if (item.timestamp.isNotEmpty()) {
                                BasicText(
                                    text = item.timestamp,
                                    style = Gort.typography.label.copy(color = colors.onSurface.copy(alpha = 0.5f)),
                                )
                            }
                        }
                        if (item.description.isNotEmpty()) {
                            BasicText(
                                text = item.description,
                                style = Gort.typography.body.copy(color = colors.onSurface.copy(alpha = 0.7f)),
                                modifier = Modifier.padding(top = spacing.xs),
                            )
                        }
                        item.content?.invoke()
                    }
                }
            }

            // Control drop for expand
            if (canExpand) {
                Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    ShadowDrop(onClick = {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        if (onExpand != null) onExpand()
                        else expanded = !expanded
                    })
                }
            }
        }
    }
}
