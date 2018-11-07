package dao;

import entity.Cart;
import entity.Goods;
import org.dom4j.DocumentException;
import utils.Page;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface GoodsDao {

    //添加商品
    public void addGoods(Goods goods) throws Exception;
    //删除商品
    public void deleteGoods(String name) throws SQLException, DocumentException, IOException;
    //更新商品名称
    public void updateGoods(Goods good) throws SQLException, DocumentException, IOException;
    //商品是否存在
    public boolean isExist(String name) throws SQLException, Exception;
    //获得总记录数
    public int getTotalCount(String name, String key) throws SQLException;
    //分页获得商品
    public List<Goods> getGoodsByPage(Page page) throws SQLException;

    void calCart(Cart cart) throws SQLException, DocumentException, IOException;

    public int getTotalCountByDate(String name, String key) throws SQLException;

    public List<Cart> getGoodsByPageAndDate(Page page) throws SQLException;

    Goods findGoodsByKey(String name,String value) throws SQLException;

    List<Goods> getAllGoods() throws SQLException;

    List<Cart> getAllCart() throws SQLException;
}
