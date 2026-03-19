package zone.clanker.gort.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zone.clanker.gort.components.GortDivider
import zone.clanker.gort.foundation.WindowSize
import zone.clanker.gort.foundation.currentWindowSize
import zone.clanker.gort.theme.Gort
import zone.clanker.gort.theme.GortColors
import zone.clanker.gort.theme.GortTheme
import zone.clanker.gort.theme.GortTypography

enum class CatalogSection(val label: String, val icon: String) {
    Theme("🎨 Theme", "🎨"),
    Buttons("🔘 Actions", "🔘"),
    Inputs("📝 Inputs", "📝"),
    Display("📊 Display", "📊"),
    Navigation("🧭 Navigation", "🧭"),
    Feedback("💬 Feedback", "💬"),
    Compound("🧩 Compound", "🧩"),
    Data("📋 Data", "📋"),
    Chat("💭 Chat", "💭"),
}

@Composable
fun CatalogApp() {
    var isDark by remember { mutableStateOf(false) }
    var currentSection by remember { mutableStateOf(CatalogSection.Buttons) }
    var currentColors by remember { mutableStateOf(GortColors.light()) }
    var currentTypography by remember { mutableStateOf(GortTypography()) }

    GortTheme(colors = currentColors, typography = currentTypography) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize().background(Gort.colors.background),
        ) {
            val windowSize = currentWindowSize()

            Column(modifier = Modifier.fillMaxSize()) {
                // Masthead
                Masthead(isDark = isDark, onToggleDark = {
                    isDark = !isDark
                    currentColors = if (isDark) GortColors.dark() else GortColors.light()
                })

                // Content area
                when (windowSize) {
                    WindowSize.Expanded -> {
                        Row(modifier = Modifier.fillMaxSize()) {
                            // Nav rail
                            NavRail(
                                currentSection = currentSection,
                                onSelect = { currentSection = it },
                            )
                            // Column rule
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                                    .background(Gort.colors.border.copy(alpha = 0.3f)),
                            )
                            // Content
                            CatalogContent(
                                section = currentSection,
                                isDark = isDark,
                                onColorsChange = { currentColors = it },
                                onTypographyChange = { currentTypography = it },
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }
                    else -> {
                        // Compact & Medium: content + bottom nav
                        Box(modifier = Modifier.weight(1f)) {
                            CatalogContent(
                                section = currentSection,
                                isDark = isDark,
                                onColorsChange = { currentColors = it },
                                onTypographyChange = { currentTypography = it },
                                modifier = Modifier.fillMaxSize(),
                            )
                        }
                        GortDivider()
                        BottomNavBar(
                            currentSection = currentSection,
                            onSelect = { currentSection = it },
                            showLabels = windowSize == WindowSize.Medium,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Masthead(isDark: Boolean, onToggleDark: () -> Unit) {
    val colors = Gort.colors
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.surface)
            .padding(horizontal = 16.dp, vertical = 12.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicText(
                text = "GORT GAZETTE",
                style = Gort.typography.titleLarge.copy(
                    color = colors.onSurface,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 4.sp,
                    fontSize = 22.sp,
                ),
            )
            BasicText(
                text = if (isDark) "☀️" else "🌙",
                style = Gort.typography.titleLarge.copy(color = colors.onSurface),
                modifier = Modifier.clickable(onClick = onToggleDark),
            )
        }
        BasicText(
            text = "Vol. 1 · March 2026 · 52 Components",
            style = Gort.typography.labelSmall.copy(
                color = colors.onSurface.copy(alpha = 0.5f),
            ),
        )
    }
    GortDivider()
}

@Composable
private fun NavRail(
    currentSection: CatalogSection,
    onSelect: (CatalogSection) -> Unit,
) {
    val colors = Gort.colors
    Column(
        modifier = Modifier
            .width(72.dp)
            .fillMaxHeight()
            .background(colors.surface)
            .verticalScroll(rememberScrollState())
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CatalogSection.entries.forEach { section ->
            val isSelected = section == currentSection
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelect(section) }
                    .then(
                        if (isSelected) Modifier.background(colors.primaryContainer)
                        else Modifier
                    )
                    .padding(vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                BasicText(
                    text = section.icon,
                    style = Gort.typography.bodyLarge,
                )
                Spacer(modifier = Modifier.height(2.dp))
                BasicText(
                    text = section.label.substringAfter(" "),
                    style = Gort.typography.labelSmall.copy(
                        color = if (isSelected) colors.primary else colors.onSurface.copy(alpha = 0.7f),
                    ),
                )
            }
        }
    }
}

@Composable
private fun BottomNavBar(
    currentSection: CatalogSection,
    onSelect: (CatalogSection) -> Unit,
    showLabels: Boolean,
) {
    val colors = Gort.colors
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.surface)
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        CatalogSection.entries.forEach { section ->
            val isSelected = section == currentSection
            Column(
                modifier = Modifier
                    .clickable { onSelect(section) }
                    .padding(horizontal = 4.dp, vertical = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                BasicText(
                    text = section.icon,
                    style = Gort.typography.bodyMedium,
                )
                if (showLabels) {
                    BasicText(
                        text = section.label.substringAfter(" "),
                        style = Gort.typography.labelSmall.copy(
                            color = if (isSelected) colors.primary else colors.onSurface.copy(alpha = 0.7f),
                            fontSize = 9.sp,
                        ),
                    )
                }
            }
        }
    }
}

@Composable
private fun CatalogContent(
    section: CatalogSection,
    isDark: Boolean = false,
    onColorsChange: (GortColors) -> Unit = {},
    onTypographyChange: (GortTypography) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 800.dp)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(Gort.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(Gort.spacing.lg),
        ) {
            when (section) {
                CatalogSection.Theme -> ThemeScreen(
                    isDark = isDark,
                    onColorsChange = onColorsChange,
                    onTypographyChange = onTypographyChange,
                )
                CatalogSection.Buttons -> ButtonsScreen()
                CatalogSection.Inputs -> InputsScreen()
                CatalogSection.Display -> DisplayScreen()
                CatalogSection.Navigation -> NavigationScreen()
                CatalogSection.Feedback -> FeedbackScreen()
                CatalogSection.Compound -> CompoundScreen()
                CatalogSection.Data -> DataScreen()
                CatalogSection.Chat -> ChatScreen()
            }
        }
    }
}

@Composable
fun SectionTitle(text: String) {
    BasicText(
        text = text,
        style = Gort.typography.headline.copy(color = Gort.colors.onSurface),
    )
}

@Composable
fun ComponentLabel(text: String) {
    BasicText(
        text = text,
        style = Gort.typography.label.copy(color = Gort.colors.onSurface.copy(alpha = 0.6f)),
        modifier = Modifier.padding(top = Gort.spacing.sm),
    )
}
