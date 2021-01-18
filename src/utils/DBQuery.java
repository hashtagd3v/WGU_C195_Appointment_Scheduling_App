package utils;

import java.sql.*;

/** This class creates a Prepared Statement object.*/
public class DBQuery {

    private static PreparedStatement statement;

    /** This method creates a statement object.*/
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
        statement = conn.prepareStatement(sqlStatement);
    }

    /** @return Returns a statement object.*/
    public static PreparedStatement getPreparedStatement() {
        return statement;
    }

}
