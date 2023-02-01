package XMLAndroid;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMLaHashMap{
    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\7N\\Documents\\GitHub\\Clase\\PSP-Cliente_Servidor\\src\\XMLAndroid\\meds.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("medicamento");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println("Node name: " + node.getNodeName());
                if (node.getNodeType() == node.ELEMENT_NODE){
                    Element eElement = (Element) node;
                    System.out.println("Id: " + eElement.getElementsByTagName("identificador").item(0).getTextContent());
                    System.out.println("First Name: "+ eElement.getElementsByTagName("nombre").item(0).getTextContent());
                    System.out.println("Last Name: " + eElement.getElementsByTagName("descripcion").item(0).getTextContent());
                    System.out.println("Subject: " + eElement.getElementsByTagName("delUsuario").item(0).getTextContent());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
