package dao.impl;

import dao.GoodsDao;
import dao.SqliteDB;
import entity.Cart;
import entity.Goods;
import utils.Page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao {

    private Statement statement;

    @Override
    public void addGoods(Goods goods) throws Exception {
        statement=SqliteDB.getStatement();

        try{
            if(isExist(goods.getName())){
                String sql="update goods set num=num+"+goods.getNum()+" where name='"+goods.getName()+"'";
                statement.executeUpdate(sql);

            }
            else{
                String sql="insert into goods(name,price,num) values" +
                        "('"+goods.getName()+"',"+goods.getPrice()+","+goods.getNum()+")";
                statement.executeUpdate(sql);
            }
        }finally {
            statement.clearBatch();
        }

    }


    @Override
    public Goods findGoodsByKey(String name,String value) throws SQLException {
        statement=SqliteDB.getStatement();
        Goods goods=null;
        String sql="select * from goods where "+name+"='"+value+"'";
        ResultSet rs = null;

        try{
            rs = statement.executeQuery(sql);
            if(rs.next()) {
                goods=new Goods();
                goods.setId(rs.getInt("id"));
                goods.setName(rs.getString("name"));
                goods.setPrice(rs.getDouble("price"));
                goods.setNum(rs.getInt("num"));
                goods.setRemark(rs.getString("remark"));
                goods.setSaleNum(rs.getInt("saleNum"));
            }
        }
        finally {
            statement.clearBatch();
            rs.close();
        }

        return goods;
    }

    @Override
    public List<Goods> getAllGoods() throws SQLException {
        statement = SqliteDB.getStatement();
        ResultSet rs = null;
        List<Goods> goods = new ArrayList<Goods>();

        try {
            rs = statement.executeQuery("select *from goods");
            while (rs.next()) {
                Goods goods1 = new Goods();
                goods1.setId(rs.getInt("id"));
                goods1.setName(rs.getString("name"));
                goods1.setNum(rs.getInt("num"));
                goods1.setPrice(rs.getDouble("price"));
                goods1.setRemark(rs.getString("remark"));
                goods1.setSaleNum(rs.getInt("saleNum"));
                goods.add(goods1);
            }

        } finally {
            statement.clearBatch();
            rs.close();
            return goods;
        }
    }

    @Override
    public List<Cart> getAllCart() throws SQLException {
        statement=SqliteDB.getStatement();
        ResultSet rs=null;
        List<Cart> carts=new ArrayList<Cart>();
        rs = statement.executeQuery("select *from cart");

        try {
            while (rs.next()){
                Cart cart=new Cart();
                cart.setId(rs.getInt("id"));
                cart.setName(rs.getString("name"));
                cart.setTotalCount(rs.getInt("num"));
                cart.setTotalPrice(rs.getDouble("totalMoney"));
                cart.setDate(rs.getString("date"));
                cart.setPayMoney(rs.getDouble("payMoney"));
                carts.add(cart);
            }
        }finally {
            statement.clearBatch();
            rs.close();
            return carts;
        }
    }

    @Override
    public void deleteGoods(String name) throws SQLException {
        statement=SqliteDB.getStatement();

        try {
            String sql="delete from goods where name='"+name+"'";
            statement.executeUpdate(sql);
        }finally {
            statement.clearBatch();
        }
    }
    @Override
    public void updateGoods(Goods good) throws SQLException {
        statement=SqliteDB.getStatement();

        try {
            String sql="update goods set name='"+good.getName()+"',price='"+good.getPrice()+"',num='"+good.getNum()+"' where id='"+good.getId()+"'" ;
            statement.executeUpdate(sql);
        }
        finally {
            statement.clearBatch();
        }

    }

    @Override
    public boolean isExist(String name) throws Exception {
        boolean flag = false;

        statement=SqliteDB.getStatement();
        try{
            String sql="select *from goods where name='"+name+"'";
            flag=statement.executeQuery(sql).next();
        }finally {
            statement.clearBatch();
        }

        return flag;
    }

    @Override
    public int getTotalCount(String name, String key) throws SQLException {
        statement=SqliteDB.getStatement();
        String sql=null;
        ResultSet rs=null;
        int count=0;

        try{
            if("asc".equalsIgnoreCase(key)){
                sql="select count(*) as count from goods";
            }else{
                sql="select count(*) as count from goods where "+name+" like '%"+key+"%'";
            }
            rs = statement.executeQuery(sql);
            count=rs.getInt("count");
        }finally {
            statement.clearBatch();
            rs.close();
        }

        return count;
    }

    @Override
    public List<Goods> getGoodsByPage(Page page) throws SQLException {
        statement=SqliteDB.getStatement();
        List<Goods> goods = new ArrayList<Goods>();
        ResultSet rs = null;
        String sql=null;

        try{
            if("asc".equalsIgnoreCase(page.getValue())){
                sql="select *from goods order by "+page.getKey()+" "+page.getValue()+ " limit " + page.getPageSize() + " offset " + page.getStart();
            }else {
                sql = " select *from goods where " + page.getKey() + " like '%" + page.getValue() + "%' limit " + page.getPageSize() + " offset " + page.getStart();
            }
            rs = statement.executeQuery(sql);
            while (rs.next()){
                Goods good=new Goods();
                good.setId(rs.getInt("id"));
                good.setName(rs.getString("name"));
                good.setPrice(rs.getDouble("price"));
                good.setNum(rs.getInt("num"));
                good.setRemark(rs.getString("remark"));
                good.setSaleNum(rs.getInt("saleNum"));
                goods.add(good);
            }
        }finally {
            statement.clearBatch();
            rs.close();
        }

        return goods;
    }

    @Override
    public void calCart(Cart cart) throws SQLException {
        statement=SqliteDB.getStatement();

        try{
            for(Goods goods:cart.getList()){
                String sql="update goods set num=num-"+goods.getNum()+",saleNum=saleNum+"+goods.getNum()+" where id='"+goods.getId()+"'";
                statement.executeUpdate(sql);
                statement.clearBatch();
            }
            /*SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(cart.getDate());*/
            double payMoney = cart.getTotalPrice();
            for(Goods goods:cart.getList()){
                String sql="insert into cart(name,num,totalMoney,date,payMoney) VALUES ('"+goods.getName()+"',"+goods.getNum()+","+(goods.getPrice()*goods.getNum())+",'"+cart.getDate()+"',"+payMoney+")";
                statement.executeUpdate(sql);
                statement.clearBatch();
            }
        }finally {
            statement.clearBatch();
            cart=null;
        }


    }

    @Override
    public int getTotalCountByDate(String name, String key) throws SQLException {

        statement=SqliteDB.getStatement();
        int count=0;
        ResultSet rs=null;
        String sql=null;

        try{
            if("".equalsIgnoreCase(name) || name==null){
                sql="SELECT count(*) as count FROM cart";
            }else{
                sql="SELECT count(*) as count FROM cart WHERE "+name+" BETWEEN '"+key+" 00:00:00' and '"+key+" 24:00:00'";
            }
            rs = statement.executeQuery(sql);
            count=rs.getInt("count");
        }finally {
            statement.clearBatch();
            rs.close();
        }

        return count;

    }

    @Override
    public List<Cart> getGoodsByPageAndDate(Page page) throws SQLException {

        statement=SqliteDB.getStatement();
        List<Cart> carts = new ArrayList<Cart>();
        String sql=null;
        try{
            //String sql="SELECT *FROM cart WHERE "+page.getKey()+" BETWEEN '"+page.getValue()+" 00:00:00' and '"+page.getValue()+" 24:00:00' limit "+page.getPageSize()+" offset "+page.getStart();
            if("".equalsIgnoreCase(page.getKey()) || page.getKey()==null){
                sql="SELECT *FROM cart limit "+page.getPageSize()+" offset "+page.getStart();
            }else{
                sql="SELECT *FROM cart WHERE "+page.getKey()+" BETWEEN '"+page.getValue()+" 00:00:00' and '"+page.getValue()+" 24:00:00' limit "+page.getPageSize()+" offset "+page.getStart();
            }
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
               Cart cart = new Cart();
               cart.setId(rs.getInt("id"));
               cart.setName(rs.getString("name"));
               cart.setTotalCount(rs.getInt("num"));

               //SimpleDateFormat f = new SimpleDateFormat(rs.getString("date"));
               cart.setTotalPrice(rs.getDouble("totalMoney"));
               cart.setDate(rs.getString("date"));
               cart.setPayMoney(rs.getDouble("payMoney"));
               carts.add(cart);
            }
        }finally {
            statement.clearBatch();
        }

        return carts;

    }


}
