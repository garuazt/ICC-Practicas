public class Main {
    public static void main(String[] args) {
        ListaLigada lista = new ListaLigada();

        System.out.println("Lista actual: " + lista);

        lista.insertar(9);
        lista.insertar(13);
        lista.insertar(21);
        lista.insertar(38);
        lista.insertar(46);

        System.out.println("Después de insertar 5 elementos: " + lista);

        lista.eliminar(1);
        System.out.println("Después de eliminar la cabeza: " + lista);

        lista.eliminar(3);
        System.out.println("Después de eliminar un elemento intermedio: " + lista);

        lista.insertarIndice(36, 3);
        System.out.println("Después de insertar un nuevo elemento en un índice válido: " + lista);

        lista.insertarIndice(1, 15);
        System.out.println("Después de insertar un nuevo elemento en un índice inválido: " + lista);
    }
}