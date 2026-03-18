package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import zone.clanker.gort.foundation.Surface
import zone.clanker.gort.theme.Gort

@Composable
fun <T> GortDropdown(
    items: List<T>,
    selectedItem: T,
    onItemSelected: (T) -> Unit,
    modifier: Modifier = Modifier,
    itemLabel: (T) -> String = { it.toString() },
    shape: Shape = Gort.corners.default,
) {
    val colors = Gort.colors
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        // Trigger
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape)
                .background(colors.surface, shape)
                .border(Gort.borders.thick, colors.border, shape)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) { expanded = !expanded }
                .padding(horizontal = Gort.spacing.md, vertical = Gort.spacing.sm),
        ) {
            BasicText(
                text = itemLabel(selectedItem) + if (expanded) " ▲" else " ▼",
                style = Gort.typography.bodyMedium.copy(color = colors.onSurface),
            )
        }

        // Options
        if (expanded) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shadow = Gort.shadows.medium,
                shape = shape,
            ) {
                Column {
                    items.forEach { item ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                ) {
                                    onItemSelected(item)
                                    expanded = false
                                }
                                .background(
                                    if (item == selectedItem) colors.primaryContainer
                                    else colors.surface,
                                )
                                .padding(horizontal = Gort.spacing.md, vertical = Gort.spacing.sm),
                        ) {
                            BasicText(
                                text = itemLabel(item),
                                style = Gort.typography.bodyMedium.copy(color = colors.onSurface),
                            )
                        }
                    }
                }
            }
        }
    }
}
