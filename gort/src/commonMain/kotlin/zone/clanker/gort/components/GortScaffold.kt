package zone.clanker.gort.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import zone.clanker.gort.theme.Gort

@Composable
fun GortScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Gort.colors.background)
            .windowInsetsPadding(WindowInsets.systemBars),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            topBar()
            Box(modifier = Modifier.weight(1f)) {
                content(PaddingValues())
                snackbarHost()
            }
            bottomBar()
        }
    }
}
