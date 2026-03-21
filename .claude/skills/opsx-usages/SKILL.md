---
name: opsx-usages
description: "Find all usages of a symbol with exact file:line locations. Use when the user wants to see where a symbol is referenced."
license: MIT
compatibility: Requires Gradle build system.
metadata:
  author: "openspec-gradle"
  version: "0.12.0"
  generatedBy: "openspec-gradle:0.12.0"
---

Find all usages of a symbol with exact file:line locations.

## Steps

1. Run: `./gradlew opsx-usages -Psymbol=ClassName`
2. Review `.opsx/usages.md` for the full usage report

## Output Format

Each usage shows:
- **file:line** — exact location
- **kind** — import, call, type-ref, supertype, self-reference
- **context** — the line of code

## Use Cases

- Before rename/move: understand impact
- Dead code detection: symbols with no usages
- API surface analysis: who depends on this?
- For multi-module projects: `-Pmodule=:moduleName`

