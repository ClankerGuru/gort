package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
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
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()

    val borderColor = when {
        isFocused -> colors.primary
        isHovered -> colors.primary.copy(alpha = 0.6f)
        else -> colors.border
    }
    val borderWidth = if (isFocused) Gort.borders.heavy else Gort.borders.thick

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Text)
            .clip(shape)
            .background(colors.surface, shape)
            .border(borderWidth, borderColor, shape)
            .padding(horizontal = Gort.spacing.md, vertical = Gort.spacing.sm),
        enabled = enabled,
        singleLine = singleLine,
        textStyle = textStyle,
        cursorBrush = SolidColor(colors.primary),
        interactionSource = interactionSource,
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
