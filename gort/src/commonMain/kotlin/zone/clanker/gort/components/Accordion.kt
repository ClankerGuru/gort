package zone.clanker.gort.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import zone.clanker.gort.foundation.Surface
import zone.clanker.gort.theme.Gort

@Composable
fun Accordion(
    title: String,
    modifier: Modifier = Modifier,
    initialExpanded: Boolean = false,
    content: @Composable ColumnScope.() -> Unit,
) {
    var expanded by remember { mutableStateOf(initialExpanded) }
    val colors = Gort.colors
    val spacing = Gort.spacing

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = colors.surface,
        borderColor = colors.border,
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(spacing.md),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BasicText(
                    text = title,
                    style = Gort.typography.title.copy(color = colors.onSurface),
                )
                BasicText(
                    text = if (expanded) "▲" else "▼",
                    style = Gort.typography.body.copy(color = colors.onSurface),
                )
            }

            AnimatedVisibility(
                visible = expanded,
                enter = expandVertically(),
                exit = shrinkVertically(),
            ) {
                Column(
                    modifier = Modifier.padding(
                        start = spacing.md,
                        end = spacing.md,
                        bottom = spacing.md,
                    ),
                    content = content,
                )
            }
        }
    }
}
