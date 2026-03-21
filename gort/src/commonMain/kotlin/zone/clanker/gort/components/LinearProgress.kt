package zone.clanker.gort.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

/**
 * Indeterminate linear loading bar — a glowing segment slides across the track.
 */
@Composable
fun LinearLoadingBar(
    modifier: Modifier = Modifier,
    color: Color = Gort.colors.primary,
    trackColor: Color = Gort.colors.primary.copy(alpha = 0.12f),
    height: Dp = 8.dp,
) {
    val infiniteTransition = rememberInfiniteTransition()

    val position by infiniteTransition.animateFloat(
        initialValue = -0.35f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .border(Gort.borders.thin, Gort.colors.border),
    ) {
        // Track background
        drawRect(color = trackColor)

        // Sliding segment (30% width)
        val segmentWidth = size.width * 0.35f
        val x = position * size.width
        drawRect(
            color = color,
            topLeft = Offset(x, 0f),
            size = Size(segmentWidth, size.height),
        )
    }
}
