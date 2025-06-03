
import java.util.*;

public class PickAPerro {

    public static Scanner scanner = new Scanner(System.in);
    public static final Random random = new Random();

    public static void main(String[] args) {
        mostrarMenuBienvenida();
        Juego juego = new Juego();
        juego.iniciar();
    }

    public static void mostrarMenuBienvenida() {
        System.out.println(Colores.CYAN + "\n╔════════════════════════════════════════════╗" + Colores.RESET);
        System.out.println(Colores.CYAN + "║         🐶 BIENVENIDO A PICK-A-PERRO 🐶        ║" + Colores.RESET);
        System.out.println(Colores.CYAN + "╚════════════════════════════════════════════╝" + Colores.RESET);
        System.out.println(Colores.AMARILLO + "\nInstrucciones:" + Colores.RESET);
        System.out.println("- El objetivo del juego es formar una secuencia de cartas con diferencias mínimas.");
        System.out.println("- Puedes tomar cartas de la zona central o declarar '¡Equipo completo!'.");
        System.out.println("- Cada carta tiene atributos: tamaño, color, número de brazos, gafas y palomitas.");
        System.out.println("\nPresiona ENTER para comenzar...");
        scanner.nextLine();
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
        if (tam != otra.tam) {
            dif++;
        }
        if (color != otra.color) {
            dif++;
        }
        if (brazos != otra.brazos) {
            dif++;
        }
        if (gafas != otra.gafas) {
            dif++;
        }
        if (palomitas != otra.palomitas) {
            dif++;
        }
        return dif;
    }

    public String toString() {
        String tamStr = (tam == 0) ? "S" : "L";
        String colorStr = switch (color) {
            case 0 ->
                "🔴";
            case 1 ->
                "🟢";
            case 2 ->
                "🔵";
            default ->
                "❓";
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
    }

    public void ejecutarTurno(ZonaDeJuego zona) {
        while (true) {
            System.out.println(Colores.NEGRITA + "Turno de " + nombre + Colores.RESET);
            zona.mostrar();
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
        while (true) {
            boolean tomoCarta = false;
            for (int i = 0; i < 30; i++) {
                Carta posible = zona.tomarCarta(i);
                if (posible == null) {
                    continue;
                }

                boolean esBuena = cartaGuia.diferencias(posible) <= 1;
                boolean seEquivoca = PickAPerro.random.nextDouble() < probabilidadError;

                if (esBuena || seEquivoca) {
                    secuencia.agregar(posible);
                    cartaGuia = posible;
                    System.out.println(nombre + " tomó carta: " + posible + " (posición " + i + ")");
                    tomoCarta = true;
                    break;
                } else {
                    zona.reinsertar(i, posible);
                }
            }

            if (!tomoCarta) {
                System.out.println(nombre + " dice: ¡Equipo completo!");
                dijoEquipoCompleto = true;
                break;
            }

            try {
                Thread.sleep(1000 + PickAPerro.random.nextInt(2000));
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

    public void iniciar() {
        humano = new Jugador("Humano");
        ia = new JugadorArtificial[numIA];
        for (int i = 0; i < numIA; i++) {
            ia[i] = new JugadorArtificial("IA " + (i + 1));
        }
        mazo = new Mazo(42);
        zona = new ZonaDeJuego();

        while (mazo.puedeIniciarRonda()) {
            System.out.println(Colores.CYAN + "\n╔════════════════════════════════════════════╗" + Colores.RESET);
            System.out.println(Colores.CYAN + "║            🌟 NUEVA RONDA 🌟               ║" + Colores.RESET);
            System.out.println(Colores.CYAN + "╚════════════════════════════════════════════╝\n" + Colores.RESET);
            iniciarRonda();
        }
        finalizarJuego();
    }

    private void iniciarRonda() {
        humano.nuevaRonda(mazo.robar());
        for (JugadorArtificial bot : ia) {
            bot.nuevaRonda(mazo.robar());
            bot.preparar(zona);
        }
        zona.rellenar(mazo);

        humano.ejecutarTurno(zona);
        for (JugadorArtificial bot : ia) {
            bot.run();
        }

        verificarRonda();
    }

    private void verificarRonda() {
        if (humano.dijoEquipoCompleto() && humano.puedeSeguir(zona)) {
            System.out.println(Colores.ROJO + "El jugador humano mintió. Pierde su secuencia." + Colores.RESET);
            humano.secuencia.limpiar();
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
