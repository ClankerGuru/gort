package zone.clanker.gort.foundation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Neobrutalist indication: no ripple. On press, scale down slightly (push-in effect).
 * Spring back on release.
 */
object GortIndication : IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode {
        return GortIndicationNode(interactionSource)
    }

    override fun hashCode(): Int = -1

    override fun equals(other: Any?): Boolean = other === this
}

private class GortIndicationNode(
    private val interactionSource: InteractionSource,
) : Modifier.Node(), DrawModifierNode {

    private val scaleAnim = Animatable(1f)

    override fun onAttach() {
        coroutineScope.launch {
            interactionSource.interactions.collectLatest { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> {
                        scaleAnim.animateTo(0.97f, spring(dampingRatio = 0.6f, stiffness = 800f))
                    }
                    is PressInteraction.Release, is PressInteraction.Cancel -> {
                        scaleAnim.animateTo(1f, spring(dampingRatio = 0.4f, stiffness = 400f))
                    }
                }
            }
        }
    }

    override fun ContentDrawScope.draw() {
        scale(scaleAnim.value) {
            this@draw.drawContent()
        }
    }
}
