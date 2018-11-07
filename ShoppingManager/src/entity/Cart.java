package entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private Integer id;
    private List<Goods> list;
    private int totalCount;
    private double totalPrice;
    private String date;
    private String name;
    private double payMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Cart() {
         this.list=new ArrayList<Goods>();
    }

    public List<Goods> getList() {
        return list;
    }

    public void setList(List<Goods> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "商品ID："+id+"    商品名称："+name+"   购买数量："+totalCount+"  总价："+totalPrice+"   购买日期："+date+"   支付金额："+payMoney+"\n";
    }
}
