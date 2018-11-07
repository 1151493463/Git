package service.impl;

import dao.GoodsDao;
import entity.Cart;
import entity.Goods;
import org.dom4j.DocumentException;
import service.GoodsService;
import utils.DaoFactory;
import utils.Page;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GoodsServiceImpl implements GoodsService {

    private GoodsDao goodsDao;

    @Override
    public boolean addGoods(Goods goods) throws Exception {

        goodsDao=DaoFactory.getGoodsDaoInstance();
        goodsDao.addGoods(goods);

        return false;
    }

    @Override
    public List<Goods> showAllGoods(int currentPage) throws SQLException {
        return null;
    }


    @Override
    public Goods findGoodsByKey( String name,String value) throws SQLException {

        return DaoFactory.getGoodsDaoInstance().findGoodsByKey(name,value);
    }

    @Override
    public void deleteGoods(String name) throws SQLException, IOException, DocumentException {
        goodsDao= DaoFactory.getGoodsDaoInstance();
        goodsDao.deleteGoods(name);
    }

    @Override
    public void updateGoods(Goods good) throws SQLException, IOException, DocumentException {
        DaoFactory.getGoodsDaoInstance().updateGoods(good);
    }


    @Override
    public Cart calMoney(Cart cart) {

        int count=0;
        double money=0;
        for (Goods goods:cart.getList()){
            count+=goods.getNum();
            money+=(goods.getPrice()*goods.getNum());
        }
        cart.setTotalCount(count);
        cart.setTotalPrice(money);

        return cart;
    }

    @Override
    public Page getGoodsByPage(Page page) throws SQLException {

        goodsDao=DaoFactory.getGoodsDaoInstance();
        page.setTotalCount(goodsDao.getTotalCount(page.getKey(),page.getValue()));
        page.setStart((page.getCurrentPage()-1)*page.getPageSize());

        if("asc".equals(page.getValue())){
            page.setData(goodsDao.getGoodsByPage(page));
        }else{
            page.setData(goodsDao.getGoodsByPage(page));
        }

        return page;
    }

    @Override
    public Page getGoodsByPageByDate(Page page) throws SQLException {

        goodsDao=DaoFactory.getGoodsDaoInstance();
        page.setTotalCount(goodsDao.getTotalCountByDate(page.getKey(),page.getValue()));
        page.setStart((page.getCurrentPage()-1)*page.getPageSize());
        page.setData(goodsDao.getGoodsByPageAndDate(page));

        return page;
    }

    @Override
    public List<Goods> getAllGoods() throws SQLException {
        return DaoFactory.getGoodsDaoInstance().getAllGoods();
    }

    @Override
    public List<Cart> getAllCart() throws SQLException {
        return DaoFactory.getGoodsDaoInstance().getAllCart();
    }

    @Override
    public Cart calCart(Cart cart) throws SQLException, IOException, DocumentException {

        Date date=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        cart.setDate(format);

        cart = calMoney(cart);

        goodsDao=DaoFactory.getGoodsDaoInstance();
        goodsDao.calCart(cart);

        return cart;
    }
}
