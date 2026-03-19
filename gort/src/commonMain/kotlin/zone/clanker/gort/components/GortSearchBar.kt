package zone.clanker.gort.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Search
import com.composables.icons.lucide.X
import zone.clanker.gort.theme.Gort

@Composable
fun GortSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "Search...",
    filters: List<String> = emptyList(),
    selectedFilter: Int = 0,
    onFilterSelect: (Int) -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Gort.spacing.sm),
    ) {
        // Search field
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(Gort.borders.default, Gort.colors.border)
                .background(Gort.colors.surface)
                .padding(horizontal = Gort.spacing.md, vertical = Gort.spacing.sm),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Gort.spacing.sm),
        ) {
            Image(
                Lucide.Search,
                contentDescription = "Search",
                colorFilter = ColorFilter.tint(Gort.colors.onSurface.copy(alpha = 0.5f)),
                modifier = Modifier.size(20.dp),
            )

            Box(modifier = Modifier.weight(1f)) {
                if (query.isEmpty()) {
                    BasicText(
                        placeholder,
                        style = Gort.typography.body.copy(color = Gort.colors.onSurface.copy(alpha = 0.4f)),
                    )
                }
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    textStyle = Gort.typography.body.copy(color = Gort.colors.onSurface),
                    cursorBrush = SolidColor(Gort.colors.primary),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            if (query.isNotEmpty()) {
                Image(
                    Lucide.X,
                    contentDescription = "Clear",
                    colorFilter = ColorFilter.tint(Gort.colors.onSurface.copy(alpha = 0.5f)),
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { onQueryChange("") },
                )
            }
        }

        // Filter chips
        if (filters.isNotEmpty()) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(Gort.spacing.sm),
                modifier = Modifier.horizontalScroll(rememberScrollState()),
            ) {
                filters.forEachIndexed { index, filter ->
                    Chip(
                        label = filter,
                        color = if (index == selectedFilter) Gort.colors.primary else Gort.colors.primaryContainer,
                        contentColor = if (index == selectedFilter) Gort.colors.onPrimary else Gort.colors.onPrimaryContainer,
                        modifier = Modifier.clickable { onFilterSelect(index) },
                    )
                    // Chip doesn't have onClick — clickable is on modifier
                }
            }
        }
    }
}
