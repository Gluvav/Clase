
// Classe Hacker que extiende la clase hilo 
public class Hacker extends Thread implements Comu{

    // El hacker tendrá un nombre y yna sala de reuniones asociada
    private String name;
    private SalaReunions salaReunions;


    public Hacker(String name, SalaReunions salaReunions){
        this.name = name;
        this.salaReunions = salaReunions;

    }

    // Método run, que corre al usart .start tras la creación de un hilo
    public void run(){
        
        //Mientras en la sala de reuniones esté Neo (asumo que se conectan desde allí a Ramonix)
        // Se realizarán ataques. Es decir, conexiones al servidor. 
        while(salaReunions.getNeo() == true){

            //Mostramos quien estña atacando por pantalla 
            System.out.println(ANSI_BLUE + name + " está atacant...");
            
            //Iniciamos la conexión con Ramonix mediante el método atacar de la clase Ataque
            // Al método le pasamos el nombre del atacante
            // Si el método devuelve true, todo ha terminado, RAMONIX ha sido derrotado
            // Neo se va de la sala a celebrar la victoria y se muestra un mensaje por 
            // Pantalla que muestra el mensaje RAMONIX DOWN 
            if(Atac.atacar(this.name)){
                salaReunions.setNeo();
                System.out.println(ANSI_YELLOW + "RAMONIX DOWN!");

            };
        
                
            
           // Si el nombre del Hacker es Neo, este descansará 2 segundos antes de realizar el siguiente ataque
            if(name.equals("Neo"))
            {
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }else{
            // eL resto de Hackers descansan 1 segundo antes de realizar un nuevo ataque
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } 

        }

        }
    }
}
 
    





