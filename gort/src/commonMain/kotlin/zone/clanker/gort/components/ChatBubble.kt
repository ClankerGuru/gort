package zone.clanker.gort.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import zone.clanker.gort.foundation.Surface
import zone.clanker.gort.theme.Gort

@Composable
fun ChatBubble(
    message: String,
    modifier: Modifier = Modifier,
    isOutgoing: Boolean = false,
    sender: String? = null,
    timestamp: String? = null,
    avatar: (@Composable () -> Unit)? = null,
) {
    val colors = Gort.colors
    val spacing = Gort.spacing

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = if (isOutgoing) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Bottom,
    ) {
        if (!isOutgoing && avatar != null) {
            Box(modifier = Modifier.padding(end = spacing.sm)) { avatar() }
        }

        Surface(
            color = if (isOutgoing) colors.primary else colors.surface,
            borderColor = colors.border,
            shadow = Gort.shadows.small,
            modifier = Modifier.widthIn(max = Gort.spacing.xxl * 8),
        ) {
            Column(modifier = Modifier.padding(spacing.sm)) {
                if (sender != null && !isOutgoing) {
                    BasicText(
                        text = sender,
                        style = Gort.typography.label.copy(color = colors.primary),
                        modifier = Modifier.padding(bottom = spacing.xs),
                    )
                }
                BasicText(
                    text = message,
                    style = Gort.typography.body.copy(
                        color = if (isOutgoing) colors.onPrimary else colors.onSurface,
                    ),
                )
                if (timestamp != null) {
                    BasicText(
                        text = timestamp,
                        style = Gort.typography.label.copy(
                            color = (if (isOutgoing) colors.onPrimary else colors.onSurface).copy(alpha = 0.5f),
                        ),
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(top = spacing.xs),
                    )
                }
            }
        }

        if (isOutgoing && avatar != null) {
            Box(modifier = Modifier.padding(start = spacing.sm)) { avatar() }
        }
    }
}

@Composable
fun ChatInput(
    value: String,
    onValueChange: (String) -> Unit,
    onSend: () -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Type a message…",
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(Gort.spacing.sm),
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            placeholder = placeholder,
        )
        Button(
            onClick = onSend,
            enabled = value.isNotBlank(),
        ) {
            BasicText(
                text = "Send",
                style = Gort.typography.label.copy(color = Gort.colors.onPrimary),
            )
        }
    }
}
