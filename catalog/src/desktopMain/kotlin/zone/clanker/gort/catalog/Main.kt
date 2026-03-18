package zone.clanker.gort.catalog

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Gort — Component Catalog",
    ) {
        CatalogApp()
    }
}
