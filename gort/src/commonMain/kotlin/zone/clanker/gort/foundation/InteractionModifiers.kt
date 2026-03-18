package zone.clanker.gort.foundation

import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp

/**
 * Adds a neobrutalist focus ring — bold and obvious.
 * Returns whether the component is currently focused.
 */
@Composable
fun Modifier.gortFocusRing(
    interactionSource: MutableInteractionSource,
    color: Color,
    shape: Shape,
): Pair<Modifier, Boolean> {
    val isFocused by interactionSource.collectIsFocusedAsState()
    val mod = if (isFocused) {
        this.border(3.dp, color, shape)
    } else {
        this
    }
    return mod to isFocused
}

/**
 * Adds hoverable + hand cursor for clickable elements.
 */
fun Modifier.gortClickable(
    interactionSource: MutableInteractionSource,
): Modifier = this
    .hoverable(interactionSource)
    .pointerHoverIcon(PointerIcon.Hand)

/**
 * Adds hoverable + text cursor for text input elements.
 */
fun Modifier.gortTextInput(
    interactionSource: MutableInteractionSource,
): Modifier = this
    .hoverable(interactionSource)
    .pointerHoverIcon(PointerIcon.Text)
