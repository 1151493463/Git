package utils;

import menu.impl.*;
import menu.Menu;

public class MenuFacadeFactory {
    /**
     * @param menuEnum
     * @return
     */
    private static Menu menuFactory(MenuEnum menuEnum){
        Menu menu = null;
        switch (menuEnum) {
            case WelcomeMenu:
                menu = new WelcomeMenu();
                break;
            case LoginMenu:
                menu = new LoginMenu();
                break;
            case ChoiceMenu:
                menu = new ChoiceMenu();
                break;
            case ShoopingContainMenu:
                menu = new ShoopingContainMenu();
                break;
            case ShoopingManagerMenu:
                menu = new ShoopingManagerMenu();
                break;
            case CalMoneyMenu:
                menu = new CalMoneyMenu();
                break;
        }
        return menu;
    }

    public static void menuFacade(MenuEnum menuEnum){
        Menu menu = menuFactory(menuEnum);
        menu.currentMenu();
    }
}
