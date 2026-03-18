package zone.clanker.gort.components

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import zone.clanker.gort.foundation.Surface
import zone.clanker.gort.theme.Gort
import kotlinx.coroutines.delay

@Composable
fun Toast(
    message: String,
    visible: Boolean,
    modifier: Modifier = Modifier,
    severity: BannerSeverity = BannerSeverity.Info,
    durationMs: Long = 3000L,
    onDismiss: () -> Unit = {},
) {
    val colors = Gort.colors
    val (bg, fg) = when (severity) {
        BannerSeverity.Info -> colors.primaryContainer to colors.onPrimaryContainer
        BannerSeverity.Success -> colors.successContainer to colors.onSuccessContainer
        BannerSeverity.Warning -> colors.warningContainer to colors.onWarningContainer
        BannerSeverity.Error -> colors.errorContainer to colors.onErrorContainer
    }

    LaunchedEffect(visible) {
        if (visible) {
            delay(durationMs)
            onDismiss()
        }
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInVertically { -it },
        exit = fadeOut() + slideOutVertically { -it },
        modifier = modifier,
    ) {
        Surface(
            color = bg,
            borderColor = colors.border,
            shadow = Gort.shadows.medium,
        ) {
            BasicText(
                text = message,
                style = Gort.typography.body.copy(color = fg),
                modifier = Modifier.padding(
                    horizontal = Gort.spacing.lg,
                    vertical = Gort.spacing.md,
                ),
            )
        }
    }
}
