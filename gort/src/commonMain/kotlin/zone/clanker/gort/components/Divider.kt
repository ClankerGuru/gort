package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import zone.clanker.gort.theme.Gort

@Composable
fun GortDivider(
    modifier: Modifier = Modifier,
    color: Color = Gort.colors.border,
    thickness: Dp = Gort.borders.thick,
    vertical: Boolean = false,
) {
    if (vertical) {
        Box(
            modifier = modifier
                .fillMaxHeight()
                .width(thickness)
                .background(color),
        )
    } else {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(thickness)
                .background(color),
        )
    }
}

// Backward compat
@Composable
fun Divider(
    modifier: Modifier = Modifier,
    color: Color = Gort.colors.border,
    thickness: Dp = Gort.borders.thick,
) {
    GortDivider(modifier, color, thickness)
}
