package zone.clanker.gort.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import zone.clanker.gort.foundation.Surface
import zone.clanker.gort.icons.lucide.*
import zone.clanker.gort.theme.Gort

// ============================================================
// Data models
// ============================================================

data class ToolCallStep(
    val title: String,
    val status: ToolCallStatus = ToolCallStatus.Pending,
)

enum class ToolCallStatus { Pending, Running, Success, Error }

data class ToolCall(
    val name: String,
    val status: ToolCallStatus = ToolCallStatus.Success,
    val duration: String = "",
    val steps: List<ToolCallStep> = emptyList(),
)

data class ChatMessage(
    val id: String,
    val content: String,
    val sender: String = "",
    val timestamp: String = "",
    val isUser: Boolean = false,
    val toolCalls: List<ToolCall> = emptyList(),
)

// ============================================================
// ChatView — full chat interface
// ============================================================

@Composable
fun ChatView(
    messages: List<ChatMessage>,
    onSend: (String) -> Unit,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null,
) {
    val colors = Gort.colors
    val spacing = Gort.spacing
    val listState = rememberLazyListState()

    Column(modifier = modifier.fillMaxSize()) {
        // Messages
        LazyColumn(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            state = listState,
            contentPadding = PaddingValues(horizontal = spacing.md, vertical = spacing.sm),
            verticalArrangement = Arrangement.spacedBy(spacing.sm),
        ) {
            items(messages, key = { it.id }) { message ->
                ChatMessageBubble(message = message)
            }
        }

        // Input bar
        Divider()
        OmniInputBar(
            onSend = onSend,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

// ============================================================
// ChatMessageBubble — single message
// ============================================================

@Composable
fun ChatMessageBubble(
    message: ChatMessage,
    modifier: Modifier = Modifier,
    onCopy: ((String) -> Unit)? = null,
) {
    val colors = Gort.colors
    val spacing = Gort.spacing

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = if (message.isUser) Alignment.End else Alignment.Start,
    ) {
        // Sender name
        if (message.sender.isNotEmpty() && !message.isUser) {
            BasicText(
                text = message.sender,
                style = Gort.typography.label.copy(color = colors.primary),
                modifier = Modifier.padding(bottom = 2.dp),
            )
        }

        // Message content — transparent background, max 800dp
        Box(modifier = Modifier.widthIn(max = 800.dp)) {
            Column {
                MarkdownRenderer(
                    markdown = message.content,
                    onCopy = onCopy,
                )

                // Tool calls — compact inline timeline
                if (message.toolCalls.isNotEmpty()) {
                    Spacer(Modifier.height(spacing.xs))
                    ToolCallsInline(message.toolCalls)
                }
            }
        }

        // Timestamp
        if (message.timestamp.isNotEmpty()) {
            BasicText(
                text = message.timestamp,
                style = Gort.typography.labelSmall.copy(
                    color = colors.onSurface.copy(alpha = 0.4f),
                ),
                modifier = Modifier.padding(top = 2.dp),
            )
        }
    }
}

@Composable
private fun ToolCallsInline(toolCalls: List<ToolCall>) {
    val colors = Gort.colors
    val spacing = Gort.spacing

    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        toolCalls.forEach { tc ->
            val statusColor = when (tc.status) {
                ToolCallStatus.Success -> colors.success
                ToolCallStatus.Error -> colors.error
                ToolCallStatus.Running -> colors.accent
                ToolCallStatus.Pending -> colors.onSurface.copy(alpha = 0.4f)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 1.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(statusColor, Gort.corners.small),
                )
                Spacer(Modifier.width(spacing.xs))
                BasicText(
                    text = tc.name,
                    style = Gort.typography.labelSmall.copy(color = colors.onSurface),
                )
                if (tc.duration.isNotEmpty()) {
                    BasicText(
                        text = " ${tc.duration}",
                        style = Gort.typography.labelSmall.copy(color = colors.onSurface.copy(alpha = 0.4f)),
                    )
                }
            }
        }
    }
}

// ============================================================
// OmniInputBar — multi-line input with mode detection
// ============================================================

enum class InputMode(val prefix: String, val label: String, val color: Color) {
    Chat("", "Chat", Color.Transparent),
    Command("/", "Command", Color(0xFF9B59B6)),
    File("@", "File", Color(0xFF3498DB)),
    Shell("!", "Shell", Color(0xFFE67E22)),
    Search("?", "Search", Color(0xFF1ABC9C)),
}

@Composable
fun OmniInputBar(
    onSend: (String) -> Unit,
    modifier: Modifier = Modifier,
    onModeChange: ((InputMode) -> Unit)? = null,
) {
    val colors = Gort.colors
    val spacing = Gort.spacing
    val haptic = LocalHapticFeedback.current

    var text by remember { mutableStateOf("") }
    val mode = remember(text) {
        when {
            text.startsWith("/") -> InputMode.Command
            text.startsWith("@") -> InputMode.File
            text.startsWith("!") -> InputMode.Shell
            text.startsWith("?") -> InputMode.Search
            else -> InputMode.Chat
        }
    }

    LaunchedEffect(mode) { onModeChange?.invoke(mode) }

    Row(
        modifier = modifier
            .background(colors.surface)
            .padding(spacing.sm),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        // Mode indicator
        if (mode != InputMode.Chat) {
            Box(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .background(mode.color.copy(alpha = 0.2f), Gort.corners.small)
                    .padding(horizontal = spacing.xs, vertical = 2.dp),
            ) {
                BasicText(
                    text = mode.label,
                    style = Gort.typography.labelSmall.copy(color = mode.color),
                )
            }
        }

        // Text input
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .weight(1f)
                .defaultMinSize(minHeight = 48.dp)
                .border(Gort.borders.default, colors.border, Gort.corners.default)
                .background(colors.surface, Gort.corners.default)
                .padding(spacing.sm),
            textStyle = Gort.typography.body.copy(
                fontFamily = FontFamily.Monospace,
                color = colors.onSurface,
            ),
            cursorBrush = SolidColor(colors.primary),
            decorationBox = { innerTextField ->
                Box {
                    if (text.isEmpty()) {
                        BasicText(
                            text = "Type a message…",
                            style = Gort.typography.body.copy(color = colors.onSurface.copy(alpha = 0.4f)),
                        )
                    }
                    innerTextField()
                }
            },
        )

        // Send button — bordered square, 48dp
        Surface(
            modifier = Modifier
                .size(48.dp)
                .clickable(enabled = text.isNotBlank()) {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onSend(text)
                    text = ""
                },
            color = if (text.isNotBlank()) colors.primary else colors.surface,
            borderColor = colors.border,
            shadow = Gort.shadows.small,
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Image(
                    imageVector = Lucide.Send,
                    contentDescription = "Send",
                    colorFilter = ColorFilter.tint(
                        if (text.isNotBlank()) colors.onPrimary else colors.onSurface.copy(alpha = 0.3f),
                    ),
                    modifier = Modifier.size(20.dp),
                )
            }
        }
    }
}

// ============================================================
// Legacy compat wrappers
// ============================================================

@Composable
fun ChatBubble(
    message: String,
    modifier: Modifier = Modifier,
    isOutgoing: Boolean = false,
    sender: String? = null,
    timestamp: String? = null,
    avatar: (@Composable () -> Unit)? = null,
) {
    ChatMessageBubble(
        message = ChatMessage(
            id = message.hashCode().toString(),
            content = message,
            sender = sender ?: "",
            timestamp = timestamp ?: "",
            isUser = isOutgoing,
        ),
        modifier = modifier,
    )
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
