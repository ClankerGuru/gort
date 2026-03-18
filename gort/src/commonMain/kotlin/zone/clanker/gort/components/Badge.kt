package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

@Composable
fun Badge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Gort.colors.error,
    contentColor: Color = Gort.colors.onError,
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(color, CircleShape)
            .border(Gort.borders.normal, Gort.colors.border, CircleShape)
            .padding(horizontal = 6.dp, vertical = 2.dp),
        contentAlignment = Alignment.Center,
    ) {
        BasicText(
            text = text,
            style = Gort.typography.labelSmall.copy(color = contentColor),
        )
    }
}
