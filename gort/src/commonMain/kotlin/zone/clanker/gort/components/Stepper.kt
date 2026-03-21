package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(0.dp),
    ) {
        steps.forEachIndexed { index, step ->
            val isComplete = index < currentStep
            val isCurrent = index == currentStep
            val isLast = index == steps.lastIndex

            val circleColor = when {
                isComplete -> colors.success
                isCurrent -> colors.primary
                else -> colors.surface
            }
            val circleContentColor = when {
                isComplete -> colors.onSuccess
                isCurrent -> colors.onPrimary
                else -> colors.onSurface.copy(alpha = 0.4f)
            }
            val textColor = when {
                isComplete || isCurrent -> colors.onSurface
                else -> colors.onSurface.copy(alpha = 0.4f)
            }
            val connectorColor = when {
                isComplete -> colors.success
                else -> colors.border
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
            ) {
                // Left: circle + connector
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(40.dp),
                ) {
                    // Numbered circle
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .border(Gort.borders.default, colors.border, CircleShape)
                            .background(circleColor, CircleShape),
                        contentAlignment = Alignment.Center,
                    ) {
                        BasicText(
                            text = if (isComplete) "✓" else "${index + 1}",
                            style = Gort.typography.label.copy(color = circleContentColor),
                        )
                    }

                    // Connector line
                    if (!isLast) {
                        Box(
                            modifier = Modifier
                                .width(Gort.borders.default)
                                .height(40.dp)
                                .background(connectorColor),
                        )
                    }
                }

                // Right: title + description
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = spacing.sm, top = spacing.xs),
                ) {
                    BasicText(
                        text = step.label,
                        style = Gort.typography.title.copy(color = textColor),
                    )
                    if (step.description.isNotEmpty()) {
                        BasicText(
                            text = step.description,
                            style = Gort.typography.body.copy(color = textColor.copy(alpha = 0.7f)),
                            modifier = Modifier.padding(top = 2.dp),
                        )
                    }
                }
            }
        }
    }
}
