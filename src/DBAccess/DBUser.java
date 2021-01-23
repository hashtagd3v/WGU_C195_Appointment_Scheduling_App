package DBAccess;

import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUser {

    public static ObservableList<User> getAllUsers() {
        ObservableList<User> usersList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT User_ID, User_Name, Password FROM users";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int userId = resultSet.getInt("User_ID");
                String userName = resultSet.getString("User_Name");
                String password = resultSet.getString("Password");

                User user = new User(userId, userName, password);
                usersList.add(user);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersList;

    }

}
