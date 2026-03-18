package zone.clanker.gort.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import zone.clanker.gort.components.GortButton
import zone.clanker.gort.components.GortButtonStyle
import zone.clanker.gort.components.GortCard
import zone.clanker.gort.components.GortDivider
import zone.clanker.gort.theme.Gort
import zone.clanker.gort.theme.GortTheme

@Composable
fun CatalogApp() {
    GortTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Gort.spacing.xl),
            verticalArrangement = Arrangement.spacedBy(Gort.spacing.lg),
        ) {
            BasicText(
                text = "GORT COMPONENT CATALOG",
                style = TextStyle(
                    color = Gort.colors.onSurface,
                    fontSize = 24.sp,
                ),
            )

            GortDivider()

            // Buttons
            BasicText(
                text = "BUTTONS",
                style = TextStyle(
                    color = Gort.colors.onSurface,
                    fontSize = 18.sp,
                ),
            )

            Row(horizontalArrangement = Arrangement.spacedBy(Gort.spacing.md)) {
                GortButton(onClick = {}, style = GortButtonStyle.Primary) {
                    BasicText(
                        "PRIMARY",
                        style = TextStyle(color = Gort.colors.onPrimary),
                    )
                }
                GortButton(onClick = {}, style = GortButtonStyle.Secondary) {
                    BasicText(
                        "SECONDARY",
                        style = TextStyle(color = Gort.colors.onSecondary),
                    )
                }
                GortButton(onClick = {}, style = GortButtonStyle.Tertiary) {
                    BasicText(
                        "TERTIARY",
                        style = TextStyle(color = Gort.colors.onTertiary),
                    )
                }
                GortButton(onClick = {}, style = GortButtonStyle.Outlined) {
                    BasicText(
                        "OUTLINED",
                        style = TextStyle(color = Gort.colors.onSurface),
                    )
                }
            }

            GortDivider()

            // Cards
            BasicText(
                text = "CARDS",
                style = TextStyle(
                    color = Gort.colors.onSurface,
                    fontSize = 18.sp,
                ),
            )

            Row(horizontalArrangement = Arrangement.spacedBy(Gort.spacing.lg)) {
                GortCard {
                    BasicText(
                        "Default card with medium shadow.",
                        style = TextStyle(color = Gort.colors.onSurface),
                    )
                }
                GortCard(color = Gort.colors.primary) {
                    BasicText(
                        "Primary colored card.",
                        style = TextStyle(color = Gort.colors.onPrimary),
                    )
                }
            }
        }
    }
}
