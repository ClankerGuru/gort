package zone.clanker.gort.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import zone.clanker.gort.theme.Gort

data class BreadcrumbItem(
    val label: String,
    val onClick: (() -> Unit)? = null,
)

@Composable
fun Breadcrumb(
    items: List<BreadcrumbItem>,
    modifier: Modifier = Modifier,
    separator: String = "/",
) {
    val colors = Gort.colors

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Gort.spacing.xs),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items.forEachIndexed { index, item ->
            val isLast = index == items.lastIndex
            val textMod = if (item.onClick != null && !isLast) {
                Modifier.clickable(onClick = item.onClick)
            } else {
                Modifier
            }

            BasicText(
                text = item.label,
                style = Gort.typography.body.copy(
                    color = if (isLast) colors.onSurface else colors.primary,
                ),
                modifier = textMod,
            )

            if (!isLast) {
                BasicText(
                    text = separator,
                    style = Gort.typography.body.copy(color = colors.onSurface.copy(alpha = 0.4f)),
                    modifier = Modifier.padding(horizontal = Gort.spacing.xs),
                )
            }
        }
    }
}
