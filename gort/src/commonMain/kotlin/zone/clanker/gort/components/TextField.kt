package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import zone.clanker.gort.theme.Gort

@Composable
fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enabled: Boolean = true,
    singleLine: Boolean = true,
    shape: Shape = Gort.corners.default,
    textStyle: TextStyle = Gort.typography.bodyMedium.copy(
        fontFamily = FontFamily.Monospace,
        color = Gort.colors.onSurface,
    ),
) {
    val colors = Gort.colors

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(colors.surface, shape)
            .border(Gort.borders.thick, colors.border, shape)
            .padding(horizontal = Gort.spacing.md, vertical = Gort.spacing.sm),
        enabled = enabled,
        singleLine = singleLine,
        textStyle = textStyle,
        cursorBrush = SolidColor(colors.primary),
        decorationBox = { innerTextField ->
            Box {
                if (value.isEmpty()) {
                    BasicText(
                        text = placeholder,
                        style = textStyle.copy(color = colors.onSurface.copy(alpha = 0.4f)),
                    )
                }
                innerTextField()
            }
        },
    )
}

@Composable
fun TextArea(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    enabled: Boolean = true,
    minLines: Int = 3,
    shape: Shape = Gort.corners.default,
    textStyle: TextStyle = Gort.typography.bodyMedium.copy(
        fontFamily = FontFamily.Monospace,
        color = Gort.colors.onSurface,
    ),
) {
    val colors = Gort.colors

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(colors.surface, shape)
            .border(Gort.borders.thick, colors.border, shape)
            .padding(Gort.spacing.md),
        enabled = enabled,
        singleLine = false,
        minLines = minLines,
        textStyle = textStyle,
        cursorBrush = SolidColor(colors.primary),
        decorationBox = { innerTextField ->
            Box {
                if (value.isEmpty()) {
                    BasicText(
                        text = placeholder,
                        style = textStyle.copy(color = colors.onSurface.copy(alpha = 0.4f)),
                    )
                }
                innerTextField()
            }
        },
    )
}
