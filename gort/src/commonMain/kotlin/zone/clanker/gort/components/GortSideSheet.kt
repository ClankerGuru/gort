package zone.clanker.gort.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import zone.clanker.gort.theme.Gort
import kotlin.math.roundToInt

@Composable
fun GortSideSheet(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    sheetWidth: Dp = 320.dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    val sheetWidthPx = with(LocalDensity.current) { sheetWidth.toPx() }
    val offsetX by animateFloatAsState(
        targetValue = if (isVisible) 0f else sheetWidthPx,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium,
        ),
    )

    if (isVisible || offsetX < sheetWidthPx) {
        Box(modifier = modifier.fillMaxSize()) {
            // Scrim
            if (isVisible) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                        .clickable(onClick = onDismiss),
                )
            }

            val shadow = Gort.shadows.medium

            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .offset { IntOffset(offsetX.roundToInt(), 0) }
                    .width(sheetWidth)
                    .fillMaxHeight(),
            ) {
                // Hard shadow pointing left
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .offset(x = -shadow.offsetX, y = shadow.offsetY)
                        .background(Gort.colors.shadow)
                        .zIndex(-1f),
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Gort.colors.surface)
                        .border(
                            width = Gort.borders.heavy,
                            color = Gort.colors.border,
                        )
                        .verticalScroll(rememberScrollState())
                        .padding(Gort.spacing.md),
                    content = content,
                )
            }
        }
    }
}
