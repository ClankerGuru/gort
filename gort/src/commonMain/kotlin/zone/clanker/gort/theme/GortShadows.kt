package zone.clanker.gort.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class GortShadowSize(
    val offsetX: Dp,
    val offsetY: Dp,
)

@Immutable
data class GortShadows(
    val none: GortShadowSize = GortShadowSize(0.dp, 0.dp),
    val small: GortShadowSize = GortShadowSize(2.dp, 2.dp),
    val medium: GortShadowSize = GortShadowSize(4.dp, 4.dp),
    val large: GortShadowSize = GortShadowSize(6.dp, 6.dp),
    val xl: GortShadowSize = GortShadowSize(8.dp, 8.dp),
    val default: GortShadowSize = medium,
)

val LocalGortShadows = staticCompositionLocalOf { GortShadows() }
