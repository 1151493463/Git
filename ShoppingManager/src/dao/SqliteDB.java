package dao;

import utils.CommonMethod;
import utils.CommonValue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteDB {

    private static final String Class_Name = "org.sqlite.JDBC";
    private static final String DB_URL = CommonValue.getProperties().getProperty("DBPath");
    private static Connection con;
    private static Statement statement;

    static {
        try {
            Class.forName(Class_Name);
            con = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            e.printStackTrace(CommonMethod.erorLog());
        } catch (SQLException e) {
            e.printStackTrace(CommonMethod.erorLog());
            e.printStackTrace();
        }
    }


    private SqliteDB() {
    }

    private static Connection getConnection(){

        return con;
    }

    public static Statement getStatement() throws SQLException {

        return getConnection().createStatement();
    }
}
