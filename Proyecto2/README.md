# Proyecto: Esteganógrafo

## Integrantes

- Ana Sofía García Rubio Asteinza
- Isaac Jovany García Franco

## Estructura del Proyecto

Este proyecto consiste en la clase `Esteganografo`, escrita en Java, que permite recuperar mensajes ocultos dentro de un texto utilizando técnicas sencillas de esteganografía. La clase implementa distintos métodos para descifrar mensajes basándose en el número de caracteres consecutivos, la cantidad de espacios al final del texto, o marcas específicas dentro de una cadena.

### Archivos

- `Esteganografo.java`: contiene la implementación principal de la lógica de descifrado.
- `README.md`: archivo descriptivo del proyecto (este documento).

## Inconvenientes Presentados

Durante el desarrollo del proyecto, uno de los principales retos fue definir un método de extracción de caracteres ocultos que fuera confiable incluso cuando el mensaje oculto no está explícitamente delimitado. También fue necesario depurar errores relacionados con el uso de caracteres especiales y manejar correctamente los espacios y puntos al final del texto original.

Algunos métodos aún están en desarrollo y requieren validación adicional para asegurar que el descifrado funcione correctamente con diferentes tipos de mensajes.

## Instrucciones para Ejecutar

1. Asegúrate de tener instalado el JDK (Java Development Kit).
2. Compila el archivo en terminal con:
   
   javac Esteganografo.java
