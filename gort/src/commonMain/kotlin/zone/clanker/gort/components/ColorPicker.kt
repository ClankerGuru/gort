package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

@Composable
fun ColorPicker(
    colors: List<Color>,
    selectedColor: Color?,
    onColorSelect: (Color) -> Unit,
    modifier: Modifier = Modifier,
    swatchSize: Dp = 36.dp,
    columns: Int = 6,
) {
    val borderColor = Gort.colors.border
    val spacing = Gort.spacing

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.xs),
    ) {
        colors.chunked(columns).forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(spacing.xs)) {
                row.forEach { color ->
                    val isSelected = color == selectedColor
                    Box(
                        modifier = Modifier
                            .size(swatchSize)
                            .border(
                                if (isSelected) Gort.borders.thick else Gort.borders.thin,
                                if (isSelected) Gort.colors.onSurface else borderColor,
                                Gort.corners.small,
                            )
                            .background(color, Gort.corners.small)
                            .clickable { onColorSelect(color) },
                    )
                }
            }
        }
    }
}

/**
 * Display-only color palette viewer.
 */
@Composable
fun ColorPalette(
    colors: List<Pair<String, Color>>,
    modifier: Modifier = Modifier,
    swatchSize: Dp = 48.dp,
) {
    val textColor = Gort.colors.onSurface

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Gort.spacing.xs),
    ) {
        colors.forEach { (name, color) ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(Gort.spacing.sm),
            ) {
                Box(
                    modifier = Modifier
                        .size(swatchSize)
                        .border(Gort.borders.thin, Gort.colors.border, Gort.corners.small)
                        .background(color, Gort.corners.small),
                )
                androidx.compose.foundation.text.BasicText(
                    text = name,
                    style = Gort.typography.label.copy(color = textColor),
                    modifier = Modifier.align(androidx.compose.ui.Alignment.CenterVertically),
                )
            }
        }
    }
}
