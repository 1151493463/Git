package menu.impl;

import entity.Cart;
import entity.Goods;
import menu.Menu;
import org.dom4j.DocumentException;
import service.GoodsService;
import utils.*;

import java.io.IOException;
import java.sql.SQLException;

public class CalMoneyMenu implements Menu {

    private GoodsService goodsService;
    private Cart cart;

    @Override
    public void currentMenu() {
        System.out.println("\n\n\t\t商超购物管理系统    收银");
        System.out.println("**********************************************************");
        System.out.println("\t\t\t1. 购物结算\n\t\t\tb. 返回上级菜单\n\t\t\tq. 退出");
        System.out.println("**********************************************************");
        System.out.print("请选择，输入数字选择相应操作:");
        input();
    }

    public void input() {
        String choice = CommonValue.getInput().next();
        switch (choice) {
            case "1":
                shoppingMenu();
                break;
            case "b":
                MenuFacadeFactory.menuFacade(MenuEnum.ChoiceMenu);
                break;
            case "q":
                CommonMethod.exit();
                break;
            default:
                System.out.println("没有该序号，请重新输入！");
                input();
        }
    }

    private void shoppingMenu() {
        try {
            cart = new Cart();
            System.out.println("***购物结算***");
            System.out.println("1. 添加商品\t\t\t2.查询商品\t\t\tb.返回上一级菜单");
            String choice = CommonValue.getInput().next();
            switch (choice){
                case "1":
                    choiceGoods(cart);
                    break;
                case "2":
                    queryGoods(cart);
                    break;
                case "b":
                    currentMenu();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
            System.out.println("添加失败！数据库异常");
            shoppingMenu();
        } catch (NullPointerException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
            System.out.println("添加失败,未知错误！");
            shoppingMenu();
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
            System.out.println("添加失败,未知错误！");
            shoppingMenu();
        }
    }

    private void queryGoods(Cart cart) throws SQLException, IOException, DocumentException {

        System.out.println("请输入商品关键：");
        String key = CommonValue.getInput().next();
        Page<Goods> page = new Page<Goods>(1, CommonValue.pageSize, "name", key);
        getPage(page, cart);
    }

    private void getPage(Page<Goods> page, Cart cart) throws SQLException, IOException, DocumentException {

            goodsService = ServiceFactory.getGoodsServiceInstance();
            page = goodsService.getGoodsByPage(page);
            if(page.getData().size()>0){
                for (Goods goods : page.getData()) {
                    System.out.println(goods.toString());
                }
            } else {
                System.out.println("没有查询到商品！");
                queryGoods(cart);
            }
            pageChoice(page, cart);

    }

    public void pageChoice(Page<Goods> page, Cart cart) throws SQLException, IOException, DocumentException {
           if (page.getCurrentPage() == 1) {
               System.out.println("\n\n\n共【" + page.getCurrentPage() + "】条数据\t\t共【" + page.getTotalPage() + "】页\t\t当前第【" + page.getCurrentPage() + "】页\t\tn.下一页\t\tl.页尾\t\tc.选择商品\t\tb.返回上级菜单(取消购物)");
           } else if (page.getCurrentPage() == page.getTotalPage()) {
               System.out.println("\n\n\n共【" + page.getCurrentPage() + "】条数据\t\t共【" + page.getTotalPage() + "】页\t\t当前第【" + page.getCurrentPage() + "】页\t\tf.第一页\t\tp.上一页\t\tc.选择商品\t\tb.返回上级菜单(取消购物)");
           } else {
               System.out.println("\n\n\n共【" + page.getCurrentPage() + "】条数据\t\t共【" + page.getTotalPage() + "】页\t\t当前第【" + page.getCurrentPage() + "】页\t\tf.第一页\t\tp.上一页\t\tn.下一页\t\tl.页尾\t\tc.选择商品\t\tb.返回上级菜单(取消购物)");
           }
        String choice = CommonValue.getInput().next();
        switch (choice) {
            case "f":
                page.setCurrentPage(1);
                getPage(page, cart);
                break;
            case "p":
                if (page.getCurrentPage() == 1) {
                    getPage(page, cart);
                } else {
                    page.setCurrentPage(page.getCurrentPage() - 1);
                    getPage(page, cart);
                }
                break;
            case "n":
                if (page.getCurrentPage() == page.getTotalCount()) {
                    getPage(page, cart);
                } else {
                    page.setCurrentPage(page.getCurrentPage() + 1);
                    getPage(page, cart);
                }
                break;
            case "l":
                page.setCurrentPage(page.getTotalPage());
                getPage(page, cart);
                break;
            case "c":
                choiceGoods(cart);
                break;
            case "b":
                cancelDeal(cart);
                break;
            default:
                System.out.println("没有该选项，请重新输入！");
                pageChoice(page,cart);
        }

    }

    private void choiceGoods(Cart cart) throws SQLException, IOException, DocumentException {

        goodsService = ServiceFactory.getGoodsServiceInstance();
        System.out.println("请输入要购买的商品ID");
        String id = CommonValue.getInput().next();
        Goods goods = goodsService.findGoodsByKey("id", id);
        if (goods != null) {
            System.out.println("请输入要购买数量：");
            int num = CommonValue.getInput().nextInt();
            goods.setNum(num);
            cart.getList().add(goods);
            cart = goodsService.calMoney(cart);
            System.out.println("已购商品数量：" + cart.getTotalCount() + "件\t\t\t总金额：" + cart.getTotalPrice());
            System.out.println("1. 继续添加\t\t\t2.查询商品\t\t\t3. 购物结算\t\t\tb.返回主菜单（取消本次交易）");
            String choice = CommonValue.getInput().next();
            switch (choice) {
                case "1":
                    choiceGoods(cart);
                    break;
                case "2":
                    queryGoods(cart);
                    break;
                case "3":
                    calCart(cart);
                    break;
                case "b":
                    cancelDeal(cart);
                    break;
                default:
                    System.out.println("没有该选项，请重新输入！");
                    choiceGoods(cart);
            }
        } else {
            System.out.println("输入的商品ID不正确，请重新输入！");
            choiceGoods(cart);
        }
    }

    private void calCart(Cart cart) throws SQLException, IOException, DocumentException {
        goodsService = ServiceFactory.getGoodsServiceInstance();
        cart = goodsService.calMoney(cart);
        System.out.println("已购商品数量：" + cart.getTotalCount() + "件\t\t\t应付金额：" + cart.getTotalPrice());
        System.out.println("支付金额：");
        double getMoney = CommonValue.getInput().nextDouble();
        if (getMoney >= cart.getTotalPrice()) {
            goodsService.calCart(cart);
            System.out.println("找零：" + (getMoney - cart.getTotalPrice()));
            System.out.println("交易成功");
        } else {
            System.out.println("交费金额不足以支付商品金额，交易失败");
        }
        shoppingMenu();
    }

    public void cancelDeal(Cart cart) throws SQLException, IOException, DocumentException {
        {
            System.out.println("是否要取消本次交已？(Y/N)");
            String confirm = CommonValue.getInput().next();
            if ("n".equalsIgnoreCase(confirm)) {
                System.out.println("1. 继续添加\t\t\t2.查询商品\t\t\t3. 购物结算");
                String choice1 = CommonValue.getInput().next();
                switch (choice1) {
                    case "1":
                        choiceGoods(cart);
                        break;
                    case "2":
                        queryGoods(cart);
                        break;
                    case "3":
                        calCart(cart);
                        break;
                    default:
                        System.out.println("没有该选项，请重新输入！");
                        cancelDeal(cart);

                }
            } else {
                MenuFacadeFactory.menuFacade(MenuEnum.CalMoneyMenu);
            }
        }
    }
}
