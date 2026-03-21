package zone.clanker.gort.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import zone.clanker.gort.icons.lucide.Copy
import zone.clanker.gort.icons.lucide.Lucide
import zone.clanker.gort.icons.lucide.Play
import zone.clanker.gort.theme.Gort

@Composable
fun Code(
    code: String,
    modifier: Modifier = Modifier,
    language: String? = null,
    filePath: String? = null,
    onCopy: ((String) -> Unit)? = null,
    onAction: ((String) -> Unit)? = null,
) {
    val colors = Gort.colors
    val spacing = Gort.spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(Gort.borders.default, colors.border, Gort.corners.small)
            .background(colors.background, Gort.corners.small),
    ) {
        // Dark header bar
        if (language != null || filePath != null || onCopy != null || onAction != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.shadow.copy(alpha = 0.9f))
                    .padding(horizontal = spacing.sm, vertical = spacing.xs),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // Left side: language label or file path
                Column {
                    if (filePath != null) {
                        BasicText(
                            text = filePath,
                            style = Gort.typography.labelSmall.copy(color = colors.onPrimary.copy(alpha = 0.7f)),
                        )
                    }
                    if (language != null) {
                        BasicText(
                            text = language,
                            style = Gort.typography.label.copy(color = colors.onPrimary.copy(alpha = 0.8f)),
                        )
                    }
                }

                // Right side: action icons
                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (onAction != null) {
                        Image(
                            imageVector = Lucide.Play,
                            contentDescription = "Run",
                            colorFilter = ColorFilter.tint(colors.onPrimary.copy(alpha = 0.8f)),
                            modifier = Modifier
                                .size(16.dp)
                                .clickable { onAction(code) },
                        )
                    }
                    if (onCopy != null) {
                        Image(
                            imageVector = Lucide.Copy,
                            contentDescription = "Copy",
                            colorFilter = ColorFilter.tint(colors.onPrimary.copy(alpha = 0.8f)),
                            modifier = Modifier
                                .size(16.dp)
                                .clickable { onCopy(code) },
                        )
                    }
                }
            }
        }

        // Code body wrapped in SelectionContainer
        SelectionContainer {
            BasicText(
                text = code,
                style = Gort.typography.code.copy(color = colors.onSurface),
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(spacing.md),
            )
        }
    }
}

@Composable
fun InlineCode(
    text: String,
    modifier: Modifier = Modifier,
) {
    val colors = Gort.colors
    BasicText(
        text = text,
        style = Gort.typography.code.copy(color = colors.primary),
        modifier = modifier
            .background(colors.background, Gort.corners.small)
            .border(Gort.borders.thin, colors.border, Gort.corners.small)
            .padding(horizontal = Gort.spacing.xs),
    )
}
