package zone.clanker.gort.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import zone.clanker.gort.theme.Gort

@Composable
fun GortListDetail(
    listContent: @Composable () -> Unit,
    detailContent: @Composable () -> Unit,
    showDetail: Boolean = false,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val width = maxWidth
        when {
            // Expanded: >1200dp — list 1/3, detail 2/3
            width > 1200.dp -> {
                Row(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.weight(1f).fillMaxHeight()) { listContent() }
                    GortDivider(vertical = true, thickness = Gort.borders.heavy)
                    Box(modifier = Modifier.weight(2f).fillMaxHeight()) { detailContent() }
                }
            }
            // Medium: 600-1200dp — list 2/5, detail 3/5
            width >= 600.dp -> {
                Row(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.weight(2f).fillMaxHeight()) { listContent() }
                    GortDivider(vertical = true, thickness = Gort.borders.heavy)
                    Box(modifier = Modifier.weight(3f).fillMaxHeight()) { detailContent() }
                }
            }
            // Compact: <600dp — single pane with animation
            else -> {
                AnimatedContent(
                    targetState = showDetail,
                    transitionSpec = {
                        if (targetState) {
                            (slideInHorizontally { it } + fadeIn()) togetherWith
                                (slideOutHorizontally { -it } + fadeOut())
                        } else {
                            (slideInHorizontally { -it } + fadeIn()) togetherWith
                                (slideOutHorizontally { it } + fadeOut())
                        }
                    },
                ) { detail ->
                    Box(modifier = Modifier.fillMaxSize()) {
                        if (detail) detailContent() else listContent()
                    }
                }
            }
        }
    }
}
