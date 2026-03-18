package zone.clanker.gort.catalog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import zone.clanker.gort.components.*
import zone.clanker.gort.theme.Gort

@Composable
fun ChatScreen() {
    SectionTitle("Chat Bubbles")

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

    SectionTitle("Chat Input")
    var chatInput by remember { mutableStateOf("") }
    ChatInput(
        value = chatInput,
        onValueChange = { chatInput = it },
        onSend = { chatInput = "" },
    )
}
