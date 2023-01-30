package chatTCP;

import javax.annotation.processing.SupportedSourceVersion;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteChat extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    static JTextField mensaje = new JTextField("");
    static JTextArea textArea1;
    private JScrollPane scrollPanel;
    JButton enviar = new JButton("Enviar");
    JButton desconectar = new JButton("Desconectar");

    DataInputStream fentrada;
    DataOutputStream fsalida;

    Socket socket = null;
    String nombre;
    boolean repetir = true;

    public ClienteChat(Socket s, String nombre){

        super("Conexion del ClienteChat por tcp");
        setLayout(null);
        mensaje.setBounds(10, 10, 400, 30);
        add(mensaje);
        textArea1 = new JTextArea();
        scrollPanel = new JScrollPane(textArea1);
        scrollPanel.setBounds(10, 50, 400, 300);
        add(scrollPanel);
        enviar.setBounds(420, 10, 100, 30);
        add(enviar);
        desconectar.setBounds(420, 50, 100, 30);
        add(desconectar);
        textArea1.setEditable(false);
        enviar.addActionListener(this);
        desconectar.addActionListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        socket = s;
        this.nombre = nombre;

        try {
            fentrada = new DataInputStream(socket.getInputStream());
            fsalida = new DataOutputStream(socket.getOutputStream());
            String texto = "Se ha conectado... " + nombre;
            fsalida.writeUTF(texto);
        } catch (IOException e){
            System.out.println("Error de 'E/S'");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        int port = 44444;

        String nickname = JOptionPane.showInputDialog("Introduce tu nombre de Usuario");

        Socket s = null;
        try {
            s = new Socket("localhost", port);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Imposible conectar con el servidor.");
            System.exit(0);
            e.printStackTrace();
        }

        if (!nickname.trim().equals("")){
            ClienteChat cliente = new ClienteChat(s, nickname);
            cliente.setBounds(0, 0, 540, 400);
            cliente.setVisible(true);
            cliente.ejecutar();
        } else {
            System.out.println("El nombre esta vacio");
        }

    }

    public void ejecutar () {

        String texto = "";
        while (repetir){
            try {
                texto = fentrada.readUTF();
                textArea1.setText(texto);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Imposible conectar con el servidor \n");
                repetir=false;
                e.printStackTrace();
            }
        }

        try {
            socket.close();
            System.exit(0);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == enviar){
            String texto = nombre + ">" + mensaje.getText();
            try {
                fsalida.writeUTF(texto);
                mensaje.setText("");
            } catch (IOException a){
                a.printStackTrace();
            }
        }
        if (e.getSource() == desconectar){
            String texto = nombre + ">Se ha desconectado.";
            try {
                fsalida.writeUTF(texto);
                fsalida.writeUTF("*");
                repetir=false;
                System.exit(0);
            } catch (IOException i){
                i.printStackTrace();
            }
        }

    }
}
