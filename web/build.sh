#!/bin/bash
# Builds docs/foodbox.jar for the browser demo (GitHub Pages + CheerpJ).
# Run from the project root:  ./web/build.sh
set -e

OUT=$(mktemp -d)
javac --release 8 -Xlint:-options -d "$OUT" src/main/java/org/cct/*.java web/BrowserMain.java
printf 'Main-Class: org.cct.BrowserMain\n' > "$OUT/manifest.txt"
jar cfm docs/foodbox.jar "$OUT/manifest.txt" -C "$OUT" org
rm -rf "$OUT"

echo "Built docs/foodbox.jar"
