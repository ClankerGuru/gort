package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

data class StepItem(
    val label: String,
    val description: String = "",
)

@Composable
fun Stepper(
    steps: List<StepItem>,
    currentStep: Int,
    modifier: Modifier = Modifier,
) {
    val colors = Gort.colors
    val spacing = Gort.spacing

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spacing.xs),
        verticalAlignment = Alignment.Top,
    ) {
        steps.forEachIndexed { index, step ->
            val isComplete = index < currentStep
            val isCurrent = index == currentStep
            val circleColor = when {
                isComplete -> colors.success
                isCurrent -> colors.primary
                else -> colors.surface
            }
            val textColor = when {
                isComplete || isCurrent -> colors.onSurface
                else -> colors.onSurface.copy(alpha = 0.4f)
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Step circle
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .border(Gort.borders.default, colors.border, Gort.corners.default)
                        .background(circleColor, Gort.corners.default),
                    contentAlignment = Alignment.Center,
                ) {
                    BasicText(
                        text = if (isComplete) "✓" else "${index + 1}",
                        style = Gort.typography.label.copy(
                            color = if (isComplete) colors.onSuccess else if (isCurrent) colors.onPrimary else colors.onSurface,
                        ),
                    )
                }
                Spacer(Modifier.height(spacing.xs))
                BasicText(
                    text = step.label,
                    style = Gort.typography.label.copy(color = textColor),
                )
                if (step.description.isNotEmpty()) {
                    BasicText(
                        text = step.description,
                        style = Gort.typography.body.copy(color = textColor.copy(alpha = 0.7f)),
                    )
                }
            }

            // Connector line
            if (index < steps.lastIndex) {
                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .height(Gort.borders.default)
                        .background(if (isComplete) colors.success else colors.border)
                        .align(Alignment.CenterVertically),
                )
            }
        }
    }
}
