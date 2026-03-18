package zone.clanker.gort.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun GortTheme(
    colors: GortColors = GortColors.light(),
    borders: GortBorders = GortBorders(),
    shadows: GortShadows = GortShadows(),
    spacing: GortSpacing = GortSpacing(),
    corners: GortCorners = GortCorners(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalGortColors provides colors,
        LocalGortBorders provides borders,
        LocalGortShadows provides shadows,
        LocalGortSpacing provides spacing,
        LocalGortCorners provides corners,
        content = content,
    )
}

object Gort {
    val colors: GortColors
        @Composable @ReadOnlyComposable
        get() = LocalGortColors.current

    val borders: GortBorders
        @Composable @ReadOnlyComposable
        get() = LocalGortBorders.current

    val shadows: GortShadows
        @Composable @ReadOnlyComposable
        get() = LocalGortShadows.current

    val spacing: GortSpacing
        @Composable @ReadOnlyComposable
        get() = LocalGortSpacing.current

    val corners: GortCorners
        @Composable @ReadOnlyComposable
        get() = LocalGortCorners.current
}
