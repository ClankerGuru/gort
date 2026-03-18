package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import zone.clanker.gort.foundation.Surface
import zone.clanker.gort.theme.Gort

data class CommandItem(
    val id: String,
    val title: String,
    val subtitle: String = "",
    val shortcut: String = "",
)

@Composable
fun CommandPalette(
    visible: Boolean,
    commands: List<CommandItem>,
    onSelect: (CommandItem) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Type a command…",
) {
    if (!visible) return

    val colors = Gort.colors
    val spacing = Gort.spacing
    var query by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    val filtered = remember(query, commands) {
        if (query.isBlank()) commands
        else commands.filter {
            it.title.contains(query, ignoreCase = true) ||
                it.subtitle.contains(query, ignoreCase = true)
        }
    }

    LaunchedEffect(visible) {
        if (visible) focusRequester.requestFocus()
    }

    // Backdrop
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.shadow.copy(alpha = 0.5f))
            .clickable(onClick = onDismiss),
        contentAlignment = Alignment.TopCenter,
    ) {
        Surface(
            modifier = modifier
                .padding(top = 80.dp)
                .widthIn(max = 500.dp)
                .fillMaxWidth(0.9f)
                .clickable(enabled = false) {}, // prevent backdrop click-through
            color = colors.surface,
            borderColor = colors.border,
            shadow = Gort.shadows.large,
        ) {
            Column {
                // Search input
                BasicTextField(
                    value = query,
                    onValueChange = { query = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester)
                        .padding(spacing.md),
                    textStyle = Gort.typography.body.copy(color = colors.onSurface),
                    cursorBrush = SolidColor(colors.primary),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        if (query.isEmpty()) {
                            BasicText(
                                text = placeholder,
                                style = Gort.typography.body.copy(color = colors.onSurface.copy(alpha = 0.4f)),
                            )
                        }
                        innerTextField()
                    },
                )

                Divider()

                // Results
                LazyColumn(
                    modifier = Modifier.heightIn(max = 400.dp),
                ) {
                    items(filtered) { cmd ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onSelect(cmd)
                                    query = ""
                                }
                                .padding(horizontal = spacing.md, vertical = spacing.sm),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                BasicText(
                                    text = cmd.title,
                                    style = Gort.typography.body.copy(color = colors.onSurface),
                                )
                                if (cmd.subtitle.isNotEmpty()) {
                                    BasicText(
                                        text = cmd.subtitle,
                                        style = Gort.typography.label.copy(color = colors.onSurface.copy(alpha = 0.5f)),
                                    )
                                }
                            }
                            if (cmd.shortcut.isNotEmpty()) {
                                BasicText(
                                    text = cmd.shortcut,
                                    style = Gort.typography.code.copy(color = colors.onSurface.copy(alpha = 0.4f)),
                                )
                            }
                        }
                    }

                    if (filtered.isEmpty()) {
                        item {
                            BasicText(
                                text = "No results",
                                style = Gort.typography.body.copy(color = colors.onSurface.copy(alpha = 0.4f)),
                                modifier = Modifier.padding(spacing.md),
                            )
                        }
                    }
                }
            }
        }
    }
}
