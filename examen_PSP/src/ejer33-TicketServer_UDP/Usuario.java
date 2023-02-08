import java.io.*;
import java.net.*;
import java.util.*;

public class Usuario {

	public static void main(String[] args) throws IOException {
		//Datos del Servidora al que enviar mensaje
		InetAddress IPServidor = InetAddress.getLocalHost();
		Scanner s = new Scanner(System.in);
		int puerto = 12345;
		int opcion = 0;
		String nombre = "";

		//Socket de envio
		DatagramSocket clientSocket = new DatagramSocket(34567);

		//Introducir datos por teclado
		System.out.println("==TICKETSERVER==");
		while (opcion != 4) {
			String cadena = "";
			nombre= "";
			System.out.println("Introduzca opcion:");
			System.out.println("1-Listar butacas");
			System.out.println("2-Reservar butacas");
			System.out.println("3-Anular butacas");
			System.out.println("4-Salir");
			opcion = s.nextInt();
			s.nextLine();
			switch(opcion) {
				case 1: cadena = "1@vacio";
					break;
				case 2: System.out.println("Introduzca su nombre para la reserva:");
					nombre = s.nextLine();
					cadena = "2@" + nombre;
					break;
				case 3: System.out.println("Introduzca su nombre para la cancelacion:");
					nombre = s.nextLine();
					cadena = "3@" + nombre;
					break;
				case 4: System.out.println("Gracias por utilizar TicketServer");
					break;
				case 123: System.out.println("MODO ROOT: Apagando el Servidora desde el Cliente...");
					cadena = "123@vacio";
					break;
				default: System.out.println("Opcion incorrecta");
					continue;

			}

			byte[] enviados = new byte[1024];
			enviados=null;
			enviados = cadena.getBytes(); //convertir cadena a bytes

			//Creamos el datagrama que irá al servidor
			DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);

			if (opcion == 4) {
				clientSocket.close();
				break;
			}else {
				clientSocket.send(envio);
				if (opcion == 123) {
					clientSocket.close();
					break;
				}
			}
		}
	}
}

