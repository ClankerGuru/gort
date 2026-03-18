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
import zone.clanker.gort.components.Button
import zone.clanker.gort.components.ButtonStyle
import zone.clanker.gort.components.Card
import zone.clanker.gort.components.Divider
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

            Divider()

            BasicText(
                text = "BUTTONS",
                style = TextStyle(
                    color = Gort.colors.onSurface,
                    fontSize = 18.sp,
                ),
            )

            Row(horizontalArrangement = Arrangement.spacedBy(Gort.spacing.md)) {
                Button(onClick = {}, style = ButtonStyle.Primary) {
                    BasicText(
                        "PRIMARY",
                        style = TextStyle(color = Gort.colors.onPrimary),
                    )
                }
                Button(onClick = {}, style = ButtonStyle.Secondary) {
                    BasicText(
                        "SECONDARY",
                        style = TextStyle(color = Gort.colors.onSecondary),
                    )
                }
                Button(onClick = {}, style = ButtonStyle.Tertiary) {
                    BasicText(
                        "TERTIARY",
                        style = TextStyle(color = Gort.colors.onTertiary),
                    )
                }
                Button(onClick = {}, style = ButtonStyle.Outlined) {
                    BasicText(
                        "OUTLINED",
                        style = TextStyle(color = Gort.colors.onSurface),
                    )
                }
            }

            Divider()

            BasicText(
                text = "CARDS",
                style = TextStyle(
                    color = Gort.colors.onSurface,
                    fontSize = 18.sp,
                ),
            )

            Row(horizontalArrangement = Arrangement.spacedBy(Gort.spacing.lg)) {
                Card {
                    BasicText(
                        "Default card with medium shadow.",
                        style = TextStyle(color = Gort.colors.onSurface),
                    )
                }
                Card(color = Gort.colors.primary) {
                    BasicText(
                        "Primary colored card.",
                        style = TextStyle(color = Gort.colors.onPrimary),
                    )
                }
            }
        }
    }
}
