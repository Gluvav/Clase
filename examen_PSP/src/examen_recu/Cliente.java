package examen_recu;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cliente {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 60000) ) {
            ObjectOutputStream oos = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()));
            ObjectInputStream ois = new ObjectInputStream(new DataInputStream(socket.getInputStream()));

            Coche coche = new Coche("Toyota Corolla", "Azul");
            oos.writeObject(coche);
            //enviar el coche bien
            System.out.println("Coche enviado: " + coche.getName() + " " + coche.getColor());
            String orig =coche.getName() + " " + coche.getColor();
            MessageDigest md1 = MessageDigest.getInstance("MD5");
            String clave1 = "1234";
            byte dataBytes1[] = orig.getBytes();
            md1.update(dataBytes1);
            byte resumClave1[] = md1.digest(clave1.getBytes());

            md1.update(orig.getBytes());

            byte resum1[] = md1.digest();

            //recibir el objeto
            coche = (Coche) ois.readObject();
            System.out.println("Coche actualizado recibido: " + coche.getName() + " " + coche.getColor());
            String moded =coche.getName() + " " + coche.getColor();

            MessageDigest md2 = MessageDigest.getInstance("MD5");
            String clave2 = "1234";
            byte dataBytes2[] = moded.getBytes();
            md2.update(dataBytes2);
            byte resumClave2[] = md2.digest(clave2.getBytes());

            md2.update(moded.getBytes());

            byte resum2[] = md2.digest();

            if (resum1.equals(resum2)){
                System.out.println("Es el mismo");
            } else {
                System.out.println("Hash MD5 fallido: los datos recibidos no son correctos.");
            }

        } catch (NoSuchAlgorithmException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
