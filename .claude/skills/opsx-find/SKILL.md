---
name: opsx-find
description: "Find a symbol by name in the project. Use when the user wants to locate a class, function, or variable."
license: MIT
compatibility: Requires Gradle build system.
metadata:
  author: "openspec-gradle"
  version: "0.12.0"
  generatedBy: "openspec-gradle:0.12.0"
---

Find a symbol (class, function, property) by name in the project.

---

**Input**: The argument after the command is the symbol name to search for.

**Steps**

1. Run the find task:
   ```bash
   ./gradlew opsx-find -Psymbol=<name>
   ```

2. Read the output at `.opsx/find.md`

3. Present the results to the user — show matching symbols with their locations and types.

**Output**

Show the matching symbols in a clear format with file paths and line numbers.

