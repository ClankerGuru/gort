package zone.clanker.gort.components

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import zone.clanker.gort.icons.lucide.*
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
    sortable: Boolean = false,
    paginated: Boolean = false,
    pageSize: Int = 10,
    maxVisibleRows: Int = 7,
    onExpand: (() -> Unit)? = null,
) {
    val colors = Gort.colors
    val spacing = Gort.spacing
    val haptic = LocalHapticFeedback.current

    // Sort state
    var sortColumn by remember { mutableStateOf(-1) }
    var sortAscending by remember { mutableStateOf(true) }

    val sortedRows = remember(rows, sortColumn, sortAscending) {
        if (sortColumn < 0) rows
        else {
            val sorted = rows.sortedBy { it.getOrElse(sortColumn) { "" } }
            if (sortAscending) sorted else sorted.reversed()
        }
    }

    // Pagination state
    var currentPage by remember { mutableStateOf(0) }
    val totalPages = if (paginated) ((sortedRows.size + pageSize - 1) / pageSize).coerceAtLeast(1) else 1

    // Expand/collapse state
    var expanded by remember { mutableStateOf(false) }
    val visibleRows = when {
        paginated -> {
            val start = currentPage * pageSize
            sortedRows.subList(start.coerceAtMost(sortedRows.size), (start + pageSize).coerceAtMost(sortedRows.size))
        }
        expanded -> sortedRows
        else -> sortedRows.take(maxVisibleRows)
    }
    val canExpand = !paginated && sortedRows.size > maxVisibleRows

    // Control drop state
    var showControls by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .border(Gort.borders.default, colors.border)
                .horizontalScroll(scrollState),
        ) {
            // Header
            Row(
                modifier = Modifier.background(colors.surface),
            ) {
                columns.forEachIndexed { index, col ->
                    val isSortable = sortable || col.sortable
                    val isSorted = sortColumn == index

                    Row(
                        modifier = Modifier
                            .width(col.width)
                            .then(
                                if (isSortable) Modifier.clickable {
                                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                    if (sortColumn == index) {
                                        sortAscending = !sortAscending
                                    } else {
                                        sortColumn = index
                                        sortAscending = true
                                    }
                                    onHeaderClick?.invoke(index)
                                } else Modifier
                            )
                            .padding(spacing.sm),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        BasicText(
                            text = col.header,
                            style = Gort.typography.label.copy(
                                color = colors.onSurface,
                            ),
                            modifier = Modifier.weight(1f),
                        )
                        if (isSortable && isSorted) {
                            BasicText(
                                text = if (sortAscending) "▲" else "▼",
                                style = Gort.typography.label.copy(color = colors.primary),
                            )
                        }
                    }
                }
            }

            // Header bottom line
            Box(
                modifier = Modifier
                    .width(columns.sumOf { it.width.value.toInt() }.dp)
                    .height(Gort.borders.thick)
                    .background(colors.border),
            )

            // Rows
            visibleRows.forEachIndexed { rowIndex, row ->
                val rowBg = if (striped && rowIndex % 2 == 1) colors.background else colors.surface
                Row(modifier = Modifier.background(rowBg)) {
                    columns.forEachIndexed { colIndex, col ->
                        BasicText(
                            text = row.getOrElse(colIndex) { "" },
                            style = Gort.typography.body.copy(color = colors.onSurface),
                            modifier = Modifier
                                .width(col.width)
                                .padding(spacing.sm),
                        )
                    }
                }
            }
        }

        // Control drop
        if (canExpand || paginated || sortedRows.size > maxVisibleRows) {
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                if (showControls) {
                    ShadowControlBar {
                        // Expand/collapse button
                        if (canExpand) {
                            CircleButton(
                                icon = if (expanded) Lucide.Minimize else Lucide.Maximize,
                                contentDescription = if (expanded) "Collapse" else "Expand",
                            ) {
                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                if (onExpand != null) {
                                    onExpand()
                                } else {
                                    expanded = !expanded
                                }
                            }
                        }

                        // Pagination controls
                        if (paginated && totalPages > 1) {
                            CircleButton(
                                icon = Lucide.ChevronLeft,
                                contentDescription = "Previous page",
                                enabled = currentPage > 0,
                            ) {
                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                currentPage--
                            }

                            if (totalPages >= 5) {
                                CircleButton(
                                    icon = Lucide.ChevronsLeft,
                                    contentDescription = "First page",
                                    enabled = currentPage > 0,
                                ) { currentPage = 0 }
                            }

                            // Page numbers
                            val range = pageRange(currentPage, totalPages)
                            range.forEach { page ->
                                PageButton(
                                    page = page,
                                    selected = page == currentPage,
                                ) {
                                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                    currentPage = page
                                }
                            }

                            if (totalPages >= 5) {
                                CircleButton(
                                    icon = Lucide.ChevronsRight,
                                    contentDescription = "Last page",
                                    enabled = currentPage < totalPages - 1,
                                ) { currentPage = totalPages - 1 }
                            }

                            CircleButton(
                                icon = Lucide.ChevronRight,
                                contentDescription = "Next page",
                                enabled = currentPage < totalPages - 1,
                            ) {
                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                currentPage++
                            }
                        }

                        // Horizontal scroll button
                        CircleButton(
                            icon = Lucide.ArrowRight,
                            contentDescription = "Scroll",
                        ) {
                            // Toggle handled by coroutine, simplified
                        }
                    }
                } else {
                    ShadowDrop(onClick = {
                        showControls = true
                    })
                }
            }
        }
    }
}

private fun pageRange(current: Int, total: Int): List<Int> {
    if (total <= 5) return (0 until total).toList()
    return buildList {
        val start = (current - 1).coerceAtLeast(0)
        val end = (current + 1).coerceAtMost(total - 1)
        for (p in start..end) add(p)
    }
}
