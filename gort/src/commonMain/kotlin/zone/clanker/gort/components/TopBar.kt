package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

@Composable
fun GortTopBar(
    modifier: Modifier = Modifier,
    color: Color = Gort.colors.surface,
    title: @Composable () -> Unit = {},
    navigationIcon: (@Composable () -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color)
                .padding(horizontal = Gort.spacing.md, vertical = Gort.spacing.sm),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Gort.spacing.sm),
        ) {
            if (navigationIcon != null) {
                navigationIcon()
            }
            Box(modifier = Modifier.weight(1f)) {
                title()
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(Gort.spacing.xs),
                content = actions,
            )
        }
        // Thick bottom border
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Gort.borders.heavy)
                .background(Gort.colors.border),
        )
    }
}
