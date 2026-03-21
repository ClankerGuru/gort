# Proposal: Gort Catalog — The Neobrutalist Gazette

## Vision

The Gort Catalog isn't a test harness. It's a **newspaper** — *The Gort Gazette*. A broadsheet on desktop, a tabloid on mobile. Black ink on cream paper. Bold headlines in monospace. Thick ruled lines between sections. Components displayed like editorial illustrations — each one a feature story with a headline, a description, a live demo, and the code to reproduce it.

Think: 1950s newspaper meets comic book meets Swiss poster design. Every screen should make someone say *"wait, this is a component library?"*

---

## The Aesthetic

### Typography Hierarchy (Newspaper Rules)
- **Masthead**: Extra-bold, tracked-out monospace. "GORT GAZETTE" across the top.
- **Section Headers**: ALL CAPS monospace with ruled lines above and below, like newspaper section dividers ("ACTIONS", "INPUTS", "DISPLAY").
- **Component Headlines**: Bold monospace, large. Like article headlines.
- **Body/Description**: Regular monospace, smaller. The lede paragraph.
- **Code**: Monospace in a bordered inset box — like a newspaper pull quote.
- **Labels/Captions**: Small caps or italic monospace, muted color.

### Layout (Editorial Grid)
- **Desktop (>1200dp)**: 3-column newspaper grid. Nav rail on left (thin, icon-only). Main content in center-left (wide). Right column for theme controls / component properties panel.
- **Tablet (600-1200dp)**: 2-column. Bottom nav bar. Content fills width. Properties panel collapses into an expandable drawer.
- **Phone (<600dp)**: Single column. Bottom nav bar. Full-width component cards stacked vertically. Everything scrolls together.

### Component Cards ("Articles")
Each component is presented as a newspaper article:

```
╔══════════════════════════════════════╗
║ BUTTON                         [⚙️] ║  ← headline + settings toggle
╠══════════════════════════════════════╣
║ The workhorse of user interaction.   ║  ← description/lede
║ Neobrutalist buttons use hard        ║
║ shadows that collapse on press.      ║
╠──────────────────────────────────────╣
║                                      ║
║   ┌─────────────────┐               ║  ← live demo area
║   │ Primary Button  │░░             ║     (cream inset background)
║   └─────────────────┘               ║
║                                      ║
╠──────────────────────────────────────╣
║ Variant: [Primary|Secondary|Outline] ║  ← interactive controls
║ Enabled: [✓]  Size: [M]             ║     (toggle variants live)
╠──────────────────────────────────────╣
║ ▸ View Code                          ║  ← expandable code snippet
╚══════════════════════════════════════╝
```

The card itself uses the Gort `Card` component — thick border, offset shadow. Nested. Dogfooding.

### Color & Mood
- Base: Cream background (`#F5F0E8`), near-black text (`#1A1A1A`)
- Accent: Hot pink (`#FF5470`) — used sparingly for active states, links, highlights
- Rules/borders: Black, thick (2-3dp). Neobrutalist signature.
- Section dividers: Full-width horizontal rules with decorative elements (■ dots, ═ double lines)
- Dark mode: Charcoal (`#1A1A1A`) background, cream text, same pink accent. Like a newspaper negative.

### Signature Touches
- **Page number in footer**: "Page 1 of 8 — © 2026 Clanker Zone" with a ruled line above
- **"Dateline" in header**: "Vol. 1, No. 1 — March 2026 — 52 Components"
- **Pull quotes**: For important design notes, use the Callout component styled as a newspaper pull quote
- **Column rules**: Thin vertical lines between columns on desktop (like actual newspaper columns)

---

## Architecture

### Phase 1: Responsive Shell + Masthead

**The skeleton that makes everything else work.**

`CatalogApp.kt` rewrite:
- `BoxWithConstraints` to detect width
- Three layout modes: phone / tablet / desktop
- **Masthead** (top bar replacement): "GORT GAZETTE" in tracked monospace, dark/light toggle as a small icon, dateline subtitle
- **Phone**: Bottom nav bar with 8 section icons (no labels on phone, labels on tablet)
- **Tablet**: Bottom nav bar with labels
- **Desktop**: Left nav rail (icons + labels, vertical), thin, with column rule separator

New file: `foundation/WindowSize.kt` in the library:
```kotlin
enum class WindowSize { Compact, Medium, Expanded }

@Composable
fun rememberWindowSize(): WindowSize
```

This goes in the `:gort` library, not just the catalog — every Gort user needs responsive breakpoints.

### Phase 2: ComponentShowcase Pattern

**The reusable "article" card for every component.**

New composable: `ComponentShowcase`
```kotlin
@Composable
fun ComponentShowcase(
    name: String,
    description: String,
    code: String,            // Kotlin source to display
    controls: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit,
)
```

Features:
- Headline + description text
- Centered demo area with subtle inset background
- Optional controls row (dropdowns, toggles for variant switching)
- Expandable code block using our `CodeBlock` component
- The whole thing wrapped in a `Card` with neobrutalist shadow

Rewrite all 8 screen files to use `ComponentShowcase` instead of raw `SectionTitle` + `ComponentLabel` + component.

### Phase 3: Press & Touch States (Library)

**Make every component feel alive on mobile.**

Add to `:gort` library:

1. **`GortIndication`** — custom `Indication` implementation:
   - No ripple (that's Material's thing)
   - On press: shadow offset animates to `0,0` (button "pushes into" the page)
   - On press: slight scale down (0.97x)
   - On release: spring back

2. **Press state in components**:
   - `Button`: Shadow collapses on press, springs back on release
   - `IconButton`: Border thickens on press
   - `Chip`: Background darkens on press
   - `Card`: Shadow reduces on press
   - `Accordion`: Header background shifts on press
   - `ListItem`: Background highlight on press

3. **`gortPressable()` modifier** in `InteractionModifiers.kt`:
   ```kotlin
   fun Modifier.gortPressable(
       interactionSource: MutableInteractionSource,
       shadowOffset: Dp = Gort.shadows.offset,
   ): Modifier  // Animates shadow offset 0 on press
   ```

### Phase 4: GortColorScheme — Seed-Based Palette Generation

**One color in, full neobrutalist palette out.**

New file: `theme/GortColorScheme.kt`

```kotlin
object GortColorScheme {
    fun fromSeed(seed: Color, isDark: Boolean = false): GortColors
    
    // Curated presets
    fun coral(): GortColors      // current default (pink/green/yellow)
    fun ocean(): GortColors      // navy/teal/amber
    fun forest(): GortColors     // emerald/brown/gold
    fun neon(): GortColors       // electric green/magenta/cyan
    fun mono(): GortColors       // pure B&W, red accent only
    fun gazette(): GortColors    // newspaper special — sepia/black/burgundy
}
```

Algorithm (neobrutalist-specific, NOT Material):
1. Seed → `primary` (as-is, full saturation)
2. `secondary` → 120° hue rotation (triadic)
3. `tertiary` → 240° hue rotation (triadic)
4. `accent` → 180° hue rotation (complementary)
5. Containers → seed tinted onto cream (light) or charcoal (dark) at 15-25% opacity
6. Background/surface → always cream/charcoal (neobrutalism rule: neutral base)
7. Semantic colors (error/success/warning) → fixed (red/green/yellow are universal)
8. Border/shadow → always near-black (light) or near-white (dark)

### Phase 5: Typography Customization

**Let users bring their own fonts.**

Update `GortTypography`:
```kotlin
data class GortTypography(
    val fontFamily: FontFamily = FontFamily.Monospace,
    val displayFontFamily: FontFamily = fontFamily,  // for headlines
    // ... existing TextStyles derived from families
) {
    companion object {
        fun monospace(): GortTypography          // default, techy
        fun editorial(): GortTypography          // serif headlines, mono body (newspaper mode)
        fun sansSerif(): GortTypography          // clean, modern
        fun withFonts(
            body: FontFamily,
            display: FontFamily = body,
        ): GortTypography
    }
}
```

### Phase 6: Theme Playground Screen

**New catalog section: "🎨 Theme" — the first section in the nav.**

Features:
- **Seed color picker** using our `ColorPicker` component
- **Preset palette buttons** (coral, ocean, forest, neon, mono, gazette)
- **Font family selector** (monospace / editorial / sans-serif)
- **Live preview strip** showing Button, Card, TextField, Badge, ChatBubble with current theme
- **Border weight slider** (1-4dp)
- **Shadow offset slider** (2-8dp)
- **"Copy Theme Code" button** that generates:
  ```kotlin
  GortTheme(
      colors = GortColorScheme.fromSeed(Color(0xFF4CC9F0)),
      typography = GortTypography.editorial(),
      borders = GortBorders(width = 3.dp),
      shadows = GortShadows(offsetX = 4.dp, offsetY = 4.dp),
  ) { ... }
  ```

### Phase 7: Visual Fixes & Polish

**All the bugs from the roast, cleaned up.**

1. **Card shadow clipping** → Add `clipToBounds = false` and padding for shadow overflow
2. **Icon buttons** → Replace emoji-in-box with proper drawn icons (simple geometric shapes via Canvas — circle, square, star, arrow, etc.)
3. **Segmented control double borders** → Shared border between segments
4. **Snackbar shadow** → Constrain shadow to snackbar width, not full screen
5. **Dialog scrim** → Proper semi-transparent black overlay
6. **Marquee clipping** → Fade edges instead of hard clip
7. **Chat send button** → Draw a proper arrow icon via Canvas
8. **"49" counter bleed** → Fix stray text in top bar
9. **Content max-width** → Cap content at 800dp on wide screens (like newspaper column width)

---

## Execution Plan

| Phase | What | Where | Est. Lines |
|-------|------|-------|-----------|
| 1 | Responsive shell + masthead | catalog + gort/foundation | ~300 |
| 2 | ComponentShowcase + screen rewrites | catalog | ~800 |
| 3 | Press/touch states + GortIndication | gort/foundation + components | ~250 |
| 4 | GortColorScheme generator | gort/theme | ~200 |
| 5 | Typography customization | gort/theme | ~80 |
| 6 | Theme playground screen | catalog | ~350 |
| 7 | Visual fixes & polish | gort/components + catalog | ~200 |
| **Total** | | | **~2,180** |

### Deployment
- Android APK → direct install / Play Store later
- WASM → deploy to `gort.clanker.zone` via Coolify
- Desktop → downloadable JAR
- The catalog IS the documentation. The catalog IS the marketing. Make it beautiful.

### Priority Order
1 → 7 → 3 → 2 → 4 → 5 → 6

Responsive shell first (unblock mobile), then fix visual bugs (stop the embarrassment), then press states (make it feel alive), then the big showcase rewrite, then theme generation, typography, and finally the playground as the cherry on top.
