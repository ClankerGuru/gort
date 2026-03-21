package zone.clanker.gort.components

import androidx.compose.ui.graphics.vector.ImageVector
import zone.clanker.gort.icons.lucide.Info
import zone.clanker.gort.icons.lucide.Lightbulb
import zone.clanker.gort.icons.lucide.Lucide
import zone.clanker.gort.icons.lucide.OctagonAlert
import zone.clanker.gort.icons.lucide.TriangleAlert

enum class Severity(val label: String, val icon: ImageVector) {
    Info("Note", Lucide.Info),
    Success("Tip", Lucide.Lightbulb),
    Warning("Warning", Lucide.TriangleAlert),
    Danger("Danger", Lucide.OctagonAlert),
}
