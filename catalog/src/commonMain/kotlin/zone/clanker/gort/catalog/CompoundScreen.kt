package zone.clanker.gort.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import zone.clanker.gort.components.*
import zone.clanker.gort.theme.Gort

@Composable
fun CompoundScreen() {
    ShowcaseSection("Date & Time")

    ComponentShowcase(
        name = "DatePicker",
        description = "Calendar-based date selector with month navigation and day grid.",
        code = """DatePicker(
    selectedDate = selectedDate,
    onDateSelect = { selectedDate = it },
)""",
    ) {
        var selectedDate by remember { mutableStateOf<GortDate?>(null) }
        DatePicker(
            selectedDate = selectedDate,
            onDateSelect = { selectedDate = it },
        )
        if (selectedDate != null) {
            BasicText(
                "Selected: ${selectedDate!!.day}/${selectedDate!!.month}/${selectedDate!!.year}",
                style = Gort.typography.body.copy(color = Gort.colors.onSurface),
            )
        }
    }

    ComponentShowcase(
        name = "TimePicker",
        description = "Hour and minute selector for scheduling and time-based inputs.",
        code = """TimePicker(selectedTime = time, onTimeChange = { time = it })""",
    ) {
        var time by remember { mutableStateOf(GortTime(14, 30)) }
        TimePicker(selectedTime = time, onTimeChange = { time = it })
        BasicText(
            "Selected: ${time.hour.toString().padStart(2, '0')}:${time.minute.toString().padStart(2, '0')}",
            style = Gort.typography.body.copy(color = Gort.colors.onSurface),
        )
    }

    ShowcaseSection("Selection")

    ComponentShowcase(
        name = "Dropdown",
        description = "Expandable single-select menu for choosing from a list of options.",
        code = """GortDropdown(
    items = listOf("Kotlin", "Java", "Scala"),
    selectedItem = value,
    onItemSelected = { value = it },
)""",
    ) {
        var dropdownValue by remember { mutableStateOf("Kotlin") }
        GortDropdown(
            items = listOf("Kotlin", "Java", "Scala", "Groovy"),
            selectedItem = dropdownValue,
            onItemSelected = { dropdownValue = it },
        )
    }

    ShowcaseSection("Drawing")

    ComponentShowcase(
        name = "DrawSurface",
        description = "Freeform canvas for sketching, signatures, or annotation overlays.",
        code = """DrawSurface(
    modifier = Modifier.fillMaxWidth().height(200.dp),
    onDrag = { start, end -> lines.add(start to end) },
) { lines.forEach { … drawLine(…) } }""",
    ) {
        val lines = remember { mutableStateListOf<Pair<androidx.compose.ui.geometry.Offset, androidx.compose.ui.geometry.Offset>>() }
        DrawSurface(
            modifier = Modifier.fillMaxWidth().height(200.dp),
            onDrag = { start, end -> lines.add(start to end) },
        ) {
            lines.forEach { (start, end) ->
                drawLine(
                    color = androidx.compose.ui.graphics.Color.Black,
                    start = start,
                    end = end,
                    strokeWidth = 3f,
                )
            }
        }
    }

    ShowcaseSection("Layout")

    ComponentShowcase(
        name = "GortListDetail",
        description = "Adaptive list-detail layout. Side-by-side on wide screens, single pane on compact.",
        code = """GortListDetail(
    listContent = { /* list */ },
    detailContent = { /* detail */ },
    showDetail = showDetail,
)""",
    ) {
        var selectedItem by remember { mutableStateOf("Kotlin") }
        val items = listOf("Kotlin", "Compose", "Gort", "Neobrutalism")
        GortListDetail(
            modifier = Modifier.fillMaxWidth().height(200.dp),
            showDetail = true,
            listContent = {
                Column(modifier = Modifier.padding(Gort.spacing.sm)) {
                    items.forEach { item ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedItem = item }
                                .background(
                                    if (item == selectedItem) Gort.colors.primaryContainer
                                    else Gort.colors.surface,
                                )
                                .padding(Gort.spacing.sm),
                        ) {
                            BasicText(
                                item,
                                style = Gort.typography.body.copy(color = Gort.colors.onSurface),
                            )
                        }
                    }
                }
            },
            detailContent = {
                Box(modifier = Modifier.padding(Gort.spacing.md)) {
                    BasicText(
                        "Selected: $selectedItem",
                        style = Gort.typography.title.copy(color = Gort.colors.onSurface),
                    )
                }
            },
        )
    }
}
