package service;

import entity.Cart;
import entity.Goods;
import org.dom4j.DocumentException;
import utils.Page;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface GoodsService {
    //添加商品
    public boolean addGoods(Goods goods) throws Exception;

    //显示所有商品
    public List<Goods> showAllGoods(int currentPage) throws SQLException;

    //跟据关键字查找商品
    public Goods findGoodsByKey(String name,String value) throws SQLException;

    //删除商品
    public void deleteGoods(String name) throws SQLException, IOException, DocumentException;

    //更新商品
    public void updateGoods(Goods good) throws SQLException, IOException, DocumentException;

    //计算商品金额
    Cart calMoney(Cart cart);
    //分页查询
    public Page getGoodsByPage(Page page) throws SQLException;
    //结算
    Cart calCart(Cart cart) throws SQLException, IOException, DocumentException;

    public Page getGoodsByPageByDate(Page page) throws SQLException;

    List<Goods> getAllGoods() throws SQLException;

    List<Cart> getAllCart() throws SQLException;
}