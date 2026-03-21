package zone.clanker.gort.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import zone.clanker.gort.theme.Gort
import kotlin.math.roundToInt

@Composable
fun GortBottomSheet(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val sheetHeightPx = with(LocalDensity.current) { 400.dp.toPx() }
    val offsetY by animateFloatAsState(
        targetValue = if (isVisible) 0f else sheetHeightPx,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium,
        ),
    )

    if (isVisible || offsetY < sheetHeightPx) {
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

            // Sheet
            val topShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
            val shadow = Gort.shadows.medium

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset { IntOffset(0, offsetY.roundToInt()) }
                    .fillMaxWidth(),
            ) {
                // Hard shadow pointing up
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .offset(x = shadow.offsetX, y = -shadow.offsetY)
                        .clip(topShape)
                        .background(Gort.colors.shadow)
                        .zIndex(-1f),
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(topShape)
                        .background(Gort.colors.surface),
                ) {
                    // Thick top border
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(Gort.borders.heavy)
                            .background(Gort.colors.border),
                    )

                    // Drag handle
                    Spacer(modifier = Modifier.height(Gort.spacing.sm))
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .width(40.dp)
                            .height(4.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(Gort.colors.border),
                    )

                    // Content
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Gort.spacing.md),
                        content = content,
                    )
                }
            }
        }
    }
}
