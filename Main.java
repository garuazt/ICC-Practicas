import java.util.Scanner;

public class Main{

     public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);

        System.out.println("----------------------------------");
        System.out.println("---------{ CALCULADORA }----------");
        System.out.println("----------------------------------");
        System.out.println("------1.Suma de dos números-------");
        System.out.println("----2.Producto de dos números-----");
        System.out.println("----3.División de dos números-----");
        System.out.println("-----------4.Sorpresa-------------");
        System.out.println("----------------------------------");

        System.out.println("¿que operación gustas realizar: 1, 2, 3 o 4?");
        int operación = scanner.nextInt();
       

        if(operación==1){
                
                System.out.println("¡Hola! bienvenido, por favor ingresa tu primer dígito");
                double x = scanner.nextDouble();
                
                System.out.println("Ahora ingresa tu segundo dígito");
                double y = scanner.nextDouble();
               
                double suma = x + y;
                
                System.out.println("El resultado de " + (x)+ " + " +(y)+ " es: "+ suma);
        }
        else if(operación==2){
                
                System.out.println("¡Hola! bienvenido, por favor ingresa tu primer dígito");
                double x = scanner.nextDouble();
                
                System.out.println("Ahora ingresa tu segundo dígito");
                double y = scanner.nextDouble();

                double producto = x * y;

                System.out.println("El resultado de " + (x)+ " * " +(y)+ " es: " + producto);
        }
        else if(operación==3){
                
                System.out.println("¡Hola! bienvenido, por favor ingresa tu primer dígito");
                double x = scanner.nextDouble();
                
                System.out.println("Ahora ingresa tu segundo dígito");
                double y = scanner.nextDouble();
                
                if(y!=0){
                        
                        double división = 1.0 * x / y;

                        System.out.println("El resultado de " + (x)+ " / " +(y)+ " es: "+ división);
                }else{
                        System.out.println("La divisón entre cero no está definida");
                }
                
        }else if(operación==4){

                System.out.println("¡Bienvenido a la sorpresa! ¿Cuál es tu nombre?");
                String nombre = scanner2.nextLine();
                
                System.out.println( nombre + " ¿te gustaría ver la sorpresa?");
                
                System.out.println("Para si teclea: true");
                
                System.out.println("Para si teclea: false");
                boolean respuesta = scanner.nextBoolean();

                if(respuesta==true){
                        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                        System.out.println("▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓");
                        System.out.println("▓▓░░░░░░░░░░░░░░░░░░░░░▒▒▒▒░░░▒▒▒▒░░░░░░▓▓");
                        System.out.println("▓▓░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒░▒▒▒▒▒▒░░░░░▓▓");
                        System.out.println("▓▓░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░░▓▓");
                        System.out.println("▓▓░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒░░░░░░▓▓");
                        System.out.println("▓▓░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒░░░░░░░▓▓");
                        System.out.println("▓▓░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒░░░░░░░░░▓▓");
                        System.out.println("▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░▒░░░░░░░░░░░▓▓");
                        System.out.println("▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓");
                        System.out.println("▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒░▒▒▒░░░▓▓");
                        System.out.println("▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒░░▓▓");
                        System.out.println("▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒░░░▓▓");
                        System.out.println("▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒░░░░▓▓");
                        System.out.println("▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒░░░░░▓▓");
                        System.out.println("▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒░░░░░░▓▓");
                        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                        System.out.println("_______▒__________▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
                        System.out.println("______▒_______________▒▒▒▒▒▒▒▒");
                        System.out.println("_____▒________________▒▒▒▒▒▒▒▒");
                        System.out.println("____▒___________▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
                        System.out.println("___▒");
                        System.out.println("__▒______▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                        System.out.println("_▒______▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓");
                        System.out.println("▒▒▒▒___▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓");
                        System.out.println("▒▒▒▒__▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓▒▓");
                        System.out.println("▒▒▒__▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
                        System.out.println("▒▒");
                }else{
                        System.out.println(" Pues adiós :( ");
                }

        }else{
                System.out.println("La opción no es valida, por favor vuelve a empezar");
        }
        
                
                
        }
}   
