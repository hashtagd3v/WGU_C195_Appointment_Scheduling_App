package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    //JDBC URL parts
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String serverName = "//wgudb.ucertify.com/WJ05nOb";

    //JDBC URL
    private static final String jdbcURL = protocol + vendorName + serverName;

    //Driver and Connection Interface Reference
    private static final String MYSQLJDBCDriver = "com.mysql.cj.jdbc.Driver";
    public static Connection connection;

    private static final String userName = "U05nOb";
    private static final String password = "53688555917";

    public static Connection getConnection() {
        try {
            Class.forName(MYSQLJDBCDriver);
            connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection Successful!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection Terminated!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
