package utils;

import dao.GoodsDao;
import dao.ManagerDao;

public class DaoFactory {

    private static Class GoodsDaoClazz=null;
    private static Class ManagersDaoClazz=null;

    static {
        try {
            GoodsDaoClazz=Class.forName(CommonValue.getProperties().getProperty("GoodsDao"));
            ManagersDaoClazz=Class.forName(CommonValue.getProperties().getProperty("ManagerDao"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        }
    }

    private DaoFactory() {

    }

    public static ManagerDao getManagerDaoInstance(){
        ManagerDao managerDao=null;
        try {
            managerDao = (ManagerDao) ManagersDaoClazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        }
        return managerDao;
    }

    public static GoodsDao getGoodsDaoInstance(){
        GoodsDao goodsDao=null;
        try {
            goodsDao = (GoodsDao) GoodsDaoClazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        }
        return goodsDao;
    }
}
