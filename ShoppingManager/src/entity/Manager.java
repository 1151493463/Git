package entity;

public class Manager {

    private Integer id;
    private String name;
    private String passWord;

    public Manager() {
    }

    public Manager(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
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

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "ID："+id+"  用户名："+name+"  密码："+passWord;
    }
}
