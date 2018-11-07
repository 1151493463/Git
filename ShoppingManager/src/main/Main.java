package main;

import utils.MenuEnum;
import utils.MenuFacadeFactory;


public class Main {
    public static void main(String[] args) {
        MenuFacadeFactory.menuFacade(MenuEnum.WelcomeMenu);
    }
}

