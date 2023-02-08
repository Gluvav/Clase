import java.util.Scanner;

public class BPA {
    public static void main(String[] args) {


        //Nombres almacenados
        String nombre1 = "Patrick", nombre2 = "Paco";

        //Mostrmos mensajes de inicio del programa
        System.out.println("Iniciando cuenta...");


        //Creamos una cuenta con saldo 40 y máximo 500
        Cuenta cuenta = new Cuenta(40, 500);

        //Mostramos un mensaje que diga que estamos asociando los usuarios a la cuenta
        System.out.println("Asociando clientes a la cuenta...");

        //Instanciamos dos objetos persona, con sus respectivos nombres
        // Pero que comparten el objeto cuenta que hemos creado previamente
        Persona p1 = new Persona(nombre1, cuenta);
        Persona p2 = new Persona(nombre2, cuenta);

        //mostramos que el programa está lito para empezar
        System.out.println("¡Todo listo! Podéis empezar a usar la cuenta: ");

        //Mostramos el saldo inicial
        System.out.printf("La cuenta tiene un saldo actual de %.2f \n", cuenta.getSaldo());

        //Lanzamos los objetos p1/p2 que extienden la clase Thread
        p1.start();
        p2.start();


    }
}