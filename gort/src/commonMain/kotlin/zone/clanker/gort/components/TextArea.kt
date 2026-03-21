package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
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
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()

    val borderColor = when {
        isFocused -> colors.primary
        isHovered -> colors.primary.copy(alpha = 0.6f)
        else -> colors.border
    }
    val borderWidth = if (isFocused) borders.heavy else borders.default

    Box(
        modifier = modifier
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Text)
            .border(borderWidth, borderColor, Gort.corners.default)
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
            interactionSource = interactionSource,
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
