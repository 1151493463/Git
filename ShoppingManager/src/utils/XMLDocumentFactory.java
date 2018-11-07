package utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * @program: ImportantNotDelete
 * @Date: 2018-08-20 14:22
 * @Author: 夜~星空
 * @Description:
 */
public class XMLDocumentFactory {

    private static SAXReader saxReader=new SAXReader();

    public static Document getDocument(String url) throws DocumentException {
        return saxReader.read(url);
    }
}
