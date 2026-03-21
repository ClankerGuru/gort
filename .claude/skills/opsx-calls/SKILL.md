---
name: opsx-calls
description: "Show the call graph for a symbol. Use when the user wants to understand what calls a function or what a function calls."
license: MIT
compatibility: Requires Gradle build system.
metadata:
  author: "openspec-gradle"
  version: "0.12.0"
  generatedBy: "openspec-gradle:0.12.0"
---

Show the call graph for a symbol — what it calls and what calls it.

---

**Input**: The argument after the command is the symbol name to analyze.

**Steps**

1. Run the calls task:
   ```bash
   ./gradlew opsx-calls -Psymbol=<name>
   ```

2. Read the output at `.opsx/calls.md`

3. Present the call graph to the user.

**Output**

Show the call graph with callers and callees clearly organized.

