package zone.clanker.gort.catalog

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import zone.clanker.gort.components.*
import zone.clanker.gort.theme.Gort

@Composable
fun InputsScreen() {
    SectionTitle("Text Field")
    var text by remember { mutableStateOf("") }
    TextField(value = text, onValueChange = { text = it }, placeholder = "Type here…")

    SectionTitle("Text Area")
    var area by remember { mutableStateOf("") }
    TextArea(value = area, onValueChange = { area = it }, placeholder = "Multi-line input…")

    SectionTitle("Checkbox")
    var checked by remember { mutableStateOf(false) }
    Checkbox(checked = checked, onCheckedChange = { checked = it })

    SectionTitle("Radio Button")
    var radio by remember { mutableIntStateOf(0) }
    Column(verticalArrangement = Arrangement.spacedBy(Gort.spacing.sm)) {
        listOf("Option A", "Option B", "Option C").forEachIndexed { i, label ->
            RadioButton(selected = radio == i, onClick = { radio = i })
        }
    }

    SectionTitle("Toggle")
    var toggled by remember { mutableStateOf(false) }
    Toggle(checked = toggled, onCheckedChange = { toggled = it })

    SectionTitle("Slider")
    var sliderValue by remember { mutableFloatStateOf(0.5f) }
    GortSlider(value = sliderValue, onValueChange = { sliderValue = it })

    SectionTitle("Number Stepper")
    var number by remember { mutableIntStateOf(5) }
    NumberStepper(value = number, onValueChange = { number = it }, min = 0, max = 20)

    SectionTitle("Rating")
    var rating by remember { mutableIntStateOf(3) }
    Rating(value = rating, onValueChange = { rating = it })

    SectionTitle("Form")
    Form {
        FormField(label = "Email", required = true, helper = "We'll never share your email") {
            var email by remember { mutableStateOf("") }
            TextField(value = email, onValueChange = { email = it }, placeholder = "you@example.com")
        }
        FormField(label = "Password", required = true) {
            var pw by remember { mutableStateOf("") }
            TextField(value = pw, onValueChange = { pw = it }, placeholder = "••••••••")
        }
        FormField(label = "Bio") {
            var bio by remember { mutableStateOf("") }
            TextArea(value = bio, onValueChange = { bio = it }, placeholder = "Tell us about yourself")
        }
    }

    SectionTitle("Choice Group")
    var selected by remember { mutableStateOf(setOf("a")) }
    ChoiceGroup(
        items = listOf(
            ChoiceItem("a", "Free Plan", "Basic features, 1 project"),
            ChoiceItem("b", "Pro Plan", "All features, unlimited projects"),
            ChoiceItem("c", "Enterprise", "Custom solutions, dedicated support"),
        ),
        selectedIds = selected,
        onSelectionChange = { selected = it },
    )

    SectionTitle("Color Picker")
    val defaultColor = Gort.colors.primary
    var pickedColor by remember { mutableStateOf(defaultColor) }
    ColorPicker(
        colors = listOf(
            Gort.colors.primary, Gort.colors.secondary, Gort.colors.tertiary,
            Gort.colors.accent, Gort.colors.error, Gort.colors.success,
            Gort.colors.warning, Gort.colors.surface, Gort.colors.background,
        ),
        selectedColor = pickedColor,
        onColorSelect = { pickedColor = it },
    )
}
