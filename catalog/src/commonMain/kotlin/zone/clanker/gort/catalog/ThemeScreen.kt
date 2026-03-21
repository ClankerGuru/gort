package zone.clanker.gort.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import zone.clanker.gort.components.*
import zone.clanker.gort.theme.Gort
import zone.clanker.gort.theme.GortColorScheme
import zone.clanker.gort.theme.GortColors
import zone.clanker.gort.theme.GortTypography

private data class PresetPalette(
    val name: String,
    val color: Color,
    val factory: (Boolean) -> GortColors,
)

private val presets = listOf(
    PresetPalette("Coral", Color(0xFFFF6B6B)) { GortColorScheme.coral(it) },
    PresetPalette("Ocean", Color(0xFF1E90FF)) { GortColorScheme.ocean(it) },
    PresetPalette("Forest", Color(0xFF2ECC71)) { GortColorScheme.forest(it) },
    PresetPalette("Neon", Color(0xFF39FF14)) { GortColorScheme.neon(it) },
    PresetPalette("Mono", Color(0xFF888888)) { GortColorScheme.mono(it) },
    PresetPalette("Gazette", Color(0xFF8B0000)) { GortColorScheme.gazette(it) },
)

@Composable
fun ThemeScreen(
    isDark: Boolean,
    onColorsChange: (GortColors) -> Unit,
    onTypographyChange: (GortTypography) -> Unit,
) {
    val colors = Gort.colors

    ShowcaseSection("Preset Palettes")

    ComponentShowcase(
        name = "Theme Presets",
        description = "Tap a color circle to switch the entire app theme live.",
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            presets.forEach { preset ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { onColorsChange(preset.factory(isDark)) },
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(preset.color, CircleShape)
                            .border(Gort.borders.thin, colors.border, CircleShape),
                    )
                    Spacer(Modifier.height(4.dp))
                    BasicText(
                        text = preset.name,
                        style = Gort.typography.labelSmall.copy(color = colors.onSurface),
                    )
                }
            }
        }
    }

    ShowcaseSection("Custom Seed Color")

    ComponentShowcase(
        name = "Seed Color Generator",
        description = "Pick any color to generate a full palette via GortColorScheme.fromSeed().",
    ) {
        val seedColors = listOf(
            Color(0xFFFF6B6B), Color(0xFF1E90FF), Color(0xFF2ECC71), Color(0xFF39FF14),
            Color(0xFFFF69B4), Color(0xFFFFBE0B), Color(0xFF8B5CF6), Color(0xFFFF8C00),
            Color(0xFF00CED1), Color(0xFFDC143C), Color(0xFF4169E1), Color(0xFF32CD32),
        )
        var selectedSeed by remember { mutableStateOf(seedColors[0]) }
        ColorPicker(
            colors = seedColors,
            selectedColor = selectedSeed,
            onColorSelect = {
                selectedSeed = it
                onColorsChange(GortColorScheme.fromSeed(it, isDark))
            },
        )
    }

    ShowcaseSection("Typography")

    ComponentShowcase(
        name = "Font Selector",
        description = "Switch between monospace, editorial, and sans-serif typography styles.",
    ) {
        var fontIdx by remember { mutableIntStateOf(0) }
        SegmentedControl(
            options = listOf("Monospace", "Editorial", "Sans-Serif"),
            selectedIndex = fontIdx,
            onSelect = {
                fontIdx = it
                val typo = when (it) {
                    0 -> GortTypography.monospace()
                    1 -> GortTypography.editorial()
                    else -> GortTypography.sansSerif()
                }
                onTypographyChange(typo)
            },
        )
    }

    ShowcaseSection("Live Preview")

    ComponentShowcase(
        name = "Theme Preview",
        description = "See how the current theme looks across different components.",
    ) {
        Button(onClick = {}) {
            BasicText("Button", style = Gort.typography.label.copy(color = Gort.colors.onPrimary))
        }
        Spacer(Modifier.height(Gort.spacing.sm))
        Card(modifier = Modifier.fillMaxWidth()) {
            BasicText(
                "Card with current theme colors",
                style = Gort.typography.body.copy(color = Gort.colors.onSurface),
            )
        }
        Spacer(Modifier.height(Gort.spacing.sm))
        var previewText by remember { mutableStateOf("") }
        TextField(value = previewText, onValueChange = { previewText = it }, placeholder = "TextField preview…")
        Spacer(Modifier.height(Gort.spacing.sm))
        Row(horizontalArrangement = Arrangement.spacedBy(Gort.spacing.sm)) {
            Badge(text = "3")
            Tag(text = "Active", color = Gort.colors.success, textColor = Gort.colors.onSuccess)
        }
        Spacer(Modifier.height(Gort.spacing.sm))
        ChatBubble(message = "Theme preview bubble", sender = "Gort", timestamp = "now")
    }

    ShowcaseSection("Current Palette")

    ComponentShowcase(
        name = "Color Grid",
        description = "All theme colors with their names and hex values.",
    ) {
        val allColors = listOf(
            "primary" to colors.primary,
            "onPrimary" to colors.onPrimary,
            "primaryContainer" to colors.primaryContainer,
            "secondary" to colors.secondary,
            "onSecondary" to colors.onSecondary,
            "secondaryContainer" to colors.secondaryContainer,
            "tertiary" to colors.tertiary,
            "onTertiary" to colors.onTertiary,
            "accent" to colors.accent,
            "background" to colors.background,
            "surface" to colors.surface,
            "onSurface" to colors.onSurface,
            "border" to colors.border,
            "error" to colors.error,
            "success" to colors.success,
            "warning" to colors.warning,
        )
        ColorPalette(colors = allColors)
    }

    ShowcaseSection("Theme Code")

    ComponentShowcase(
        name = "Copy Theme Code",
        description = "Kotlin code to reproduce the current theme configuration.",
    ) {
        fun Color.toHex(): String {
            val r = (red * 255).toInt().toString(16).padStart(2, '0').uppercase()
            val g = (green * 255).toInt().toString(16).padStart(2, '0').uppercase()
            val b = (blue * 255).toInt().toString(16).padStart(2, '0').uppercase()
            return "0xFF$r$g$b"
        }

        val themeCode = """GortTheme(
    colors = GortColorScheme.fromSeed(
        seed = Color(${colors.primary.toHex()}),
        isDark = $isDark,
    ),
    typography = GortTypography(),
) {
    // Your content
}"""
        Code(code = themeCode, language = "kotlin", onCopy = {})
    }
}
