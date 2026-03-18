package zone.clanker.gort.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class GortBorders(
    val thin: Dp = 1.dp,
    val normal: Dp = 2.dp,
    val thick: Dp = 3.dp,
    val heavy: Dp = 4.dp,
    val default: Dp = 3.dp,
)

val LocalGortBorders = staticCompositionLocalOf { GortBorders() }
