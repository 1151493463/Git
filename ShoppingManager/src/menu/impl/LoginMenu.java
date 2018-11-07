package menu.impl;

import Expection.executeUpdateExpection;
import menu.Menu;
import org.dom4j.DocumentException;
import service.ManagerService;
import utils.*;

import java.io.IOException;
import java.sql.SQLException;

public class LoginMenu implements Menu {

    private ManagerService managerService;

    @Override
    public void currentMenu() {
        System.out.println("\n\n\t\t商超购物管理系统   管理员登录");
        System.out.println("**********************************************************");
        System.out.println("\t\t\t1. 注册\n\t\t\t2. 登录\n\t\t\tq. 退出");
        System.out.println("**********************************************************");
        System.out.print("请选择，输入数字选择相应操作:");
        input();

    }

    public void input(){
        String choice = CommonValue.getInput().next();
        switch (choice){
            case "1":
                enRoll();
                break;
            case "2":
                login();
                break;
            case "q":
                CommonMethod.exit();
                break;
            default:
                System.out.println("没有该序号，请重新输入！");
                input();
        }
    }

    private void enRoll() {

        System.out.println("***账户注册***");
        System.out.println("请输入用户名：");
        try {
        String name=CommonValue.getInput().next();
        managerService=ServiceFactory.getManagerServiceInstance();
        if(CommonMethod.isNull(name) && CommonMethod.loginNameCheck(name)){
            if(managerService.isExistUser(name)){
                System.out.println("请输入密码：");
                String passWord=CommonValue.getInput().next();
                if(CommonMethod.loginPasswordCheck(passWord)){
                    managerService.enRoll(name,passWord);
                    login();
                }else{
                    System.out.println("密码不符合规范，请重新输入！");
                    enRoll();
                }
            }else{
                System.out.println("该用户已存在，请重新输入！");
                enRoll();
            }
        }else{
            System.out.println("用户名不符合规范，请重新输入！");
            enRoll();
        }
        } catch (SQLException e) {
            System.out.println("数据库异常！");
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
            enRoll();
        } catch (executeUpdateExpection executeUpdateExpection) {
            executeUpdateExpection.printStackTrace();
            System.out.println("该用户已存在，请重新输入！");
            enRoll();
        } catch (DocumentException e) {
            System.out.println("该用户已存在，请重新输入！");
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        }
    }

    /**
     * 用户登陆
     */
    public void login() {

        String name=null;
        String password=null;
        System.out.println("*****用户登陆*****");
        System.out.println("请输入用户名:");
        name=CommonValue.getInput().next();
        if(CommonMethod.loginNameCheck(name)){
            System.out.println("请输入密码：");
            password=CommonValue.getInput().next();
            if(CommonMethod.loginPasswordCheck(password)){
                managerService=ServiceFactory.getManagerServiceInstance();
                try {
                    managerService.login(name, password);
                    MenuFacadeFactory.menuFacade(MenuEnum.ChoiceMenu);
                }
                  catch(executeUpdateExpection e1){
                      e1.printStackTrace(CommonMethod.erorLog());
                      System.out.println("用户名不存在");
                      login();
                }
                catch (SQLException e2){
                    System.out.println("数据库异常！");
                    e2.printStackTrace(CommonMethod.erorLog());
                    e2.printStackTrace();
                }
                catch (Exception e3) {
                    e3.printStackTrace(CommonMethod.erorLog());
                    e3.printStackTrace();
                    login();
                }

            }else{
                System.out.println("输入的密码不符合规范（4~20字母或数字或下划线组成），请重新输入：");
                login();
            }
        }else{
            System.out.println("输入的用户名不符合规范（2~20字母或数字或汉字或下划线组成），请重新输入：");
            login();
        }
    }
}
