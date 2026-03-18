package zone.clanker.gort.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

@Composable
fun Toggle(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val colors = Gort.colors
    val anim = Gort.animation
    val trackShape = RoundedCornerShape(4.dp)
    val thumbShape = RoundedCornerShape(2.dp)

    val thumbOffset by animateDpAsState(
        targetValue = if (checked) 20.dp else 0.dp,
        animationSpec = tween(anim.durationMedium),
    )
    val trackColor = if (checked) colors.primary else colors.surface

    Box(
        modifier = modifier
            .size(width = 48.dp, height = 28.dp)
            .clip(trackShape)
            .background(trackColor, trackShape)
            .border(Gort.borders.thick, colors.border, trackShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                enabled = enabled,
            ) { onCheckedChange(!checked) }
            .padding(4.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Box(
            modifier = Modifier
                .offset(x = thumbOffset)
                .size(18.dp)
                .clip(thumbShape)
                .background(if (checked) colors.onPrimary else colors.border, thumbShape)
                .border(Gort.borders.normal, colors.border, thumbShape),
        )
    }
}
