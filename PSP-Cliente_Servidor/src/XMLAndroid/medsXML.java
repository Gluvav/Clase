package XMLAndroid;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class medsXML {

    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        BufferedReader brNames = new BufferedReader(new FileReader(
                new File("C:\\Users\\7N\\Documents\\GitHub\\Clase\\PSP-Cliente_Servidor\\src\\XMLAndroid\\names.txt")));
        BufferedReader brDescriptions = new BufferedReader(new FileReader(
                new File("C:\\Users\\7N\\Documents\\GitHub\\Clase\\PSP-Cliente_Servidor\\src\\XMLAndroid\\description.txt")));

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("Medicamentos");
        doc.appendChild(rootElement);

        String lineNames = brNames.readLine();
        String lineDesc = brDescriptions.readLine();
        int identifier = 0;

        while (lineNames != null || lineDesc != null) {
            //adding things
            Element medicamento = doc.createElement("medicamento");
            rootElement.appendChild(medicamento);

            //...create XML elements, and others...
            Element id = doc.createElement("identificador");
            id.setTextContent(identifier+"");
            medicamento.appendChild(id);
            Element nombre = doc.createElement("nombre");
            nombre.setTextContent(lineNames);
            medicamento.appendChild(nombre);
            Element descripcion = doc.createElement("descripcion");
            descripcion.setTextContent(lineDesc);
            medicamento.appendChild(descripcion);
            Element delUsuario = doc.createElement("delUsuario");
            delUsuario.setTextContent("False");
            medicamento.appendChild(delUsuario);

            lineNames = brNames.readLine();
            lineDesc = brDescriptions.readLine();
            identifier++;
        }

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