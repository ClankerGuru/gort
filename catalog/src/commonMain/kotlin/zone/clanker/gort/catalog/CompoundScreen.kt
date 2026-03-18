package zone.clanker.gort.catalog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import zone.clanker.gort.components.*
import zone.clanker.gort.theme.Gort

@Composable
fun CompoundScreen() {
    SectionTitle("Date Picker")
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

    SectionTitle("Time Picker")
    var time by remember { mutableStateOf(GortTime(14, 30)) }
    TimePicker(selectedTime = time, onTimeChange = { time = it })
    BasicText(
        "Selected: ${time.hour.toString().padStart(2, '0')}:${time.minute.toString().padStart(2, '0')}",
        style = Gort.typography.body.copy(color = Gort.colors.onSurface),
    )

    SectionTitle("Dropdown")
    var dropdownValue by remember { mutableStateOf("Kotlin") }
    GortDropdown(
        items = listOf("Kotlin", "Java", "Scala", "Groovy"),
        selectedItem = dropdownValue,
        onItemSelected = { dropdownValue = it },
    )

    SectionTitle("Draw Surface")
    ComponentLabel("Click and drag to draw")
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
