

public class Persona extends Thread { //Extiende la clase Thread para actuar como un hilo

    private String nombre; //Nombre de usuario
   private  Cuenta cuenta; //Cuenta asociada

    public Persona(String nombre, Cuenta cuenta) {
        //En el constructor necesitamos un nombre y un objeto cuenta
        this.nombre = nombre;
        this.cuenta = cuenta;
    }

    //Sobreescribe el método run(), heredado de Thread, el cual se lanza
    // Cuando el objeto llama a la función .start();
    @Override
    public void run() {

        //Creamos un array de valores aleatorios a los que acceder durante las operaciones
        Float cantidades[] = new Float[4];

        //Tenemos un contador que nos sirve para acceder al array de valores.
        // Esto nos permite repetir una operación bloqueada al desbloquearse, con el mismo
        // Valor que se ha intentado con anterioridad.
        int cont = 0;


        //Generamos los valores aleatorios entre 1 y 500.99...
        //He considerado que un ingreso/ reintegro de 0 no tiene sentido
        for(int i = 0; i < 4; i++){
            cantidades[i] = (float) Math.random() * (cuenta.getMaximo() + 1);
        }

        //Realizamos dos ingresos y dos reintegros, alternados.
        for (int i = 0; i < 2; i++) {

            //Llamamos al método ingreso de cuenta cuyos argumentos son:

            //1. Un float que será la cantidad a ingresar y que obtenemos mediante
            // Math.random() - genera un número aleatorio entro 0 y 1 -no incluido-
            // Y lo * por el máximo de la cuenta + 1 , si no nunca obtendríamos el máximo

            //2. El nombre del usuario que realiza el ingreso

            if(cuenta.ingreso(cantidades[cont], this.nombre)){
                //Si se completa el ingreso (devuelve true), pasamos al siguiente valor del array
                cont++;
            }else{
                //Si no se había completado, volvemos a intentar la transacción con el mismo valor
                // Si esta se completa, pasamos al siguiente valor del array.
                cuenta.ingreso(cantidades[cont], this.nombre);
                cont++;
            }



            //Posteriormente, llamamos al método reintegro de cuenta cuyos argumentos son:

            //1. Un float que será la cantidad a reintegrar y que obtenemos mediante
            // Math.random() - genera un número aleatorio entro 0 y 1 -no incluido-
            // Y lo * por el máximo de la cuenta + 1, si no nunca obtendríamos el máximo

            //2. El nombre del usuario que realiza el ingreso

            if(cuenta.reintegro(cantidades[cont], this.nombre)){
                //Si se completa el ingreso (devuelve true), pasamos al siguiente valor del array
                cont++;
            }else{
                //Si no se había completado, volvemos a intentar la transacción con el mismo valor
                // Si esta se completa, pasamos al siguiente valor del array.
                cuenta.reintegro(cantidades[cont], this.nombre);
                cont++;
            }

        }


    }

    }



