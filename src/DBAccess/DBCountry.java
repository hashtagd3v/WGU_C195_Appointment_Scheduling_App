package DBAccess;

import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCountry {

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
