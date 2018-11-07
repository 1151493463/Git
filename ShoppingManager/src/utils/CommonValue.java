package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class CommonValue {

    private static Properties properties=new Properties();
    private static Scanner in = new Scanner(System.in).reset();


    private CommonValue() { }

    static {
        InputStream inputStream = CommonValue.class.getResourceAsStream("/config.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        }
    }

    public static final int pageSize=Integer.parseInt(properties.getProperty("pageSize"));
    public static final String GoodsXmlURL=properties.getProperty("GoodsXmlURL");
    public static final String ManagerXmlURL=properties.getProperty("ManagerXmlURL");
    public static final String CartXmlURL=properties.getProperty("CartXmlURL");

    public static Scanner getInput(){
        return in.reset();
    }

    public static Properties getProperties(){
        return properties;
    }
}
