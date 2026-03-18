package zone.clanker.gort.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextDecoration
import zone.clanker.gort.theme.Gort

data class BreadcrumbItem(
    val label: String,
    val onClick: (() -> Unit)? = null,
)

@Composable
fun Breadcrumb(
    items: List<BreadcrumbItem>,
    modifier: Modifier = Modifier,
    separator: String = "/",
) {
    val colors = Gort.colors

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Gort.spacing.xs),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items.forEachIndexed { index, item ->
            val isLast = index == items.lastIndex
            val isClickable = item.onClick != null && !isLast

            if (isClickable) {
                val interactionSource = remember { MutableInteractionSource() }
                val isHovered by interactionSource.collectIsHoveredAsState()

                BasicText(
                    text = item.label,
                    style = Gort.typography.body.copy(
                        color = colors.primary,
                        textDecoration = if (isHovered) TextDecoration.Underline else TextDecoration.None,
                    ),
                    modifier = Modifier
                        .hoverable(interactionSource)
                        .pointerHoverIcon(PointerIcon.Hand)
                        .clickable(onClick = item.onClick),
                )
            } else {
                BasicText(
                    text = item.label,
                    style = Gort.typography.body.copy(color = colors.onSurface),
                )
            }

            if (!isLast) {
                BasicText(
                    text = separator,
                    style = Gort.typography.body.copy(color = colors.onSurface.copy(alpha = 0.4f)),
                    modifier = Modifier.padding(horizontal = Gort.spacing.xs),
                )
            }
        }
    }
}
