package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import zone.clanker.gort.theme.Gort

@Composable
fun Tag(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Gort.colors.primary,
    textColor: Color = Gort.colors.onPrimary,
) {
    BasicText(
        text = text,
        style = Gort.typography.label.copy(color = textColor),
        modifier = modifier
            .border(Gort.borders.thin, Gort.colors.border, Gort.corners.small)
            .background(color, Gort.corners.small)
            .padding(horizontal = Gort.spacing.sm, vertical = Gort.spacing.xs),
    )
}
