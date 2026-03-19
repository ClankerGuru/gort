package zone.clanker.gort.catalog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import zone.clanker.gort.components.*
import zone.clanker.gort.theme.Gort

@Composable
fun NavigationScreen() {
    ShowcaseSection("Page Navigation")

    ComponentShowcase(
        name = "Tabs",
        description = "Horizontal tab bar for switching between related content panels.",
        code = """GortTabs(
    tabs = listOf("Overview", "Details", "Settings"),
    selectedIndex = tabIndex,
    onTabSelected = { tabIndex = it },
)""",
    ) {
        var tabIndex by remember { mutableIntStateOf(0) }
        GortTabs(
            tabs = listOf("Overview", "Details", "Settings"),
            selectedIndex = tabIndex,
            onTabSelected = { tabIndex = it },
        )
    }

    ComponentShowcase(
        name = "Breadcrumb",
        description = "Hierarchical path indicator showing the user's location in a navigation tree.",
        code = """Breadcrumb(
    items = listOf(
        BreadcrumbItem("Home", onClick = {}),
        BreadcrumbItem("Projects", onClick = {}),
        BreadcrumbItem("Gort"),
    ),
)""",
    ) {
        Breadcrumb(
            items = listOf(
                BreadcrumbItem("Home", onClick = {}),
                BreadcrumbItem("Projects", onClick = {}),
                BreadcrumbItem("Gort"),
            ),
        )
    }

    ComponentShowcase(
        name = "Pagination",
        description = "Page number controls for navigating through paginated data sets.",
        code = """Pagination(currentPage = page, totalPages = 12, onPageChange = { page = it })""",
    ) {
        var page by remember { mutableIntStateOf(3) }
        Pagination(currentPage = page, totalPages = 12, onPageChange = { page = it })
    }

    ShowcaseSection("Progress & Structure")

    ComponentShowcase(
        name = "Stepper",
        description = "Multi-step progress indicator for wizards, onboarding, and sequential workflows.",
        code = """Stepper(
    steps = listOf(StepItem("Account", "Create your account"), …),
    currentStep = 2,
)""",
    ) {
        Stepper(
            steps = listOf(
                StepItem("Account", "Create your account"),
                StepItem("Profile", "Set up your profile"),
                StepItem("Payment", "Add payment method"),
                StepItem("Done", "All set!"),
            ),
            currentStep = 2,
        )
    }

    ComponentShowcase(
        name = "FileTree",
        description = "Expandable tree view for file system browsing, project structures, or hierarchical data.",
        code = """FileTree(
    roots = listOf(TreeNode("src", icon = "📁", children = listOf(…))),
    initialExpandedDepth = 2,
)""",
    ) {
        FileTree(
            roots = listOf(
                TreeNode("src", icon = "📁", children = listOf(
                    TreeNode("main", icon = "📁", children = listOf(
                        TreeNode("kotlin", icon = "📁", children = listOf(
                            TreeNode("App.kt", icon = "📄"),
                            TreeNode("Theme.kt", icon = "📄"),
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
    }

    ComponentShowcase(
        name = "Accordion",
        description = "Collapsible content sections for FAQs, settings groups, or detail panels.",
        code = """Accordion(title = "What is Gort?", initialExpanded = true) {
    BasicText("Gort is a neobrutalist UI library.")
}""",
    ) {
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
    }

    ShowcaseSection("Commands")

    ComponentShowcase(
        name = "CommandPalette",
        description = "Searchable command launcher overlay — like VS Code's Ctrl+Shift+P.",
        code = """CommandPalette(
    visible = true,
    commands = listOf(CommandItem("1", "New File", "Create a new file", "Ctrl+N")),
    onSelect = { … },
    onDismiss = { … },
)""",
    ) {
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
}
