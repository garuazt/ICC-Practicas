#!/bin/bash

for dir in */ ; do
  if [ ! -f "$dir/README.md" ]; then
    echo "# ${dir%/}" > "$dir/README.md"
    echo "Sección del proyecto correspondiente a la carpeta ${dir%/}." >> "$dir/README.md"
    echo "README.md creado en $dir"
  fi
done

