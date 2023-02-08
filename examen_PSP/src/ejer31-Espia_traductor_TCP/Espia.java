import java.io.*;
import java.net.*;
import java.util.*;

public class Espia {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner s = new Scanner(System.in);
		String host = "localhost";
		String mensaje = "";
		int puerto = 6000;

		Socket Cliente = new Socket(host, puerto);

		DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());

		System.out.println("Introduzca mensaje para codificarlo:");
		mensaje = s.nextLine();
		flujoSalida.writeUTF(mensaje);

		DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());

		System.out.println("El resultado de la traducción es...");
		System.out.println(flujoEntrada.readUTF());

		flujoEntrada.close();
		flujoSalida.close();
		Cliente.close();
	}

}

