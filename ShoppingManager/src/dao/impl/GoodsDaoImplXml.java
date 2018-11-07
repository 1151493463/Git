package dao.impl;

import Expection.executeUpdateExpection;
import dao.GoodsDao;
import dao.XmlDB;
import entity.Cart;
import entity.Goods;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import utils.CommonValue;
import utils.Page;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * @program: ImportantNotDelete
 * @Date: 2018-08-19 22:17
 * @Author: 夜~星空
 * @Description:
 */
public class GoodsDaoImplXml implements GoodsDao {
    @Override
    public void addGoods(Goods goods) throws Exception {
        Map<String, Goods> goods1 = XmlDB.getGoods();
        if(goods1.get(goods.getName())==null){
            goods.setId(goods1.size()+1);
            goods1.put(goods.getName(),goods);
            Goods good=null;
            Element elementGood = DocumentHelper.createElement("good");
            Element id = DocumentHelper.createElement("id");
            id.setText(String.valueOf(goods.getId()));
            Element name = DocumentHelper.createElement("name");
            name.setText(goods.getName());
            Element price = DocumentHelper.createElement("price");
            price.setText(String.valueOf(goods.getPrice()));
            Element num = DocumentHelper.createElement("num");
            num.setText(String.valueOf(goods.getNum()));
            Element remark = DocumentHelper.createElement("remark");
            remark.setText(goods.getRemark()+"");
            Element saleNum = DocumentHelper.createElement("saleNum");
            saleNum.setText(String.valueOf(goods.getSaleNum())+"");
            elementGood.add(id);
            elementGood.add(name);
            elementGood.add(price);
            elementGood.add(num);
            elementGood.add(remark);
            elementGood.add(saleNum);
            File file=new File(CommonValue.GoodsXmlURL);
            SAXReader saxReader=new SAXReader();
            Document document = saxReader.read(file);
            document.getRootElement().add(elementGood);
            XmlDB.saveChanges(document,file);
        }else{
            throw new executeUpdateExpection("执行insert,delete,update操作失败！");
        }
    }

    @Override
    public void deleteGoods(String name) throws SQLException, DocumentException, IOException {
        Map<String, Goods> goods = XmlDB.getGoods();
        goods.remove(name);
        File file=new File(CommonValue.GoodsXmlURL);
        SAXReader saxReader=new SAXReader();
        Document document = saxReader.read(file);
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        for(Element element:elements){
            if(element.elementText("name").equals(name)){
                rootElement.remove(element);
                XmlDB.saveChanges(document,file);
                break;
            }
        }
    }

    @Override
    public void updateGoods(Goods good) throws SQLException, DocumentException, IOException {
        Map<String, Goods> goods1 = XmlDB.getGoods();
        Collection<Goods> values = goods1 .values();
        String name=null;
        for(Goods goods:values){
            if(goods.getId().equals(good.getId())){
                name= good.getName();
                goods1.remove(name);
                goods1.put(good.getName(),good);
                break;
            }
        }
        File file=new File(CommonValue.GoodsXmlURL);
        SAXReader saxReader=new SAXReader();
        Document document = saxReader.read(file);
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        for(Element element:elements){
            if(element.elementText("id").equals(good.getId().toString())) {
                element.element("name").setText(good.getName());
                element.element("price").setText(String.valueOf(good.getPrice()));
                element.element("num").setText(String.valueOf(good.getNum()));
                XmlDB.saveChanges(document, file);
                break;
            }
        }
    }

    @Override
    public boolean isExist(String name) throws SQLException, Exception {
        boolean flag=false;
        Map<String, Goods> goods = XmlDB.getGoods();
        if(goods.get(name)==null){
            flag=true;
        }
        return flag;
    }

    @Override
    public int getTotalCount(String name, String key) throws SQLException {
        Map<String, Goods> goods = XmlDB.getGoods();
        int count=0;
        if("asc".equalsIgnoreCase(key)){
            count = goods.size();
        }else{
            Collection<Goods> values = goods.values();
            for(Goods goods1:values){
                if(goods1.getName().contains(key)){
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public List<Goods> getGoodsByPage(Page page) throws SQLException {
        List<Goods> values = new ArrayList<Goods>((Collection<? extends Goods>) XmlDB.getGoods().values());
        if("asc".equalsIgnoreCase(page.getValue())){
            if("price".equals(page.getKey())){
                Collections.sort(values, new Comparator<Goods>() {
                    @Override
                    public int compare(Goods o1, Goods o2) {
                        return (int) (o1.getPrice()-o2.getPrice());
                    }
                });
            }else if("num".equals(page.getKey())){
                Collections.sort(values, new Comparator<Goods>() {
                    @Override
                    public int compare(Goods o1, Goods o2) {
                        return (o1.getNum()-o2.getNum());
                    }
                });
            }
        }else {
            List<Goods> goods=new ArrayList<Goods>();
            if("".equals(page.getValue()) && page.getValue()==null){
                values=goods;
            } else{
                for(Goods goods1:values){
                    if(goods1.getName().contains(page.getValue())){
                        goods.add(goods1);
                    }
                }
                values=goods;
            }
        }
        return values;
    }

    @Override
    public void calCart(Cart cart) throws SQLException, DocumentException, IOException {
        List<Cart> carts = XmlDB.getCarts();
        File fileCart=new File(CommonValue.CartXmlURL);
        SAXReader saxReader=new SAXReader();
        Document documentCart = saxReader.read(fileCart);
        Element rootElement = documentCart.getRootElement();

        File fileGoods=new File(CommonValue.GoodsXmlURL);
        SAXReader saxReader1=new SAXReader();
        Document documentGoods = saxReader.read(fileGoods);

        Element rootElement1 = documentGoods.getRootElement();
        List<Element> elementsGoods = rootElement1.elements();

        for(int i=0;i<cart.getList().size();i++){
            Goods goods=cart.getList().get(i);

            Cart cart1=new Cart();
            cart1.setId(carts.size()+1);
            cart1.setName(goods.getName());
            cart1.setTotalCount(goods.getNum());
            double d=goods.getNum()*goods.getPrice();
            cart1.setTotalPrice(Double.parseDouble(String.valueOf((d))));
            cart1.setDate(cart.getDate());
            cart1.setPayMoney(cart.getPayMoney());
            carts.add(cart1);

            Element cart2 = DocumentHelper.createElement("cart");
            Element id = DocumentHelper.createElement("id");
            id.setText(String.valueOf(carts.size()+i));
            Element name = DocumentHelper.createElement("name");
            name.setText(goods.getName());
            Element totalCount = DocumentHelper.createElement("totalCount");
            totalCount.setText(String.valueOf(goods.getNum()));
            Element totalPrice = DocumentHelper.createElement("totalPrice");
            totalPrice.setText(String.valueOf(d));
            Element date = DocumentHelper.createElement("date");
            date.setText(cart.getDate());
            Element payMoney = DocumentHelper.createElement("payMoney");
            payMoney.setText(String.valueOf(cart.getPayMoney()));
            cart2.add(id);
            cart2.add(name);
            cart2.add(totalCount);
            cart2.add(totalPrice);
            cart2.add(date);
            cart2.add(payMoney);
            rootElement.add(cart2);

            for(Element element:elementsGoods){
                if(element.elementText("name").equals(goods.getName())){
                    Element num = element.element("num");
                    String num1 = element.elementText("num");
                    num.setText(String.valueOf(goods.getNum()-Integer.parseInt(num1)));
                    String saleNum1 = element.elementText("saleNum");
                    Element saleNum = element.element("saleNum");
                    saleNum.setText(String.valueOf(goods.getNum()+Integer.parseInt(saleNum1)));
                }
            }

        }
        XmlDB.saveChanges(documentGoods,fileGoods);
        XmlDB.saveChanges(documentCart,fileCart);

    }

    @Override
    public int getTotalCountByDate(String name, String key) throws SQLException {
        List<Cart> carts = XmlDB.getCarts();
        int count=0;
        if("".equalsIgnoreCase(name) || name==null){
            count=carts.size();
        }else{
            for(Cart cart:carts){
                if(cart.getDate().compareTo((key+" 24:00:00"))<=0 &&
                        cart.getDate().compareTo((key+" 00:00:00"))>=0){
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public List<Cart> getGoodsByPageAndDate(Page page) throws SQLException {
        List<Cart> carts = XmlDB.getCarts();
        List<Cart> carts1=new ArrayList<Cart>();

        if("".equalsIgnoreCase(page.getKey()) || page.getKey()==null){
            carts1=carts;
        }else{
            for(Cart cart:carts){
                if(cart.getDate().compareTo((page.getValue()+" 24:00:00"))<=0 &&
                        cart.getDate().compareTo((page.getValue()+" 00:00:00"))>=0){
                    carts1.add(cart);
                }
            }
        }



        return carts1;
    }

    @Override
    public Goods findGoodsByKey(String name, String value) throws SQLException {

        Map<String, Goods> goods = XmlDB.getGoods();
        Collection<Goods> values = goods.values();

        Goods goods1=null;
        if("name".equals(name)){
            goods1 = goods.get(value);
        }else if("name".equals("id")){
            for(Goods goods2:values){
                if(goods2.getId().equals(value)){
                    goods1=goods2;
                    break;
                }
            }
        }
        return goods1;
    }

    @Override
    public List<Goods> getAllGoods() throws SQLException {
        return null;
    }

    @Override
    public List<Cart> getAllCart() throws SQLException {
        return null;
    }
}
