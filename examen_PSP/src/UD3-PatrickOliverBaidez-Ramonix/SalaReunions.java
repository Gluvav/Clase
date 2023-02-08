
public class SalaReunions implements Comu{

    // La variable booleana neo controla que Neo esté presente en la sala
    private boolean neo = false; 

    // Devuelve el valor de la variable neo
    public boolean getNeo(){
        return neo; 
    }

    // Cambia el estado de la variable neo
    public void setNeo(){

        if(neo){ // Si neo es true, cambia su valor a false
            neo=false; 
        }else{ // Si neo es false, cambia su valor a true
            neo = true; 
        }

       
    }


//Muestra el nombre del nuevo asistente a la reunión . Si el asistente es Neo, 
// La variable neo guarda el valor true. 
    public void unido(String h){
        System.out.println(ANSI_PURPLE + h + " Ha arribat a la sala");

        if(h.equals("Neo")){
            System.out.println(ANSI_WHITE + "Comença l'atac!");
            neo = true; 
        }
    }


    // Devolverá un mensaje diciendo que ya estñan todos listos para atacar a Ramonix
    public String reunion(){

        return(ANSI_WHITE + "Todos reunidos! A por RAMONIX!");

    }

}
