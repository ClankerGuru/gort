package zone.clanker.gort.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import zone.clanker.gort.foundation.Surface
import zone.clanker.gort.theme.Gort

/**
 * A bordered drawing surface. Wraps Compose Canvas in a Gort Surface
 * with border and optional shadow. Supports drag input.
 */
@Composable
fun DrawSurface(
    modifier: Modifier = Modifier,
    onDrag: ((start: Offset, end: Offset) -> Unit)? = null,
    onDraw: DrawScope.() -> Unit,
) {
    Surface(
        modifier = modifier,
        color = Gort.colors.surface,
        borderColor = Gort.colors.border,
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    if (onDrag != null) {
                        Modifier.pointerInput(Unit) {
                            detectDragGestures { change, _ ->
                                onDrag(change.previousPosition, change.position)
                            }
                        }
                    } else Modifier
                ),
            onDraw = onDraw,
        )
    }
}
