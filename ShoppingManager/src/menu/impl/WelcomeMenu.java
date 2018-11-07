package menu.impl;

import menu.Menu;
import utils.CommonValue;
import utils.MenuEnum;
import utils.MenuFacadeFactory;

public class WelcomeMenu implements Menu {
    @Override
    public void currentMenu() {
        System.out.println("\n\n\t\t欢迎光临商超购物管理系统");
        System.out.println("**********************************************************");
        System.out.print("按回车键进入系统");
        CommonValue.getInput().nextLine();
        MenuFacadeFactory.menuFacade(MenuEnum.LoginMenu);

    }
}
