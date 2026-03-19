package zone.clanker.gort.catalog

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import zone.clanker.gort.components.*
import zone.clanker.gort.theme.Gort

@Composable
fun InputsScreen() {
    ShowcaseSection("Search")

    ComponentShowcase(
        name = "SearchBar",
        description = "Full-width search input with icon, clear button, and optional filter chips.",
        code = """GortSearchBar(
    query = query,
    onQueryChange = { query = it },
    placeholder = "Search...",
    filters = listOf("All", "Unread", "Favorites"),
    selectedFilter = selected,
    onFilterSelect = { selected = it },
)""",
    ) {
        var q by remember { mutableStateOf("") }
        var f by remember { mutableIntStateOf(0) }
        GortSearchBar(
            query = q,
            onQueryChange = { q = it },
            placeholder = "Search components...",
            filters = listOf("All", "Unread", "Favorites", "Groups"),
            selectedFilter = f,
            onFilterSelect = { f = it },
        )
    }

    ShowcaseSection("Text Inputs")

    ComponentShowcase(
        name = "TextField",
        description = "Single-line text input with placeholder support and neobrutalist border.",
        code = """TextField(value = text, onValueChange = { text = it }, placeholder = "Type here…")""",
    ) {
        var text by remember { mutableStateOf("") }
        TextField(value = text, onValueChange = { text = it }, placeholder = "Type here…")
    }

    ComponentShowcase(
        name = "TextArea",
        description = "Multi-line text input for longer content like comments or descriptions.",
        code = """TextArea(value = area, onValueChange = { area = it }, placeholder = "Multi-line input…")""",
    ) {
        var area by remember { mutableStateOf("") }
        TextArea(value = area, onValueChange = { area = it }, placeholder = "Multi-line input…")
    }

    ShowcaseSection("Toggles & Checks")

    ComponentShowcase(
        name = "Checkbox",
        description = "Boolean toggle for options that can be independently enabled or disabled.",
        code = """Checkbox(checked = checked, onCheckedChange = { checked = it })""",
        controls = {
            var checked by remember { mutableStateOf(false) }
            Checkbox(checked = checked, onCheckedChange = { checked = it })
        },
    ) {
        var checked by remember { mutableStateOf(true) }
        Checkbox(checked = checked, onCheckedChange = { checked = it })
    }

    ComponentShowcase(
        name = "RadioButton",
        description = "Mutually exclusive selection from a set of options.",
        code = """RadioButton(selected = radio == 0, onClick = { radio = 0 })""",
    ) {
        var radio by remember { mutableIntStateOf(0) }
        Column(verticalArrangement = Arrangement.spacedBy(Gort.spacing.sm)) {
            listOf("Option A", "Option B", "Option C").forEachIndexed { i, _ ->
                RadioButton(selected = radio == i, onClick = { radio = i })
            }
        }
    }

    ComponentShowcase(
        name = "Toggle",
        description = "On/off switch for binary settings that take effect immediately.",
        code = """Toggle(checked = toggled, onCheckedChange = { toggled = it })""",
    ) {
        var toggled by remember { mutableStateOf(false) }
        Toggle(checked = toggled, onCheckedChange = { toggled = it })
    }

    ShowcaseSection("Range & Value Inputs")

    ComponentShowcase(
        name = "Slider",
        description = "Continuous value selector for ranges like volume, brightness, or percentages.",
        code = """GortSlider(value = sliderValue, onValueChange = { sliderValue = it })""",
    ) {
        var sliderValue by remember { mutableFloatStateOf(0.5f) }
        GortSlider(value = sliderValue, onValueChange = { sliderValue = it })
    }

    ComponentShowcase(
        name = "NumberStepper",
        description = "Increment/decrement control for precise numeric values within a bounded range.",
        code = """NumberStepper(value = number, onValueChange = { number = it }, min = 0, max = 20)""",
    ) {
        var number by remember { mutableIntStateOf(5) }
        NumberStepper(value = number, onValueChange = { number = it }, min = 0, max = 20)
    }

    ComponentShowcase(
        name = "Rating",
        description = "Star-based rating input for reviews and feedback.",
        code = """Rating(value = rating, onValueChange = { rating = it })""",
    ) {
        var rating by remember { mutableIntStateOf(3) }
        Rating(value = rating, onValueChange = { rating = it })
    }

    ShowcaseSection("Form & Selection")

    ComponentShowcase(
        name = "Form",
        description = "Structured form layout with labeled fields, validation helpers, and required markers.",
        code = """Form {
    FormField(label = "Email", required = true, helper = "We'll never share your email") {
        TextField(value = email, onValueChange = { email = it }, placeholder = "you@example.com")
    }
}""",
    ) {
        Form {
            FormField(label = "Email", required = true, helper = "We'll never share your email") {
                var email by remember { mutableStateOf("") }
                TextField(value = email, onValueChange = { email = it }, placeholder = "you@example.com")
            }
            FormField(label = "Password", required = true) {
                var pw by remember { mutableStateOf("") }
                TextField(value = pw, onValueChange = { pw = it }, placeholder = "••••••••")
            }
        }
    }

    ComponentShowcase(
        name = "ChoiceGroup",
        description = "Rich selection cards for choosing between plans, options, or configurations.",
        code = """ChoiceGroup(
    items = listOf(ChoiceItem("a", "Free", "Basic features")),
    selectedIds = selected,
    onSelectionChange = { selected = it },
)""",
    ) {
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
    }

    ComponentShowcase(
        name = "ColorPicker",
        description = "Palette-based color selector for picking from a predefined set of colors.",
        code = """ColorPicker(
    colors = listOf(Gort.colors.primary, Gort.colors.secondary),
    selectedColor = pickedColor,
    onColorSelect = { pickedColor = it },
)""",
    ) {
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
}
