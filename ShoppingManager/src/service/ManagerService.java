package service;

import Expection.executeUpdateExpection;
import entity.Manager;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.sql.SQLException;

public interface ManagerService {
    //账户注册
    public void enRoll(String name, String passWord) throws SQLException, executeUpdateExpection, IOException, DocumentException;
    //登录
    public void login(String name,String password) throws Exception;
    //删除账户
    public void deleteUser(String name) throws executeUpdateExpection, SQLException, IOException, DocumentException;
    //根据名称查找账户
    public Manager findUserByName(String name) throws SQLException, executeUpdateExpection;

    void updateUser(Manager manager) throws SQLException, executeUpdateExpection, IOException, DocumentException;

    boolean isExistUser(String name) throws SQLException;
}
