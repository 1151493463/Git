package menu.impl;

import entity.Goods;
import menu.Menu;
import org.dom4j.DocumentException;
import service.GoodsService;
import utils.*;

import java.io.IOException;
import java.sql.SQLException;

public class ShoopingContainMenu implements Menu {

    private GoodsService goodsService;

    @Override
    public void currentMenu() {
        System.out.println("\n\n\t\t商超购物管理系统    商品维护");
        System.out.println("**********************************************************");
        System.out.println("\t\t\t1. 商品添加\n" + "\t\t\t2. 更改商品\n" + "\t\t\t3. 删除商品\n" + "\t\t\t4. 显示所有商品\n" + "\t\t\t5. 查询商品\n\t\t\tb. 返回上级菜单\n\t\t\tq. 退出");
        System.out.println("**********************************************************");
        System.out.print("请选择，输入数字选择相应操作:");
        input();
    }
    public void input(){

        String choice = CommonValue.getInput().next();
        switch (choice){
            case "1":
                addGoodsMenu();
                break;
            case "2":
                updateGoods();
                break;
            case "3":
                deleteGoods();
                break;
            case "4":
                showAllGoods();
                break;
            case "5":
                SearchGoods();
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

    private void SearchGoods() {
        System.out.println("***查询商品***\n");
        System.out.println("\t\t1. 按商品价格升序查询\n\t\t2. 按商品数量升序查询\n\t\t3. 输入关键字查询商品\n\t\tb. 返回上级菜单");
        String choice=CommonValue.getInput().next();
        try{
            Page<Goods> page=null;
            switch (choice){
                case "1":
                    page=new Page<Goods>(1,CommonValue.pageSize,"price","asc");
                    getPage(page);
                    break;
                case "2":
                    page=new Page<Goods>(1,CommonValue.pageSize,"num","asc");
                    getPage(page);
                    break;
                case "3":
                    System.out.println("请输入要查询的商品关键字：");
                    String name=CommonValue.getInput().next();
                    page=new Page<Goods>(1,CommonValue.pageSize,"name",name);
                    getPage(page);
                    break;
                case "b":
                    currentMenu();
                    break;
                default:
                    System.out.println("没有该选项，请重新输入！");
                    SearchGoods();
            }
        }catch (SQLException e){
            System.out.println("数据库异常！");
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
            SearchGoods();
        }


    }

    private void showAllGoods(){

        Page<Goods> page=new Page<Goods>(1,CommonValue.pageSize,"name","");
        try {
            getPage(page);
        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        }

    }

    private void getPage(Page<Goods> page) throws SQLException {

        goodsService=ServiceFactory.getGoodsServiceInstance();
        page = goodsService.getGoodsByPage(page);
        if(page.getData().size()>0){
            for(Goods goods:page.getData()){
                System.out.println(goods.toString());
            }
            pageChoice(page);
        }else{
            System.out.println("没有查询到商品！");
            SearchGoods();
        }
    }

    public void pageChoice(Page<Goods> page){
        if(page.getCurrentPage()==1){
            System.out.println("\n\n\n共【"+page.getTotalCount()+"】条数据\t\t共【"+page.getTotalPage()+"】页\t\t当前第【"+page.getCurrentPage()+"】页\t\tn.下一页\t\tl.页尾\t\tb.返回上级菜单");
        }
        else if(page.getCurrentPage()==page.getTotalPage()){
            System.out.println("\n\n\n共【"+page.getTotalCount()+"】条数据\t\t共【"+page.getTotalPage()+"】页\t\t当前第【"+page.getCurrentPage()+"】页\t\tf.第一页\t\tp.上一页\t\tb.返回上级菜单");
        }else{
            System.out.println("\n\n\n共【"+page.getTotalCount()+"】条数据\t\t共【"+page.getTotalPage()+"】页\t\t当前第【"+page.getCurrentPage()+"】页\t\tf.第一页\t\tp.上一页\t\tn.下一页\t\tl.页尾\t\tb.返回上级菜单");
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
                    SearchGoods();
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

    private void deleteGoods() {

        System.out.println("请输入要删除商品名称：");
        String name=CommonValue.getInput().next();
        if(CommonMethod.isNull(name)) {
            goodsService = ServiceFactory.getGoodsServiceInstance();
            try {
                goodsService.deleteGoods(name);
            } catch (SQLException e) {
                e.printStackTrace();
                e.printStackTrace(CommonMethod.erorLog());
                System.out.println("删除失败！数据库异常");
                deleteGoods();
            }
            catch (Exception e1){
                e1.printStackTrace();
                e1.printStackTrace(CommonMethod.erorLog());
                System.out.println("添加失败,未知错误！");
                deleteGoods();
            }
        }else{
            System.out.println("商品名称不能为空！");
            deleteGoods();
        }

    }

    private void updateGoods() {
        try {
        Goods good = null;
        System.out.println("***更新商品***\n");
        System.out.println("请输入更改商品名称：");
        String name = CommonValue.getInput().next();
        if(CommonMethod.isNull(name)){
            goodsService=ServiceFactory.getGoodsServiceInstance();
                good = goodsService.findGoodsByKey("name",name);
                if(good!=null){
                    System.out.println(good.toString());
                    updateGoodsChoice(good);
                }else{
                    System.out.println("输入的商品不存在，请重新输入！");
                    updateGoods();
                }
            }
            else{
                System.out.println("输入不能为空！");
                updateGoods();
            }
            } catch (SQLException e) {
                System.out.println("更新失败！数据库异常");
                updateGoods();
                e.printStackTrace();
                e.printStackTrace(CommonMethod.erorLog());
            e.printStackTrace(CommonMethod.erorLog());
            }catch (Exception e1){
                e1.printStackTrace();
                e1.printStackTrace(CommonMethod.erorLog());
                updateGoods();
                System.out.println("添加失败,未知错误！");
            }
    }

    public void updateGoodsChoice(Goods good) throws SQLException, IOException, DocumentException {
        System.out.println("\n\t\t1. 更改商品名称\n\t\t2. 更改商品价格\n\t\t3. 更改商品数量\n\t\tb. 返回主菜单");
        String choice=CommonValue.getInput().next();
        switch (choice){
            case "1":
                updateGoodName(good);
                break;
            case "2":
               updateGoodPrice(good);
                break;
            case "3":
                updateGoodNum(good);
                break;
            case "b":
                currentMenu();
                break;
            default:
                System.out.println("没有该选项，请重新输入！");
                updateGoodsChoice(good);
        }
    }

    public void updateGoodName(Goods good) throws SQLException, IOException, DocumentException {
        System.out.println(good.toString());
        System.out.println("请输入新的商品名称：");
        String name=CommonValue.getInput().next();
        if(CommonMethod.isNull(name)){
            good.setName(name);
            goodsService.updateGoods(good);
            System.out.println("更新成功！");
            updateGoodsChoice(good);
        }
        else{
            System.out.println("商品名称不能为空，请重新输入！");
            updateGoodName(good);
        }
    }
    public void updateGoodPrice(Goods good) throws SQLException, IOException, DocumentException {
        System.out.println(good.toString());
        System.out.println("请输入新的商品价格：");
        String price=CommonValue.getInput().next();
        if(CommonMethod.isNull(price) && Double.parseDouble(price)>=0) {
            good.setPrice(Double.parseDouble(price));
            goodsService.updateGoods(good);
            System.out.println("更新成功！");
            updateGoodsChoice(good);
        }else{
            System.out.println("商品价格不能为空或小于零，请重新输入！");
            updateGoodName(good);
        }
    }
    public void updateGoodNum(Goods good) throws SQLException, IOException, DocumentException {
        System.out.println(good.toString());
        System.out.println("请输入新的商品数量：");
        String num=CommonValue.getInput().next();
        if(CommonMethod.isNull(num) && Integer.parseInt(num)>=0) {
            good.setNum(Integer.parseInt(num));
            goodsService.updateGoods(good);
            System.out.println("更新成功！");
            updateGoodsChoice(good);
        }else{
            System.out.println("商品数量不能为空或小于零，请重新输入！");
            updateGoodName(good);
        }
    }

    private void addGoodsMenu() {

        Goods goods=new Goods();
        System.out.println("***添加商品***");
        System.out.println("商品名称：");
        String name=CommonValue.getInput().next();
        try {
        if(CommonMethod.isNull(name)){
            goods.setName(name);
            System.out.println("商品价格：");
            double price = CommonValue.getInput().nextDouble();
            if(price>=0){
                goods.setPrice(price);
                System.out.println("商品数量：");
                int num=CommonValue.getInput().nextInt();
                if(num>=0){
                    goods.setNum(num);
                    goodsService=ServiceFactory.getGoodsServiceInstance();
                        goodsService.addGoods(goods);
                        System.out.println("添加成功！");
                        System.out.println("\n\t\tc. 继续添加\n\t\tb. 返回主菜单");
                        String choice=CommonValue.getInput().next();
                        if("c".equals(choice)){
                            addGoodsMenu();
                        }else {
                            MenuFacadeFactory.menuFacade(MenuEnum.ShoopingContainMenu);
                        }
                }
                else{
                    System.out.println("商品价格不能小于零！");
                    addGoodsMenu();
                }            }else{
                System.out.println("商品价格不能小于零！");
                addGoodsMenu();
            }
        }
        else{
            System.out.println("商品名称不能为空！");
            addGoodsMenu();
        }
        }
        catch (SQLException e1){
            System.out.println("添加失败,数据库异常！");
            addGoodsMenu();
        }
        catch (Exception e2) {
            e2.printStackTrace();
            System.out.println("添加失败,未知错误！");
            addGoodsMenu();
        }
    }



}
