package zone.clanker.gort.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import zone.clanker.gort.theme.Gort

/**
 * Backward-compatible Chip wrapper that delegates to [Tag].
 */
@Composable
fun Chip(
    label: String,
    modifier: Modifier = Modifier,
    color: Color = Gort.colors.primaryContainer,
    contentColor: Color = Gort.colors.onPrimaryContainer,
    shape: Shape = Gort.corners.small,
) {
    Tag(
        text = label,
        modifier = modifier,
        mode = TagMode.Display,
        color = color,
        textColor = contentColor,
    )
}
