public class Manecilla {
    private int limite;
    private int valor;

    public Manecilla(int valor, int limite) {
        this.limite = limite;
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public int getLimite() {
        return limite;
    }

    public void setValor(int incremento) {
            valor = incremento;
    }

    public int incremento() {
        if (valor == limite - 1) {
            valor = 0;  
        } else {
            valor++;    
        }
        return valor;
    }
}
