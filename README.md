# GORT

> *A neobrutalist design system for Compose Multiplatform.*

**Bold. Loud. Unapologetic.**

Gort is an opinionated UI component library that replaces Material 3 with thick borders, hard offset shadows, flat colors, and monospace type. Built on Compose Foundation — no Material dependency. Works on Android, iOS, Desktop, and Web.

Named after the 8-foot robot from *The Day the Earth Stood Still* (1951). Silent. Powerful. Takes no prisoners.

## Install

```kotlin
// build.gradle.kts
dependencies {
    implementation("zone.clanker:gort:<version>")
}
```

## Quick Start

```kotlin
GortTheme {
    GortButton(onClick = { /* destroy humanity */ }) {
        Text("LAUNCH")
    }
}
```

## Custom Theme

Swap the colors. Keep the structure. It still looks good.

```kotlin
GortTheme(
    colors = GortColors.light(
        primary = Color(0xFF8B5CF6),   // your brand
        secondary = Color(0xFF10B981),
    )
) {
    // All components now use your palette
    // but stay neobrutalist
}
```

## Accessibility

One-line toggle to OpenDyslexic for dyslexia-friendly reading:

```kotlin
GortTheme(
    typography = GortTypography.openDyslexic()
) {
    // All text now uses OpenDyslexic font
}
```

## Components

### Core
- `GortButton` — Primary, secondary, tertiary, outlined
- `GortCard` — Bordered card with offset shadow
- `GortTextField` — Thick-bordered input
- `GortToggle` — Mechanical switch
- `GortCheckbox` — Chunky checkmark
- `GortChip` — Tag/label
- `GortDivider` — Thick rule
- `GortBadge` — Notification dot

### Layout
- `GortScaffold` — App shell
- `GortTopBar` — Nav bar with thick bottom border
- `GortBottomBar` — Tab bar with thick top border

### Feedback
- `GortDialog` — Modal with shadow
- `GortSnackbar` — Toast notification
- `GortTooltip` — Info popup

## Design Tokens

| Token | Default |
|-------|---------|
| Border | 3dp solid |
| Shadow | 4dp × 4dp hard offset |
| Corners | 4dp radius |
| Font | JetBrains Mono |
| Accessibility Font | OpenDyslexic |
| Press animation | 80ms snap |

## Platforms

- ✅ Android
- ✅ iOS (arm64 + simulator)
- ✅ Desktop (JVM)
- ✅ Web (Kotlin/WASM)

## Philosophy

Material 3 is fine. It's also everywhere. Every Compose app looks the same.

Gort is for apps that want to look like they were designed by a 1950s robot with strong opinions about typography and an unhealthy attachment to thick borders.

> *"Klaatu barada nikto."*

## License

MIT © [ClankerGuru](https://github.com/ClankerGuru)
