package zone.clanker.gort.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import zone.clanker.gort.icons.lucide.Lucide
import zone.clanker.gort.icons.lucide.X
import zone.clanker.gort.theme.Gort

enum class TagMode { Display, Filter, Removable, Disabled }

@Composable
fun Tag(
    text: String,
    modifier: Modifier = Modifier,
    mode: TagMode = TagMode.Display,
    selected: Boolean = false,
    color: Color = Gort.colors.primary,
    textColor: Color = Gort.colors.onPrimary,
    onClick: (() -> Unit)? = null,
    onRemove: (() -> Unit)? = null,
) {
    val colors = Gort.colors
    val isDisabled = mode == TagMode.Disabled
    val bgColor = when {
        isDisabled -> colors.surface
        selected && mode == TagMode.Filter -> color
        mode == TagMode.Filter -> colors.surface
        else -> color
    }
    val fgColor = when {
        isDisabled -> colors.onSurface.copy(alpha = 0.4f)
        selected && mode == TagMode.Filter -> textColor
        mode == TagMode.Filter -> colors.onSurface
        else -> textColor
    }

    Row(
        modifier = modifier
            .defaultMinSize(minHeight = 22.dp)
            .border(Gort.borders.thin, colors.border, Gort.corners.small)
            .background(bgColor, Gort.corners.small)
            .then(
                if (onClick != null && !isDisabled) Modifier.clickable(onClick = onClick)
                else Modifier
            )
            .padding(horizontal = 8.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        BasicText(
            text = text,
            style = Gort.typography.labelSmall.copy(color = fgColor),
        )

        if (mode == TagMode.Removable && onRemove != null) {
            Image(
                imageVector = Lucide.X,
                contentDescription = "Remove",
                colorFilter = ColorFilter.tint(fgColor),
                modifier = Modifier
                    .size(10.dp)
                    .clickable(onClick = onRemove),
            )
        }
    }
}
