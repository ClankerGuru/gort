# Proposal: Gort Charts — Neobrutalist Infographic Components

## Vision

Gort Charts is a set of **pure Compose Canvas chart components** styled in the neobrutalist aesthetic. Think: newspaper infographics, nutritional labels, vintage data visualizations, comic-book dashboards. Thick borders, flat bold colors, hard shadows, monospace labels, square data points.

**Not a general-purpose charting library.** This is an opinionated, style-first approach — charts that look like they belong in a printed zine or editorial broadsheet. The goal is infographic-quality visuals that render identically across Android, Desktop, WASM, iOS.

## Prior Art & Research

### MPAndroidChart (PhilJay)
- **38k+ stars**, the OG Android chart library
- Chart types: Line, Bar, Pie, Radar, Bubble, Candlestick, Scatter, Combined
- Features: touch gestures (zoom/pan), animations, legends, axis formatting, highlight/selection
- Architecture: `Chart` base class → `BarLineChartBase` → specific charts. Data model: `DataSet` → `Entry` objects
- **Android-only, XML Views.** No KMP, no Compose. Effectively abandoned (last release 2020).
- What we steal: chart type coverage, gesture patterns, data model concepts

### KoalaPlot (KMP)
- Compose Multiplatform native, published on Maven Central
- Chart types: Pie/Donut, Line, Bar (vertical/horizontal), Stacked Area, Polar/Radar
- Features: zoom/pan, annotations, hover tracking, composable customization at every level
- Still developmental (0.x), API may change
- **Generic/unstyled** — designed for any theme. No personality.

### Netguru compose-multiplatform-charts
- Basic KMP charts (line, bar, pie)
- Minimal, not heavily maintained

### Gap We Fill
Every existing KMP chart library is **style-agnostic**. They provide raw data visualization with customizable styling. Nobody provides charts with a **strong visual identity** out of the box. Gort Charts are neobrutalist by default — thick strokes, flat fills, hard shadows, bold labels. Zero configuration needed to get a chart that looks *sick*.

## Chart Types

### Phase 1 — Core (8 components)

| Component | Description | MPAndroidChart equivalent |
|-----------|-------------|--------------------------|
| `BarChart` | Vertical bars with thick black outlines, flat fill, hard shadow per bar. Supports grouped & stacked. | BarChart |
| `HorizontalBarChart` | Same but horizontal — great for rankings/leaderboards. | HorizontalBarChart |
| `LineChart` | Thick lines with square data points (not circles). Graph paper grid. Area fill option. | LineChart |
| `PieChart` | Flat slices with thick black borders. Exploded slice support. Labels with leader lines. | PieChart |
| `DonutChart` | Pie with a hole — center composable slot for big number/label. | PieChart (hole) |
| `BubbleChart` | Bordered circles on XY plane, size-mapped to value. | BubbleChart |
| `RadarChart` | Spider/radar chart with thick polygon outlines, filled areas. | RadarChart |
| `ScatterPlot` | XY scatter with square/cross/triangle markers (not circles). | ScatterChart |

### Phase 2 — Infographic Primitives (6 components)

| Component | Description |
|-----------|-------------|
| `StatCard` | Big bold number + label + trend indicator (↑↓→). KPI dashboard tile with hard shadow. |
| `ProgressBar` | Chunky segmented bar with thick borders. Think: XP bar from a retro game. |
| `Sparkline` | Tiny inline chart for embedding in tables/cards. No axes, just the line. |
| `GaugeChart` | Semicircle gauge with needle. Think: speedometer on a zine. |
| `Legend` | Color swatches with labels, neobrutalist card style. Standalone composable. |
| `DataLabel` | Nutritional-label-style key/value pair with ruled line between. |

### Phase 3 — Advanced (4 components)

| Component | Description |
|-----------|-------------|
| `CandlestickChart` | OHLC financial chart. Thick candle bodies, square wicks. |
| `TreemapChart` | Nested rectangles with thick borders. Great for showing proportions. |
| `WaterfallChart` | Running total with positive/negative segments. |
| `CombinedChart` | Overlay line + bar on same axes. |

## Architecture

### Data Model
```kotlin
// Minimal, Kotlin-idiomatic
data class ChartEntry(val x: Float, val y: Float, val label: String? = null)
data class ChartDataSet(
    val entries: List<ChartEntry>,
    val label: String? = null,
    val color: Color? = null, // null = use theme sequence
)

// Pie-specific
data class PieSlice(val value: Float, val label: String? = null, val color: Color? = null)
```

### Rendering
- **Pure `Canvas` composables** — no Android Views, no third-party rendering
- All drawing uses `DrawScope` with `Gort.colors`, `Gort.borders`, `Gort.shadows`
- Thick strokes everywhere: `StrokeCap.Square`, `StrokeJoin.Miter` (sharp corners)
- Grid lines as dashed patterns via `PathEffect.dashPathEffect`
- Hard offset shadows drawn as separate shapes behind data elements
- Labels in `Gort.typography.code` (monospace) for that data/infographic feel

### Theming
- Colors auto-sequence from `Gort.colors`: primary → secondary → tertiary → accent → then generated palette
- Dark mode: shadows flip to glow (consistent with existing Gort dark mode)
- All charts respect `GortColorScheme.fromSeed()` — custom seed = custom chart palette

### Animation
- Entry animations with spring bounce (consistent with rest of Gort)
- Bars grow from zero, slices sweep from 0° to final angle, lines draw left-to-right
- `animateFloatAsState` with `DampingRatioMediumBouncy` everywhere

### Interaction (Phase 2+)
- Tap to highlight / select data point
- Haptic feedback on selection
- Tooltip composable slot on hover/tap
- No zoom/pan in Phase 1 (keep it simple for infographics)

## Module Structure

Charts live in the existing `:gort` module under a new package:
```
zone.clanker.gort.charts/
├── BarChart.kt
├── HorizontalBarChart.kt
├── LineChart.kt
├── PieChart.kt
├── DonutChart.kt
├── BubbleChart.kt
├── RadarChart.kt
├── ScatterPlot.kt
├── StatCard.kt
├── ProgressBar.kt
├── Sparkline.kt
├── GaugeChart.kt
├── Legend.kt
├── DataLabel.kt
├── ChartDefaults.kt      // color sequences, default styling
└── model/
    ├── ChartEntry.kt
    └── PieSlice.kt
```

No new module — charts are part of the Gort design system, not a separate dependency.

## Catalog Integration

New catalog section: **"Charts"** (icon: `Lucide.ChartBar` or `Lucide.BarChart3`)

Each chart gets a `ComponentShowcase` card with:
- Live interactive demo with sample data
- Randomize button to regenerate data (shows animation)
- View Code expandable

Section layout:
1. Core Charts (Bar, Line, Pie, Donut, Scatter, Bubble, Radar)
2. Infographic Primitives (StatCard, ProgressBar, Sparkline, Gauge, DataLabel)

## Design Principles

1. **Style over flexibility.** Every chart looks neobrutalist by default. You can tweak colors but you can't make it look like a Material chart.
2. **Flat is king.** No gradients, no 3D effects, no rounded anything. Square caps, sharp corners, thick strokes.
3. **Data ink ratio.** Minimal chrome, maximum data. Axes are thin, data elements are bold.
4. **Infographic-first.** Designed for static display (documentation, dashboards, websites), not for financial trading terminals.
5. **Compose-native.** Pure Canvas drawing, no bitmap rendering, no third-party deps. Scales to any size.

## Dependencies

**Zero new dependencies.** Everything drawn with Compose Foundation Canvas API.

## Effort Estimate

- Phase 1 (8 core charts): ~2-3 sessions
- Phase 2 (6 infographic primitives): ~1-2 sessions  
- Phase 3 (4 advanced charts): ~1-2 sessions
- Catalog integration: alongside each phase

## Open Questions

1. Should charts support a `Modifier.gortShadow()` on the entire chart card, or just shadow individual data elements?
2. Axis label formatting — provide default formatters or require user-supplied lambdas?
3. Should `Sparkline` auto-detect min/max or require explicit range?
4. Treemap in Phase 3 — worth the complexity, or skip?
