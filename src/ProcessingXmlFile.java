import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class ProcessingXmlFile {
    private static String inputFileName;
    private static String outputFileName;

    ProcessingXmlFile (String inputFName, String outputFName) {
        inputFileName = inputFName;
        outputFileName = outputFName;
    }
    public ArrayList ReadXml() throws Exception {
        File file = new File(inputFileName);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        ArrayList list = new ArrayList();
        doc = dbf.newDocumentBuilder().parse(file);
        NodeList nodeList = doc.getElementsByTagName("element");
        for(int i = 0; i < nodeList.getLength();i++){
            list.add(nodeList.item(i).getTextContent());
        }
        return list;
    }

    public void WriteXML() throws Exception {
        String root = "root";
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element rootElement = document.createElement(root);

        document.appendChild(rootElement);

        ArrayList list = ReadXml();
        for(int i = 0; i < list.size();i++){
            String element = "element";
            Element em = document.createElement(element);
            RPN rpn = new RPN(list.get(i).toString());
            em.appendChild(document.createTextNode('\n' + rpn.RPNToAnswer().toString() + '\n'));
            rootElement.appendChild(em);
        }

        OutputStream fileOutputStream = new FileOutputStream(outputFileName);
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(fileOutputStream);
        transformer.transform(source, result);
        fileOutputStream.close();

    }
}
