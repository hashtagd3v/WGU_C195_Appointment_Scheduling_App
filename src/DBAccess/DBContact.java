package DBAccess;

import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class provides access to contacts database.*/
public class DBContact {

    /** @return Returns a list of all contacts from database.*/
    public static ObservableList<Contact> getAllContacts() {
        ObservableList<Contact> contactList = FXCollections.observableArrayList();

        try{

            String sql = "SELECT * FROM contacts";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int contactId = resultSet.getInt("Contact_ID");
                String contactName = resultSet.getString("Contact_Name");
                String email = resultSet.getString("Email");

                Contact contact = new Contact(contactId, contactName, email);

                contactList.add(contact);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contactList;

    }


}
