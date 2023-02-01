package Banca;

import java.util.Random;
import java.util.Scanner;

public class Persona extends Thread{
    //creamos el objeto sin constructor.
    final Cuenta cuenta;
    int cnt = 0;
    Random rnd1 = new Random();
    int d1 = (int) (rnd1.nextDouble() * 501.0);
    Random rnd2 = new Random();
    int d2 = (int) (rnd2.nextDouble() * 501.0);
    Random rnd3 = new Random();
    int d3 = (int) (rnd3.nextDouble() * 501.0);
    Random rnd4 = new Random();
    int d4 = (int) (rnd4.nextDouble() * 501.0);

    public Persona(Cuenta c) {
        //en el constructor recibimos el objeto que hemos declarado en BPA, de forma que estamos trabajando con el mismo objeto en ambos sitos.
        cuenta = c;
    }

    public void run() {
        super.run();
        //dentro del run abro un scanner para la razon del final.
        Scanner str = new Scanner(System.in);
        synchronized (cuenta) {
            //sincronizamos un trozo del codigo y llamamos a los metodos sincronizados pasandoles el objeto, para tener acceso a los metodos del objeto
            //ingresar(cuenta);
            while(true) {
                if (ingresar(d1, cuenta) != 0) {
                    cnt++;
                    if (cnt >=2){
                        break;
                    }
                    try {
                        cuenta.notify();
                        cuenta.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    cnt--;
                    break;
                }
            }
            //retirar(cuenta);
            while (true) {
                if (retirar(d2, cuenta) != 0) {
                    cnt++;
                    if (cnt >=2){
                        break;
                    }
                    try {
                        cuenta.notify();
                        cuenta.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    cnt--;
                    break;
                }
            }
            //ingresar(cuenta);
            while (true) {
                if (ingresar(d3, cuenta) < 0) {
                    cnt++;
                    if (cnt >=2){
                        break;
                    }
                    try {
                        cuenta.notify();
                        cuenta.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    cnt--;
                    break;
                }
            }
            //retirar(cuenta);
            while (true) {
                if (retirar(d4, cuenta) < 0) {
                    cnt++;
                    if (cnt >=2){
                        break;
                    }
                    try {
                        cuenta.notify();
                        cuenta.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    cnt--;
                    break;
                }
            }
            //mostramos por pantalla la razon por la que se ha terminado..
            System.out.println(Thread.currentThread().getName() + " ha terminado.");
            cuenta.notify();
        }
    }

    public synchronized int ingresar(int dinero, Cuenta c) {
        //creamos un avariable random y generamos un numero entero entre 1 y 500

        //mostramos la operacion a realizar
        System.out.println("Se va ha ingresar saldo. (Saldo actual: " + c.verSaldo() + ")");
        //comprobamos si el dinero que se va a ingresar no superara el maximo permitido en la cuenta y si funciona se llama al metodo de cuenta para ingresar el dinero.
        if (c.verMaxDin() > (dinero + c.verSaldo())) {
            System.out.println(c.ingresar(dinero, Thread.currentThread().getName()));
            return 0;
        } else { //si no, mostramos que se pasa del maximo.
            System.out.println(Thread.currentThread().getName() + " quiere ingresar " + dinero + "€. Maximo alcalzado. Saldo actual: " + c.verSaldo());
            System.out.println("Esperando...");
            return -1;
        }
    }

    public synchronized int retirar(int dinero, Cuenta c){
        //lo mismo que en ingresar
        //mostramos lo que se va ha hacer
        System.out.println("Se va ha retirar saldo. (Saldo actual: " + cuenta.verSaldo() + ")");
        //comprobamos si hay suficiente saldo para realizar la operacion, si hay, se llama al metodo de cuenta para retirar dinero
        if (c.verSaldo() > dinero) {
            System.out.println(cuenta.sacar(dinero, Thread.currentThread().getName()));
            return 0;
        } else { //si no, le decimos que no hay suficiente saldo.
            System.out.println(Thread.currentThread().getName() + " quiere retirar " + dinero + "€, pero no hay suficiente saldo. Saldo actual: " + c.verSaldo());
            System.out.println("Esperando...");
            return -1;
        }
    }

}
