package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

@Composable
fun GortBottomBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Thick top border
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Gort.borders.heavy)
                .background(Gort.colors.border),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Gort.colors.surface)
                .padding(vertical = Gort.spacing.xs),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            content = content,
        )
    }
}

@Composable
fun RowScope.GortBottomBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    label: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
) {
    val colors = Gort.colors
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()
    val contentColor = when {
        selected -> colors.primary
        isHovered -> colors.onSurface.copy(alpha = 0.8f)
        else -> colors.onSurface.copy(alpha = 0.6f)
    }

    Column(
        modifier = modifier
            .weight(1f)
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            )
            .padding(vertical = Gort.spacing.xs),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        CompositionLocalProvider(
            LocalGortBottomBarItemColor provides contentColor,
        ) {
            icon()
            label?.invoke()
        }
        // Selected indicator line
        if (selected) {
            Box(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .fillMaxWidth(0.6f)
                    .height(Gort.borders.thick)
                    .background(colors.primary),
            )
        }
    }
}

val LocalGortBottomBarItemColor = compositionLocalOf { Color.Unspecified }
