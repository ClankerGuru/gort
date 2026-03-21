package zone.clanker.gort.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
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
    val headerInteraction = remember { MutableInteractionSource() }
    val isHeaderHovered by headerInteraction.collectIsHoveredAsState()

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = colors.surface,
        borderColor = colors.border,
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .hoverable(headerInteraction)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .clickable { expanded = !expanded }
                    .background(
                        if (isHeaderHovered) colors.primaryContainer.copy(alpha = 0.2f)
                        else Color.Transparent,
                    )
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
