package examen_recu;

import java.io.*;
import java.net.Socket;

public class HiloServidor extends Thread{

    Socket socket = null;
    Coche coche;

    public HiloServidor(Socket s) throws IOException {
        socket = s;
    }

    @Override
    public void run() {
        super.run();

        try {
            ObjectInputStream ois = new ObjectInputStream(new DataInputStream(socket.getInputStream()));
            ObjectOutputStream oos = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()));
            coche = (Coche) ois.readObject();

            //recibir el objeto
            System.out.println("Coche recibido: " + coche.getName() + " " + coche.getColor() );
            //modificar el color del coche
            coche.setColor("Rojo");
            System.out.println("Coche modificado a : " + coche.getName() + " " + coche.getColor());
            //enviar el coche de vuelta
            oos.writeObject(coche);
            System.out.println();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
