package service.impl;

import Expection.executeUpdateExpection;
import dao.ManagerDao;
import entity.Manager;
import org.dom4j.DocumentException;
import service.ManagerService;
import utils.DaoFactory;

import java.io.IOException;
import java.sql.SQLException;

public class ManagerServiceImpl implements ManagerService {

    private ManagerDao managerDao;

    @Override
    public void enRoll(String name, String passWord) throws SQLException, executeUpdateExpection, IOException, DocumentException {

        managerDao=DaoFactory.getManagerDaoInstance();
        managerDao.enRoll(name,passWord);

    }

    @Override
    public void login(String name, String password) throws Exception {

        Manager manager = new Manager();
        manager.setName(name);
        manager.setPassWord(password);
        managerDao=DaoFactory.getManagerDaoInstance();
        managerDao.login(manager);


    }

    @Override
    public void deleteUser(String name) throws executeUpdateExpection, SQLException, IOException, DocumentException {

        managerDao=DaoFactory.getManagerDaoInstance();
        managerDao.deleteUser(name);


    }

    @Override
    public Manager findUserByName(String name) throws SQLException, executeUpdateExpection {

        managerDao=DaoFactory.getManagerDaoInstance();

        return managerDao.findUserByName(name);
    }


    @Override
    public void updateUser(Manager manager) throws SQLException, executeUpdateExpection, IOException, DocumentException {

        managerDao=DaoFactory.getManagerDaoInstance();
        managerDao.updateUser(manager);
    }

    @Override
    public boolean isExistUser(String name) throws SQLException {

        managerDao=DaoFactory.getManagerDaoInstance();

        return managerDao.isExistUser(name);
    }
}
