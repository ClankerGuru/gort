package zone.clanker.gort.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import zone.clanker.gort.theme.Gort

@Composable
fun FormField(
    label: String,
    modifier: Modifier = Modifier,
    helper: String? = null,
    error: String? = null,
    required: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colors = Gort.colors
    val spacing = Gort.spacing

    Column(modifier = modifier.fillMaxWidth()) {
        BasicText(
            text = if (required) "$label *" else label,
            style = Gort.typography.label.copy(color = colors.onSurface),
            modifier = Modifier.padding(bottom = spacing.xs),
        )
        content()
        if (error != null) {
            BasicText(
                text = error,
                style = Gort.typography.label.copy(color = colors.error),
                modifier = Modifier.padding(top = spacing.xs),
            )
        } else if (helper != null) {
            BasicText(
                text = helper,
                style = Gort.typography.label.copy(color = colors.onSurface.copy(alpha = 0.5f)),
                modifier = Modifier.padding(top = spacing.xs),
            )
        }
    }
}

@Composable
fun Form(
    modifier: Modifier = Modifier,
    spacing: androidx.compose.ui.unit.Dp = Gort.spacing.md,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(spacing),
        content = content,
    )
}
