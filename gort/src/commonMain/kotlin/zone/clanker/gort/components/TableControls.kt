package zone.clanker.gort.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zone.clanker.gort.icons.lucide.*
import zone.clanker.gort.theme.Gort

/**
 * Small dark circle that hangs from the bottom of a table/component.
 * Looks like a shadow drip. Tap to expand controls.
 */
@Composable
internal fun ShadowDrop(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = Gort.colors
    val haptic = LocalHapticFeedback.current
    val shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)

    Box(
        modifier = modifier
            .background(colors.shadow, shape)
            .clickable {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onClick()
            }
            .padding(horizontal = 10.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            imageVector = Lucide.Ellipsis,
            contentDescription = "Show controls",
            modifier = Modifier.size(18.dp),
            colorFilter = ColorFilter.tint(Color.White),
        )
    }
}

/**
 * Shadow-drop control bar that hangs from the bottom of a component.
 * Dark background, circular icon buttons, looks like a shadow extension.
 */
@Composable
internal fun ShadowControlBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    val colors = Gort.colors
    val shadowShape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)

    Row(
        modifier = modifier
            .background(colors.shadow, shadowShape)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium,
                ),
            )
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        content = content,
    )
}

/**
 * Circular button for use inside ShadowControlBar.
 */
@Composable
internal fun CircleButton(
    icon: ImageVector,
    contentDescription: String,
    enabled: Boolean = true,
    selected: Boolean = false,
    onClick: () -> Unit = {},
) {
    val colors = Gort.colors
    val haptic = LocalHapticFeedback.current
    val bg = when {
        selected -> colors.accent
        else -> Color.Transparent
    }
    val tint = when {
        !enabled -> colors.onSurface.copy(alpha = 0.2f)
        selected -> colors.onPrimary
        else -> Color.White
    }

    Box(
        modifier = Modifier
            .size(36.dp)
            .background(bg, CircleShape)
            .then(
                if (enabled) Modifier.clickable {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    onClick()
                } else Modifier
            ),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(16.dp),
            colorFilter = ColorFilter.tint(tint),
        )
    }
}

/**
 * Circular numbered page button.
 */
@Composable
internal fun PageButton(
    page: Int,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val colors = Gort.colors
    val haptic = LocalHapticFeedback.current
    val bg = if (selected) colors.accent else Color.Transparent
    val textColor = if (selected) colors.onPrimary else Color.White

    Box(
        modifier = Modifier
            .size(36.dp)
            .background(bg, CircleShape)
            .clickable {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                onClick()
            },
        contentAlignment = Alignment.Center,
    ) {
        BasicText(
            text = "${page + 1}",
            style = Gort.typography.label.copy(
                color = textColor,
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
            ),
        )
    }
}
