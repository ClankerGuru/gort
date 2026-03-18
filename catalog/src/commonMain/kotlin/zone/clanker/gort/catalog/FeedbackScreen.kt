package zone.clanker.gort.catalog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import zone.clanker.gort.components.*
import zone.clanker.gort.theme.Gort

@Composable
fun FeedbackScreen() {
    SectionTitle("Banner")
    Banner(message = "System update available.", severity = BannerSeverity.Info, onDismiss = {})
    Banner(message = "Changes saved successfully!", severity = BannerSeverity.Success)
    Banner(message = "Your trial expires in 3 days.", severity = BannerSeverity.Warning)
    Banner(message = "Failed to connect to server.", severity = BannerSeverity.Error, onDismiss = {})

    SectionTitle("Notification")
    Notification(
        title = "New message",
        message = "You have 3 unread messages from the team.",
        severity = BannerSeverity.Info,
        onDismiss = {},
        actions = {
            Button(onClick = {}, variant = ButtonVariant.Outline) {
                BasicText("View", style = Gort.typography.label.copy(color = Gort.colors.onSurface))
            }
            Button(onClick = {}, variant = ButtonVariant.Primary) {
                BasicText("Reply", style = Gort.typography.label.copy(color = Gort.colors.onPrimary))
            }
        },
    )

    SectionTitle("Toast")
    var showToast by remember { mutableStateOf(false) }
    Button(onClick = { showToast = true }) {
        BasicText("Show Toast", style = Gort.typography.label.copy(color = Gort.colors.onPrimary))
    }
    Toast(
        message = "Item saved successfully!",
        visible = showToast,
        severity = BannerSeverity.Success,
        onDismiss = { showToast = false },
    )

    SectionTitle("Snackbar")
    GortSnackbar(message = "3 items deleted.")

    SectionTitle("Dialog")
    var showDialog by remember { mutableStateOf(false) }
    Button(onClick = { showDialog = true }) {
        BasicText("Open Dialog", style = Gort.typography.label.copy(color = Gort.colors.onPrimary))
    }
    if (showDialog) {
        GortDialog(onDismiss = { showDialog = false }) {
            Column(modifier = Modifier.padding(Gort.spacing.lg)) {
                BasicText("Confirm Action", style = Gort.typography.headline.copy(color = Gort.colors.onSurface))
                Spacer(Modifier.height(Gort.spacing.sm))
                BasicText("Are you sure you want to proceed?", style = Gort.typography.body.copy(color = Gort.colors.onSurface))
                Spacer(Modifier.height(Gort.spacing.md))
                Row(horizontalArrangement = Arrangement.spacedBy(Gort.spacing.sm)) {
                    Button(onClick = { showDialog = false }, variant = ButtonVariant.Outline) {
                        BasicText("Cancel", style = Gort.typography.label.copy(color = Gort.colors.onSurface))
                    }
                    Button(onClick = { showDialog = false }, variant = ButtonVariant.Danger) {
                        BasicText("Delete", style = Gort.typography.label.copy(color = Gort.colors.onError))
                    }
                }
            }
        }
    }

    SectionTitle("Tooltip")
    GortTooltip(text = "This is a helpful tooltip")
    BasicText("Hover over me", style = Gort.typography.body.copy(color = Gort.colors.primary))
}
