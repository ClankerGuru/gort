package zone.clanker.gort.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import zone.clanker.gort.foundation.Surface
import zone.clanker.gort.theme.Gort

/**
 * Dashboard widget — a bordered card with a header section and content body.
 */
@Composable
fun Widget(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    action: (@Composable () -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = Gort.colors
    val spacing = Gort.spacing

    Surface(
        modifier = modifier,
        color = colors.surface,
        borderColor = colors.border,
        shadow = Gort.shadows.small,
    ) {
        Column {
            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.md, vertical = spacing.sm),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    BasicText(
                        text = title,
                        style = Gort.typography.title.copy(color = colors.onSurface),
                    )
                    if (subtitle != null) {
                        BasicText(
                            text = subtitle,
                            style = Gort.typography.label.copy(color = colors.onSurface.copy(alpha = 0.5f)),
                        )
                    }
                }
                if (action != null) {
                    action()
                }
            }
            Divider()
            // Body
            Column(
                modifier = Modifier.padding(spacing.md),
                content = content,
            )
        }
    }
}
