//package http_service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;



public class http_client {

	public static void main(String[] args) throws Exception {
	
	//	Creo objeto InetAddress a partir de la url  
    InetAddress addr = InetAddress.getByName("www.google.com");

    //creo socket al objto Inetaddress en el puerto 80
    Socket socket = new Socket(addr, 80);
    
    boolean autoflush = true;
    
    //creo flujos
    PrintWriter out = new PrintWriter(socket.getOutputStream(), autoflush);
    BufferedReader in = new BufferedReader(
    new InputStreamReader(socket.getInputStream()));

    // envio HTTP request al web server
    out.println("GET / HTTP/1.1");
    out.println("Host: www.google.com:80");
    out.println("Connection: Close");
    out.println();

    // leo respuesta 
    boolean loop = true;
    StringBuilder sb = new StringBuilder(8096); //objeto para respuesta
    while (loop) {
      if (in.ready()) {
        int i = 0;
        while (i != -1) {
          i = in.read(); ///mientras el codigo no sea -1 (EOF) continuo leyendo
          sb.append((char) i);
        }
        loop = false;
      }
    }
    System.out.println(sb.toString());
    socket.close();
  }
}
