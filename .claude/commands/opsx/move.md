---
name: "OPSX: Move"
description: "Move a class/file to a different package"
category: "Code Intelligence"
tags: [refactoring, move]
---

Move a class/file to a different package, updating all imports. Usage: `./gradlew opsx-move -Psymbol=ClassName -PtargetPackage=new.package.name`. Add `-PdryRun=true` to preview without modifying files. Always preview first.
