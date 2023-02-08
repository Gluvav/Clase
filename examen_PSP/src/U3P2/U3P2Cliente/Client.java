import java.io.*;
import java.net.*;

class Client {
	// NOM MAQUINA I PORT
	static final String HOST = "localhost";
	static final int PORT = 5000;
	
	public Client(String host) {
		try {
			// ES CREA EL SOCKET
			Socket sCliente = new Socket(HOST, PORT);
			System.out.println("Conectat al servidor...");
			System.out.println("Conectat.");

			// CREE ELS FLUXOS D'ENTRADA I EIXIDA PER AL SOCKET
			// ASSOCIE FLUX EIXIDA DE DADES AL SOCKET DEL CLIENT
			InputStream isEntrada = sCliente.getInputStream();
			// ASSOCIE FLUX DE DADES A UN ALTRE FLUX TIPUS DataInputStream
			DataInputStream disEntrada = new DataInputStream(isEntrada);
			// ASSOCIE FLUX EIXIDA DE DADES AL SOCKET DEL CLIENT
			OutputStream osEixida = sCliente.getOutputStream();
			// ASSOCIE FLUX DE DADES A UN ALTRE FLUX TIPUS DataOutputStream
			DataOutputStream dosEixida = new DataOutputStream(osEixida);
			
			// ENVIE CADENA I MOSTRE RESPOSTA DEL SERVIDOR
			System.out.println("Hola");
			dosEixida.writeUTF("Hola");
			System.out.println("Servidora: " + disEntrada.readUTF());
			System.out.println("Com estas");
			dosEixida.writeUTF("Com estas");
			System.out.println("Servidora: " + disEntrada.readUTF());
			System.out.println("Adeu");
			dosEixida.writeUTF("Adeu");
			System.out.println("Servidora: " + disEntrada.readUTF());

			// TANQUE EL SOCKET
			System.out.println("Tancant connexi�...");
			sCliente.close();
		} catch (Exception e) {
			System.out.println("Error: no s'ha pogut connectar a " + host + ":" + PORT);
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] arg) {
		if (arg.length!=1) {
			System.out.println("Error: deus introduir un par�metre que �s el HOST");
			return;
		}
		new Client(arg[0]);
		return;
	}
}
