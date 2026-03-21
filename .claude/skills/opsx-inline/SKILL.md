---
name: opsx-inline
description: "Inline a function or class — replace call sites with the implementation body. The reverse of extract."
license: MIT
compatibility: Requires Gradle build system.
metadata:
  author: "openspec-gradle"
  version: "0.12.0"
  generatedBy: "openspec-gradle:0.12.0"
---

Inline a function/method — replace call sites with the implementation body. This is the reverse of extract.

## Steps

1. Find the implementation: `./gradlew opsx-find -Psymbol=functionName`
2. Find all call sites: `./gradlew opsx-usages -Psymbol=functionName`
3. For each call site:
   - Replace the function call with the function body
   - Substitute parameters with the actual arguments
   - Adjust variable names to avoid conflicts
4. Delete the original function
5. Verify: `./gradlew opsx-usages -Psymbol=functionName` — should show zero results
6. Build to verify: `./gradlew build`

## When to Inline

- Function is trivial (1-2 lines) and called in few places
- Function adds indirection without abstraction value
- Simplifying before a larger refactoring

## Caution

- Don't inline functions with side effects called from multiple sites
- Watch for parameter evaluation order changes
- Check for overrides — don't inline virtual methods

