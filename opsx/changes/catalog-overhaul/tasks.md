# Tasks: Catalog Overhaul

## Navigation

- [/] `co-1` agent:github retries:2 cooldown:30 — Add Navigation 3 dependency to catalog module and replace enum-based navigation with sealed class routes and NavDisplay
- [ ] `co-2` agent:github retries:2 cooldown:30 depends:co-1 — Wire bottom nav bar to Navigation 3 back stack with proper back handling
- [ ] `co-3` agent:github retries:1 depends:co-2 — Make Chat route fullscreen by hiding bottom nav when Chat is on the back stack
