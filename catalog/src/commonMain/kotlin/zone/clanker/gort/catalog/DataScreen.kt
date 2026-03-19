package zone.clanker.gort.catalog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import zone.clanker.gort.components.*
import zone.clanker.gort.theme.Gort

@Composable
fun DataScreen() {
    ShowcaseSection("Tables")

    ComponentShowcase(
        name = "Table",
        description = "Structured data grid with sortable columns and bordered rows.",
        code = """Table(
    columns = listOf(TableColumn("Name", width = 150.dp, sortable = true), …),
    rows = listOf(listOf("Xavier", "Owner", "Active", "2 min ago"), …),
)""",
    ) {
        Table(
            columns = listOf(
                TableColumn("Name", width = 150.dp, sortable = true),
                TableColumn("Role", width = 120.dp),
                TableColumn("Status", width = 100.dp),
                TableColumn("Last Active", width = 140.dp, sortable = true),
            ),
            rows = listOf(
                listOf("Xavier", "Owner", "Active", "2 min ago"),
                listOf("Gort", "Robot", "Online", "Just now"),
                listOf("Klaatu", "Diplomat", "Away", "1951"),
                listOf("HAL 9000", "AI", "Error", "2001"),
                listOf("TARS", "Robot", "Active", "Yesterday"),
            ),
        )
    }

    ShowcaseSection("Lists")

    ComponentShowcase(
        name = "ListGroup",
        description = "Vertical list of interactive items with titles, subtitles, and trailing elements.",
        code = """ListGroup {
    ListItem(title = "Settings", subtitle = "Manage preferences", onClick = {})
}""",
    ) {
        ListGroup {
            ListItem(
                title = "Settings",
                subtitle = "Manage your preferences",
                trailing = { BasicText("→", style = Gort.typography.body.copy(color = Gort.colors.onSurface)) },
                onClick = {},
            )
            Divider()
            ListItem(
                title = "Notifications",
                subtitle = "3 unread",
                trailing = { Badge(text = "3") },
                onClick = {},
            )
            Divider()
            ListItem(
                title = "Account",
                subtitle = "xavier@clanker.zone",
                onClick = {},
            )
        }
    }

    ShowcaseSection("Timeline")

    ComponentShowcase(
        name = "Timeline",
        description = "Vertical event sequence for changelogs, activity feeds, or process histories.",
        code = """Timeline(
    items = listOf(
        TimelineItem("Project created", "Initial commit", "Mar 15", color = Gort.colors.success),
        …
    ),
)""",
    ) {
        Timeline(
            items = listOf(
                TimelineItem("Project created", "Initial commit", "Mar 15", color = Gort.colors.success),
                TimelineItem("First PR merged", "Added core components", "Mar 16", color = Gort.colors.primary),
                TimelineItem("v0.1.0 released", "First public release", "Mar 17", color = Gort.colors.accent),
                TimelineItem("50 components", "Full design system", "Mar 18", color = Gort.colors.warning),
            ),
        )
    }
}
