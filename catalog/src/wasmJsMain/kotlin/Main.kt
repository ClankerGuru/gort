import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import zone.clanker.gort.catalog.CatalogApp

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val root = document.getElementById("ComposeTarget") ?: document.body!!
    ComposeViewport(root) {
        CatalogApp()
    }
}
