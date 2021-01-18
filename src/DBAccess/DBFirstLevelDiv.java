package DBAccess;

import Model.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBFirstLevelDiv {

    public static ObservableList<FirstLevelDivision> getAllFirstLevelDiv(){
        ObservableList<FirstLevelDivision> divList = FXCollections.observableArrayList();

        try{

            String sql = "SELECT * FROM first_level_divisions";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                String firstLevelDivName = resultSet.getString("Division");

                FirstLevelDivision firstLevelDivision = new FirstLevelDivision(firstLevelDivName);

                divList.add(firstLevelDivision);

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return divList;
    }

}
