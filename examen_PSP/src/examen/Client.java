package examen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 60000) ){

            BufferedReader brEntrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pwSalida = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            String cadena = "", cadenaRecibida = "";
            System.out.println("Introduce words, and when you send *, i'll give them back to you in alphabetical order.");
            do {
                System.out.println("Introduce word:");
                cadena = in.readLine();
                pwSalida.println(cadena);
            } while (!cadena.contentEquals("*"));
            cadenaRecibida = brEntrada.readLine();
            int palabras = Integer.parseInt(cadenaRecibida);
            String cadenaFinal = "";
            for (int i = 0; i < palabras -1; i++){
                cadenaFinal += brEntrada.readLine() + ", ";
            }
            System.out.println("Recibo cadena compuesta y ordenada:");
            System.out.println(cadenaFinal);
            System.out.println("Fin de la comunicacion");

            pwSalida.close();
            brEntrada.close();
            in.close();
            socket.close();

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
