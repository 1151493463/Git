package dao.impl;

import Expection.executeUpdateExpection;
import dao.ManagerDao;
import dao.SqliteDB;
import entity.Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManagerDaoImpl implements ManagerDao {

    private Statement statement;

    @Override
    public void login(Manager manager) throws Exception {

        statement = SqliteDB.getStatement();
        ResultSet rs=null;
        try {
            String sql = "select *from manager where name='" + manager.getName() +
                    "' and password='" + manager.getPassWord() + "'";
            rs = statement.executeQuery(sql);
            if (!rs.next()) {
                throw new executeUpdateExpection("执行insert,delete,update操作失败！");
            }
        } finally {
            rs.close();
            statement.clearBatch();
        }
    }

    @Override
    public Manager findUserByName(String name) throws SQLException {
        statement = SqliteDB.getStatement();

        String sql = "select *from manager where name='" + name + "'";
        ResultSet rs = statement.executeQuery(sql);
        Manager manager = null;
      if(rs.next()){
           manager=new Manager();
           manager.setId(rs.getInt("id"));
           manager.setName(rs.getString("name"));
           manager.setPassWord(rs.getString("password"));
       }

       statement.clearBatch();
       rs.close();

        return manager;
    }

    @Override
    public void enRoll(String name, String passWord) throws SQLException, executeUpdateExpection {
        statement = SqliteDB.getStatement();

        try {
            String sql = "insert into manager(name,password) values('" + name + "','" + passWord + "')";
            if(statement.executeUpdate(sql)<1){
                throw new executeUpdateExpection("执行insert,delete,update操作失败！");
            }
        } finally {
            statement.clearBatch();
        }
    }

    @Override
    public void deleteUser(String name) throws SQLException, executeUpdateExpection {
        statement = SqliteDB.getStatement();

        try {
            String sql = "DELETE from manager where name='" + name + "'";
            if (statement.executeUpdate(sql) < 1) {
                throw new executeUpdateExpection("执行insert,delete,update操作失败！");
            }
        } finally {
            statement.clearBatch();
        }
    }

    @Override
    public void updateUser(Manager manager) throws SQLException, executeUpdateExpection {
        statement=SqliteDB.getStatement();

        try{
            String sql="update manager set name='"+manager.getName()+"',password='"+
                    manager.getPassWord()+"' where id='"+manager.getId()+"'";
            if(statement.executeUpdate(sql)<1){
                throw new executeUpdateExpection("执行insert,delete,update操作失败！");
            }
        }finally {
            statement.clearBatch();
        }
    }

    @Override
    public boolean isExistUser(String name) throws SQLException {
        boolean flag=false;

        statement = SqliteDB.getStatement();
        ResultSet rs=null;

       try {
           String sql = "select *from manager where name='" + name + "'";
           rs = statement.executeQuery(sql);
           flag=rs.next();
       }finally {
           statement.clearBatch();
           rs.close();
       }

        return flag;
    }
}
