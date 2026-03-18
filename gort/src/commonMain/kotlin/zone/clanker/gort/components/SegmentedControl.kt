package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import zone.clanker.gort.theme.Gort

@Composable
fun SegmentedControl(
    options: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = Gort.colors
    val shape = Gort.corners.default

    Row(
        modifier = modifier
            .border(Gort.borders.default, colors.border, shape)
            .clip(shape),
    ) {
        options.forEachIndexed { index, label ->
            val isSelected = index == selectedIndex
            BasicText(
                text = label,
                style = Gort.typography.label.copy(
                    color = if (isSelected) colors.onPrimary else colors.onSurface,
                ),
                modifier = Modifier
                    .clickable { onSelect(index) }
                    .background(if (isSelected) colors.primary else colors.surface)
                    .padding(horizontal = Gort.spacing.md, vertical = Gort.spacing.sm)
                    .then(
                        if (index < options.lastIndex)
                            Modifier.border(
                                width = Gort.borders.thin,
                                color = colors.border,
                            )
                        else Modifier
                    ),
            )
        }
    }
}
