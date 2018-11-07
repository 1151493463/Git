package menu.impl;

import menu.Menu;
import utils.CommonMethod;
import utils.CommonValue;
import utils.MenuEnum;
import utils.MenuFacadeFactory;

public class ChoiceMenu implements Menu {
    @Override
    public void currentMenu() {
        System.out.println("\n\n\t\t商超购物管理系统   功能选择界面");
        System.out.println("**********************************************************");
        System.out.println("\t\t\t1. 商品维护\n\t\t\t2. 收银\n\t\t\t3. 商品管理\n\t\t\tb. 返回登陆页面\n\t\t\tq. 退出");
        System.out.println("**********************************************************");
        System.out.print("请选择，输入数字选择相应操作:");
        input();
    }
    public void input(){

        String choice = CommonValue.getInput().next();
        switch (choice){
            case "1":
                MenuFacadeFactory.menuFacade(MenuEnum.ShoopingContainMenu);
                break;
            case "2":
                MenuFacadeFactory.menuFacade(MenuEnum.CalMoneyMenu);
                break;
            case "3":
                MenuFacadeFactory.menuFacade(MenuEnum.ShoopingManagerMenu);
                break;
            case "b":
                MenuFacadeFactory.menuFacade(MenuEnum.LoginMenu);
                break;
            case "q":
                CommonMethod.exit();
                break;
            default:
                System.out.println("操作序号输入有误，请重新选择");
                input();
        }
    }

}
