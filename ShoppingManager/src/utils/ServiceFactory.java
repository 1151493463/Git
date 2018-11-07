package utils;

import service.GoodsService;
import service.ManagerService;

public class ServiceFactory {

    private static Class ManagerServiceClazz=null;
    private static Class GoodsServiceClazz=null;

    static {
        try {
            ManagerServiceClazz=Class.forName(CommonValue.getProperties().getProperty("ManagerService"));
            GoodsServiceClazz=Class.forName(CommonValue.getProperties().getProperty("GoodsService"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        }
    }

    private ServiceFactory() {
    }

    public static ManagerService getManagerServiceInstance(){
            ManagerService managerService = null;
        try {
            managerService=(ManagerService) ManagerServiceClazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        }
        return managerService;
    }

    public static GoodsService getGoodsServiceInstance(){
        GoodsService goodsService=null;
        try {
            goodsService= (GoodsService) GoodsServiceClazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        }
        return goodsService;
    }

}
