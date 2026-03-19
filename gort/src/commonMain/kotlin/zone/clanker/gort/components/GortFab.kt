package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import zone.clanker.gort.foundation.gortPressableShadow
import zone.clanker.gort.theme.Gort

@Composable
fun GortFab(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Gort.colors.primary,
    contentColor: Color = Gort.colors.onPrimary,
    content: @Composable () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val shadow = Gort.shadows.medium
    val (animX, animY) = gortPressableShadow(
        interactionSource = interactionSource,
        shadowOffsetX = shadow.offsetX,
        shadowOffsetY = shadow.offsetY,
    )

    Box(modifier = modifier.size(56.dp)) {
        // Hard offset shadow
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(x = animX, y = animY)
                .background(Gort.colors.shadow)
                .zIndex(-1f),
        )

        // FAB content
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(containerColor)
                .border(Gort.borders.default, Gort.colors.border)
                .hoverable(interactionSource)
                .pointerHoverIcon(PointerIcon.Hand)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick,
                ),
            contentAlignment = Alignment.Center,
        ) {
            content()
        }
    }
}
