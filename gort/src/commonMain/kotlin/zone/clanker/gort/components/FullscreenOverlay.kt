package zone.clanker.gort.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import zone.clanker.gort.icons.lucide.Lucide
import zone.clanker.gort.icons.lucide.X
import zone.clanker.gort.theme.Gort

/**
 * Reusable fullscreen takeover overlay.
 * Covers the entire screen with background color and a close button in the top-right.
 */
@Composable
fun FullscreenOverlay(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val colors = Gort.colors

    Box(
        modifier = modifier
            .fillMaxSize()
            .zIndex(100f)
            .background(colors.background),
    ) {
        // Content area with scroll
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp)
                .verticalScroll(rememberScrollState())
                .padding(Gort.spacing.lg),
        ) {
            content()
        }

        // Close button in top-right
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(Gort.spacing.md),
        ) {
            IconButton(
                onClick = onDismiss,
                size = 40.dp,
            ) {
                Image(
                    imageVector = Lucide.X,
                    contentDescription = "Close",
                    colorFilter = ColorFilter.tint(colors.onSurface),
                    modifier = Modifier.size(20.dp),
                )
            }
        }
    }
}
