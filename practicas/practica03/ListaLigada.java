public class ListaLigada {
    
    private Nodo cabeza;
    private static int cantidadElementos = 0;

    public ListaLigada() {
        this.cabeza = null;
    }

    public static int getCantidadElementos() {
        return cantidadElementos;
    }

    public void insertar(int nuevoElem) {
        Nodo nuevoNodo = new Nodo(nuevoElem);

        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        cantidadElementos++;
    }

    public void eliminar(int elem) {
        if (cabeza == null) return;

        if (cabeza.getElemento() == elem) {
            cabeza = cabeza.getSiguiente();
            cantidadElementos--;
            return;
        }

        Nodo anterior = cabeza;
        Nodo actual = cabeza.getSiguiente();

        while (actual != null) {
            if (actual.getElemento() == elem) {
                anterior.setSiguiente(actual.getSiguiente());
                cantidadElementos--;
                return;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
    }

    public String toString() {
        if (cabeza == null) return "Lista vacía";
        
        StringBuilder sb = new StringBuilder();
        Nodo actual = cabeza;
        while (actual != null) {
            sb.append(actual.getElemento());
            if (actual.getSiguiente() != null) {
                sb.append(" -> ");
            }
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }

    public void insertarIndice(int elem, int indice) {
        if (indice < 0 || indice > cantidadElementos) return;

        Nodo nuevo = new Nodo(elem);

        if (indice == 0) {
            nuevo.setSiguiente(cabeza);
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            for (int i = 0; i < indice - 1; i++) {
                actual = actual.getSiguiente();
            }
            nuevo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevo);
        }
        cantidadElementos++;
    }
}
