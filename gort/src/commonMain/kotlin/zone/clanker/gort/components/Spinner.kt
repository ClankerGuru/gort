package zone.clanker.gort.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

@Composable
fun Spinner(
    modifier: Modifier = Modifier,
    color: Color = Gort.colors.primary,
    strokeWidth: Dp = 3.dp,
    size: Dp = 32.dp,
) {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = LinearEasing),
        ),
    )

    Canvas(modifier = modifier.size(size)) {
        val stroke = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Square)
        // Background track
        drawArc(
            color = color.copy(alpha = 0.2f),
            startAngle = 0f,
            sweepAngle = 360f,
            useCenter = false,
            style = stroke,
            topLeft = Offset(stroke.width / 2, stroke.width / 2),
            size = Size(this.size.width - stroke.width, this.size.height - stroke.width),
        )
        // Spinning arc — thick, blocky (Square cap = neobrutalist)
        drawArc(
            color = color,
            startAngle = rotation,
            sweepAngle = 90f,
            useCenter = false,
            style = stroke,
            topLeft = Offset(stroke.width / 2, stroke.width / 2),
            size = Size(this.size.width - stroke.width, this.size.height - stroke.width),
        )
    }
}
