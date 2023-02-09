package examen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class HiloServidor extends Thread {

    Socket socket = null;

    BufferedReader brEntrada;
    PrintWriter pwSalida;

    //constructor del hijo, que recibe el socket del cliente
    public HiloServidor(Socket c) throws IOException {
        socket = c;
        //creo los flujos de entrada y salida, para poder comunicarme con el cliente
        brEntrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pwSalida = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        super.run();

        String cadenaCliente = "";
        boolean parar = false;

        ArrayList<String> cadenas = new ArrayList<>();

        while (!cadenaCliente.trim().equals("*") && !parar){
            System.out.println("En el Hijo: me comunico con: " + socket.toString());
            try {
                cadenaCliente = brEntrada.readLine();
                cadenas.add(cadenaCliente);
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        /*
        Le paso el tamaño de la lista, porque no se si se puede pasar la lista directamente, en el cliente
        hago un parseInt de la cadena y en un for recibo cada elemento de forma individual
        */
        Collections.sort(cadenas);
        pwSalida.println(cadenas.size() + "");
        for (int i = 0; i < cadenas.size(); i++) {
            if (cadenas.get(i).equals("*")){
            } else {
                pwSalida.println(cadenas.get(i));
            }
        }
        System.out.println("Ended connection with " + socket.toString());

        pwSalida.close();
        try {
            brEntrada.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
