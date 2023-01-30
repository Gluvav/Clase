package chatTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.net.Socket;

public class HijoServidor extends Thread{

    DataInputStream fsalida;
    Socket socket = null;
    Socket s1 = null;

    public HijoServidor(Socket s){

        socket = s;
        try {
            fsalida = new DataInputStream(socket.getInputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();

        ServidorChat.mensaje.setText("Numero de conexiones actuales " + ServidorChat.actuales);

        String texto = ServidorChat.textArea.getText();
        enviarMensajes(texto);

        while (true) {
            String cadena = "";
            try {
                cadena = fsalida.readUTF();

                if (cadena.trim().contentEquals("*")){
                    ServidorChat.actuales--;
                    ServidorChat.mensaje.setText("Numero de conexiones actuales " + ServidorChat.actuales);
                    break;
                }

                ServidorChat.textArea.append(cadena + "\n");
                texto = ServidorChat.textArea.getText();
                enviarMensajes(texto);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void enviarMensajes (String texto) {

        for (int i = 0; i < ServidorChat.conexiones; i++) {
            s1 = ServidorChat.socketsClientes[i];
            try {
                DataOutputStream fsalida = new DataOutputStream(s1.getOutputStream());
                fsalida.writeUTF(texto);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
}
