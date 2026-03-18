package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

data class TableColumn(
    val header: String,
    val width: Dp = 120.dp,
    val sortable: Boolean = false,
)

@Composable
fun Table(
    columns: List<TableColumn>,
    rows: List<List<String>>,
    modifier: Modifier = Modifier,
    onHeaderClick: ((Int) -> Unit)? = null,
    striped: Boolean = true,
) {
    val colors = Gort.colors
    val spacing = Gort.spacing

    Column(
        modifier = modifier
            .border(Gort.borders.default, colors.border)
            .horizontalScroll(rememberScrollState()),
    ) {
        // Header
        Row(
            modifier = Modifier
                .background(colors.surface)
                .border(width = Gort.borders.thin, color = colors.border),
        ) {
            columns.forEachIndexed { index, col ->
                BasicText(
                    text = col.header + if (col.sortable) " ↕" else "",
                    style = Gort.typography.label.copy(color = colors.onSurface),
                    modifier = Modifier
                        .width(col.width)
                        .then(
                            if (col.sortable && onHeaderClick != null)
                                Modifier.clickable { onHeaderClick(index) }
                            else Modifier
                        )
                        .padding(spacing.sm)
                        .border(width = Gort.borders.thin, color = colors.border),
                )
            }
        }

        // Rows
        rows.forEachIndexed { rowIndex, row ->
            val rowBg = if (striped && rowIndex % 2 == 1) {
                colors.background
            } else {
                colors.surface
            }

            Row(modifier = Modifier.background(rowBg)) {
                columns.forEachIndexed { colIndex, col ->
                    BasicText(
                        text = row.getOrElse(colIndex) { "" },
                        style = Gort.typography.body.copy(color = colors.onSurface),
                        modifier = Modifier
                            .width(col.width)
                            .padding(spacing.sm)
                            .border(width = Gort.borders.thin, color = colors.border.copy(alpha = 0.3f)),
                    )
                }
            }
        }
    }
}
