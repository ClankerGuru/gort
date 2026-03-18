package zone.clanker.gort.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import zone.clanker.gort.components.GortDivider
import zone.clanker.gort.components.GortTopBar
import zone.clanker.gort.theme.Gort
import zone.clanker.gort.theme.GortColors
import zone.clanker.gort.theme.GortTheme

enum class CatalogSection(val label: String) {
    Buttons("🔘 Actions"),
    Inputs("📝 Inputs"),
    Display("📊 Display"),
    Navigation("🧭 Navigation"),
    Feedback("💬 Feedback"),
    Compound("🧩 Compound"),
    Data("📋 Data"),
    Chat("💭 Chat"),
}

@Composable
fun CatalogApp() {
    var isDark by remember { mutableStateOf(false) }
    var currentSection by remember { mutableStateOf(CatalogSection.Buttons) }

    GortTheme(colors = if (isDark) GortColors.dark() else GortColors.light()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Gort.colors.background),
        ) {
            GortTopBar(
                title = {
                    BasicText("Gort Catalog", style = Gort.typography.headline.copy(color = Gort.colors.onSurface))
                },
                actions = {
                    BasicText(
                        text = if (isDark) "☀️ Light" else "🌙 Dark",
                        style = Gort.typography.label.copy(color = Gort.colors.onSurface),
                        modifier = Modifier.clickable { isDark = !isDark },
                    )
                },
            )

            Row(modifier = Modifier.fillMaxSize()) {
                // Sidebar
                Column(
                    modifier = Modifier
                        .width(200.dp)
                        .fillMaxHeight()
                        .background(Gort.colors.surface)
                        .verticalScroll(rememberScrollState()),
                ) {
                    CatalogSection.entries.forEach { section ->
                        val isSelected = section == currentSection
                        BasicText(
                            text = section.label,
                            style = Gort.typography.body.copy(
                                color = if (isSelected) Gort.colors.primary else Gort.colors.onSurface,
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { currentSection = section }
                                .then(
                                    if (isSelected) Modifier.background(Gort.colors.primaryContainer)
                                    else Modifier
                                )
                                .padding(Gort.spacing.md),
                        )
                    }
                }

                GortDivider(vertical = true)

                // Content
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .verticalScroll(rememberScrollState())
                        .padding(Gort.spacing.lg),
                    verticalArrangement = Arrangement.spacedBy(Gort.spacing.lg),
                ) {
                    when (currentSection) {
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
