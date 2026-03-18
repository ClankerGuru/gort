package zone.clanker.gort.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import zone.clanker.gort.theme.Gort

@Composable
fun Rating(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    max: Int = 5,
    filledIcon: String = "★",
    emptyIcon: String = "☆",
) {
    val colors = Gort.colors

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Gort.spacing.xs),
    ) {
        repeat(max) { index ->
            val isFilled = index < value
            BasicText(
                text = if (isFilled) filledIcon else emptyIcon,
                style = Gort.typography.headline.copy(
                    color = if (isFilled) colors.warning else colors.onSurface.copy(alpha = 0.3f),
                ),
                modifier = Modifier.clickable { onValueChange(index + 1) },
            )
        }
    }
}
