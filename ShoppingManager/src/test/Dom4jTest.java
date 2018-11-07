package test;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.function.Predicate;

public class Dom4jTest {

    private File file;
    private Document document;

    @Before
    public void before(){
        try {
            file=new File("D:\\Users\\14758\\Workspaces\\MyEclipse 2017 CI\\test\\src\\dom4j\\Book.xml");
            SAXReader saxReader=new SAXReader();
            document = saxReader.read(file);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 1.查询第一本书的书名，并输出到控制台
     */

    @Test
    public void test1(){

        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        Element element = elements.get(0);
        String book = element.elementText("书名");
        System.out.println(book);

    }

    /**
     * 2.查询第二本书的售价，并输出到控制台
     */
    @Test
    public void test2(){

        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        Element element = elements.get(1);
        String price = element.elementText("售价");
        System.out.println(price);

    }
    /**
     * 3.给第一本书添加一个特价节点（2种方式）
     * @throws IOException
     */
    @Test
    public void test3() throws IOException{

        Element createElement = DocumentHelper.createElement("特价");
        createElement.setText("30.00元");
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        Element book = elements.get(0);
        book.add(createElement);
        XMLWriter writer=new XMLWriter(new FileOutputStream(file),OutputFormat.createPrettyPrint());
        writer.write(document);
        writer.close();
		/*Writer osWrite=new OutputStreamWriter(new FileOutputStream(file));//创建输出流
        OutputFormat format = OutputFormat.createPrettyPrint();  //获取输出的指定格式
        format.setEncoding("UTF-8");//设置编码 ，确保解析的xml为UTF-8格式
        XMLWriter writer = new XMLWriter(osWrite,format);//XMLWriter 指定输出文件以及格式
        writer.write(document);//把document写入xmlFile指定的文件(可以为被解析的文件或者新创建的文件)
        writer.flush();
        writer.close();*/

    }
    /**
     * 4.给第二本书在作者节点前插入一个特价节点
     * @throws IOException
     */
    @Test
    public void test4() throws IOException{
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        Element createElement = DocumentHelper.createElement("特价");
        createElement.setText("30.00元");
        List book = elements.get(1).elements();
        book.add(1, createElement);
        Writer osWrite=new OutputStreamWriter(new FileOutputStream(file));//创建输出流
        OutputFormat format = OutputFormat.createPrettyPrint();  //获取输出的指定格式
        format.setEncoding("UTF-8");//设置编码 ，确保解析的xml为UTF-8格式
        XMLWriter writer = new XMLWriter(osWrite,format);//XMLWriter 指定输出文件以及格式
        writer.write(document);//把document写入xmlFile指定的文件(可以为被解析的文件或者新创建的文件)
        writer.flush();
        writer.close();
    }
    /**
     * 5.删除第二本书的特价节点（2种方式）
     * @throws IOException
     */
    @Test
    public void test5() throws IOException{
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        Element book = elements.get(1);
        Element bargin = book.element("特价");
        book.remove(bargin);
        write();
    }
    @Test
    public void test5_1() throws IOException{
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        List<Element> book = elements.get(1).elements();
        Element remove = book.remove(1);
        System.out.println(book.getClass());
        boolean removeIf = book.removeIf(new Predicate<Element>() {

            @Override
            public boolean test(Element t) {
                // TODO Auto-generated method stub
                if (t.getName().equals("特价")) {
                    return true;
                }
                return false;
            }
        });
        System.out.println(book.size());
        Element element = elements.get(1);
        write();
    }

    /**
     * 6.更新第一本书的特价节点的内容为19.8元
     * @throws IOException
     */
    @Test
    public void test6() throws IOException{
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        Element book = elements.get(0);
        Element bargin = book.element("特价");
        bargin.setText("19.8元");
        write();
    }
    /**
     * 1.给第一本书添加一个属性，如：出版社="清华大学出版社"(2种方式)
     * @throws IOException
     */
    @Test
    public void test7() throws IOException{
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        Element book = elements.get(0);
        book.addAttribute("出版社", "清华大学出版社");
        //book.setAttributeValue("出版社", "出版社");
        write();
    }
    /**
     * 2.在控制台上打印输出第一本书的出版社属性的值,并更新属性的值为“人民出版社”(3种方式)
     * method1
     * @throws IOException
     */
    @Test
    public void test8() throws IOException{
        Element rootElement = document.getRootElement();
        Element book = rootElement.element("书");
        String attributeValue = book.attributeValue("出版社");
        Attribute attribute = book.attribute("出版社");
        attribute.setValue("人民出版社");
        System.out.println(attributeValue);
        write();
    }
    /**
     * 2.在控制台上打印输出第一本书的出版社属性的值,并更新属性的值为“人民出版社”(3种方式)
     * method2
     * @throws IOException
     */
    @Test
    public void test9() throws IOException{
        Element rootElement = document.getRootElement();
        Element book = rootElement.element("书");
        book.setAttributeValue("出版社", "清华大学出版社");
        write();
    }

    /**
     * 2.在控制台上打印输出第一本书的出版社属性的值,并更新属性的值为“人民出版社”(3种方式)
     * method3
     * @throws IOException
     */
    @Test
    public void test10() throws IOException{
        Element rootElement = document.getRootElement();
        Element book = rootElement.element("书");
        write();
    }
    /**
     * 3.删除第一本书的出版社属性(2种方式)
     * @throws IOException
     */
    @Test
    public void test11() throws IOException{
        Element rootElement = document.getRootElement();
        Element book = rootElement.element("书");
        Attribute attribute = book.attribute("出版社");
        book.remove(attribute);
        write();
    }

    /**
     * 3.删除第一本书的出版社属性(2种方式)
     * @throws IOException
     */
    @Test
    public void test12() throws IOException{
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        Element book = elements.get(0);
        Attribute attribute = book.attribute("出版社");
        book.remove(attribute);
        write();
    }
    /**
     * （作业）给第二本书添加一个属性，如：编号="b01"
     * @throws IOException
     */
    @Test
    public void test13() throws IOException{
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        Element book = elements.get(1);
        book.addAttribute("编号", "b001");
        write();
    }
    /**
     * （作业）给第二本书添加一个属性，如：编号="b01"
     * @throws IOException
     */
    @Test
    public void test14() throws IOException{
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        Element book = elements.get(1);
        Attribute createAttribute = DocumentHelper.createAttribute(book, "编号", "b001");
        book.add(createAttribute);
        write();
    }
    /**
     * 5.（作业）在控制台上打印输出第二本书编号属性的值,并更新该属性的值
     */
    @Test
    public void test15(){
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        Element book = elements.get(1);
        System.out.println(book.attributeValue("编号"));

    }
    /**
     * 6.（作业）删除第二本书的编号属性
     * @throws IOException
     */
    @Test
    public void test16() throws IOException{

        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        Element book = elements.get(1);
        Attribute attribute = book.attribute("编号");
        book.remove(attribute);
        write();
    }

    public void write() throws IOException{
        Writer osWrite=new OutputStreamWriter(new FileOutputStream(file));//创建输出流
        OutputFormat format = OutputFormat.createPrettyPrint();  //获取输出的指定格式
        format.setEncoding("UTF-8");//设置编码 ，确保解析的xml为UTF-8格式
        XMLWriter writer = new XMLWriter(osWrite,format);//XMLWriter 指定输出文件以及格式
        writer.write(document);//把document写入xmlFile指定的文件(可以为被解析的文件或者新创建的文件)
        writer.flush();
        writer.close();
    }
}
