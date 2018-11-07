package entity;

public class Goods{

    private Integer id;
    private String name;
    private double price;
    private int num;
    private String remark;
    private int saleNum;

    public Goods() {
    }

    public Goods(String name, double price, int num) {
        this.name = name;
        this.price = price;
        this.num = num;
    }

    public Goods(String name, double price, int num, String remark, int saleNum) {
        this.name = name;
        this.price = price;
        this.num = num;
        this.remark = remark;
        this.saleNum = saleNum;
    }

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    @Override
    public String toString() {
        return "商品ID："+id+"    商品名称："+name+"   商品价格："+price+"  剩余数量："+num+"   备注："+remark+"   销量："+saleNum+"\n";
    }
}
