package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import zone.clanker.gort.theme.Gort

@Composable
fun Chip(
    label: String,
    modifier: Modifier = Modifier,
    color: Color = Gort.colors.primaryContainer,
    contentColor: Color = Gort.colors.onPrimaryContainer,
    shape: Shape = Gort.corners.small,
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(color, shape)
            .border(Gort.borders.normal, Gort.colors.border, shape)
            .padding(horizontal = Gort.spacing.sm, vertical = Gort.spacing.xs),
    ) {
        BasicText(
            text = label,
            style = Gort.typography.labelMedium.copy(color = contentColor),
        )
    }
}
