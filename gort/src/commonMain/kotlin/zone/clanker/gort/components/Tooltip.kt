package zone.clanker.gort.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import zone.clanker.gort.foundation.Surface
import zone.clanker.gort.theme.Gort

@Composable
fun GortTooltip(
    text: String,
    modifier: Modifier = Modifier,
    shape: Shape = Gort.corners.small,
) {
    Surface(
        modifier = modifier,
        color = Gort.colors.onSurface,
        borderColor = Gort.colors.border,
        shadow = Gort.shadows.small,
        shape = shape,
        borderWidth = Gort.borders.normal,
    ) {
        Box(modifier = Modifier.padding(horizontal = Gort.spacing.sm, vertical = Gort.spacing.xs)) {
            BasicText(
                text = text,
                style = Gort.typography.labelSmall.copy(color = Gort.colors.surface),
            )
        }
    }
}
