public class Esteganografo{
    
    private String original;
    private int n;
    private String mensaje;
    private String m;
    private String e;
    
    public String descifraNulo( String original , int n ){
        
        int cuenta = 0;
        String oculto = "";

        for( int i = 0; i < original.length(); i++ ){

            char temp = original.charAt(i);
            if ( temp == ' '){
                cuenta = 0;
            } else {
                cuenta++;
                if ( cuenta == n){
                    oculto += temp;
                }

            }

        }
        
        return oculto;

    }

    public String descifraNulo ( String original ){

        int nEspacios = 0;
        int j = original.length() - 1;
        
        while (j >= 0 && (original.charAt(j) == ' ' || original.charAt(j) == '.')) {
            if (original.charAt(j) == ' ') {
                nEspacios++;
            }
            j--;
        }

        int cuenta = 0;
        String ocultoEspacios = "";

        for( int i = 0; i < original.length(); i++ ){

            char temp = original.charAt(i);
            if ( temp == ' '){
                cuenta = 0;
            } else {
                cuenta++;
                if ( cuenta == nEspacios){
                    ocultoEspacios += temp;
                }

            }

        }
        
        return ocultoEspacios;
    }

    public boolean contieneNombre ( String mensaje , String nombre ){

    }

    public String descifraPalabrasMarcadas ( String m , String e ){
        if (!contieneNombre(mensaje, nombre)){
            descifrado += mensaje;
        }
    }

    public String descifraLetrasMarcadas ( String m , String e ){
        if()
    }

}
