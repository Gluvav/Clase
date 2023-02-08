import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Atac {

    //Tenemos las variables host, que es localhost, y puerto, que es el 6000 y coincide 
    // con el puerto del servidor. 
    private static String host = "localhost";
    private static int puerto = 6000;

    // Creamos una variable para controlar cuando cesar el ataque
    private static boolean terminar = false; 

  
    // Método atacar, se encarga de establecer conexión con el servidor Ramonix
    // posteriormente pasarle el nombre del atacante, leer la respuesta del servidor
    // si ha sido derotado o no, guardar la respuesta en la variable terminar y cerrar 
    // la concexión con el servidor. Finalmente devuelve el valor de terminar. 
    public static boolean atacar(String name){

        
            try{
                Socket cliente = new Socket(host, puerto);
                DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
                flujoSalida.writeUTF(name);

                DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
                terminar = flujoEntrada.readBoolean();
                
                
                cliente.close();
                
    
            }catch(Exception e){
                e.printStackTrace();
            }


            return terminar; 
        

        
        
        }

    }

