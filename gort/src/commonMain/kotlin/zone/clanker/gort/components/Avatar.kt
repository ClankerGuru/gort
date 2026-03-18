package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

@Composable
fun GortAvatar(
    modifier: Modifier = Modifier,
    text: String = "",
    size: Dp = 40.dp,
    color: Color = Gort.colors.primaryContainer,
    contentColor: Color = Gort.colors.onPrimaryContainer,
    shape: Shape = CircleShape,
    content: (@Composable () -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(shape)
            .background(color, shape)
            .border(Gort.borders.thick, Gort.colors.border, shape),
        contentAlignment = Alignment.Center,
    ) {
        if (content != null) {
            content()
        } else if (text.isNotEmpty()) {
            BasicText(
                text = text.take(2).uppercase(),
                style = Gort.typography.labelLarge.copy(color = contentColor),
            )
        }
    }
}
