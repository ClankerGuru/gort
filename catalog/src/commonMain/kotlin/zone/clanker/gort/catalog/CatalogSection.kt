package zone.clanker.gort.catalog

import androidx.compose.ui.graphics.vector.ImageVector
import zone.clanker.gort.icons.lucide.*

enum class CatalogSection(val label: String, val icon: ImageVector) {
    Theme("Theme", Lucide.Palette),
    Buttons("Actions", Lucide.MousePointerClick),
    Inputs("Inputs", Lucide.TextCursorInput),
    Display("Display", Lucide.ChartBar),
    Navigation("Navigation", Lucide.Compass),
    Feedback("Feedback", Lucide.MessageSquare),
    Compound("Compound", Lucide.Puzzle),
    Data("Data", Lucide.Database),
    Chat("Chat", Lucide.MessageCircle),
}
