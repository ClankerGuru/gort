package zone.clanker.gort.catalog

import androidx.compose.runtime.Composable

@Composable
actual fun PlatformBackHandler(enabled: Boolean, onBack: () -> Unit) {
    // No-op on desktop — back gesture is mobile-only
}
