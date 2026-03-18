package zone.clanker.gort.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import zone.clanker.gort.theme.Gort

@Composable
fun ListItem(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    leading: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    val colors = Gort.colors
    val spacing = Gort.spacing
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isClickable = onClick != null

    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(
                if (isClickable) Modifier
                    .hoverable(interactionSource)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .clickable(onClick = onClick)
                else Modifier.hoverable(interactionSource)
            )
            .background(
                if (isHovered && isClickable) colors.primaryContainer.copy(alpha = 0.2f)
                else Color.Transparent,
            )
            .padding(horizontal = spacing.md, vertical = spacing.sm),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leading != null) {
            Box(modifier = Modifier.padding(end = spacing.md)) { leading() }
        }
        Column(modifier = Modifier.weight(1f)) {
            BasicText(
                text = title,
                style = Gort.typography.body.copy(color = colors.onSurface),
            )
            if (subtitle != null) {
                BasicText(
                    text = subtitle,
                    style = Gort.typography.label.copy(color = colors.onSurface.copy(alpha = 0.6f)),
                )
            }
        }
        if (trailing != null) {
            Box(modifier = Modifier.padding(start = spacing.md)) { trailing() }
        }
    }
}

@Composable
fun ListGroup(
    modifier: Modifier = Modifier,
    divider: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(modifier = modifier) {
        content()
    }
}
