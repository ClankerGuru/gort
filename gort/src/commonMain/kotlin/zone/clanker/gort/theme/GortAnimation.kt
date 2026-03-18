package zone.clanker.gort.theme

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

@Immutable
data class GortAnimation(
    val durationFast: Int = 80,
    val durationMedium: Int = 150,
    val durationSlow: Int = 300,
    val durationExpand: Int = 250,
    val easingStandard: Easing = CubicBezierEasing(0.2f, 0f, 0f, 1f),
    val easingSnap: Easing = CubicBezierEasing(0.4f, 0f, 0.2f, 1f),
)

val LocalGortAnimation = staticCompositionLocalOf { GortAnimation() }
