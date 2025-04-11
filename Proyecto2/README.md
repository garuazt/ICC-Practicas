# Proyecto: Esteganógrafo

## Descripción General

Este proyecto implementa una herramienta de esteganografía textual en Java. La clase `Esteganografo` tiene como propósito extraer mensajes ocultos contenidos dentro de textos aparentemente normales, aplicando distintas técnicas sencillas. La estructura del proyecto sigue la especificación de la interfaz `ServiciosEsteganografo`.

## Integrantes

- Ana Sofía García Rubio Asteinza
- Isaac Jovany García Franco

## Estructura de Archivos

- `Esteganografo.java`: implementación de los métodos de descifrado.
- `ServiciosEsteganografo.java`: interfaz que define el comportamiento esperado del esteganógrafo.
- `README.md`: este archivo.

## Funcionalidades

### Métodos implementados (hasta ahora)

#### `String descifraNulo(String original, int n)`
Extrae la **n-ésima letra de cada palabra** en el texto original para formar un mensaje oculto.

#### `String descifraNulo(String original)`
Cuenta los espacios al final del texto y usa ese número como índice para extraer letras de cada palabra. Es útil cuando el valor de `n` está codificado como espacios finales.

### Métodos definidos en la interfaz (pendientes o incompletos)

#### `boolean contieneNombre(String mensaje, String nombre)`
Busca si un nombre está oculto dentro de un mensaje, **ignorando mayúsculas, minúsculas, espacios y signos de puntuación**.

#### `String descifraPalabrasMarcadas(String m, String e)`
Compara dos textos similares (`m` y `e`) y extrae aquellas **palabras diferentes** para reconstruir un mensaje oculto.

#### `String descifraLetrasMarcadas(String m, String e)`
Compara dos textos similares (`m` y `e`) y extrae aquellas **letras diferentes** para formar un mensaje oculto.

## Instrucciones para Ejecutar

1. Asegúrate de tener instalado el JDK.
2. Compila los archivos `.java`:

javac ServiciosEsteganografo.java Esteganografo.java

