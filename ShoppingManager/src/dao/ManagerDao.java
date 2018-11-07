package dao;

import Expection.executeUpdateExpection;
import entity.Manager;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.sql.SQLException;

public interface ManagerDao {

    void login(Manager manager) throws SQLException, Exception;

    Manager findUserByName(String name) throws SQLException, executeUpdateExpection;

    void  enRoll(String name, String passWord) throws SQLException, executeUpdateExpection, DocumentException, IOException;

    void deleteUser(String name) throws SQLException, executeUpdateExpection, DocumentException, IOException;

    void updateUser(Manager manager) throws SQLException, executeUpdateExpection, DocumentException, IOException;

    boolean isExistUser(String name) throws SQLException;
}
