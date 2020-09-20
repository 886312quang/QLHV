package qlhv.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class DBConnect {

    public static Connection getConnection() {
        try {
            Connection cons = null;
            Class.forName("com.mysql.jdbc.Driver");
            cons = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_qlhv", "root", "mysql886312");
            return cons;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        Connection c = getConnection();
        System.out.println(c.toString());
        c.close();
    }
}
