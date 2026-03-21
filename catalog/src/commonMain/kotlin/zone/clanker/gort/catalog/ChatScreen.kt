package zone.clanker.gort.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import zone.clanker.gort.components.*
import zone.clanker.gort.icons.lucide.ArrowLeft
import zone.clanker.gort.icons.lucide.Lucide
import zone.clanker.gort.icons.lucide.Menu
import zone.clanker.gort.theme.Gort

@Composable
fun ChatScreen(
    onBack: (() -> Unit)? = null,
) {
    val colors = Gort.colors
    val spacing = Gort.spacing

    var showCommandPalette by remember { mutableStateOf(false) }

    val sampleMessages = remember {
        listOf(
            ChatMessage(
                id = "1",
                content = "Hey Gort, can you explain the **neobrutalist** design system?",
                sender = "Xavier",
                timestamp = "10:42",
                isUser = true,
            ),
            ChatMessage(
                id = "2",
                content = """Neobrutalism is a design style characterized by:

- **Bold borders** and hard shadows (no blur)
- **Flat, saturated colors** — no gradients
- **Monospace typography** for labels and UI chrome
- **Chunky interactive elements** with obvious hover/press states

Here's a quick example:

```kotlin
GortTheme {
    Button(onClick = { }) {
        BasicText("Bold & Beautiful")
    }
}
```

> "We don't do subtle around here." — The Gort Gazette""",
                sender = "Gort",
                timestamp = "10:42",
                toolCalls = listOf(
                    ToolCall("search_docs", ToolCallStatus.Success, "1.2s"),
                    ToolCall("format_response", ToolCallStatus.Success, "0.3s"),
                ),
            ),
            ChatMessage(
                id = "3",
                content = "What about the component count?",
                sender = "Xavier",
                timestamp = "10:43",
                isUser = true,
            ),
            ChatMessage(
                id = "4",
                content = """We currently have **52 components** across these categories:

| Category | Count |
|----------|-------|
| Actions  | 6     |
| Inputs   | 12    |
| Display  | 10    |
| Navigation | 8   |
| Feedback | 6     |
| Compound | 5     |
| Data     | 5     |

- [x] Core components
- [x] Theme system
- [ ] Chart components
- [ ] iOS support""",
                sender = "Gort",
                timestamp = "10:43",
                toolCalls = listOf(
                    ToolCall("count_components", ToolCallStatus.Success, "0.1s"),
                ),
            ),
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top bar with back + menu
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.sm, vertical = spacing.xs),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                if (onBack != null) {
                    IconButton(onClick = onBack, size = 40.dp) {
                        Image(
                            imageVector = Lucide.ArrowLeft,
                            contentDescription = "Back",
                            colorFilter = ColorFilter.tint(colors.onSurface),
                            modifier = Modifier.size(20.dp),
                        )
                    }
                }
                IconButton(onClick = { showCommandPalette = true }, size = 40.dp) {
                    Image(
                        imageVector = Lucide.Menu,
                        contentDescription = "Menu",
                        colorFilter = ColorFilter.tint(colors.onSurface),
                        modifier = Modifier.size(20.dp),
                    )
                }
            }

            // Chat view
            ChatView(
                messages = sampleMessages,
                onSend = { /* demo only */ },
                modifier = Modifier.weight(1f),
                onBack = onBack,
            )
        }

        // Command palette overlay
        CommandPalette(
            visible = showCommandPalette,
            commands = listOf(
                CommandItem("theme", "Switch Theme", "Toggle dark/light mode"),
                CommandItem("clear", "Clear Chat", "Remove all messages"),
                CommandItem("export", "Export Chat", "Save conversation"),
            ),
            onSelect = { showCommandPalette = false },
            onDismiss = { showCommandPalette = false },
        )
    }
}
