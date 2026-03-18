package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import zone.clanker.gort.foundation.Surface
import zone.clanker.gort.theme.Gort

data class GortDate(val year: Int, val month: Int, val day: Int)

@Composable
fun DatePicker(
    selectedDate: GortDate?,
    onDateSelect: (GortDate) -> Unit,
    modifier: Modifier = Modifier,
    initialYear: Int = 2026,
    initialMonth: Int = 1,
) {
    var viewYear by remember { mutableIntStateOf(selectedDate?.year ?: initialYear) }
    var viewMonth by remember { mutableIntStateOf(selectedDate?.month ?: initialMonth) }
    val colors = Gort.colors
    val spacing = Gort.spacing
    val months = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
    val daysInMonth = daysInMonth(viewYear, viewMonth)
    val firstDayOfWeek = firstDayOfWeek(viewYear, viewMonth)

    Surface(
        modifier = modifier,
        color = colors.surface,
        borderColor = colors.border,
        shadow = Gort.shadows.medium,
    ) {
        Column(modifier = Modifier.padding(spacing.md)) {
            // Month/year nav
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BasicText(
                    text = "◀",
                    style = Gort.typography.title.copy(color = colors.onSurface),
                    modifier = Modifier.clickable {
                        if (viewMonth == 1) { viewMonth = 12; viewYear-- } else viewMonth--
                    },
                )
                BasicText(
                    text = "${months[viewMonth - 1]} $viewYear",
                    style = Gort.typography.title.copy(color = colors.onSurface),
                )
                BasicText(
                    text = "▶",
                    style = Gort.typography.title.copy(color = colors.onSurface),
                    modifier = Modifier.clickable {
                        if (viewMonth == 12) { viewMonth = 1; viewYear++ } else viewMonth++
                    },
                )
            }

            Spacer(Modifier.height(spacing.sm))

            // Day-of-week headers
            Row(modifier = Modifier.fillMaxWidth()) {
                listOf("Mo", "Tu", "We", "Th", "Fr", "Sa", "Su").forEach { dow ->
                    BasicText(
                        text = dow,
                        style = Gort.typography.label.copy(color = colors.onSurface.copy(alpha = 0.5f)),
                        modifier = Modifier.weight(1f),
                    )
                }
            }

            Spacer(Modifier.height(spacing.xs))

            // Day grid
            var dayCounter = 1
            for (week in 0..5) {
                if (dayCounter > daysInMonth) break
                Row(modifier = Modifier.fillMaxWidth()) {
                    for (dow in 0..6) {
                        if (week == 0 && dow < firstDayOfWeek || dayCounter > daysInMonth) {
                            Spacer(Modifier.weight(1f).height(36.dp))
                        } else {
                            val day = dayCounter
                            val isSelected = selectedDate?.let {
                                it.year == viewYear && it.month == viewMonth && it.day == day
                            } == true

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(36.dp)
                                    .then(
                                        if (isSelected) Modifier
                                            .border(Gort.borders.default, colors.primary, Gort.corners.small)
                                            .background(colors.primaryContainer, Gort.corners.small)
                                        else Modifier
                                    )
                                    .clickable { onDateSelect(GortDate(viewYear, viewMonth, day)) },
                                contentAlignment = Alignment.Center,
                            ) {
                                BasicText(
                                    text = day.toString(),
                                    style = Gort.typography.body.copy(
                                        color = if (isSelected) colors.onPrimaryContainer else colors.onSurface,
                                    ),
                                )
                            }
                            dayCounter++
                        }
                    }
                }
            }
        }
    }
}

/**
 * Display-only calendar month view.
 */
@Composable
fun Calendar(
    year: Int,
    month: Int,
    modifier: Modifier = Modifier,
    highlightedDays: Set<Int> = emptySet(),
) {
    DatePicker(
        selectedDate = null,
        onDateSelect = {},
        modifier = modifier,
        initialYear = year,
        initialMonth = month,
    )
}

private fun daysInMonth(year: Int, month: Int): Int = when (month) {
    2 -> if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) 29 else 28
    4, 6, 9, 11 -> 30
    else -> 31
}

// Returns 0=Monday..6=Sunday using Zeller-like calculation
private fun firstDayOfWeek(year: Int, month: Int): Int {
    var y = year; var m = month
    if (m < 3) { m += 12; y-- }
    val q = 1
    val k = y % 100; val j = y / 100
    val h = (q + (13 * (m + 1)) / 5 + k + k / 4 + j / 4 + 5 * j) % 7
    // h: 0=Saturday, 1=Sunday, 2=Monday...
    return (h + 5) % 7 // convert to 0=Monday
}
