package QuizMagic;

import java.net.*;
import java.io.*;

public class Client {

	public static void main(String args[]) throws IOException {
		try (
				Socket socket = new Socket("localhost", 60000)) {

			// CREE FLUXOS D'ENTRADA I EIXIDA
			PrintWriter feixida = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader fentrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// FLUX PER A L'ENTRADA PER TECLAT
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			String nom;

			System.out.println("Benvingut a Quiz Magic\nIntrodueix el nom del jugador");
			nom = in.readLine();
			feixida.println(nom);

			// REP LA PREGUNTA DEL SERVIDOR
			String resposta, pregunta = fentrada.readLine();
			// LLIG PREGUNTES FINS QUE ENVIE UNA CADENA QUE COMENCE PER Puntuaci�:
			while (!(pregunta.startsWith("Puntuaci�:"))) {
				System.out.println(pregunta);
				// LLIG LA RESPOSTA DES DEL TECLAT
				resposta = in.readLine();
				// ENVIE LA RESPOSTA AL SERVIDOR
				feixida.println(resposta);
				pregunta = fentrada.readLine();
			}
			System.out.println("Molt b� " + nom + ". " + pregunta);
			System.out.println("Fi comunicaci�");

			// TANQUE FLUXOS I SOCKET
			feixida.close();
			fentrada.close();
			in.close();
			socket.close();
		}
	}

}
