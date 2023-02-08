
//UDPClient.java: A simple UDP client program.
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class U3P3UDPClient {
	public static void main(String args[]) {
		// args recibe mensaje host destino y puerto

		DatagramSocket aSocket = null; // declaro DatagramSocket

		// control entrada argumentos
		if (args.length < 2) {
			System.out.println("Uso: java UDPClient <Host name> <Port number>");
			System.exit(1);
		}

		try {

			// proceso envio datagrama
			aSocket = new DatagramSocket(); // creo datagrama
			
			System.out.println("Introduzca el mensaje a enviar:");
			String cadena;
			Scanner in;
			in = new Scanner(System.in);
			// System.out.println("Introduce lo que quieras enviar al servidor");
			cadena = in.nextLine();
			in.close();
			byte[] m = cadena.getBytes(); // matriz de bytes
			InetAddress aHost = InetAddress.getByName(args[0]); // recupero host de argumento

			int serverPort = Integer.valueOf(args[1]).intValue(); // recupero puerto de argumento
																	// int value devuelve valor tipo int

			DatagramPacket request = new DatagramPacket(m, cadena.length(), aHost, serverPort); // datagrama a enviar

			aSocket.send(request); // envio datagrama

			// proceso recepción datagrama
			byte[] buffer = new byte[1000]; // declaro matriz bytesmatriz de bytes
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length); // declaro datagrama respuesta
			aSocket.receive(reply); // recibo datagrama
			System.out.println("Reply: " + new String(reply.getData())); // muestro respuesta
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (aSocket != null)
				aSocket.close();
		}
	}
}