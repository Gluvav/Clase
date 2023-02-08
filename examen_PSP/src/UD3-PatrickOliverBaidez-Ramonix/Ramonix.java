import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//Servidor RAMONIX, recibe los ataques mediante conexiones de threads
public class Ramonix implements Comu{
    
    //Vida total inicial de Ramonix
    public static int health = 500;


    //Método main que se ejecuta al lanzar el servidor
    public static void main(String[] args) throws IOException {
        
        //Clases y variables necesarias para establecer las conexiones con los clientes
        //Puerto 6000 - vale cualquiera a partir del 1024
        int puerto = 6000;
		ServerSocket servidor = new ServerSocket(puerto);
		Socket clienteconectado = null; // El cliente se inicia a nulo hasta que se escuchen peticiones


        //Mostramos que estamos en el servidor 
        System.out.println("SOY RAMONIX Y HE VENIDO A ESCLAVIZAROS CON TRABAJOS DE PSP!");
        


        // Aceptaremos conexiones hasta que la salud llegue a 0
        while(health>0){

            try{
                //Probamos a aceptar alguna conexión
                clienteconectado = servidor.accept(); 
                
                //creamos un flujo de entrada para poder recibir información 
                DataInputStream flujoEntrada = new
			    DataInputStream(clienteconectado.getInputStream());
                
                //El flujo de entrada envía el nombre del atacante, que almacenamos en un String
			    String  nombre = flujoEntrada.readUTF();
                
                //Pasamos el nombre al método damage geenerado más adelante. 
                //Este reduce o aumenta la salud, dependiendo del atacante
                damage(nombre);


                //Definimos un flujo de salida para mandar respuesta al cliente
                DataOutputStream flujoSalida = new
                DataOutputStream(clienteconectado.getOutputStream());
                

                
                if(health==0){//Si la vida de RAMONIX llega a 0, el flujo de salida devuelve un true 
                    flujoSalida.writeBoolean(true);

                }else{//Si la vida de RAMONIX no llega a 0, el flujo de salida devuelve un false 
                    flujoSalida.writeBoolean(false);

                }

                


            }catch(Exception e){
                //Si una conexiñon no funcionase bien, devolvemos el valor del Socket a null
                clienteconectado = null; 
            }

        } //FIN DEL WHILE

        try {

            //Al derrotar a RAMONIX cerramos el servidor e imprimimos un mensaje por pantalla
            servidor.close();
            System.out.println(ANSI_RED + "RAMONIX HA SIDO DERROTADO");
        } catch (IOException e) {
            
            e.printStackTrace();
        }
	
    }

    //método para sumar o restar vida a RAMONIX dependiendo del atacante - name
    public static void damage(String name){
        
        //Muestra quien está atacando
        System.out.println(ANSI_GREEN + "Ataque desde " + name);
        
        // Si ataca Neo, RAMONIX pierde 20 pv 
        if(name.equals("Neo")){
            health-=20; 
        }else if(name.equals("Ab4$t0$")){
            //Si ataca abastos, RAMONIX recupera 10 pv
            health+=10;
        }else{
            //El resto de personajes quitan 10 pv a Ramonix
            health-=10; 
        }

        if(health<0){
            //En caso de que un ataque deje a Ramonix por debajo de 0 pv 
            // Esto se corrije y se settea la vida a 0. 
            health = 0; 
        }
        
        // Se imprime por pantalla la vida restante de RAMONIX
        System.out.println(ANSI_WHITE + "    **** Energia: " + health);
       
    }

}






