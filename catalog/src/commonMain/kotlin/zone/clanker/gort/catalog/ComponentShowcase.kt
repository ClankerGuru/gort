package zone.clanker.gort.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zone.clanker.gort.components.Card
import zone.clanker.gort.components.Code
import zone.clanker.gort.theme.Gort

@Composable
fun ComponentShowcase(
    name: String,
    description: String,
    code: String = "",
    controls: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val colors = Gort.colors

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Headline
            BasicText(
                text = name,
                style = Gort.typography.titleLarge.copy(
                    color = colors.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                ),
            )
            Spacer(Modifier.height(4.dp))
            BasicText(
                text = description,
                style = Gort.typography.bodyMedium.copy(
                    color = colors.onSurface.copy(alpha = 0.6f),
                ),
            )

            Spacer(Modifier.height(Gort.spacing.md))

            // Live demo area
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.background.copy(alpha = 0.6f), Gort.corners.small)
                    .padding(Gort.spacing.md),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(Gort.spacing.sm),
                ) {
                    content()
                }
            }

            // Controls
            if (controls != null) {
                Spacer(Modifier.height(Gort.spacing.sm))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Gort.spacing.sm),
                ) {
                    controls()
                }
            }

            // Expandable code section
            if (code.isNotBlank()) {
                Spacer(Modifier.height(Gort.spacing.sm))
                var expanded by remember { mutableStateOf(false) }
                BasicText(
                    text = if (expanded) "▾ Hide Code" else "▸ View Code",
                    style = Gort.typography.labelMedium.copy(color = colors.primary),
                    modifier = Modifier.clickable { expanded = !expanded }.padding(vertical = 4.dp),
                )
                if (expanded) {
                    Spacer(Modifier.height(4.dp))
                    Code(code = code, language = "kotlin")
                }
            }
        }
    }
}

@Composable
fun ShowcaseSection(title: String) {
    val colors = Gort.colors
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(Modifier.height(Gort.spacing.lg))
        Box(
            modifier = Modifier.fillMaxWidth().height(1.dp).background(colors.border.copy(alpha = 0.3f)),
        )
        Spacer(Modifier.height(Gort.spacing.sm))
        BasicText(
            text = title.uppercase(),
            style = Gort.typography.labelLarge.copy(
                color = colors.onSurface.copy(alpha = 0.5f),
                letterSpacing = 3.sp,
                fontWeight = FontWeight.Bold,
            ),
        )
        Spacer(Modifier.height(Gort.spacing.sm))
        Box(
            modifier = Modifier.fillMaxWidth().height(1.dp).background(colors.border.copy(alpha = 0.3f)),
        )
        Spacer(Modifier.height(Gort.spacing.md))
    }
}
