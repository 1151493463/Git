package dao;

import entity.Cart;
import entity.Goods;
import entity.Manager;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import utils.CommonMethod;
import utils.CommonValue;
import utils.XMLDocumentFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: ImportantNotDelete
 * @Date: 2018-08-18 20:09
 * @Author: 夜~星空
 * @Description:
 */
public class XmlDB {

    private static Map<String,Manager> managers=new HashMap<String, Manager>();
    private static List<Cart> carts=new ArrayList<Cart>();
    private static Map<String,Goods> goods=new HashMap<String, Goods>();

    static {
        try {
            Document documentGoods = XMLDocumentFactory.getDocument(CommonValue.GoodsXmlURL);
            List<Element> elementsGoods = CommonMethod.parseXML(documentGoods);
            for(Element e:elementsGoods){
               Goods good=new Goods();
               good.setId(Integer.valueOf(e.elementText("id")));
               good.setName(e.elementText("name"));
               good.setPrice(Double.parseDouble(e.elementText("price")));
               good.setNum(Integer.parseInt(e.elementText("num")));
               good.setRemark(e.elementText("remark"));
               good.setSaleNum(Integer.parseInt(e.elementText("saleNum")));
               goods.put(good.getName(),good);
            }
            Document documentManager = XMLDocumentFactory.getDocument(CommonValue.ManagerXmlURL);
            List<Element> elementsManager = CommonMethod.parseXML(documentManager);
            for(Element e:elementsManager){
                Manager manager=new Manager();
                manager.setId(Integer.valueOf(e.elementText("id")));
                manager.setName(e.elementText("name"));
                manager.setPassWord(e.elementText("password"));
                managers.put(manager.getName(),manager);
            }
            Document documentCart = XMLDocumentFactory.getDocument(CommonValue.CartXmlURL);
            List<Element> elementsCart = CommonMethod.parseXML(documentCart);
            for(Element e:elementsCart){
                Cart cart=new Cart();
                cart.setId(Integer.valueOf(e.elementText("id")));
                cart.setName(e.elementText("name"));
                cart.setTotalPrice(Double.parseDouble(e.elementText("totalPrice")));
                cart.setTotalCount(Integer.parseInt(e.elementText("totalCount")));
                cart.setDate(e.elementText("date"));
                cart.setPayMoney(Double.parseDouble(e.elementText("payMoney")));
                carts.add(cart);
            }
            } catch (DocumentException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        }
    }

    public static Map<String,Goods> getGoods(){
        return goods;
    }
    public static Map<String,Manager> getManagers(){
        return managers;
    }
    public static List<Cart> getCarts(){
        return carts;
    }

    public static void saveChanges(Document document,File file) throws IOException, DocumentException {

        Writer osWrite=new OutputStreamWriter(new FileOutputStream(file));//创建输出流

        OutputFormat format = OutputFormat.createPrettyPrint();  //获取输出的指定格式

        format.setEncoding("UTF-8");//设置编码 ，确保解析的xml为UTF-8格式

        XMLWriter writer = new XMLWriter(osWrite,format);//XMLWriter 指定输出文件以及格式

        writer.write(document);//把document写入xmlFile指定的文件(可以为被解析的文件或者新创建的文件)

        writer.flush();

        writer.close();
    }










}
