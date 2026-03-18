package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import zone.clanker.gort.theme.Gort

enum class BannerSeverity { Info, Success, Warning, Error }

@Composable
fun Banner(
    message: String,
    modifier: Modifier = Modifier,
    severity: BannerSeverity = BannerSeverity.Info,
    onDismiss: (() -> Unit)? = null,
) {
    val colors = Gort.colors
    val (bg, fg) = when (severity) {
        BannerSeverity.Info -> colors.primaryContainer to colors.onPrimaryContainer
        BannerSeverity.Success -> colors.successContainer to colors.onSuccessContainer
        BannerSeverity.Warning -> colors.warningContainer to colors.onWarningContainer
        BannerSeverity.Error -> colors.errorContainer to colors.onErrorContainer
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(Gort.borders.default, colors.border)
            .background(bg)
            .padding(Gort.spacing.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicText(
            text = message,
            style = Gort.typography.body.copy(color = fg),
            modifier = Modifier.weight(1f),
        )
        if (onDismiss != null) {
            BasicText(
                text = "✕",
                style = Gort.typography.body.copy(color = fg),
                modifier = Modifier.clickable(onClick = onDismiss).padding(start = Gort.spacing.sm),
            )
        }
    }
}
