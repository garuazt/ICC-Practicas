# 🐶 Pick-A-Perro

Pick-A-Perro es una versión por turnos del juego de cartas del mismo nombre, implementada en Java. El objetivo es formar una secuencia de cartas con diferencias mínimas en sus atributos visuales.

## INTEGRANTES:
- Isaac Jovany García Franco ()
- Ana Sofía García Rubio Asteinza (320555840)

## 🎮 Cómo jugar

Cada carta tiene cinco atributos:
- Tamaño: grande o chico
- Color: rojo, verde o azul
- Número de brazos
- Presencia de gafas
- Presencia de palomitas

Durante su turno, cada jugador puede:
- Tomar una carta de la zona central si tiene como máximo 1 diferencia con la carta anterior.
- O declarar “¡Equipo completo!” para cerrar su secuencia.

Tras cada ronda, se validan las secuencias:
- Si es válida, suma tantos puntos como cartas tenga.
- Si el jugador mintió (declaró "equipo completo" pero la secuencia es inválida), pierde su secuencia.

## 🧠 Características

- Interfaz de texto con colores ANSI.
- Juego por turnos: 1 jugador humano contra 3 jugadores artificiales (IA).
- IA con comportamiento configurable (puede cometer errores de forma aleatoria).
- Mazo de 96 cartas generadas automáticamente.
- Validación de secuencias al final de cada ronda.
- Tablero dinámico y puntuación acumulada.
- Finaliza cuando se acaban las cartas.

## ⚙️ Requisitos

- Java 17 o superior
- Terminal con soporte ANSI (para colores)
- Compilar con:
  ```bash
  javac PickAPerro.java
