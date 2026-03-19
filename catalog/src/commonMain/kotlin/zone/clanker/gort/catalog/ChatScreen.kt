package zone.clanker.gort.catalog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import zone.clanker.gort.components.*
import zone.clanker.gort.theme.Gort

@Composable
fun ChatScreen() {
    ShowcaseSection("Messages")

    ComponentShowcase(
        name = "ChatBubble",
        description = "Speech bubble for incoming and outgoing messages with sender name and timestamp.",
        code = """ChatBubble(
    message = "Hey, have you seen the new design system?",
    sender = "Xavier",
    timestamp = "10:42",
)
ChatBubble(
    message = "Looks incredible!",
    isOutgoing = true,
    timestamp = "10:43",
)""",
    ) {
        ChatBubble(
            message = "Hey, have you seen the new design system?",
            sender = "Xavier",
            timestamp = "10:42",
        )
        Spacer(Modifier.height(Gort.spacing.sm))
        ChatBubble(
            message = "Yes! 50 components, all neobrutalist. Looks incredible.",
            isOutgoing = true,
            timestamp = "10:43",
        )
        Spacer(Modifier.height(Gort.spacing.sm))
        ChatBubble(
            message = "The shadow-press animation on the buttons is *chef's kiss*",
            sender = "Xavier",
            timestamp = "10:43",
        )
    }

    ShowcaseSection("Input")

    ComponentShowcase(
        name = "ChatInput",
        description = "Message composition bar with text field and send button for chat interfaces.",
        code = """ChatInput(
    value = chatInput,
    onValueChange = { chatInput = it },
    onSend = { chatInput = "" },
)""",
    ) {
        var chatInput by remember { mutableStateOf("") }
        ChatInput(
            value = chatInput,
            onValueChange = { chatInput = it },
            onSend = { chatInput = "" },
        )
    }
}
