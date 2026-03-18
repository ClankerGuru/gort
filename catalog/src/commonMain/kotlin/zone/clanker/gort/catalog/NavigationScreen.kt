package zone.clanker.gort.catalog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import zone.clanker.gort.components.*
import zone.clanker.gort.theme.Gort

@Composable
fun NavigationScreen() {
    SectionTitle("Tabs")
    var tabIndex by remember { mutableIntStateOf(0) }
    GortTabs(
        tabs = listOf("Overview", "Details", "Settings"),
        selectedIndex = tabIndex,
        onTabSelected = { tabIndex = it },
    )

    SectionTitle("Breadcrumb")
    Breadcrumb(
        items = listOf(
            BreadcrumbItem("Home", onClick = {}),
            BreadcrumbItem("Projects", onClick = {}),
            BreadcrumbItem("Gort"),
        ),
    )

    SectionTitle("Pagination")
    var page by remember { mutableIntStateOf(3) }
    Pagination(currentPage = page, totalPages = 12, onPageChange = { page = it })

    SectionTitle("Stepper")
    Stepper(
        steps = listOf(
            StepItem("Account", "Create your account"),
            StepItem("Profile", "Set up your profile"),
            StepItem("Payment", "Add payment method"),
            StepItem("Done", "All set!"),
        ),
        currentStep = 2,
    )

    SectionTitle("File Tree")
    FileTree(
        roots = listOf(
            TreeNode("src", icon = "📁", children = listOf(
                TreeNode("main", icon = "📁", children = listOf(
                    TreeNode("kotlin", icon = "📁", children = listOf(
                        TreeNode("App.kt", icon = "📄"),
                        TreeNode("Theme.kt", icon = "📄"),
                    )),
                    TreeNode("resources", icon = "📁", children = listOf(
                        TreeNode("strings.xml", icon = "📄"),
                    )),
                )),
                TreeNode("test", icon = "📁", children = listOf(
                    TreeNode("AppTest.kt", icon = "🧪"),
                )),
            )),
            TreeNode("build.gradle.kts", icon = "⚙️"),
            TreeNode("README.md", icon = "📝"),
        ),
        initialExpandedDepth = 2,
    )

    SectionTitle("Accordion")
    Accordion(title = "What is Gort?", initialExpanded = true) {
        BasicText(
            "Gort is a neobrutalist UI component library for Compose Multiplatform.",
            style = Gort.typography.body.copy(color = Gort.colors.onSurface),
        )
    }
    Accordion(title = "Which platforms?") {
        BasicText(
            "Android, Desktop (JVM), WASM, JS, iOS, macOS, Linux, Windows.",
            style = Gort.typography.body.copy(color = Gort.colors.onSurface),
        )
    }
    Accordion(title = "Is it free?") {
        BasicText(
            "Yes, MIT license.",
            style = Gort.typography.body.copy(color = Gort.colors.onSurface),
        )
    }

    SectionTitle("Command Palette")
    ComponentLabel("Press the button to open")
    var paletteVisible by remember { mutableStateOf(false) }
    Button(onClick = { paletteVisible = true }) {
        BasicText("Open Command Palette", style = Gort.typography.label.copy(color = Gort.colors.onPrimary))
    }
    CommandPalette(
        visible = paletteVisible,
        commands = listOf(
            CommandItem("1", "New File", "Create a new file", "Ctrl+N"),
            CommandItem("2", "Open File", "Open existing file", "Ctrl+O"),
            CommandItem("3", "Save", "Save current file", "Ctrl+S"),
            CommandItem("4", "Find", "Search in project", "Ctrl+Shift+F"),
            CommandItem("5", "Settings", "Open preferences"),
        ),
        onSelect = { paletteVisible = false },
        onDismiss = { paletteVisible = false },
    )
}
