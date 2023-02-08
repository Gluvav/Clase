public class Cuenta {

    //Variables que emplearemos en la clase cuenta
    private float saldo, maximo;

    public Cuenta(float saldo, float maximo) {
        this.saldo = saldo; //Saldo en la cuenta
        this.maximo = maximo; //Máximo dinero que puede haber en la cuenta
    }

    //Devuelve el saldo actual
    public float getSaldo() {
        return saldo;
    }

    //Devuelve el saldo máximo
    public float getMaximo(){
        return maximo;
    }

    //Método sincronizado que permite el ingreso de cierta cantidad pasada por parámetro
    public synchronized boolean ingreso(float saldo, String nombre) {

        //Booleano que nos permite saber si se completa la operación o se bloquea
        boolean completado = false;

        //Mostramos el saldo previo a la operación por pantalla
        System.out.printf("Saldo actual(%.2f) => ", this.saldo);

        //Si al ingresar el saldo supera el máximo, no se realiza la operación
        if(this.saldo + saldo > this.maximo){
            System.out.printf("%s: No se puede realizar el ingreso de %.2f. Supera %.2f, el máximo de esta cuenta \n", nombre, saldo, maximo);
            try { // Se deja en pausa
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else{ // Si el ingreso es posible, se realiza y se avisa al usuario

            this.saldo += saldo;
            System.out.printf("%s: Ingresa %.2f , nuevo saldo: %.2f \n", nombre, saldo, this.saldo);
            notify(); // Si se ha realizado un ingreso, se notifica a los demás por si están en pausa, esperando a poder sacar dinero
            completado = true; // La operación se completa y el booleano se vuelve true
        }
        //Devolvemos el booleano para saber que se ha completado esta operación
        return completado;




    }

    //Método sincronizado que permite el reintegro de cierta cantidad pasada por parámetro
    public synchronized boolean reintegro(float saldo, String nombre) {

        //Booleano que nos permite saber si se completa la operación o se bloquea
        boolean completado = false;

        //Mostramos el saldo previo a la operación por pantalla
        System.out.printf("Saldo actual(%.2f) => ", this.saldo);

        // Si el reintegro es demasiado alto, no se realiza la operación
        if(this.saldo - saldo < 0){
            System.out.printf("%s: No se puede realizar el reintegro de %.2f. El mínimo de esta cuenta es 0 \n", nombre, saldo);
            try {//Se bloquea
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else{ // En caso de poder realizarse la transacción, se avisa al usuario que se ha realizado
            this.saldo -= saldo;
            System.out.printf("%s: Retira %f, saldo actual: %.2f \n", nombre, saldo, this.saldo);
            notify(); // Se avisa a los demás por si están esperando para poder ingresar dinero
            completado = true; //La operación se completa y el booleano se vuelve true
        }
        //Devolvemos el booleano para saber que se ha completado esta operación
        return completado;

    }
}
