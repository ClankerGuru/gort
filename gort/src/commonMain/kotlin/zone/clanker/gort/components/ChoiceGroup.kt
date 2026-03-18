package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import zone.clanker.gort.theme.Gort

data class ChoiceItem(
    val id: String,
    val title: String,
    val description: String = "",
    val icon: (@Composable () -> Unit)? = null,
)

@Composable
fun ChoiceGroup(
    items: List<ChoiceItem>,
    selectedIds: Set<String>,
    onSelectionChange: (Set<String>) -> Unit,
    modifier: Modifier = Modifier,
    multiSelect: Boolean = false,
) {
    val colors = Gort.colors
    val spacing = Gort.spacing
    val shape = Gort.corners.default

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        items.forEach { item ->
            val isSelected = item.id in selectedIds

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        if (isSelected) Gort.borders.thick else Gort.borders.default,
                        if (isSelected) colors.primary else colors.border,
                        shape,
                    )
                    .background(
                        if (isSelected) colors.primaryContainer else colors.surface,
                        shape,
                    )
                    .clip(shape)
                    .clickable {
                        val newSelection = if (multiSelect) {
                            if (isSelected) selectedIds - item.id else selectedIds + item.id
                        } else {
                            setOf(item.id)
                        }
                        onSelectionChange(newSelection)
                    }
                    .padding(spacing.md),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (item.icon != null) {
                    Box(modifier = Modifier.padding(end = spacing.sm)) { item.icon.invoke() }
                }
                Column(modifier = Modifier.weight(1f)) {
                    BasicText(
                        text = item.title,
                        style = Gort.typography.title.copy(
                            color = if (isSelected) colors.onPrimaryContainer else colors.onSurface,
                        ),
                    )
                    if (item.description.isNotEmpty()) {
                        BasicText(
                            text = item.description,
                            style = Gort.typography.body.copy(
                                color = (if (isSelected) colors.onPrimaryContainer else colors.onSurface).copy(alpha = 0.7f),
                            ),
                        )
                    }
                }
                if (isSelected) {
                    BasicText(
                        text = "✓",
                        style = Gort.typography.title.copy(color = colors.primary),
                    )
                }
            }
        }
    }
}
