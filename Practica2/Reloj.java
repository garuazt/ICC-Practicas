public class Reloj {

    private Manecilla horario;
    private Manecilla minutero;
    private Manecilla segundero;

    private static final int LIMITE_SEGUNDO = 60;
    private static final int LIMITE_MINUTO = 60;
    private static final int LIMITE_HORA = 24;

    public Reloj(int hora, int minuto, int segundo) {
        this.horario = new Manecilla(hora, LIMITE_HORA);
        this.minutero = new Manecilla(minuto, LIMITE_MINUTO);
        this.segundero = new Manecilla(segundo, LIMITE_SEGUNDO);
    }

    public void mostrarHora() {
        String horaFormateada = String.format("%02d:%02d:%02d", horario.getValor(), minutero.getValor(), segundero.getValor());
        System.out.println("--------");
        System.out.println(horaFormateada);
        System.out.println("--------");
    }

    public void iniciar() {
        while (true) {
            int nuevoSegundo = segundero.incremento(); 
            if (nuevoSegundo == 0) {
                int nuevoMinuto = minutero.incremento();
                if (nuevoMinuto == 0) {
                    horario.incremento();
                }
            }

            mostrarHora();

            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
                break; 
            }
        }
    }

    public static void main(String[] args) {
        Reloj reloj = new Reloj(23, 59, 55); 
        reloj.iniciar(); 
    }
    
}

