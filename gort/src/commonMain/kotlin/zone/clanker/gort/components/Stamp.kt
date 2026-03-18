package zone.clanker.gort.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import zone.clanker.gort.theme.Gort

@Composable
fun Stamp(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Gort.colors.error,
    rotation: Float = -15f,
) {
    BasicText(
        text = text.uppercase(),
        style = Gort.typography.headline.copy(color = color),
        modifier = modifier
            .rotate(rotation)
            .border(Gort.borders.thick, color, Gort.corners.small)
            .padding(horizontal = Gort.spacing.md, vertical = Gort.spacing.sm),
    )
}

@Composable
fun StampOverlay(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Gort.colors.error,
    rotation: Float = -15f,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(modifier = modifier) {
        content()
        Stamp(
            text = text,
            color = color,
            rotation = rotation,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}
