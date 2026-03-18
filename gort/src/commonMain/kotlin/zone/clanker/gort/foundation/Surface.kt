package zone.clanker.gort.foundation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.zIndex
import zone.clanker.gort.theme.Gort
import zone.clanker.gort.theme.GortShadowSize

/**
 * The foundational surface for all Gort components.
 *
 * Draws a solid-color shadow rectangle behind the content,
 * then the content box with border on top. No blur, no elevation —
 * just hard offset shadows.
 */
@Composable
fun Surface(
    modifier: Modifier = Modifier,
    color: Color = Gort.colors.surface,
    borderColor: Color = Gort.colors.border,
    shadowColor: Color = Gort.colors.shadow,
    borderWidth: Dp = Gort.borders.default,
    shadow: GortShadowSize = Gort.shadows.default,
    shape: Shape = Gort.corners.default,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(modifier = modifier) {
        // Shadow layer — solid rectangle offset behind
        if (shadow.offsetX.value != 0f || shadow.offsetY.value != 0f) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .offset(x = shadow.offsetX, y = shadow.offsetY)
                    .clip(shape)
                    .background(shadowColor)
                    .zIndex(-1f)
            )
        }

        // Content layer — border + fill
        Box(
            modifier = Modifier
                .clip(shape)
                .background(color)
                .border(borderWidth, borderColor, shape),
            content = content,
        )
    }
}

/**
 * Convenience alias used as a standalone container component.
 */
@Composable
fun GortSurface(
    modifier: Modifier = Modifier,
    color: Color = Gort.colors.surface,
    borderColor: Color = Gort.colors.border,
    shadowColor: Color = Gort.colors.shadow,
    borderWidth: Dp = Gort.borders.default,
    shadow: GortShadowSize = Gort.shadows.none,
    shape: Shape = Gort.corners.default,
    content: @Composable BoxScope.() -> Unit,
) {
    Surface(modifier, color, borderColor, shadowColor, borderWidth, shadow, shape, content)
}
