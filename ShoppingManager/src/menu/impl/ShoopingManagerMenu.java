package menu.impl;

import Expection.executeUpdateExpection;
import entity.Cart;
import entity.Goods;
import entity.Manager;
import menu.Menu;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.dom4j.DocumentException;
import service.GoodsService;
import service.ManagerService;
import utils.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ShoopingManagerMenu implements Menu {

    private GoodsService goodsService;
    private ManagerService managerService;

    @Override
    public void currentMenu() {
        System.out.println("\n\n\t\t商超购物管理系统   商品管理 ");
        System.out.println("**********************************************************");
        System.out.println("\t\t\t1. 列出卖出商品\n\t\t\t2. 售货员管理\n\t\t\t3. 导出商品列表\n\t\t\tb. 返回上级菜单\n\t\t\tq. 退出");
        System.out.println("**********************************************************");
        System.out.print("请选择，输入数字选择相应操作:");
        input();
    }

    public void input(){

        String choice = CommonValue.getInput().next();
        switch (choice){
            case "1":
                showSaleGoodsList();
                break;
            case "2":
                userManage();
                break;
            case "3":
                exportGoods();
                break;
            case "b":
                MenuFacadeFactory.menuFacade(MenuEnum.ChoiceMenu);
                break;
            case "q":
                CommonMethod.exit();
                break;
            default:
                System.out.println("操作序号输入有误，请重新选择");
                input();
        }
    }

    private void exportGoods() {
            goodsService = ServiceFactory.getGoodsServiceInstance();
            try {
                List<Cart> allCart = goodsService.getAllCart();
                List<Goods> allGoods = goodsService.getAllGoods();
                Workbook workbook = new HSSFWorkbook();
                Sheet sheet = workbook.createSheet("商品列表");
                Row row = sheet.createRow(0);
                String[] head = {"商品编号", "商品名称", "商品价格", "商品数量", "商品备注", "销售量"};
                for (int j = 0; j < head.length; j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(head[j]);
                }
                for (int i1 = 0; i1 < allGoods.size(); i1++) {
                    Row row1 = sheet.createRow(i1 + 1);
                    Goods goods = allGoods.get(i1);
                    row1.createCell(0).setCellValue(goods.getId());
                    row1.createCell(1).setCellValue(goods.getName());
                    row1.createCell(2).setCellValue(goods.getPrice());
                    row1.createCell(3).setCellValue(goods.getNum());
                    row1.createCell(4).setCellValue(goods.getRemark());
                    row1.createCell(5).setCellValue(goods.getSaleNum());
                }
                Sheet sheet1 = workbook.createSheet("销售列表");
                Row row1 = sheet1.createRow(0);
                String[] head1 = {"商品编号", "商品名称", "购买数量", "价格", "购买日期", "支付金额"};
                for (int j = 0; j < head1.length; j++) {
                    Cell cell = row1.createCell(j);
                    cell.setCellValue(head1[j]);
                }
                for (int i1 = 0; i1 < allCart.size(); i1++) {
                    Row row2 = sheet1.createRow(i1 + 1);
                    Cart cart = allCart.get(i1);
                    row2.createCell(0).setCellValue(cart.getId());
                    row2.createCell(1).setCellValue(cart.getName());
                    row2.createCell(2).setCellValue(cart.getTotalCount());
                    row2.createCell(3).setCellValue(cart.getTotalPrice());
                    row2.createCell(4).setCellValue(cart.getDate());
                    row2.createCell(5).setCellValue(cart.getPayMoney());
                }
                ((HSSFWorkbook) workbook).write(new FileOutputStream("F:/商品列表.xls"));
                System.out.println("导出至F:/商品列表.xls");
                currentMenu();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * 售货员管理
     */
    private void userManage() {

        System.out.println("***售货员管理***");
        System.out.println("\t\t\t1. 增加管理员\n\t\t\t2. 删除管理员\n\t\t\t3. 修改管理员\n\t\t\tb. 返回上一菜单");
        System.out.println("**********************************************************");
        String choice = CommonValue.getInput().next();
        switch (choice){
            case "1":
                addManager();
                break;
            case "2":
                deleteManager();
                break;
            case "3":
                updateManager();
                break;
            case "b":
                currentMenu();
                break;
            default:
                System.out.println("没有该选项，请重新输入！");
                userManage();
        }


    }

    private void updateManager() {

        System.out.println("***更新管理员***\n");
        System.out.println("请输入要更改的管理员名称");
        String name=CommonValue.getInput().next();
        try {
            managerService=ServiceFactory.getManagerServiceInstance();
            Manager userByName = managerService.findUserByName(name);
            if(userByName!=null){
                System.out.println(userByName.toString());
                updateManagerType(userByName);
            }else{
                System.out.println("输入的用户不存在，请重新输入！");
                updateManager();
            }
        } catch (SQLException e) {
            System.out.println("更新失败！数据库异常");
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        } catch (executeUpdateExpection e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
            System.out.println("更新失败，请检查输入是否正确！");
        }catch (Exception e){
            System.out.println("更新失败,未知错误！");
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        }
    }

    public void updateManagerType(Manager userByName) throws SQLException, executeUpdateExpection, IOException, DocumentException {
        System.out.println("选择您要更改的内容：");
        System.out.println("\t\t1. 更改管理员名称\n\t\t2. 更改管理员密码\n\t\tb. 返回上级菜单");
        String choice=CommonValue.getInput().next();
        switch (choice){
            case "1":
                updateManagerName(userByName);
                userManage();
                break;
            case "2":
                System.out.println("请输入新的管理员密码：");
                String newPassword=CommonValue.getInput().next();
                userByName.setPassWord(newPassword);
                managerService.updateUser(userByName);
                System.out.println("更新成功！");
                userManage();
                break;
            case "b":
                currentMenu();
                break;
            default:
                System.out.println("没有该选项，请重新输入！");
                updateManagerType(userByName);
        }
    }

    private void updateManagerName(Manager manager) throws SQLException, executeUpdateExpection, IOException, DocumentException {
        System.out.println("请输入新的管理员名称：");
        String newName=CommonValue.getInput().next();
        managerService=ServiceFactory.getManagerServiceInstance();
        if(managerService.isExistUser(newName)){
            manager.setName(newName);
            managerService.updateUser(manager);
            System.out.println("更新成功！");
            userManage();
        }else{
            System.out.println("该用户名已存在，请冲新输入！");
            updateManagerName(manager);
        }
    }

    private void deleteManager() {

        System.out.println("***删除管理员***\n");
        System.out.println("请输入要删除的管理员名称：");
        String name = CommonValue.getInput().next();
        managerService=ServiceFactory.getManagerServiceInstance();
        try {
            managerService.deleteUser(name);
            System.out.println("删除成功！");
            userManage();
        } catch (executeUpdateExpection userNotFindExpection) {
            userNotFindExpection.printStackTrace();
            System.out.println("删除失败，用户未发现，请检查用户名是否正确");
            deleteManager();
        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
            System.out.println("更新失败！数据库异常");
            deleteManager();
        }
        catch (Exception e){
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
            System.out.println("添加失败,未知错误！");
            deleteManager();
        }

    }

    private void addManager() {

        System.out.println("***添加管理员操作***\n");
        System.out.println("请输入管理员名称：");
        String name = CommonValue.getInput().next();
        try {
            if(CommonMethod.isNull(name) && CommonMethod.loginNameCheck(name)) {
                managerService=ServiceFactory.getManagerServiceInstance();
                if(managerService.findUserByName(name)==null){
                    System.out.println("请输入管理员密码：");
                    String passWord = CommonValue.getInput().next();
                   if(CommonMethod.isNull(passWord) && CommonMethod.loginPasswordCheck(passWord)){
                       managerService.enRoll(name,passWord);
                       Manager userByName = managerService.findUserByName(name);
                       if(userByName!=null){
                           System.out.println(userByName.toString());
                           System.out.println("添加成功！");
                           currentMenu();
                       }
                       else{
                           System.out.println("添加失败，请重新尝试！");
                       }
                   }else{
                       System.out.println("输入的密码不符合规范（4~20字母或数字或下划线组成），请重新输入：");
                   }
                }else{
                    System.out.println("该用户已存在，请重新输入！");
                }
            }else{
                System.out.println("输入的用户名不符合规范（2~20字母或数字或汉字或下划线组成），请重新输入：");
            }
            addManager();
        }catch (SQLException e){
            System.out.println("更新失败！数据库异常");
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
            addManager();
        }catch (Exception e){
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
            System.out.println("添加失败,未知错误！");
            addManager();
        }

    }

    /**
     * 按日期条件列出卖出商品
     */
    private void showSaleGoodsList() {
        try {
            System.out.println("\t\t1.今日售出的商品\n\t\t2.查询指定日期售出的商品\n\t\t3.查询全部售出的商品\n\t\tb. 返回上级菜单");
            String choice = CommonValue.getInput().next();
            switch (choice) {
                case "1":
                    showTodaySaleGoods();
                    break;
                case "2":
                    showSaleGoodsByDate();
                    break;
                case "3":
                    showAllSaleGoods();
                    break;
                case "b":
                    currentMenu();
                    break;
                default:
                    System.out.println("没有该选项，请重新输入！");
                    showSaleGoodsList();
            }
        }catch (SQLException e){
            System.out.println("数据库异常！");
            showSaleGoodsList();
        }catch (Exception e){
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
            System.out.println("添加失败,未知错误！");
            showSaleGoodsList();
        }
    }

    private void showAllSaleGoods() throws SQLException {
        Page<Cart> page=new Page<Cart>(1,CommonValue.pageSize,null,null);
        getPage(page);
    }

    private void showSaleGoodsByDate() throws SQLException {

        System.out.println("请输入要查询的日期（例如：2018-08-17）：");
        String date=CommonValue.getInput().next();
        if(CommonMethod.checkDate(date)){
            Date date1=new Date();
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
            if(date.compareTo(simpleDateFormat.format(date1))<=0){
                Page<Cart> page=new Page<Cart>(1,CommonValue.pageSize,"date",date);
                getPage(page);
            }else{
                System.out.println("输入的日期大于当前日期，请重新输入！");
                showSaleGoodsByDate();
            }
        }else{
            System.out.println("输入的日期格式不正确，请重新输入！");
            showSaleGoodsByDate();
        }


    }

    private void showTodaySaleGoods() throws SQLException {
        Date date=new Date();
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        Page<Cart> page=new Page<Cart>(1,CommonValue.pageSize,"date",format);
        getPage(page);
    }

    private void getPage(Page<Cart> page) throws SQLException {

        goodsService=ServiceFactory.getGoodsServiceInstance();
        page = goodsService.getGoodsByPageByDate(page);
        if(page.getData().size()>0){
            for(Cart cart:page.getData()){
                System.out.println(cart.toString());
            }
        }else{
            System.out.println("没有查询到商品！");
        }
        pageChoice(page);
    }

    public void pageChoice(Page<Cart> page){
        if(page.getCurrentPage()==1){
            System.out.println("\n\n\n共【"+page.getTotalCount()+"】条数据\t\t共【"+page.getTotalPage()+"】页\t\t当前第【"+page.getCurrentPage()+"】页\t\tn.下一页\t\tl.页尾\t\tb.返回主菜单");
        }
        else if(page.getCurrentPage()==page.getTotalPage()){
            System.out.println("\n\n\n共【"+page.getTotalCount()+"】条数据\t\t共【"+page.getTotalPage()+"】页\t\t当前第【"+page.getCurrentPage()+"】页\t\tf.第一页\t\tp.上一页\t\tb.返回主菜单");
        }else{
            System.out.println("\n\n\n共【"+page.getTotalCount()+"】条数据\t\t共【"+page.getTotalPage()+"】页\t\t当前第【"+page.getCurrentPage()+"】页\t\tf.第一页\t\tp.上一页\t\tn.下一页\t\tl.页尾\t\tb.返回主菜单");
        }
        String choice=CommonValue.getInput().next();
        try {
            switch (choice){
                case "f":
                    page.setCurrentPage(1);
                    getPage(page);
                    break;
                case "p":
                    if(page.getCurrentPage()==1){
                        getPage(page);
                    }
                    else{
                        page.setCurrentPage(page.getCurrentPage()-1);
                        getPage(page);
                    }
                    break;
                case "n":
                    if(page.getCurrentPage()==page.getTotalCount()){
                        getPage(page);
                    }else{
                        page.setCurrentPage(page.getCurrentPage()+1);
                        getPage(page);
                    }
                    break;
                case "l":
                    page.setCurrentPage(page.getTotalPage());
                    getPage(page);
                    break;
                case "b":
                    showSaleGoodsList();
                    break;
                default:
                    System.out.println("没有该选项，请重新输入！");
                    pageChoice(page);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        }
    }
}
