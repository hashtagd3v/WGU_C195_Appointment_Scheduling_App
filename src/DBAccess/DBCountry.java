package DBAccess;

import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;
import utils.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This is the Country DB access class.
 * This class obtains a list of country from countries database table.*/
public class DBCountry {

    /** @return Returns array list of all country from database.*/
    public static ObservableList<Country> getAllCountry(){
        ObservableList<Country> countryList = FXCollections.observableArrayList();

        try {

            String sql = "SELECT * FROM countries";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String country = resultSet.getString("Country");
                Country c = new Country(country);
                countryList.add(c);

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return countryList;

    }

}
