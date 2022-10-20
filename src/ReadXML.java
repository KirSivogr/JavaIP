import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ReadXML {
    public static void readFromXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // Фабрика по производству папок
            DocumentBuilder bf = factory.newDocumentBuilder(); // Создать папку с фабрикой
            Document doc = bf.parse("C:\\Users\\sivog\\IdeaProjects\\PP\\news.xml"); // Метод parse () объекта класса DocumentBuilder для анализа файла XML и возврата объекта Document

            NodeList channels = doc.getElementsByTagName("channel");
            for (int i = 0; i < channels.getLength(); i++) {
                Element channel = (Element) channels.item(i);
                Attr idAttr = doc.createAttribute("id");
                idAttr.setValue("hello");
                channel.setAttributeNode(idAttr);
                NodeList childs = channel.getChildNodes();
                for (int j = 0; j < childs.getLength(); j++) {
                    Node child = childs.item(j);
                    if (child.getNodeName().equals("title"))
                        System.out.println("<title> название канала:" + child.getFirstChild().getNodeValue());
                    if (child.getNodeName().equals("description"))
                        System.out.println("<описание> сведения о канале:" + child.getFirstChild().getNodeValue());
                    if (child.getNodeName().equals("item")) {
                        NodeList items = child.getChildNodes();
                        for (int k = 0; k < items.getLength(); k++) {
                            Node item = items.item(k);
                            if (item.getNodeName().equals("title"))
                                System.out.println("Содержимое <title> является заголовком новости:" + item.getFirstChild().getNodeValue());
                            if (item.getNodeName().equals("link"))
                                System.out.println("Содержимое <link> является ссылкой на новость:" + item.getFirstChild().getNodeValue());
                            if (item.getNodeName().equals("description"))
                                System.out.println("<item> / <description> - это новость:" + item.getFirstChild().getNodeValue());
                        }
                    }
                }
            }
            writeToXml(doc, "C:\\news2.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeToXml(Document doc, String wayToFile) {
        try {
            OutputStream fileOutputStream = new FileOutputStream(wayToFile);
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(fileOutputStream);
            transformer.transform(source, result);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
