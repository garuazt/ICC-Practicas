# 🐶 Pick-A-Perro

Pick-A-Perro es una versión por turnos del juego de cartas del mismo nombre, implementada en Java. El objetivo es formar una secuencia de cartas con diferencias mínimas en sus atributos visuales.

## INTEGRANTES:
- Isaac Jovany García Franco (322330690)
- Ana Sofía García Rubio Asteinza (320555840)

## Descripción del Proyecto
Este proyecto implementa una versión en Java del juego de mesa **Pick-a-Perro**, adaptado a una modalidad por rondas con un jugador humano y múltiples jugadores artificiales.  
Cada carta tiene cinco características: tamaño, color, número de brazos, gafas y palomitas. El objetivo es formar secuencias de cartas que difieran en solo una característica por turno.  
Durante cada ronda, las IA y el jugador humano toman cartas del centro respetando esta regla. Si algún jugador cree que no hay más movimientos posibles, puede declarar “¡Equipo completo!”.  
El sistema valida las secuencias y otorga puntos al finalizar la ronda. El juego termina cuando no hay suficientes cartas para iniciar una nueva ronda.

## Estructura del Proyecto
- `PickAPerro.java`: clase principal. Maneja la bienvenida e invoca al juego.
- `Juego`: controla el flujo del juego por rondas, crea y gestiona los jugadores y valida el final.
- `Jugador` y `JugadorArtificial`: clases que representan a los jugadores humanos e IA respectivamente.
- `Carta`: modela las cartas del juego y calcula sus diferencias.
- `ListaCarta`: representa la secuencia de cartas de cada jugador.
- `Mazo`: genera y administra las cartas restantes.
- `ZonaDeJuego`: mantiene las cartas disponibles al centro y su estado gráfico.
- `Colores`: permite usar colores ANSI para mejorar la interfaz en consola.

## Inconvenientes Presentados
- Problemas iniciales con clases duplicadas y referencias circulares entre archivos.
- Conflictos con la sincronización de hilos al mostrar el estado del tablero.
- Ajustes para mostrar el tablero solo cuando cambia y evitar impresión redundante.
- Mejoras estéticas en consola y control de flujo tras declaración de "¡Equipo completo!".

## Instrucciones para Ejecutar

1. **Requisitos**:
   - Java JDK 17 o superior (puedes verificarlo con `java -version`)
   - Terminal compatible con colores ANSI (como la terminal de Mac, Linux, o Windows con soporte ANSI)

2. **Compilación**:
   En la carpeta raíz del proyecto, ejecuta en terminal:

   ```bash
   javac PickAPerro.java
