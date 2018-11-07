package utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonMethod {
    public static void exit(){
        System.out.println("谢谢使用！");
        System.exit(0);
    }

    public static boolean loginNameCheck(String str) {

        boolean flag = false;
        String regEx = "^[0-9A-Za-z_\u4e00-\u9fa5]{2,20}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        flag = matcher.matches();

        return flag;
    }

    public static boolean loginPasswordCheck(String str){

        boolean flag = false;
        String regEx = "^[0-9A-Za-z_]{4,20}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        flag = matcher.matches();

        return flag;

    }
    public static boolean isNull(String str){
        return (!"".equals(str.trim()) && str!=null);
    }


    public static boolean checkDate(String date){

        String regEx="[0-9]{4}-(0?[1-9]|1[0-2])-((0?[1-9])|((1|2)[0-9])|30|31)$";
        Pattern pattern=Pattern.compile(regEx);
        Matcher matcher=pattern.matcher(date);

        return matcher.matches();
    }

    public static List<Element> parseXML(Document document) throws DocumentException {

        Element rootElement = document.getRootElement();
        List elements = rootElement.elements();

        return elements;
    }

    public static PrintStream erorLog(){
        FileOutputStream fileOutputStream=null;
        try {
            Date date=new Date();
            fileOutputStream = new FileOutputStream(CommonValue.getProperties().getProperty("errorLog"),true);
            fileOutputStream.write((date.toString()+"\n").getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        }
        return new PrintStream(fileOutputStream,true);

    }
}
