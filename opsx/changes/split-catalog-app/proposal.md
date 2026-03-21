# Proposal: Split CatalogApp.kt

## Summary
`CatalogApp.kt` (281 lines, 26 imports) contains the section enum, app shell, masthead, nav rail, bottom nav bar, content router, and utility composables — all in one file. Split into focused single-responsibility files.

## Motivation
- `opsx-verify` flags high import count
- Every catalog change touches this one file
- Navigation, theming, and content routing are unrelated concerns sharing a file

## Scope
**In scope:** Extract composables and the enum into separate files within the same package.
**Out of scope:** Behavior changes, new features, theme modifications, build file changes.
