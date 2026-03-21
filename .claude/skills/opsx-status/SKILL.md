---
name: opsx-status
description: "Show status of all open changes and proposals. Use when the user wants to see what changes are in progress."
license: MIT
compatibility: Requires Gradle build system.
metadata:
  author: "openspec-gradle"
  version: "0.12.0"
  generatedBy: "openspec-gradle:0.12.0"
---

Show the status of all open changes and proposals.

---

**Steps**

1. Run the status task:
   ```bash
   ./gradlew opsx-status
   ```

2. Read the output at `.opsx/status.md`

3. Present the status dashboard to the user.

**Output**

Show all active changes with their current status, progress, and next steps.

