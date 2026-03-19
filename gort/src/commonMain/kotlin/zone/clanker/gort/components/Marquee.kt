package zone.clanker.gort.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

@Composable
fun Marquee(
    text: String,
    modifier: Modifier = Modifier,
    durationMs: Int = 8000,
) {
    val colors = Gort.colors
    val density = LocalDensity.current
    var containerWidth by remember { mutableIntStateOf(0) }
    var textWidth by remember { mutableIntStateOf(0) }

    val infiniteTransition = rememberInfiniteTransition()
    val scrollOffset by infiniteTransition.animateFloat(
        initialValue = containerWidth.toFloat(),
        targetValue = -textWidth.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMs, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
    )

    val fadeWidth = with(density) { 32.dp.toPx() }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(Gort.borders.default, colors.border)
            .background(colors.background)
            .clipToBounds()
            .onSizeChanged { containerWidth = it.width }
            .drawWithContent {
                drawContent()
                // Left fade edge
                drawRect(
                    brush = Brush.horizontalGradient(
                        colors = listOf(colors.background, Color.Transparent),
                        startX = 0f,
                        endX = fadeWidth,
                    ),
                    size = Size(fadeWidth, size.height),
                )
                // Right fade edge
                drawRect(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color.Transparent, colors.background),
                        startX = size.width - fadeWidth,
                        endX = size.width,
                    ),
                    topLeft = Offset(size.width - fadeWidth, 0f),
                    size = Size(fadeWidth, size.height),
                )
            },
    ) {
        BasicText(
            text = text,
            style = Gort.typography.body.copy(color = colors.onSurface),
            modifier = Modifier
                .offset { IntOffset(scrollOffset.toInt(), 0) }
                .padding(vertical = Gort.spacing.sm)
                .onSizeChanged { textWidth = it.width },
            maxLines = 1,
        )
    }
}
