package zone.clanker.gort.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import zone.clanker.gort.foundation.Surface
import zone.clanker.gort.theme.Gort

@Composable
fun Notification(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    severity: BannerSeverity = BannerSeverity.Info,
    icon: (@Composable () -> Unit)? = null,
    onDismiss: (() -> Unit)? = null,
    actions: (@Composable RowScope.() -> Unit)? = null,
) {
    val colors = Gort.colors
    val spacing = Gort.spacing
    val (bg, fg) = when (severity) {
        BannerSeverity.Info -> colors.primaryContainer to colors.onPrimaryContainer
        BannerSeverity.Success -> colors.successContainer to colors.onSuccessContainer
        BannerSeverity.Warning -> colors.warningContainer to colors.onWarningContainer
        BannerSeverity.Error -> colors.errorContainer to colors.onErrorContainer
    }

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = bg,
        borderColor = colors.border,
        shadow = Gort.shadows.medium,
    ) {
        Column(modifier = Modifier.padding(spacing.md)) {
            Row(
                verticalAlignment = Alignment.Top,
            ) {
                if (icon != null) {
                    Box(modifier = Modifier.padding(end = spacing.sm)) { icon() }
                }
                Column(modifier = Modifier.weight(1f)) {
                    BasicText(
                        text = title,
                        style = Gort.typography.title.copy(color = fg),
                    )
                    Spacer(Modifier.height(spacing.xs))
                    BasicText(
                        text = message,
                        style = Gort.typography.body.copy(color = fg),
                    )
                }
                if (onDismiss != null) {
                    BasicText(
                        text = "✕",
                        style = Gort.typography.body.copy(color = fg),
                        modifier = Modifier.clickable(onClick = onDismiss),
                    )
                }
            }
            if (actions != null) {
                Spacer(Modifier.height(spacing.sm))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                    content = actions,
                )
            }
        }
    }
}
