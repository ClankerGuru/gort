package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import zone.clanker.gort.foundation.Surface
import zone.clanker.gort.theme.Gort
import zone.clanker.gort.theme.GortShadowSize

/**
 * Keyboard shortcut display. Renders each key as a bordered box.
 * Usage: Kbd("Ctrl", "K") or Kbd("⌘", "Shift", "P")
 */
@Composable
fun Kbd(
    vararg keys: String,
    modifier: Modifier = Modifier,
) {
    val colors = Gort.colors
    val spacing = Gort.spacing

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing.xs),
    ) {
        keys.forEachIndexed { index, key ->
            if (index > 0) {
                BasicText(
                    text = "+",
                    style = Gort.typography.label.copy(color = colors.onSurface.copy(alpha = 0.5f)),
                    modifier = Modifier.padding(horizontal = spacing.xs),
                )
            }
            Surface(
                color = colors.surface,
                borderColor = colors.border,
                borderWidth = Gort.borders.thin,
                shadow = GortShadowSize(Gort.shadows.small.offsetX, Gort.shadows.small.offsetY),
                shadowColor = colors.shadow,
                shape = Gort.corners.small,
            ) {
                BasicText(
                    text = key,
                    style = Gort.typography.code.copy(color = colors.onSurface),
                    modifier = Modifier.padding(horizontal = spacing.sm, vertical = spacing.xs),
                )
            }
        }
    }
}
