
import java.util.Scanner;

public class Esteganografo{
    
    private String original;
    private int n;
    private String mensaje;
    private String nombre;
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

    public boolean contieneNombre(String mensaje, String nombre) {
        
        String mensajeAux = mensaje.replaceAll("[.,;:¡!¿?()\\[\\]{}\"'\\-— ]", "");
        mensajeAux = mensajeAux.toLowerCase();
        mensajeAux = mensajeAux.replace("á", "a")
                                .replace("é", "e")
                                .replace("í", "i")
                                .replace("ó", "o")
                                .replace("ú", "u");
        String nombreAux = nombre.toLowerCase();
        nombreAux = nombreAux.replace("á", "a")
                            .replace("é", "e")
                            .replace("í", "i")
                            .replace("ó", "o")
                            .replace("ú", "u");
        return mensajeAux.contains(nombreAux);

    }

    //public String descifraPalabrasMarcadas ( String m , String e ){}

    //public String descifraLetrasMarcadas ( String m , String e ){}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Esteganografo est = new Esteganografo();
    
        System.out.println("------------------------------------");
        System.out.println("---------{ ESTEGANOGRAFO }----------");
        System.out.println("------------------------------------");
        System.out.println("----------1.Descifra Nulo-----------");
        System.out.println("-----2.Descifra Nulo (espacios)-----");
        System.out.println("--------3.Contiene el Nombre--------");
        System.out.println("--------4.Palabras Marcadas---------");
        System.out.println("---------5.Letras Marcadas----------");
        System.out.println("------------------------------------");
    
        System.out.print("¿Qué tipo de descifrado deseas realizar? (1, 2, 3, 4 o 5): ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); 
    
        if (opcion == 1) {
            System.out.println("Ingresa el mensaje original:");
            String original = scanner.nextLine();
    
            System.out.println("Ingresa el valor de n:");
            int n = scanner.nextInt();
    
            String resultado = est.descifraNulo(original, n);
            System.out.println("Mensaje oculto: " + resultado);
        }else if (opcion == 2) {
            System.out.println("Ingresa el mensaje original con espacios al final:");
            String original = scanner.nextLine();
    
            String resultado = est.descifraNulo(original);
            System.out.println("Mensaje oculto: " + resultado);
        }else if (opcion == 3) {
            System.out.println("Ingresa el mensaje:");
            String mensaje = scanner.nextLine();
    
            System.out.println("Ingresa el nombre que quieres buscar:");
            String nombre = scanner.nextLine();
    
            boolean contiene = est.contieneNombre(mensaje, nombre);
            System.out.println("¿El mensaje contiene el nombre oculto? " + (contiene ? "Sí" : "No"));
        }
        //else if (opcion == 4) {}else if (opcion == 5) {}
        else {
            System.out.println("Opción no válida. Por favor selecciona 1, 2, 3, 4 o 5.");
        }
        
    }
    
    }


