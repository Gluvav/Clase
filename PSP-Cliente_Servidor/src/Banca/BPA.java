package Banca;

public class BPA {

    public static void main(String[] args) {
        //creamos el grupo de rhreadst
        ThreadGroup th = new ThreadGroup("bpa");
        //crreamos el objeto con los valores por defecto 40 y 500
        Cuenta c = new Cuenta(40, 500);

        Persona ana = new Persona(c);
        Persona joan = new Persona(c);
        Thread t1 = new Thread(th, ana, "Ana");
        Thread t2 = new Thread(th, joan, "Joan");
        //empezamos los hilos
        t1.start();
        t2.start();
    }

}
