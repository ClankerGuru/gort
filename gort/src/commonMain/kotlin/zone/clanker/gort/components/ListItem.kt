package zone.clanker.gort.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
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
        // Content with dividers handled by caller using Divider() between items
        content()
    }
}
