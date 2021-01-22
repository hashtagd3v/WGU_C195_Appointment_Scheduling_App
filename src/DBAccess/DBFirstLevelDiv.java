package DBAccess;

import Model.Country;
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

    /** @return Returns observable list of all first level division within US only.*/
    public static ObservableList<FirstLevelDivision> getUSFirstLevelDivisions(){
        ObservableList<FirstLevelDivision> firstLevelDivisionsUSOnly = FXCollections.observableArrayList();

        try{

            String sql = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = 1";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int firstLevelDivId = resultSet.getInt("Division_ID");
                String firstLevelDivName = resultSet.getString("Division");
                FirstLevelDivision firstLevelDivision = new FirstLevelDivision(firstLevelDivId, firstLevelDivName);
                firstLevelDivisionsUSOnly.add(firstLevelDivision);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return firstLevelDivisionsUSOnly;

    }

    /** @return Returns observable list of all first level division within UK only.*/
    public static ObservableList<FirstLevelDivision> getUnitedKingdomFirstLevelDivisions(){
        ObservableList<FirstLevelDivision> firstLevelDivisionsUKOnly = FXCollections.observableArrayList();

        try{

            String sql = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = 2";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int firstLevelDivId = resultSet.getInt("Division_ID");
                String firstLevelDivName = resultSet.getString("Division");
                FirstLevelDivision firstLevelDivision = new FirstLevelDivision(firstLevelDivId, firstLevelDivName);
                firstLevelDivisionsUKOnly.add(firstLevelDivision);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return firstLevelDivisionsUKOnly;

    }

    /** @return Returns observable list of all first level division within Canada only.*/
    public static ObservableList<FirstLevelDivision> getCanadaFirstLevelDivisions(){
        ObservableList<FirstLevelDivision> firstLevelDivisionsCanadaOnly = FXCollections.observableArrayList();

        try{

            String sql = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = 3";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int firstLevelDivId = resultSet.getInt("Division_ID");
                String firstLevelDivName = resultSet.getString("Division");
                FirstLevelDivision firstLevelDivision = new FirstLevelDivision(firstLevelDivId, firstLevelDivName);
                firstLevelDivisionsCanadaOnly.add(firstLevelDivision);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return firstLevelDivisionsCanadaOnly;

    }

}
