package dao.impl;

import Expection.executeUpdateExpection;
import dao.ManagerDao;
import dao.XmlDB;
import entity.Manager;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import utils.CommonValue;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @program: ImportantNotDelete
 * @Date: 2018-08-19 22:20
 * @Author: 夜~星空
 * @Description:
 */
public class ManagerDaoImplXml implements ManagerDao {
    @Override
    public void login(Manager manager) throws SQLException, Exception {
        Map<String, Manager> managers = XmlDB.getManagers();
        if(managers.get(manager.getName())!=null && manager.getPassWord().equals(managers.get(manager.getName()).getPassWord())){
        }else{
            throw new executeUpdateExpection("执行insert,delete,update操作失败！");
        }

    }

    @Override
    public Manager findUserByName(String name) throws SQLException, executeUpdateExpection {

        Manager manager = XmlDB.getManagers().get(name);
        if(manager==null){
            return null;
        }else{
            return manager;
        }


    }

    @Override
    public void enRoll(String name, String passWord) throws SQLException, executeUpdateExpection, DocumentException, IOException {

        Map<String, Manager> managers = XmlDB.getManagers();
        Manager manager=new Manager();
        manager.setId(managers.size()+1);
        manager.setName(name);
        manager.setPassWord(passWord);
        managers.put(name,manager);
        manager=null;
        File file=new File(CommonValue.ManagerXmlURL);
        SAXReader saxReader=new SAXReader();
        Document document = saxReader.read(file);
        Element element = DocumentHelper.createElement("manager");
        Element id = DocumentHelper.createElement("id");
        id.setText(String.valueOf(managers.size()+1));
        Element name1 = DocumentHelper.createElement("name");
        name1.setText(name);
        Element password = DocumentHelper.createElement("password");
        password.setText(passWord);
        element.add(id);
        element.add(name1);
        element.add(password);
        Element rootElement = document.getRootElement();
        rootElement.add(element);
        XmlDB.saveChanges(document,file);


    }

    @Override
    public void deleteUser(String name) throws SQLException, executeUpdateExpection, DocumentException, IOException {
        Map<String, Manager> managers = XmlDB.getManagers();
        managers.remove(managers.get(name));
        File file=new File(CommonValue.ManagerXmlURL);
        SAXReader saxReader=new SAXReader();
        Document document = saxReader.read(file);
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        System.out.println(elements);
       for(Element element:elements){
           //System.out.println(element.elementText("name"));
           if(element.elementText("name").equals(name)){
              rootElement.remove(element);
               XmlDB.saveChanges(document,file);
               break;
           }
       }
    }

    @Override
    public void updateUser(Manager manager) throws SQLException, executeUpdateExpection, DocumentException, IOException {
        Map<String, Manager> managers = XmlDB.getManagers();
        Collection<Manager> values = managers.values();
        String name=null;
        for(Manager manager1:values){
            if(manager1.getId().equals(manager1.getId())){
                name= manager1.getName();
                managers.remove(name);
                managers.put(manager.getName(),manager);
                break;
            }
        }
        File file=new File(CommonValue.ManagerXmlURL);
        SAXReader saxReader=new SAXReader();
        Document document = saxReader.read(file);
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        //System.out.println(elements);
        for(Element element:elements){
            if(element.elementText("id").equals(manager.getId().toString())) {
                element.element("name").setText(manager.getName());
                element.element("password").setText(manager.getPassWord());
                XmlDB.saveChanges(document, file);
                break;
            }
        }
    }

    @Override
    public boolean isExistUser(String name) throws SQLException {

        if(XmlDB.getManagers().get(name)!=null){
            return true;
        }
        return false;
    }
}
