package zone.clanker.gort.theme

import androidx.compose.ui.graphics.Color
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * Seed-based color palette generator for neobrutalist themes.
 * One color in → full palette out via HSL hue rotation.
 */
object GortColorScheme {

    /**
     * Generate a full [GortColors] from a seed color.
     * - primary = seed (full saturation)
     * - secondary = +120° hue rotation (triadic)
     * - tertiary = +240° hue rotation (triadic)
     * - accent = +180° hue rotation (complementary)
     * - Containers = seed tinted onto cream/charcoal at 15-25% opacity
     * - Background = always cream (light) or charcoal (dark)
     * - Border/shadow = always near-black (light) or near-white (dark)
     * - Semantic colors (error/success/warning) = fixed
     */
    fun fromSeed(seed: Color, isDark: Boolean = false): GortColors {
        val hsl = colorToHsl(seed)
        val primary = seed
        val secondary = hslToColor(hsl.copy(h = (hsl.h + 120f) % 360f))
        val tertiary = hslToColor(hsl.copy(h = (hsl.h + 240f) % 360f))
        val accent = hslToColor(hsl.copy(h = (hsl.h + 180f) % 360f))

        val bg = if (isDark) Color(0xFF1A1A1A) else Color(0xFFF5F0E8)
        val surface = if (isDark) Color(0xFF2A2A2A) else Color(0xFFFFFFFF)
        val onSurface = if (isDark) Color(0xFFF5F5F5) else Color(0xFF1A1A1A)
        val border = if (isDark) Color(0xFFF5F5F5) else Color(0xFF1A1A1A)
        val shadow = if (isDark) Color(0xFF000000) else Color(0xFF1A1A1A)

        fun container(base: Color, alpha: Float = 0.20f): Color =
            blendColor(base.copy(alpha = alpha), if (isDark) Color(0xFF2A2A2A) else Color(0xFFFFF8F0))

        val onPrimary = contrastColor(primary)
        val onSecondary = contrastColor(secondary)
        val onTertiary = contrastColor(tertiary)
        val onAccent = contrastColor(accent)

        return GortColors(
            background = bg,
            surface = surface,
            onSurface = onSurface,
            border = border,
            shadow = shadow,
            primary = primary,
            onPrimary = onPrimary,
            primaryContainer = container(primary),
            onPrimaryContainer = onSurface,
            secondary = secondary,
            onSecondary = onSecondary,
            secondaryContainer = container(secondary),
            onSecondaryContainer = onSurface,
            tertiary = tertiary,
            onTertiary = onTertiary,
            tertiaryContainer = container(tertiary),
            onTertiaryContainer = onSurface,
            accent = accent,
            onAccent = onAccent,
            error = Color(0xFFFF3333),
            onError = Color(0xFFFFFFFF),
            errorContainer = container(Color(0xFFFF3333), 0.15f),
            onErrorContainer = onSurface,
            success = Color(0xFF36D399),
            onSuccess = Color(0xFF1A1A1A),
            successContainer = container(Color(0xFF36D399), 0.15f),
            onSuccessContainer = onSurface,
            warning = Color(0xFFFFBE0B),
            onWarning = Color(0xFF1A1A1A),
            warningContainer = container(Color(0xFFFFBE0B), 0.15f),
            onWarningContainer = onSurface,
        )
    }

    /** Current default — coral pink/green/yellow */
    fun coral(isDark: Boolean = false): GortColors =
        if (isDark) GortColors.dark() else GortColors.light()

    /** Navy/teal/amber */
    fun ocean(isDark: Boolean = false): GortColors =
        fromSeed(Color(0xFF1E90FF), isDark)

    /** Emerald/brown/gold */
    fun forest(isDark: Boolean = false): GortColors =
        fromSeed(Color(0xFF2ECC71), isDark)

    /** Electric green/magenta/cyan */
    fun neon(isDark: Boolean = false): GortColors =
        fromSeed(Color(0xFF39FF14), isDark)

    /** Pure B&W, red accent only */
    fun mono(isDark: Boolean = false): GortColors {
        val bg = if (isDark) Color(0xFF1A1A1A) else Color(0xFFF5F0E8)
        val surface = if (isDark) Color(0xFF2A2A2A) else Color(0xFFFFFFFF)
        val onSurface = if (isDark) Color(0xFFF5F5F5) else Color(0xFF1A1A1A)
        val border = if (isDark) Color(0xFFF5F5F5) else Color(0xFF1A1A1A)
        val shadow = if (isDark) Color(0xFF000000) else Color(0xFF1A1A1A)
        val gray = if (isDark) Color(0xFF888888) else Color(0xFF555555)
        val lightGray = if (isDark) Color(0xFF3A3A3A) else Color(0xFFE0E0E0)
        return GortColors(
            background = bg, surface = surface, onSurface = onSurface, border = border, shadow = shadow,
            primary = onSurface, onPrimary = if (isDark) Color(0xFF1A1A1A) else Color(0xFFFFFFFF),
            primaryContainer = lightGray, onPrimaryContainer = onSurface,
            secondary = gray, onSecondary = Color(0xFFFFFFFF),
            secondaryContainer = lightGray, onSecondaryContainer = onSurface,
            tertiary = gray, onTertiary = Color(0xFFFFFFFF),
            tertiaryContainer = lightGray, onTertiaryContainer = onSurface,
            accent = Color(0xFFFF3333), onAccent = Color(0xFFFFFFFF),
            error = Color(0xFFFF3333), onError = Color(0xFFFFFFFF),
            errorContainer = Color(0xFFFFD6D6), onErrorContainer = onSurface,
            success = Color(0xFF36D399), onSuccess = Color(0xFF1A1A1A),
            successContainer = Color(0xFFD0F5E5), onSuccessContainer = onSurface,
            warning = Color(0xFFFFBE0B), onWarning = Color(0xFF1A1A1A),
            warningContainer = Color(0xFFFFF3CC), onWarningContainer = onSurface,
        )
    }

    /** Newspaper special — sepia/black/burgundy */
    fun gazette(isDark: Boolean = false): GortColors =
        fromSeed(Color(0xFF8B0000), isDark)

    // --- HSL utilities ---

    private data class Hsl(val h: Float, val s: Float, val l: Float)

    private fun colorToHsl(color: Color): Hsl {
        val r = color.red; val g = color.green; val b = color.blue
        val cmax = max(r, max(g, b))
        val cmin = min(r, min(g, b))
        val delta = cmax - cmin
        val l = (cmax + cmin) / 2f
        val s = if (delta == 0f) 0f else delta / (1f - abs(2f * l - 1f))
        val h = when {
            delta == 0f -> 0f
            cmax == r -> 60f * (((g - b) / delta) % 6f)
            cmax == g -> 60f * (((b - r) / delta) + 2f)
            else -> 60f * (((r - g) / delta) + 4f)
        }
        return Hsl((h + 360f) % 360f, s.coerceIn(0f, 1f), l.coerceIn(0f, 1f))
    }

    private fun hslToColor(hsl: Hsl): Color {
        val c = (1f - abs(2f * hsl.l - 1f)) * hsl.s
        val x = c * (1f - abs((hsl.h / 60f) % 2f - 1f))
        val m = hsl.l - c / 2f
        val (r, g, b) = when {
            hsl.h < 60f -> Triple(c, x, 0f)
            hsl.h < 120f -> Triple(x, c, 0f)
            hsl.h < 180f -> Triple(0f, c, x)
            hsl.h < 240f -> Triple(0f, x, c)
            hsl.h < 300f -> Triple(x, 0f, c)
            else -> Triple(c, 0f, x)
        }
        return Color((r + m).coerceIn(0f, 1f), (g + m).coerceIn(0f, 1f), (b + m).coerceIn(0f, 1f))
    }

    private fun blendColor(src: Color, dst: Color): Color {
        val a = src.alpha
        return Color(
            red = src.red * a + dst.red * (1f - a),
            green = src.green * a + dst.green * (1f - a),
            blue = src.blue * a + dst.blue * (1f - a),
            alpha = 1f,
        )
    }

    private fun contrastColor(color: Color): Color {
        val luminance = 0.299f * color.red + 0.587f * color.green + 0.114f * color.blue
        return if (luminance > 0.5f) Color(0xFF1A1A1A) else Color(0xFFFFFFFF)
    }
}
