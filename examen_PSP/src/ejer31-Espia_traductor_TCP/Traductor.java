import java.io.*;
import java.net.*;

//import java.util.*;

public class Traductor {

	public static void main(String[] args) throws IOException {
		int numeroPuerto = 6000;
		ServerSocket servidor = new ServerSocket(numeroPuerto);
		Socket clienteConectado = null;
		String mensaje_entrada="";
		String mensaje_salida="";
		System.out.println("Esperando al espia...");
		clienteConectado = servidor.accept();

		//CREO FLUJO DE ENTRADA DEL CLIENTE
		InputStream entrada = null;
		entrada = clienteConectado.getInputStream();
		DataInputStream flujoEntrada = new DataInputStream(entrada);

		//EL ESPIA ENVIA UN MENSAJE Y EL TRADUCTOR LO TRADUCE
		mensaje_entrada = flujoEntrada.readUTF();
		mensaje_salida = traducir(mensaje_entrada);
		System.out.println("El mensaje ha sido traducido.");
		//CREO FLUJO DE SALIDA AL CLIENTE
		OutputStream salida = null;
		salida = clienteConectado.getOutputStream();
		DataOutputStream flujoSalida = new DataOutputStream(salida);

		//ENVIO UN SALUDO AL CLIENTE
		flujoSalida.writeUTF(mensaje_salida);
		System.out.println("Devolviendo el mensaje traducido al espia...");

		//CERRAR STREAMS Y SOCKETS
		entrada.close();
		flujoEntrada.close();
		salida.close();
		flujoSalida.close();
		clienteConectado.close();
		servidor.close();
	}

	public static String traducir(String mensaje_entrada) {
		String parte1="";
		String parte2="";
		int pos;
		while (mensaje_entrada.indexOf("el area 51") != -1) {
			pos = mensaje_entrada.indexOf("el area 51");
			parte1 = "";
			parte2 = "";
			parte1 = mensaje_entrada.substring(0,pos);
			parte2 = mensaje_entrada.substring(pos+10);
			mensaje_entrada = parte1 + "Murcia" + parte2;
		}
		return mensaje_entrada;
	}
}

