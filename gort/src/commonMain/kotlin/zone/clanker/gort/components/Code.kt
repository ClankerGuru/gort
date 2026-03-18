package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import zone.clanker.gort.theme.Gort

@Composable
fun Code(
    code: String,
    modifier: Modifier = Modifier,
    language: String? = null,
    onCopy: ((String) -> Unit)? = null,
) {
    val colors = Gort.colors
    val spacing = Gort.spacing

    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(Gort.borders.default, colors.border, Gort.corners.small)
            .background(colors.background, Gort.corners.small),
    ) {
        if (language != null || onCopy != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.surface)
                    .padding(horizontal = spacing.sm, vertical = spacing.xs),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BasicText(
                    text = language ?: "",
                    style = Gort.typography.label.copy(color = colors.onSurface.copy(alpha = 0.6f)),
                )
                if (onCopy != null) {
                    BasicText(
                        text = "Copy",
                        style = Gort.typography.label.copy(color = colors.primary),
                        modifier = Modifier.clickable { onCopy(code) },
                    )
                }
            }
            Divider()
        }
        BasicText(
            text = code,
            style = Gort.typography.code.copy(color = colors.onSurface),
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(spacing.md),
        )
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
