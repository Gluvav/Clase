import java.io.*;
import java.net.*;
import java.util.*;

public class TicketServer {

	public static void main(String[] args) throws IOException {
		String[] butacas = new String[4];
		Queue<String> reservas = new LinkedList<String>();
		Arrays.fill(butacas, "");
		int opcion=0;

		DatagramPacket recibo;

		//Asocio el socket al puerto 12345
		DatagramSocket socket = new DatagramSocket(12345);
		System.out.println("Servidora arrancado...");

		while(true) {
			byte[] bufer = new byte[1024];
			recibo = new DatagramPacket(bufer, bufer.length);
			socket.receive(recibo);
			//Gestion del mensaje recibido
			String mensaje = new String(recibo.getData()).trim();
			String[] partes = mensaje.split("@");
			opcion = Integer.parseInt(partes[0]);
			switch(opcion){
				case 1: listar_butacas(butacas, reservas);
					break;
				case 2: reservar_butaca(butacas, partes[1], reservas);
					break;
				case 3: anular_butaca(butacas,partes[1], reservas);
					break;
				case 123: break;
				default: System.out.println("La opcion "+opcion+" es incorrecta.");
					break;
			}
			if (opcion == 123)
				break;
		}//while
		socket.close();
	}

	public static void listar_butacas(String[] butacas, Queue<String> reservas) {
		int esperando = 1;

		for (int i=0; i < butacas.length; i++ ) {
			if (butacas[i].equals("")) {
				System.out.println((i+1)+":"+"LIBRE!");
			}else {
				System.out.println((i+1)+":"+butacas[i]);
			}
		}

		if (reservas.size() > 0) {
			System.out.println("Reservas:");
			Iterator iter = reservas.iterator();
			while (iter.hasNext()) {
				String persona = (String)iter.next();
				System.out.println(esperando+":"+persona);
				esperando++;
			}
		}
	}
	
	public static void reservar_butaca(String[] butacas, String nombre, Queue<String> reservas) {
		int encontrado=-1;
		int pos_libre=0;
		encontrado = buscar_butaca(butacas, nombre);
		if (encontrado == -1) { //Si esa persona no tiene comprada ninguna butaca...
			pos_libre = buscar_libre(butacas);
			if (pos_libre == -1) { //Si todas las butacas estan ocupadas...
				System.out.println("Lo siento, ya no quedan butacas libres, pasas a la lista de reservas.");
				reservas.add(nombre);
				return;
			}
			butacas[pos_libre] = nombre;
			System.out.println(nombre + " tiene asignada la butaca "+ (pos_libre+1)+".");
		}
	}

	public static int buscar_butaca(String[] butacas, String nombre) {
		int resultado = -1;
		for (int i=0; i < butacas.length; i++) {
			if (butacas[i].equalsIgnoreCase(nombre)) {
				System.out.println(nombre + " tenia asignada la butaca "+ (i+1)+".");
				resultado = i;
				break;
			}
		}
		return resultado;
	}

	public static int buscar_libre(String[] butacas) {
		int resultado = -1;
		for (int i=0; i < butacas.length; i++) {
			if (butacas[i].equals("")) {
				resultado = i;
				break;
			}
		}
		return resultado;
	}

	public static void anular_butaca(String[] butacas, String nombre, Queue<String> reservas) {
		int encontrado;
		encontrado = buscar_butaca(butacas, nombre);
		if (encontrado == -1) {
			System.out.println(nombre + " no tiene comprada ninguna butaca");
		}else {
			butacas[encontrado] = "";
			System.out.println("La butaca "+(encontrado+1)+" queda libre.");
			if (reservas.size() > 0) {
				System.out.print("Se adjudica a la primera persona de la lista de reservas que es: ");
				System.out.println(reservas.peek());
				butacas[encontrado] = reservas.peek();
				reservas.remove();
			}
		}
	}
}

