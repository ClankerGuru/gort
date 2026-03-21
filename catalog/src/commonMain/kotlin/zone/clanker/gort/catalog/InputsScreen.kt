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
        code = """GortSearchBar(query = query, onQueryChange = { query = it }, placeholder = "Search...")""",
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
        description = "Single-line input with label, helper text, error states, and leading/trailing icons.",
        code = """TextField(
    value = text, onValueChange = { text = it },
    label = "Email", placeholder = "you@example.com",
    helperText = "We'll never share your email",
)""",
    ) {
        var text by remember { mutableStateOf("") }
        TextField(
            value = text,
            onValueChange = { text = it },
            label = "Email",
            placeholder = "you@example.com",
            helperText = "We'll never share your email",
        )
    }

    ComponentShowcase(
        name = "TextField — Password",
        description = "Password field with visibility toggle icon.",
        code = """TextField(value = pw, onValueChange = { pw = it }, label = "Password", isPassword = true)""",
    ) {
        var pw by remember { mutableStateOf("") }
        TextField(
            value = pw,
            onValueChange = { pw = it },
            label = "Password",
            placeholder = "••••••••",
            isPassword = true,
        )
    }

    ComponentShowcase(
        name = "TextField — Error",
        description = "TextField with error state.",
        code = """TextField(value = text, onValueChange = {}, errorText = "This field is required")""",
    ) {
        TextField(
            value = "",
            onValueChange = {},
            label = "Required Field",
            placeholder = "Enter value",
            errorText = "This field is required",
        )
    }

    ComponentShowcase(
        name = "TextArea",
        description = "Multi-line text input for longer content.",
        code = """TextArea(value = area, onValueChange = { area = it }, placeholder = "Multi-line input…")""",
    ) {
        var area by remember { mutableStateOf("") }
        TextArea(value = area, onValueChange = { area = it }, placeholder = "Multi-line input…")
    }

    ShowcaseSection("Toggles & Checks")

    ComponentShowcase(
        name = "Checkbox",
        description = "Boolean toggle for options.",
        code = """Checkbox(checked = checked, onCheckedChange = { checked = it })""",
    ) {
        var checked by remember { mutableStateOf(true) }
        Checkbox(checked = checked, onCheckedChange = { checked = it })
    }

    ComponentShowcase(
        name = "RadioButton",
        description = "Mutually exclusive selection.",
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
        description = "On/off switch for binary settings.",
        code = """Toggle(checked = toggled, onCheckedChange = { toggled = it })""",
    ) {
        var toggled by remember { mutableStateOf(false) }
        Toggle(checked = toggled, onCheckedChange = { toggled = it })
    }

    ShowcaseSection("Range & Value Inputs")

    ComponentShowcase(
        name = "Slider",
        description = "Continuous value selector.",
        code = """GortSlider(value = sliderValue, onValueChange = { sliderValue = it })""",
    ) {
        var sliderValue by remember { mutableFloatStateOf(0.5f) }
        GortSlider(value = sliderValue, onValueChange = { sliderValue = it })
    }

    ComponentShowcase(
        name = "NumberStepper",
        description = "Increment/decrement control.",
        code = """NumberStepper(value = number, onValueChange = { number = it }, min = 0, max = 20)""",
    ) {
        var number by remember { mutableIntStateOf(5) }
        NumberStepper(value = number, onValueChange = { number = it }, min = 0, max = 20)
    }

    ComponentShowcase(
        name = "Rating",
        description = "Canvas-drawn star rating with gold fill and half-star support.",
        code = """Rating(value = rating, onValueChange = { rating = it })""",
    ) {
        var rating by remember { mutableIntStateOf(3) }
        Rating(value = rating, onValueChange = { rating = it })
    }

    ShowcaseSection("Form & Selection")

    ComponentShowcase(
        name = "Form",
        description = "Structured form layout with validation.",
        code = """Form { FormField(label = "Email", required = true) { TextField(…) } }""",
    ) {
        Form {
            FormField(label = "Email", required = true, helper = "We'll never share your email") {
                var email by remember { mutableStateOf("") }
                TextField(value = email, onValueChange = { email = it }, placeholder = "you@example.com")
            }
            FormField(label = "Password", required = true) {
                var pw by remember { mutableStateOf("") }
                TextField(value = pw, onValueChange = { pw = it }, placeholder = "••••••••", isPassword = true)
            }
        }
    }

    ComponentShowcase(
        name = "ChoiceGroup",
        description = "Animated selection cards with spring border/checkmark transitions.",
        code = """ChoiceGroup(items = listOf(…), selectedIds = selected, onSelectionChange = { selected = it })""",
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
        description = "Palette-based color selector.",
        code = """ColorPicker(colors = listOf(…), selectedColor = picked, onColorSelect = { picked = it })""",
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
