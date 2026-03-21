---
name: "OPSX: ip-3"
description: "Update the Gort release workflow to publish both `:gort` and `:icons` modules. In `.github/workflows/release.yml`, change the publish step from `:gort:publishAllPublicationsToMavenCentralRepository` to publish both modules: first `:icons:publishAllPublicationsToMavenCentralRepository` then `:gort:publishAllPublicationsToMavenCentralRepository` (icons must publish first since gort depends on it). File: `.github/workflows/release.yml`. Verify: workflow YAML is valid. (icons-publishing)"
category: Task
tags: [task, icons-publishing]
---

✅ **ip-3**: Update the Gort release workflow to publish both `:gort` and `:icons` modules. In `.github/workflows/release.yml`, change the publish step from `:gort:publishAllPublicationsToMavenCentralRepository` to publish both modules: first `:icons:publishAllPublicationsToMavenCentralRepository` then `:gort:publishAllPublicationsToMavenCentralRepository` (icons must publish first since gort depends on it). File: `.github/workflows/release.yml`. Verify: workflow YAML is valid.

**Proposal:** icons-publishing

## Context

Read these files before starting:
- `opsx/changes/icons-publishing/proposal.md` — what & why
- `opsx/changes/icons-publishing/design.md` — how
- `opsx/changes/icons-publishing/tasks.md` — all tasks & progress

## Implementation

1. Read the context files above
2. Implement this task: **Update the Gort release workflow to publish both `:gort` and `:icons` modules. In `.github/workflows/release.yml`, change the publish step from `:gort:publishAllPublicationsToMavenCentralRepository` to publish both modules: first `:icons:publishAllPublicationsToMavenCentralRepository` then `:gort:publishAllPublicationsToMavenCentralRepository` (icons must publish first since gort depends on it). File: `.github/workflows/release.yml`. Verify: workflow YAML is valid.**
3. When complete, mark done: `./gradlew opsx-ip-3 --set=done`

