package zone.clanker.gort.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

@Composable
fun Skeleton(
    modifier: Modifier = Modifier,
    width: Dp = 200.dp,
    height: Dp = 20.dp,
) {
    val colors = Gort.colors
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
    )

    Box(
        modifier = modifier
            .size(width, height)
            .border(Gort.borders.thin, colors.border, Gort.corners.small)
            .background(colors.onSurface.copy(alpha = alpha), Gort.corners.small),
    )
}

@Composable
fun SkeletonText(
    lines: Int = 3,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Gort.spacing.sm),
    ) {
        repeat(lines) { index ->
            Skeleton(
                width = if (index == lines - 1) 150.dp else 250.dp,
                height = 16.dp,
            )
        }
    }
}

@Composable
fun SkeletonCard(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(Gort.spacing.md),
        verticalArrangement = Arrangement.spacedBy(Gort.spacing.sm),
    ) {
        Skeleton(width = 250.dp, height = 150.dp)
        Skeleton(width = 180.dp, height = 20.dp)
        SkeletonText(lines = 2)
    }
}
