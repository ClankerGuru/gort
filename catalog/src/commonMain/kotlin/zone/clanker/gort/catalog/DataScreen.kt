package zone.clanker.gort.catalog

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import zone.clanker.gort.components.*
import zone.clanker.gort.theme.Gort

@Composable
fun DataScreen() {
    var fullscreenContent by remember { mutableStateOf<(@Composable () -> Unit)?>(null) }

    ShowcaseSection("Tables")

    ComponentShowcase(
        name = "Table — Expandable",
        description = "Data grid with sort, expand/collapse (max 7 rows), and control drop.",
        code = """Table(
    columns = listOf(TableColumn("Name", 150.dp, sortable = true), …),
    rows = rows,
    sortable = true,
    onExpand = { /* fullscreen */ },
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
                listOf("R2-D2", "Droid", "Active", "A long time ago"),
                listOf("Data", "Android", "Active", "2364"),
                listOf("Sonny", "NS-5", "Active", "2035"),
                listOf("Wall-E", "Compactor", "Active", "2805"),
                listOf("Baymax", "Healthcare", "Active", "Today"),
            ),
            sortable = true,
            maxVisibleRows = 7,
            onExpand = {
                fullscreenContent = {
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
                            listOf("R2-D2", "Droid", "Active", "A long time ago"),
                            listOf("Data", "Android", "Active", "2364"),
                            listOf("Sonny", "NS-5", "Active", "2035"),
                            listOf("Wall-E", "Compactor", "Active", "2805"),
                            listOf("Baymax", "Healthcare", "Active", "Today"),
                        ),
                        sortable = true,
                        maxVisibleRows = 100,
                    )
                }
            },
        )
    }

    ComponentShowcase(
        name = "Table — Paginated",
        description = "Large dataset with page controls in the shadow drop.",
        code = """Table(columns = …, rows = …, paginated = true, pageSize = 5)""",
    ) {
        Table(
            columns = listOf(
                TableColumn("ID", width = 60.dp),
                TableColumn("Item", width = 150.dp),
                TableColumn("Qty", width = 80.dp),
                TableColumn("Price", width = 100.dp),
            ),
            rows = (1..25).map { listOf("#$it", "Item $it", "${it * 3}", "$${it * 9}.99") },
            paginated = true,
            pageSize = 5,
        )
    }

    ComponentShowcase(
        name = "Table — Wide (30 columns)",
        description = "Horizontally scrollable table with many columns.",
        code = """Table(columns = (1..30).map { TableColumn("Col ${'$'}it") }, rows = …)""",
    ) {
        Table(
            columns = (1..30).map { TableColumn("Col $it", width = 100.dp) },
            rows = (1..5).map { row -> (1..30).map { col -> "R${row}C$col" } },
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
        name = "Timeline — Vertical",
        description = "Vertical event sequence with direction toggle and expand.",
        code = """Timeline(items = listOf(…), maxItems = 7, onExpand = { })""",
    ) {
        Timeline(
            items = listOf(
                TimelineItem("Project created", "Initial commit", "Mar 15", color = Gort.colors.success),
                TimelineItem("First PR merged", "Added core components", "Mar 16", color = Gort.colors.primary),
                TimelineItem("v0.1.0 released", "First public release", "Mar 17", color = Gort.colors.accent),
                TimelineItem("50 components", "Full design system", "Mar 18", color = Gort.colors.warning),
                TimelineItem("Catalog app", "Interactive showcase", "Mar 19", color = Gort.colors.tertiary),
                TimelineItem("Hover states", "Focus rings & animations", "Mar 20", color = Gort.colors.primary),
                TimelineItem("Chat system", "Full chat interface", "Mar 21", color = Gort.colors.success),
                TimelineItem("v1.0.0", "Stable release", "Mar 22", color = Gort.colors.error),
            ),
            maxItems = 5,
            onExpand = {
                fullscreenContent = {
                    Timeline(
                        items = listOf(
                            TimelineItem("Project created", "Initial commit", "Mar 15", color = Gort.colors.success),
                            TimelineItem("First PR merged", "Added core components", "Mar 16", color = Gort.colors.primary),
                            TimelineItem("v0.1.0 released", "First public release", "Mar 17", color = Gort.colors.accent),
                            TimelineItem("50 components", "Full design system", "Mar 18", color = Gort.colors.warning),
                            TimelineItem("Catalog app", "Interactive showcase", "Mar 19", color = Gort.colors.tertiary),
                            TimelineItem("Hover states", "Focus rings & animations", "Mar 20", color = Gort.colors.primary),
                            TimelineItem("Chat system", "Full chat interface", "Mar 21", color = Gort.colors.success),
                            TimelineItem("v1.0.0", "Stable release", "Mar 22", color = Gort.colors.error),
                        ),
                        maxItems = 100,
                    )
                }
            },
        )
    }

    ComponentShowcase(
        name = "Timeline — Horizontal",
        description = "Horizontal mode with selectable nodes and detail card below.",
        code = """Timeline(items = listOf(…), horizontal = true)""",
    ) {
        Timeline(
            items = listOf(
                TimelineItem("Q1", "Planning", color = Gort.colors.primary),
                TimelineItem("Q2", "Development", color = Gort.colors.accent),
                TimelineItem("Q3", "Testing", color = Gort.colors.warning),
                TimelineItem("Q4", "Launch", color = Gort.colors.success),
            ),
            horizontal = true,
        )
    }

    // Fullscreen overlay
    if (fullscreenContent != null) {
        FullscreenOverlay(onDismiss = { fullscreenContent = null }) {
            fullscreenContent?.invoke()
        }
    }
}
