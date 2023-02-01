package XMLAndroid;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;

public class Hasher {

    public static void main(String[] args) throws ParserConfigurationException, TransformerException, FileNotFoundException {

        Scanner scStr = new Scanner(System.in);
        HashMap<Integer, String> map = new HashMap<>();
        //map.put("a", "b");

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("Datos");
        doc.appendChild(rootElement);

        //adding things
        Element alumno = doc.createElement("alumno");
        rootElement.appendChild(alumno);

        //...create XML elements, and others...
        Element nia = doc.createElement("nia");
        System.out.println("Dime tu nia:");
        map.put(0, scStr.nextLine());
        nia.setTextContent(map.get(0));
        alumno.appendChild(nia);
        Element name = doc.createElement("name");
        System.out.println("Dime tu nombre:");
        map.put(1, scStr.nextLine());
        name.setTextContent(map.get(1));
        alumno.appendChild(name);
        Element apes = doc.createElement("surname");
        System.out.println("Dime tu apellido:");
        map.put(2, scStr.nextLine());
        apes.setTextContent(map.get(2));
        alumno.appendChild(apes);
        Element curso = doc.createElement("curso");
        System.out.println("Dime tu curso:");
        map.put(3, scStr.nextLine());
        curso.setTextContent(map.get(3));
        alumno.appendChild(curso);

        // write dom document to a file
        FileOutputStream output = new FileOutputStream("C:\\Users\\7N\\Documents\\GitHub\\Clase\\PSP-Cliente_Servidor\\src\\XMLAndroid\\meds.xml");
            writeXml(doc, output);

    }

    private static void writeXml(Document doc,
                                 OutputStream output)
            throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // pretty print
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }

}