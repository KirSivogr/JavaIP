import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class JavaReadXml {
    // Документ можно рассматривать как зеркало XML в памяти, тогда, когда этот документ получен, это означает, что
    private Document doc = null;
    public void init(String xmlFile) throws Exception {
        // фабрика, DocumentBuilder генерируется через эту фабрику,
        // DocumentBuilder используется для генерации документа
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        // Этот документ является изображением файла XML в памяти
        doc = db.parse(new File(xmlFile));
    }

    // Этот метод отвечает за отображение содержимого файла XML
    public void viewXML(String xmlFile) throws Exception {
        this.init(xmlFile);
        // В XML-файле есть только один корневой элемент. Сначала вынимаем корневой элемент, чтобы увидеть
        Element element = doc.getDocumentElement();
        System.out.println("Корневой элемент: " + element.getTagName());

        NodeList nodeList = doc.getElementsByTagName("person");
        System.out.println("длина цепочки книжных узлов: " + nodeList.getLength());

        Node fatherNode = nodeList.item(0);
        System.out.println("Родительский узел :" + fatherNode.getNodeName());

        // Вынимаем атрибуты родительского узла
        NamedNodeMap attributes = fatherNode.getAttributes();

        for (int i = 0; i < attributes.getLength(); i++) {
            Node attribute = attributes.item(i);
            System.out.println("Имя атрибута книги: " + attribute.getNodeName()
                    + "Соответствующее значение атрибута: " + attribute.getNodeValue());
        }
        NodeList childNodes = fatherNode.getChildNodes();
        System.out.println(childNodes.getLength());
        for (int j = 0; j < childNodes.getLength(); j++) {
            Node childNode = childNodes.item(j);
            // Если этот узел принадлежит элементу, то принять значение
            if (childNode instanceof Element) {
                // System.out.println ("Имя дочернего узла:" + childNode.getNodeName () + "Соответствующее значение равно" + childNode.getFirstChild (). GetNodeValue ());
                System.out.println("Имя дочернего узла: " + childNode.getNodeName()
                        + " Соответствующее значение " + childNode.getFirstChild().getNodeValue());
            }
        }
    }
}
