package test;

import Expection.executeUpdateExpection;
import dao.GoodsDao;
import dao.SqliteDB;
import dao.XmlDB;
import entity.Goods;
import entity.Manager;
import menu.impl.LoginMenu;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
import service.ManagerService;
import utils.CommonMethod;
import utils.CommonValue;
import utils.ServiceFactory;

import javax.swing.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;

public class Demo {
    @Test
    public void test1() throws SQLException, ClassNotFoundException {
        /*Connection connection = SqliteDB.getConnection();
        Statement statement = connection.createStatement();*/
        Statement statement = SqliteDB.getStatement();
        //statement.setQueryTimeout(30); // set timeout to 30 sec.
        // 执行查询语句
        ResultSet rs = statement.executeQuery("select * from manager");
        while (rs.next()) {
            String col1 = rs.getString("name");
            String col2 = rs.getString("password");
            System.out.println("col1 = " + col1 + "  col2 = " + col2);

            //System.out.println(location);
            // 执行插入语句操作
            //statement1.executeUpdate("insert into table_name2(col2) values('" + col2_value + "')");
            // 执行更新语句
            //statement1.executeUpdate("update table_name2 set 字段名1=" +  字段值1 + " where 字段名2='" +  字段值2 + "'");
        }
    }
    @Test
    public void test2(){
        System.out.println(CommonValue.getProperties().getProperty("DBPath"));
    }

    @Test
    public void test3(){
       // System.out.println(Factory.getInstance());
    }

    @Test
    public void test4() {
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.login();
        String dt="  ";
        dt.trim();
    }
    @Test
    public void test5(){
        Date date=new Date();
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        System.out.println();
        System.out.println(format);
    }
    @Test
    public void test6(){

        try {
            System.out.println(1/0);
        }catch (ArithmeticException e){

            System.out.println("zero");
            System.out.println(CommonValue.getProperties());

        }
    }
    @Test
    public void test7(){
        Test1<Goods> t = new Test1<Goods>();

        Goods goods = new Goods();
        goods.setId(1);
        List<Goods> list =new ArrayList<Goods>();
        list.add(goods);
        t.setList(list);

        System.out.println(t.getList().get(0).getId());



    }
    @Test
    public void test8(){

        double d1=8.5;
        double d2=8.5;
        System.out.println(d1>=d2);

    }
    @Test
    public void test9(){

        try {
            throw new executeUpdateExpection("执行insert,delete,update操作失败！");
        }
        catch (executeUpdateExpection e){
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        }
    }
    @Test
    public void test10(){
        ArrayList arrayList=new ArrayList();
    }

    @Test
    public void test11(){
        String s="2018-08-19";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //String format = simpleDateFormat.format(s);
        Date date=new Date();
        String format1 = simpleDateFormat.format(date);
        System.out.println(s.compareTo(format1));
    }
    @Test
    public void test12(){

        System.out.println(5/2+5%2*0.1);
        System.out.println(Math.ceil(5/2+5%2*0.1));

    }
    @Test
    public void test13(){

        try {
            Class clazz=Class.forName("dao.impl.GoodsDaoImpl");
            System.out.println(clazz);
            System.out.println(clazz.newInstance());
            Object o = clazz.newInstance();
            GoodsDao goodsDao= (GoodsDao) o;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        } catch (InstantiationException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        }
    }
    @Test
    public void test14(){
        System.out.println("fjsduhfus");
        System.out.println("fkoskopkfoskkf");
    }


    @Test
    public void test15(){
        SAXReader reader=new SAXReader();
        /*try {*/
         /*   Document read = reader.read("D:\\Users\\14758\\IdeaProjects\\ImportantNotDelete\\ShoppingManager\\src\\XML\\Goods.xml");
            XMLTool document= (XMLTool) read;
            document.parse(new XMLOPeration<Element>() {
                @Override*/
               /* public void doOPeration(List<Element> elements) {
                    for(Element element:elements){
                        System.out.println(element.getText());
                    }
                }
            });*/
           /* System.out.println(document);
            System.out.println("rootElelment:"+document.getRootElement());
            Element rootElement = document.getRootElement();
            System.out.println("goodElement:"+rootElement.element("good"));
            List<Element> elements = rootElement.elements();
            for(Element element:elements){
                System.out.println("elements:"+element);
                System.out.println("----------------------");
                List<Element> list = element.elements();
                for(Element e:list){
                    System.out.println("e.getText:"+e.getText());
                    //System.out.println(e.elementText("id"));
                }
            }*/
       /*     System.out.println();
        } catch (DocumentException e) {
            e.printStackTrace();
        }*/
    }
    @Test
    public void test16(){
        System.out.println(XmlDB.getGoods());
    }
    @Test
    public void test17() throws DocumentException, IOException {
        File xmlFile=new File("D:\\Users\\14758\\IdeaProjects\\ImportantNotDelete\\ShoppingManager\\src\\XML\\Manager.xml");
        SAXReader saxReader=new SAXReader();
        Document document = saxReader.read(xmlFile);
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        for(Element e:elements){
            if(e.elementText("name").equals("tom")){
                rootElement.remove(e);


            }
        }
        Writer osWrite=new OutputStreamWriter(new FileOutputStream(xmlFile));//创建输出流

        OutputFormat format = OutputFormat.createPrettyPrint();  //获取输出的指定格式

        format.setEncoding("UTF-8");//设置编码 ，确保解析的xml为UTF-8格式

        XMLWriter writer = new XMLWriter(osWrite,format);//XMLWriter 指定输出文件以及格式

        writer.write(document);//把document写入xmlFile指定的文件(可以为被解析的文件或者新创建的文件)

        writer.flush();

        writer.close();
    }
    @Test
    public  void test19(){
        String[][] strings = new String[1][];
        int a[][]={{1,3},{2,3,4},{1,2}};
        //int a[][]=new int[][3];
        String s[][]={{"can","I"},{"help","you"}};
        System.out.println(a[0][0]);
        System.out.println(s[0][0]);
        s[2][0]="dds";

        List list=new ArrayList();
        list.removeIf(new Predicate() {
            @Override
            public boolean test(Object o) {
                return false;
            }
        });
    }
    @Test
    public void test20() throws ParseException {
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date parse = simpleDateFormat.parse("2018-07-23");
        String format = simpleDateFormat.format(date);
        Date parse1 = simpleDateFormat.parse(format);
        int i = parse.compareTo(parse1);
        System.out.println(i);
    }
    @Test
    public void test21(){
        Map<String, Manager> managers = XmlDB.getManagers();
        List<Manager> values = (List<Manager>) managers.values();
        System.out.println(values);
    }
    @Test
    public void test22(){
        Map<String, Manager> managers = XmlDB.getManagers();
        Manager manager=new Manager();
        manager.setId(managers.size()+1);
        manager.setName("12212");
        manager.setPassWord("2121");
        managers.put("212",manager);
        manager=null;

        System.out.println(XmlDB.getManagers());

    }
    @Test
    public void test23(){
        File file=new File("F:");
        File[] listFiles = file.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                // TODO Auto-generated method stub
                return false;
            }
        });
        System.out.println(listFiles);
    }
    @Test
    public void test24(){
        ManagerService managerService=ServiceFactory.getManagerServiceInstance();
        try {
            Manager manager=new Manager();
            manager.setId(1);
            manager.setName("tom");
            manager.setPassWord("123456");
            managerService.updateUser(manager);
        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        } catch (Expection.executeUpdateExpection executeUpdateExpection) {
            executeUpdateExpection.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        } catch (IOException e) {
            e.printStackTrace(CommonMethod.erorLog());
            e.printStackTrace();
        }
    }
    @Test
    public void test25(){
        Collection<Goods> values1 = XmlDB.getGoods().values();
        List<Goods> values = new ArrayList<Goods>(values1);
        System.out.println(values);
    }

    @Test
    public void test26() throws IOException {
        //建立一个excel表
        Workbook workbook=new HSSFWorkbook();
        //建立sheet
        Sheet sheet = workbook.createSheet("测试");
        //设置相应的行
        Row row = sheet.createRow(0);
        //设置所在单元格
        Cell cell = row.createCell(0);
        //写入数据
        cell.setCellValue("这是一条测试数据");
        //保存到计算机相应的位置
        ((HSSFWorkbook) workbook).write(new FileOutputStream("D://测试.xls"));
    }

    @Test
    public void test27() throws SQLException, IOException {
        Statement statement = SqliteDB.getStatement();
        String sql="select *from goods";
        ResultSet rs = statement.executeQuery(sql);
        Workbook workbook=new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("商品列表");
        Row row = sheet.createRow(0);
        String[] head={"商品编号","商品名称","商品价格","商品数量","商品备注","销售量"};
        for(int i=0;i<6;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(head[i]);
        }
        int i=1;
        while (rs.next()){
            Row row1 = sheet.createRow(i);
            row1.createCell(0).setCellValue(rs.getInt("id"));
            row1.createCell(1).setCellValue(rs.getString("name"));
            row1.createCell(2).setCellValue(rs.getDouble("price"));
            row1.createCell(3).setCellValue(rs.getInt("num"));
            row1.createCell(4).setCellValue(rs.getString("remark"));
            row1.createCell(5).setCellValue(rs.getInt("saleNum"));
            i++;
        }
        ((HSSFWorkbook) workbook).write(new FileOutputStream("D://测试.xls"));
    }

    @Test
    public void test28(){
        try {
            int i=1/0;

        }catch (ArithmeticException e){

                Date date=new Date();
                e.printStackTrace(CommonMethod.erorLog());
        }
    }
    @Test
    public void test(){
        File file=new File("D:/");
        System.out.println(file.exists());
        JFileChooser jFileChooser=new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.setDialogTitle("请选择要保存的位置");
        int i = jFileChooser.showOpenDialog(null);
        if(i==JFileChooser.APPROVE_OPTION){
            String path = jFileChooser.getSelectedFile().getPath();
            System.out.println(path.replaceAll("\\\\","/"));
        }
    }

}
