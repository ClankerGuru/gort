package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

@Composable
fun TextArea(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enabled: Boolean = true,
    minLines: Int = 4,
) {
    val colors = Gort.colors
    val borders = Gort.borders
    val spacing = Gort.spacing
    val typography = Gort.typography

    Box(
        modifier = modifier
            .border(borders.default, colors.border, Gort.corners.default)
            .background(if (enabled) colors.surface else colors.background, Gort.corners.default)
            .padding(spacing.sm)
            .defaultMinSize(minHeight = (minLines * 24).dp),
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            textStyle = typography.body.copy(
                color = colors.onSurface,
                fontFamily = typography.code.fontFamily,
            ),
            cursorBrush = SolidColor(colors.primary),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    BasicText(
                        text = placeholder,
                        style = typography.body.copy(color = colors.onSurface.copy(alpha = 0.5f)),
                    )
                }
                innerTextField()
            },
        )
    }
}
