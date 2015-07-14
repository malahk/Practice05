package ua.org.oracle.academy.malahk.connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Admin on 08.07.2015.
 */
public class Connector {

    private static String URL = "jdbc:mysql://localhost:3306/test01\n";
    private static String USER = "root";
    private static String PASSWORD = "toor";
    private static Connection conn;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connector() {

    }

    public static Connection getConn() {
        return conn;
    }

    public static void closeConn() {

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
