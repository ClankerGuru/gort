package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

data class TimelineItem(
    val title: String,
    val description: String = "",
    val timestamp: String = "",
    val color: Color? = null,
)

@Composable
fun Timeline(
    items: List<TimelineItem>,
    modifier: Modifier = Modifier,
) {
    val colors = Gort.colors
    val spacing = Gort.spacing

    Column(modifier = modifier) {
        items.forEachIndexed { index, item ->
            val nodeColor = item.color ?: colors.primary
            val isLast = index == items.lastIndex

            Row(modifier = Modifier.fillMaxWidth()) {
                // Left: node + connector line
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(32.dp),
                ) {
                    // Node
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .border(Gort.borders.default, colors.border, Gort.corners.default)
                            .background(nodeColor, Gort.corners.default),
                    )
                    // Connector
                    if (!isLast) {
                        Box(
                            modifier = Modifier
                                .width(Gort.borders.default)
                                .height(48.dp)
                                .background(colors.border),
                        )
                    }
                }

                // Right: content
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = spacing.sm, bottom = if (isLast) 0.dp else spacing.md),
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
                }
            }
        }
    }
}
