package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** This class connects the app to the database.*/
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

    /** This method creates a connection to the database.
     * @return Returns database connection.*/
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

    /** This method closes the database connection to the app.*/
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection Terminated!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
