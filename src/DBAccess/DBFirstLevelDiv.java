package DBAccess;

import Model.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This is the First Level Division DB class to access database.
 * This class obtains a list of first level division from the first_level_divisions
 * database table.*/
public class DBFirstLevelDiv {

    /** @return Returns observable list of all first level division from the database.*/
    public static ObservableList<FirstLevelDivision> getAllFirstLevelDiv(){
        ObservableList<FirstLevelDivision> divList = FXCollections.observableArrayList();

        try{

            String sql = "SELECT * FROM first_level_divisions";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                int firstLevelDivId = resultSet.getInt("Division_ID");
                String firstLevelDivName = resultSet.getString("Division");
                FirstLevelDivision firstLevelDivision = new FirstLevelDivision(firstLevelDivId, firstLevelDivName);
                divList.add(firstLevelDivision);

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return divList;
    }

}
