package zone.clanker.gort.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort
import kotlin.math.roundToInt

@Composable
fun GortDrawer(
    isOpen: Boolean,
    onClose: () -> Unit,
    modifier: Modifier = Modifier,
    drawerWidth: Dp = 280.dp,
    content: @Composable ColumnScope.() -> Unit,
    body: @Composable () -> Unit,
) {
    val drawerWidthPx = with(LocalDensity.current) { drawerWidth.toPx() }
    val offsetX by animateFloatAsState(
        targetValue = if (isOpen) 0f else -drawerWidthPx,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessMedium),
    )

    Box(modifier = modifier.fillMaxSize()) {
        body()

        // Scrim
        if (isOpen) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable(onClick = onClose),
            )
        }

        // Drawer panel
        Column(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .width(drawerWidth)
                .fillMaxHeight()
                .background(Gort.colors.surface)
                .border(Gort.borders.default, Gort.colors.border)
                .verticalScroll(rememberScrollState())
                .padding(Gort.spacing.md),
            content = content,
        )
    }
}
