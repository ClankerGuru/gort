package zone.clanker.gort.foundation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

/**
 * Responsive breakpoints for adaptive layouts.
 * - Compact: < 600dp (phone)
 * - Medium: 600-1200dp (tablet)
 * - Expanded: > 1200dp (desktop)
 */
enum class WindowSize { Compact, Medium, Expanded }

val LocalWindowSize = compositionLocalOf { WindowSize.Expanded }

/**
 * Determines the current [WindowSize] from the available width.
 * Must be called inside [BoxWithConstraints] or similar constraints-aware scope.
 */
fun BoxWithConstraintsScope.currentWindowSize(): WindowSize = when {
    maxWidth < 600.dp -> WindowSize.Compact
    maxWidth < 1200.dp -> WindowSize.Medium
    else -> WindowSize.Expanded
}
