
import java.util.*;

public class PickAPerro {
    public static Scanner scanner = new Scanner(System.in);
    public static final Random random = new Random();
    public static volatile boolean jugando = true;

    public static void main(String[] args) {
        mostrarMenuBienvenida();
        Juego juego = new Juego();
        juego.iniciar();
    }

    public static void mostrarMenuBienvenida() {
        System.out.println(Colores.CYAN + "\n╔════════════════════════════════════════════╗" + Colores.RESET);
        System.out.println(Colores.CYAN + "║       🐶 BIENVENIDO A PICK-A-PERRO 🐶      ║" + Colores.RESET);
        System.out.println(Colores.CYAN + "╚════════════════════════════════════════════╝" + Colores.RESET);
        System.out.println(Colores.AMARILLO + "\nInstrucciones:" + Colores.RESET);
        System.out.println("\n{ PON EN PANTALLA COMPLETA TU TERMINAL O COMPILADOR }\n");
        System.out.println("- El objetivo del juego es formar una secuencia de cartas con diferencias mínimas.");
        System.out.println("- Puedes tomar cartas de la zona central o declarar '¡Equipo completo!'.");
        System.out.println("- Cada carta tiene atributos: tamaño, color, número de brazos, gafas y palomitas.");
        System.out.println("\nPresiona ENTER para comenzar...\n");
        scanner.nextLine();
    }

    public static synchronized void imprimirSincronizado(String mensaje) {
    System.out.println(mensaje);
    }

}

class Colores {
    public static final String RESET = "\u001B[0m";
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AZUL = "\u001B[34m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String NEGRITA = "\u001B[1m";
}

class Carta {
    private int tam, color, brazos;
    private boolean gafas, palomitas;

    public Carta(int tam, int color, int brazos, boolean gafas, boolean palomitas) {
        this.tam = tam;
        this.color = color;
        this.brazos = brazos;
        this.gafas = gafas;
        this.palomitas = palomitas;
    }

    public int diferencias(Carta otra) {
        int dif = 0;
        if (tam != otra.tam) dif++;
        if (color != otra.color) dif++;
        if (brazos != otra.brazos) dif++;
        if (gafas != otra.gafas) dif++;
        if (palomitas != otra.palomitas) dif++;
        return dif;
    }

    public String toString() {
        String tamStr = (tam == 0) ? "S" : "L";
        String colorStr = switch (color) {
            case 0 -> "🔴";
            case 1 -> "🟢";
            case 2 -> "🔵";
            default -> "❓";
        };
        String brazosStr = brazos + "B";
        String gafasStr = gafas ? "😎" : "👀";
        String palomitasStr = palomitas ? "🍿" : "–";
        return "│ " + tamStr + " " + colorStr + " " + brazosStr + " " + gafasStr + " " + palomitasStr + " │";
    }
}

class Jugador {
    protected String nombre;
    protected ListaCarta secuencia;
    protected int puntos;
    protected Carta cartaGuia;
    protected boolean dijoEquipoCompleto;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
        this.secuencia = new ListaCarta();
    }

    public void nuevaRonda(Carta inicial) {
        this.secuencia.limpiar();
        this.cartaGuia = inicial;
        this.dijoEquipoCompleto = false;
        this.secuencia.agregar(inicial);
        System.out.println(nombre + " comienza con carta: " + inicial);
    }

    public void turno(ZonaDeJuego zona) {
        // No usar hilos aquí: se maneja desde Juego
    }

    public void ejecutarTurno(ZonaDeJuego zona) {
        while (true) {
            System.out.println(Colores.NEGRITA + "Turno de " + nombre + Colores.RESET);
            System.out.println("Tu carta guía: " + cartaGuia);
            System.out.println("Escribe el número de la carta que deseas tomar o -1 para decir '¡Equipo completo!':");

            int eleccion;
            try {
                eleccion = PickAPerro.scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida.");
                PickAPerro.scanner.nextLine();
                continue;
            }

            if (eleccion == -1) {
                dijoEquipoCompleto = true;
                PickAPerro.jugando = false;
                Juego.setQuienDijoEquipoCompleto(this); 
                break;
            }

            if (eleccion < 0 || eleccion >= 30) {
                System.out.println("Número fuera de rango.");
                continue;
            }

            Carta tomada = zona.tomarCarta(eleccion);
            if (tomada == null) {
                System.out.println("Movimiento inválido.");
            } else {
                secuencia.agregar(tomada);
                cartaGuia = tomada;

                // ✅ Mostrar tablero actualizado
                System.out.println(Colores.CYAN + "\n--- Tablero actualizado ---" + Colores.RESET);
                zona.mostrar();
            }
        }
    }

    public boolean dijoEquipoCompleto() {
        return dijoEquipoCompleto;
    }

    public boolean puedeSeguir(ZonaDeJuego zona) {
        return zona.hayCartaValidaPara(cartaGuia);
    }

    public void verificar() {
        System.out.println("Secuencia de " + nombre + ":");
        secuencia.imprimir();

        if (secuencia.secuenciaValida()) {
            int ganados = secuencia.tamano();
            puntos += ganados;
            System.out.println(Colores.VERDE + "Secuencia válida. +" + ganados + " puntos." + Colores.RESET);
        } else {
            System.out.println(Colores.ROJO + "Secuencia inválida. 0 puntos esta ronda." + Colores.RESET);
        }
    }

    public void mostrarPuntaje() {
        System.out.println(nombre + ": " + puntos + " puntos");
    }

    public int getPuntos() {
        return puntos;
    }

    public String getNombre() {
        return nombre;
    }
}

class JugadorArtificial extends Jugador implements Runnable {
    private double probabilidadError = 0.05;
    private ZonaDeJuego zona;

    public JugadorArtificial(String nombre) {
        super(nombre);
    }

    public void setProbabilidadError(double probabilidad) {
        this.probabilidadError = probabilidad;
    }

    public void preparar(ZonaDeJuego zona) {
        this.zona = zona;
    }

    @Override
    public void run() {
        dijoEquipoCompleto = false;
        while (PickAPerro.jugando) {
            boolean tomoCarta = false;
            for (int i = 0; i < 30; i++) {
                Carta posible = zona.tomarCarta(i);
                if (posible == null) continue;

                boolean esBuena = cartaGuia.diferencias(posible) <= 1;
                boolean seEquivoca = PickAPerro.random.nextDouble() < probabilidadError;

                if (esBuena || seEquivoca) {
                    secuencia.agregar(posible);
                    cartaGuia = posible;
                    
                    PickAPerro.imprimirSincronizado("\n" + Colores.AZUL + nombre + " tomó carta: " + posible + " (posición " + i + ")" + Colores.RESET);
                    PickAPerro.imprimirSincronizado(Colores.CYAN + "--- Tablero actualizado ---" + Colores.RESET);
                    PickAPerro.imprimirSincronizado(zona.estadoComoTexto());
                    PickAPerro.imprimirSincronizado("─────────────────────────────────────────────");

                    tomoCarta = true;
                    break;
                } else {
                    zona.reinsertar(i, posible);
                }
            }

            if (!tomoCarta) {
                PickAPerro.imprimirSincronizado("\n" + Colores.ROJO + nombre + " dice: ¡Equipo completo!" + Colores.RESET);
                dijoEquipoCompleto = true;
                PickAPerro.jugando = false;
                break;
            }

            try {
                Thread.sleep(10000 + PickAPerro.random.nextInt(5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Juego {

    private Jugador humano;
    private JugadorArtificial[] ia;
    private Mazo mazo;
    private ZonaDeJuego zona;
    private int numIA = 3;
    private static Jugador quienDijoPrimero = null;

    public static synchronized void setQuienDijoEquipoCompleto(Jugador j) {
        if (quienDijoPrimero == null) {
            quienDijoPrimero = j;
        }
    }



    public void iniciar() {
        humano = new Jugador("Humano");
        ia = new JugadorArtificial[numIA];
        for (int i = 0; i < numIA; i++) {
            ia[i] = new JugadorArtificial("IA " + (i + 1));
        }
        mazo = new Mazo(); 
        zona = new ZonaDeJuego();

       while (true) {
            System.out.println(Colores.CYAN + "\n╔════════════════════════════════════════════╗" + Colores.RESET);
            System.out.println(Colores.CYAN + "║            🌟 NUEVA RONDA 🌟               ║" + Colores.RESET);
            System.out.println(Colores.CYAN + "╚════════════════════════════════════════════╝\n" + Colores.RESET);
            iniciarRonda();

            if (!mazo.puedeIniciarRonda(1 + numIA)) {
                System.out.println(Colores.ROJO + "\nÚltima ronda: ya no hay suficientes cartas para continuar." + Colores.RESET);
                break;
            }
        }
        finalizarJuego();

    }

        private void iniciarRonda() {
        humano.nuevaRonda(mazo.robar());
        for (JugadorArtificial bot : ia) {
            bot.nuevaRonda(mazo.robar());
        }
        zona.rellenar(mazo);

        for (JugadorArtificial bot : ia) {
            bot.preparar(zona);
        }

        Thread[] hilosIA = new Thread[ia.length];
        for (int i = 0; i < ia.length; i++) {
            hilosIA[i] = new Thread(ia[i]);
            hilosIA[i].start();
        }

        Thread hiloHumano = new Thread(() -> humano.ejecutarTurno(zona));
        hiloHumano.start();


        for (Thread hilo : hilosIA) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        try {
            hiloHumano.join(); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


        PickAPerro.jugando = false;
        PickAPerro.jugando = true;

        if (quienDijoPrimero != null) {
            System.out.println(Colores.AMARILLO + "\n" + quienDijoPrimero.getNombre() + " gritó: ¡Equipo completo!" + Colores.RESET);
        }

        verificarRonda();

        PickAPerro.scanner.nextLine(); 
        System.out.println("\nPresiona ENTER para continuar a la siguiente ronda...");
        PickAPerro.scanner.nextLine(); 




    }


    private void verificarRonda() {
        if (humano.dijoEquipoCompleto() && humano.puedeSeguir(zona)) {
            System.out.println(Colores.ROJO + "El jugador humano mintió. Pierde su secuencia." + Colores.RESET);
            humano.secuencia.limpiar();
        }
        
        if (quienDijoPrimero != null &&
            !quienDijoPrimero.puedeSeguir(zona) &&
            mazo.restantes() > 0) {

            Carta bonus = mazo.robar();
            quienDijoPrimero.secuencia.agregar(bonus);
            System.out.println(Colores.VERDE + quienDijoPrimero.getNombre() +
                " acertó con '¡Equipo completo!' y recibe una carta bonus: " + bonus + Colores.RESET);
        }

        for (JugadorArtificial bot : ia) {
            if (bot.dijoEquipoCompleto() && bot.puedeSeguir(zona)) {
                System.out.println(Colores.ROJO + bot.nombre + " mintió. Pierde su secuencia." + Colores.RESET);
                bot.secuencia.limpiar();
            }
        }
        humano.verificar();
        for (JugadorArtificial bot : ia) {
            bot.verificar();
        }
        System.out.println(Colores.CYAN + "\n════════════ FIN DE RONDA ─ PUNTAJES ════════════" + Colores.RESET);
        humano.mostrarPuntaje();
        for (JugadorArtificial bot : ia) {
            bot.mostrarPuntaje();
        }

        quienDijoPrimero = null;

    }

    private void finalizarJuego() {
        System.out.println(Colores.NEGRITA + "\n╔════════════════════════════════════════════╗" + Colores.RESET);
        System.out.println(Colores.NEGRITA + "║          🏁 RESULTADOS FINALES 🏁          ║" + Colores.RESET);
        System.out.println(Colores.NEGRITA + "╚════════════════════════════════════════════╝" + Colores.RESET);

        List<Jugador> todos = new ArrayList<>();
        todos.add(humano);
        Collections.addAll(todos, ia);
        todos.sort((a, b) -> Integer.compare(b.getPuntos(), a.getPuntos()));

        for (int i = 0; i < todos.size(); i++) {
            System.out.println((i + 1) + ". " + todos.get(i).getNombre() + " - " + todos.get(i).getPuntos() + " puntos");
        }

        int ganadorPuntos = todos.get(0).getPuntos();
        List<Jugador> ganadores = new ArrayList<>();
        for (Jugador j : todos) {
            if (j.getPuntos() == ganadorPuntos) {
                ganadores.add(j);
            }
        }

        if (ganadores.size() == 1) {
            System.out.println(Colores.VERDE + "\n🎉 Ganador: " + ganadores.get(0).getNombre() + " 🎉" + Colores.RESET);
        } else {
            System.out.print(Colores.AMARILLO + "\n🤝 Empate entre:" + Colores.RESET);
            for (Jugador j : ganadores) {
                System.out.print(" " + j.getNombre());
            }
            System.out.println();
        }
    }
}

class ListaCarta {
    private ArrayList<Carta> cartas;

    public ListaCarta() {
        cartas = new ArrayList<>();
    }

    public void agregar(Carta carta) {
        cartas.add(carta);
    }

    public void limpiar() {
        cartas.clear();
    }

    public int tamano() {
        return cartas.size();
    }

    public void imprimir() {
        for (Carta c : cartas) {
            System.out.println(c);
        }
    }

    public boolean secuenciaValida() {
        for (int i = 1; i < cartas.size(); i++) {
            if (cartas.get(i - 1).diferencias(cartas.get(i)) > 1) {
                return false;
            }
        }
        return true;
    }
}

class Mazo {
    private ArrayList<Carta> cartas;

    public Mazo() {
        cartas = new ArrayList<>();

        for (int tam = 0; tam <= 1; tam++) {
            for (int color = 0; color <= 1; color++) {
                for (int brazos = 1; brazos <= 2; brazos++) {
                    for (boolean gafas : new boolean[]{true, false}) {
                        for (boolean palomitas : new boolean[]{true, false}) {
                            for (int copia = 0; copia < 3; copia++) {
                                cartas.add(new Carta(tam, color, brazos, gafas, palomitas));
                            }
                        }
                    }
                }
            }
        }

        Collections.shuffle(cartas);
    }

    public Carta robar() {
        if (!cartas.isEmpty()) {
            return cartas.remove(0);
        }
        return null;
    }

    public boolean puedeIniciarRonda(int jugadores) {
        return cartas.size() >= (jugadores + 30); 
    }

    public int restantes() {
        return cartas.size();
    }

}


class ZonaDeJuego {
    private Carta[] cartas = new Carta[30];

    public void rellenar(Mazo mazo) {
        for (int i = 0; i < cartas.length; i++) {
            cartas[i] = mazo.robar();
        }
    }

    public void mostrar() {
        System.out.println(Colores.CYAN + "\n--- Estado actual del tablero ---" + Colores.RESET);
        for (int i = 0; i < cartas.length; i++) {
            if (cartas[i] != null) {
                System.out.println(i + ": " + cartas[i]);
            }
        }
    }



    public Carta tomarCarta(int index) {
        if (index >= 0 && index < cartas.length && cartas[index] != null) {
            Carta temp = cartas[index];
            cartas[index] = null;
            return temp;
        }
        return null;
    }

    public void reinsertar(int index, Carta carta) {
        if (index >= 0 && index < cartas.length && cartas[index] == null) {
            cartas[index] = carta;
        }
    }

    public boolean hayCartaValidaPara(Carta cartaGuia) {
        for (Carta c : cartas) {
            if (c != null && cartaGuia.diferencias(c) <= 1) {
                return true;
            }
        }
        return false;
    }

    public String estadoComoTexto() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < cartas.length; i++) {
        if (cartas[i] != null) {
            sb.append(String.format("%2d: %-25s", i, cartas[i]));
        } else {
            sb.append(String.format("%2d: %-25s", i, "│ (vacía) │"));
        }

        if ((i + 1) % 6 == 0) {
            sb.append("\n");
        }
    }
    return sb.toString();
    }
}