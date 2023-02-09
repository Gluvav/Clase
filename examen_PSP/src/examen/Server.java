package examen;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String args[]) throws IOException {

        ServerSocket servidor; // CREE serverSocket
        servidor = new ServerSocket(60000);
        System.out.println("Inici Servidora en 60000");

        while (true) {
            Socket socketClient = new Socket();
            socketClient = servidor.accept(); // ESPERO AL CLIENT
            HiloServidor fil = new HiloServidor(socketClient); // Hijo recibe el cliente
            fil.start(); // INICIO EL HIJO
            System.out.println("Inici FilServidor");
        }

    }
}
