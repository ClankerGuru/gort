package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import zone.clanker.gort.theme.Gort

enum class CalloutType { Info, Tip, Warning, Danger }

@Composable
fun Callout(
    modifier: Modifier = Modifier,
    type: CalloutType = CalloutType.Info,
    title: String? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = Gort.colors
    val accentColor = when (type) {
        CalloutType.Info -> colors.accent
        CalloutType.Tip -> colors.success
        CalloutType.Warning -> colors.warning
        CalloutType.Danger -> colors.error
    }
    val bg = when (type) {
        CalloutType.Info -> colors.primaryContainer
        CalloutType.Tip -> colors.successContainer
        CalloutType.Warning -> colors.warningContainer
        CalloutType.Danger -> colors.errorContainer
    }
    val borderWidth = Gort.borders.heavy

    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(Gort.borders.thin, colors.border)
            .background(bg)
            .drawBehind {
                drawRect(
                    color = accentColor,
                    topLeft = Offset.Zero,
                    size = Size(borderWidth.toPx(), size.height),
                )
            }
            .padding(start = Gort.spacing.lg, top = Gort.spacing.md, end = Gort.spacing.md, bottom = Gort.spacing.md),
    ) {
        if (title != null) {
            BasicText(
                text = title,
                style = Gort.typography.title.copy(color = colors.onSurface),
                modifier = Modifier.padding(bottom = Gort.spacing.xs),
            )
        }
        content()
    }
}
