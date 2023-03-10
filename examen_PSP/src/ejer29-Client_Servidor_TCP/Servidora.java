import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;

import java.util.*;

public class Servidora {

	public static void main(String[] args) throws IOException {
		int puerto = 6000;
		Scanner s = new Scanner(System.in);
		ServerSocket servidor = new ServerSocket(puerto);
		Socket clienteconectado = null;
		FileWriter file=null;

		clienteconectado = servidor.accept();

		String titulo = calcula_timestamp() ;
		File file_salida = new File(titulo+".txt");
		if (!file_salida.exists()) {
			try {
				file_salida.createNewFile();
			}catch(IOException e) {
				System.out.println(e.getMessage());
			}
		}
		file = new FileWriter(file_salida,true);

		DataInputStream flujoEntrada = new DataInputStream(clienteconectado.getInputStream());
		String datorecibido = "";
		datorecibido = flujoEntrada.readUTF();
		System.out.println("Se ha recibido el producto:"+datorecibido);
		file.write(datorecibido+"\n");
		flujoEntrada.close();
		clienteconectado.close();
		servidor.close();
		file.close();

	}

	public static String calcula_timestamp() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String cadena = dateFormat.format(new Date());
		cadena = cadena.substring(0,10);
		return cadena;
	}

}

