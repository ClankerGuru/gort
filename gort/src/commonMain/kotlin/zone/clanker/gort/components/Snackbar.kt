package zone.clanker.gort.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import zone.clanker.gort.foundation.Surface
import zone.clanker.gort.theme.Gort

@Composable
fun GortSnackbar(
    message: String,
    modifier: Modifier = Modifier,
    color: Color = Gort.colors.surface,
    contentColor: Color = Gort.colors.onSurface,
    shape: Shape = Gort.corners.small,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = color,
        shadow = Gort.shadows.medium,
        shape = shape,
    ) {
        Box(modifier = Modifier.padding(Gort.spacing.md)) {
            BasicText(
                text = message,
                style = Gort.typography.bodyMedium.copy(color = contentColor),
            )
        }
    }
}
