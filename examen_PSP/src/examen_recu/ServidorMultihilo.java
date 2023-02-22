package examen_recu;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMultihilo {

    public static void main(String[] args) throws IOException {
        ServerSocket servidor; // CREE serverSocket
        servidor = new ServerSocket(60000);
        System.out.println("Servidor iniciado. Esperando conexiones...\n");

        while (true) {
            Socket socketClient = new Socket();
            socketClient = servidor.accept(); // ESPERO AL CLIENT
            System.out.println("Cielnte conectado desde " + socketClient.toString());
            HiloServidor fil = new HiloServidor(socketClient); // Hijo recibe el cliente
            fil.start(); // INICIO EL HIJO
        }
    }
}
