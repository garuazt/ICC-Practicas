# Proyecto: Esteganógrafo

## Descripción General

Este proyecto en Java implementa una herramienta de esteganografía textual. La clase `Esteganografo` permite descifrar mensajes ocultos dentro de textos aparentemente ordinarios, utilizando distintas estrategias como el conteo de caracteres, espacios finales, o la detección de nombres ocultos. Incluye un menú interactivo para ejecutar cada método desde la terminal.

## Integrantes

- Ana Sofía García Rubio Asteinza
- Isaac Jovany García Franco

## Estructura del Proyecto

- `Esteganografo.java`: clase principal con la lógica de descifrado y un menú en `main`.
- `ServiciosEsteganografo.java`: interfaz que define los métodos esperados.
- `README.md`: este documento.

## Funcionalidades Implementadas

### `String descifraNulo(String original, int n)`
Extrae la **n-ésima letra de cada palabra**, definida por secuencias de caracteres sin espacios. Reinicia el conteo tras cada espacio.

### `String descifraNulo(String original)`
Versión sin parámetro `n`: cuenta los **espacios al final del texto** para determinar el índice de extracción.

### `boolean contieneNombre(String mensaje, String nombre)`
Verifica si el `nombre` está contenido en el `mensaje`, ignorando **espacios, signos de puntuación, mayúsculas, minúsculas y acentos**.

## Interfaz en Consola

El método `main` ofrece un menú interactivo que permite al usuario elegir entre:

1. Descifrado nulo con valor `n` proporcionado
2. Descifrado nulo con espacios al final
3. Verificación de nombre oculto en un texto
4. Descifrado por palabras marcadas
5. Descifrado por letras marcadas

## Funciones Pendientes

### `String descifraPalabrasMarcadas(String m, String e)`
Comparará dos textos (`m` y `e`) palabra por palabra, para recuperar aquellas palabras distintas en `e` y reconstruir el mensaje oculto.

### `String descifraLetrasMarcadas(String m, String e)`
Comparará los textos letra por letra y extraerá las diferencias para formar el mensaje.

## Instrucciones para Ejecutar

1. Asegúrate de tener instalado el JDK.
2. Compila los archivos `.java`:
   javac ServiciosEsteganografo.java Esteganografo.java
3. Ejecuta el programa:
   java Esteganografo

