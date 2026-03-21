package zone.clanker.gort.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import zone.clanker.gort.icons.lucide.Eye
import zone.clanker.gort.icons.lucide.EyeOff
import zone.clanker.gort.icons.lucide.Lucide
import zone.clanker.gort.theme.Gort

@Composable
fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    label: String? = null,
    helperText: String? = null,
    errorText: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    isPassword: Boolean = false,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    shape: Shape = Gort.corners.default,
    onBlurValidate: ((String) -> String?)? = null,
    textStyle: TextStyle = Gort.typography.bodyMedium.copy(
        fontFamily = FontFamily.Monospace,
        color = Gort.colors.onSurface,
    ),
) {
    val colors = Gort.colors
    val spacing = Gort.spacing
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    var passwordVisible by remember { mutableStateOf(false) }
    var validationError by remember { mutableStateOf<String?>(null) }

    val displayError = errorText ?: validationError
    val hasError = displayError != null

    val borderColor = when {
        hasError -> colors.error
        isFocused -> colors.primary
        isHovered -> colors.primary.copy(alpha = 0.6f)
        else -> colors.border
    }
    val borderWidth = if (isFocused || hasError) Gort.borders.heavy else Gort.borders.thick

    Column(modifier = modifier) {
        // Label
        if (label != null) {
            BasicText(
                text = label,
                style = Gort.typography.label.copy(color = colors.onSurface),
                modifier = Modifier.padding(bottom = spacing.xs),
            )
        }

        // Input field
        BasicTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                validationError = null
            },
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 48.dp)
                .hoverable(interactionSource)
                .pointerHoverIcon(PointerIcon.Text)
                .clip(shape)
                .background(colors.surface, shape)
                .border(borderWidth, borderColor, shape)
                .padding(horizontal = spacing.md, vertical = spacing.sm),
            enabled = enabled,
            singleLine = singleLine,
            textStyle = textStyle,
            cursorBrush = SolidColor(colors.primary),
            interactionSource = interactionSource,
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (leadingIcon != null) {
                        Box(modifier = Modifier.padding(end = spacing.xs)) { leadingIcon() }
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        if (value.isEmpty()) {
                            BasicText(
                                text = placeholder,
                                style = textStyle.copy(color = colors.onSurface.copy(alpha = 0.4f)),
                            )
                        }
                        innerTextField()
                    }
                    if (isPassword) {
                        Image(
                            imageVector = if (passwordVisible) Lucide.EyeOff else Lucide.Eye,
                            contentDescription = if (passwordVisible) "Hide password" else "Show password",
                            colorFilter = ColorFilter.tint(colors.onSurface.copy(alpha = 0.5f)),
                            modifier = Modifier
                                .size(20.dp)
                                .clickable { passwordVisible = !passwordVisible },
                        )
                    }
                    if (trailingIcon != null) {
                        Box(modifier = Modifier.padding(start = spacing.xs)) { trailingIcon() }
                    }
                }
            },
        )

        // Helper or error text
        if (displayError != null) {
            BasicText(
                text = displayError,
                style = Gort.typography.labelSmall.copy(color = colors.error),
                modifier = Modifier.padding(top = spacing.xs),
            )
        } else if (helperText != null) {
            BasicText(
                text = helperText,
                style = Gort.typography.labelSmall.copy(color = colors.onSurface.copy(alpha = 0.5f)),
                modifier = Modifier.padding(top = spacing.xs),
            )
        }
    }
}
