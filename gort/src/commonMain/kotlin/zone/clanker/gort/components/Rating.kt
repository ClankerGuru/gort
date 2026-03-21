package zone.clanker.gort.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private val starGold = Color(0xFFFFD700)
private val starGray = Color(0xFFBDBDBD)

@Composable
fun Rating(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    max: Int = 5,
    halfStarSupport: Boolean = false,
    floatValue: Float = value.toFloat(),
) {
    val haptic = LocalHapticFeedback.current
    var hoveredIndex by remember { mutableIntStateOf(-1) }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Gort.spacing.xs),
    ) {
        repeat(max) { index ->
            val interactionSource = remember { MutableInteractionSource() }
            val isHovered by interactionSource.collectIsHoveredAsState()

            if (isHovered) hoveredIndex = index
            else if (hoveredIndex == index) hoveredIndex = -1

            val effectiveValue = if (hoveredIndex >= 0) hoveredIndex + 1f else floatValue
            val fillAmount = when {
                index + 1 <= effectiveValue -> 1f
                index < effectiveValue -> effectiveValue - index
                else -> 0f
            }

            Canvas(
                modifier = Modifier
                    .size(28.dp)
                    .hoverable(interactionSource)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .clickable {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onValueChange(index + 1)
                    },
            ) {
                drawStar(fillAmount)
            }
        }
    }
}

private fun DrawScope.drawStar(fillAmount: Float) {
    val path = starPath(size)

    // Gray outline for empty
    drawPath(path, color = starGray, style = Stroke(width = 1.5f))

    if (fillAmount > 0f) {
        if (fillAmount >= 1f) {
            // Full fill
            drawPath(path, color = starGold, style = Fill)
            drawPath(path, color = Color.Black, style = Stroke(width = 1.5f))
        } else {
            // Partial fill (half star)
            clipRect(right = size.width * fillAmount) {
                drawPath(path, color = starGold, style = Fill)
                drawPath(path, color = Color.Black, style = Stroke(width = 1.5f))
            }
        }
    }
}

private fun starPath(size: Size): Path {
    val cx = size.width / 2
    val cy = size.height / 2
    val outerR = size.minDimension / 2 * 0.95f
    val innerR = outerR * 0.38f
    val path = Path()
    val startAngle = -PI / 2

    for (i in 0 until 5) {
        val outerAngle = startAngle + i * 2 * PI / 5
        val innerAngle = startAngle + (i + 0.5) * 2 * PI / 5

        val ox = cx + (outerR * cos(outerAngle)).toFloat()
        val oy = cy + (outerR * sin(outerAngle)).toFloat()
        val ix = cx + (innerR * cos(innerAngle)).toFloat()
        val iy = cy + (innerR * sin(innerAngle)).toFloat()

        if (i == 0) path.moveTo(ox, oy) else path.lineTo(ox, oy)
        path.lineTo(ix, iy)
    }
    path.close()
    return path
}
