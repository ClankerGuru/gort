package zone.clanker.gort.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class GortColors(
    val background: Color,
    val surface: Color,
    val onSurface: Color,
    val border: Color,
    val shadow: Color,
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val tertiary: Color,
    val onTertiary: Color,
    val accent: Color,
    val onAccent: Color,
    val error: Color,
    val onError: Color,
) {
    companion object {
        fun light(
            background: Color = Color(0xFFFFFFFF),
            surface: Color = Color(0xFFFFFFFF),
            onSurface: Color = Color(0xFF1A1A1A),
            border: Color = Color(0xFF1A1A1A),
            shadow: Color = Color(0xFF1A1A1A),
            primary: Color = Color(0xFFFF5470),
            onPrimary: Color = Color(0xFFFFFFFF),
            secondary: Color = Color(0xFF36D399),
            onSecondary: Color = Color(0xFF1A1A1A),
            tertiary: Color = Color(0xFFFFBE0B),
            onTertiary: Color = Color(0xFF1A1A1A),
            accent: Color = Color(0xFF4CC9F0),
            onAccent: Color = Color(0xFF1A1A1A),
            error: Color = Color(0xFFFF3333),
            onError: Color = Color(0xFFFFFFFF),
        ) = GortColors(
            background, surface, onSurface, border, shadow,
            primary, onPrimary, secondary, onSecondary,
            tertiary, onTertiary, accent, onAccent,
            error, onError,
        )

        fun dark(
            background: Color = Color(0xFF1A1A1A),
            surface: Color = Color(0xFF2A2A2A),
            onSurface: Color = Color(0xFFF5F5F5),
            border: Color = Color(0xFFF5F5F5),
            shadow: Color = Color(0xFF000000),
            primary: Color = Color(0xFFFF5470),
            onPrimary: Color = Color(0xFFFFFFFF),
            secondary: Color = Color(0xFF36D399),
            onSecondary: Color = Color(0xFF1A1A1A),
            tertiary: Color = Color(0xFFFFBE0B),
            onTertiary: Color = Color(0xFF1A1A1A),
            accent: Color = Color(0xFF4CC9F0),
            onAccent: Color = Color(0xFF1A1A1A),
            error: Color = Color(0xFFFF3333),
            onError: Color = Color(0xFFFFFFFF),
        ) = GortColors(
            background, surface, onSurface, border, shadow,
            primary, onPrimary, secondary, onSecondary,
            tertiary, onTertiary, accent, onAccent,
            error, onError,
        )
    }
}

val LocalGortColors = staticCompositionLocalOf { GortColors.light() }
