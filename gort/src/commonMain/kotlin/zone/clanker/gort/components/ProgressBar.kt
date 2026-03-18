package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

@Composable
fun GortProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = Gort.colors.primary,
    trackColor: Color = Gort.colors.surface,
    height: Dp = 24.dp,
    shape: Shape = Gort.corners.small,
) {
    val colors = Gort.colors

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .clip(shape)
            .background(trackColor, shape)
            .border(Gort.borders.thick, colors.border, shape),
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(fraction = progress.coerceIn(0f, 1f))
                .background(color),
        )
    }
}
