package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

@Composable
fun GortSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    color: Color = Gort.colors.primary,
    trackColor: Color = Gort.colors.surface,
    shape: Shape = Gort.corners.small,
) {
    val colors = Gort.colors
    val density = LocalDensity.current
    var trackSize by remember { mutableStateOf(IntSize.Zero) }
    val thumbSize = 24.dp
    val thumbSizePx = with(density) { thumbSize.toPx() }
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp)
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .onSizeChanged { trackSize = it }
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    val newVal = (offset.x / trackSize.width).coerceIn(0f, 1f)
                    onValueChange(newVal)
                }
            }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, _ ->
                    val newVal = (change.position.x / trackSize.width).coerceIn(0f, 1f)
                    onValueChange(newVal)
                }
            },
        contentAlignment = Alignment.CenterStart,
    ) {
        // Track
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .clip(shape)
                .background(trackColor, shape)
                .border(Gort.borders.normal, colors.border, shape),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction = value.coerceIn(0f, 1f))
                    .background(color),
            )
        }

        // Thumb
        val thumbOffset = with(density) {
            ((trackSize.width - thumbSizePx) * value.coerceIn(0f, 1f)).toDp()
        }
        Box(
            modifier = Modifier
                .offset(x = thumbOffset)
                .size(thumbSize)
                .clip(RoundedCornerShape(4.dp))
                .background(
                    if (isHovered) colors.primaryContainer else colors.surface,
                    RoundedCornerShape(4.dp),
                )
                .border(
                    if (isHovered) Gort.borders.heavy else Gort.borders.thick,
                    if (isHovered) colors.primary else colors.border,
                    RoundedCornerShape(4.dp),
                ),
        )
    }
}
