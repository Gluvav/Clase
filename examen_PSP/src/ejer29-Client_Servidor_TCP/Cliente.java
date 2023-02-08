import java.io.*;
import java.net.*;

import java.util.*;

public class Cliente {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner s = new Scanner(System.in);
		String host = "localhost";
		String producto = "";
		int puerto = 6000;

		Socket Cliente = new Socket(host, puerto);

		DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());

		System.out.println("Introduzca producto para insertarlo en la lista de la compra:");
		producto = s.nextLine();
		flujoSalida.writeUTF(producto);
		//if (!producto.contentEquals("salir"))f
			System.out.println("Se ha volcado el producto "+producto+" a la lista de la compra.");
		//}
		flujoSalida.close();
		Cliente.close();
	}

}

