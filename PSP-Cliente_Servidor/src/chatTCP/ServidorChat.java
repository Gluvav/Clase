package chatTCP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServidorChat extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    static final int port = 44444;//puerto en el que escucha
    static ServerSocket servidor;
    static int conexiones = 0;
    static int actuales = 0;
    static int maxConexiones = 10;//Numero maximo de conexiones por puerto

    static JTextField mensaje = new JTextField("");
    static JTextField mensaje2 = new JTextField("");
    private JScrollPane scrollPanel;
    static JTextArea textArea;
    JButton salir = new JButton("Salir");
    static Socket socketsClientes[] = new Socket[maxConexiones];

    public ServidorChat(){

        //contruir la ventana del servidor
        super("Servidor del Chat");
        setLayout(null);

        mensaje.setBounds(10, 10, 400, 30);
        add(mensaje);
        mensaje.setEditable(false);

        mensaje2.setBounds(10, 10, 400, 30);
        add(mensaje2);
        mensaje2.setEditable(false);

        textArea = new JTextArea();
        scrollPanel = new JScrollPane(textArea);
        scrollPanel.setBounds(10, 50, 400, 300);
        add(scrollPanel);

        salir.setBounds(420, 10, 100, 30);
        add(salir);

        textArea.setEditable(false);
        salir.addActionListener(this);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }

    public static void main(String[] args) throws IOException {

        servidor = new ServerSocket(port);
        System.out.println("Servidor iniciado");

        ServidorChat pantalla = new ServidorChat();
        pantalla.setBounds(0, 0, 540, 400);
        pantalla.setVisible(true);
        mensaje.setText("Numero de conexiones actuales " + conexiones);

        while (conexiones < maxConexiones){
            Socket socket = new Socket();
            try{
                socket = servidor.accept();
            } catch (SocketException ns){
                break;
            }

            socketsClientes[conexiones] = socket;
            conexiones++;
            actuales++;
            HijoServidor hilo = new HijoServidor(socket);
            hilo.start();
        }

        if (!(servidor.isClosed())) {
            try {
                mensaje2.setForeground(Color.red);
                mensaje2.setText("Numero maximo de conexiones establecidas " + conexiones);
                servidor.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == salir){
                System.exit(0);
        }
    }
}
