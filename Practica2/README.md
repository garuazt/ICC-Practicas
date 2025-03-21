# Proyecto: Simulación de Reloj Digital en Java

## Integrantes
- [Isaac Jovany García Franco No. 322330690]
- [Ana Sofía García Rubio Asteinza No. 320555840]

## Descripción del proyecto
Este proyecto implementa una clase `Reloj` que simula el funcionamiento de un reloj digital de 24 horas, mostrando la hora en formato `hh:mm:ss` y actualizándola cada segundo.

El reloj está compuesto por tres objetos de la clase `Manecilla` (una por cada unidad de tiempo: horas, minutos y segundos), los cuales se actualizan progresivamente. La clase incluye:

- Control del paso del tiempo mediante `Thread.sleep(1000)` para simular el avance real de los segundos.
- Lógica de incremento automático que ajusta minutos y horas al alcanzar los límites (60 segundos, 60 minutos, 24 horas).
- Método `mostrarHora()` que imprime la hora formateada.

> **Nota:** El código depende de una clase adicional llamada `Manecilla`, que debe estar implementada en el mismo proyecto para que funcione correctamente.

## Estructura del proyecto
- `Reloj.java`: contiene la implementación del reloj, con su método `main` para ejecutarlo.
- `Manecilla.java`: clase auxiliar que representa una manecilla con valor y límite (no incluida aquí, pero necesaria para compilar y ejecutar correctamente).

## Inconvenientes
- La ejecución del reloj es indefinida, por lo que requiere cerrarse manualmente desde la consola.
- Es necesario implementar o tener disponible la clase `Manecilla`, ya que no forma parte de la librería estándar de Java.

## Instrucciones de ejecución

1. Asegúrate de tener Java instalado (versión 8 o superior).
2. Asegúrate de contar con los archivos `Reloj.java` y `Manecilla.java` en la misma carpeta.
3. Abre la terminal en esa carpeta.
4. Compila los archivos:
5. Ejecuta el programa:


El reloj comenzará a correr desde la hora indicada en el método `main` (en este caso, `23:59:55`).

