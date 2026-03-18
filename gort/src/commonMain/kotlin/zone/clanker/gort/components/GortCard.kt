package zone.clanker.gort.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import zone.clanker.gort.foundation.GortSurface
import zone.clanker.gort.theme.Gort
import zone.clanker.gort.theme.GortShadowSize

@Composable
fun GortCard(
    modifier: Modifier = Modifier,
    color: Color = Gort.colors.surface,
    shadow: GortShadowSize = Gort.shadows.medium,
    shape: Shape = Gort.corners.default,
    content: @Composable BoxScope.() -> Unit,
) {
    GortSurface(
        modifier = modifier,
        color = color,
        shadow = shadow,
        shape = shape,
    ) {
        Box(
            modifier = Modifier.padding(Gort.spacing.md),
            content = content,
        )
    }
}
