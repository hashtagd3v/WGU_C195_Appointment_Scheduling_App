package DBAccess;

import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCustomer {

    public static ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        try {
            //SQL Query to get customer list and populate to customer table view list
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, customers.Division_ID, COUNTRY_ID FROM customers, first_level_divisions WHERE customers.Division_ID=first_level_divisions.Division_ID";

            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);

            //execute query and obtain results
            ResultSet resultSet = preparedStatement.executeQuery();

            //go through resultSet one row at a time
            while (resultSet.next()) {

                //get results data from each column
                int customerId = resultSet.getInt("Customer_ID");
                String customerName = resultSet.getString("Customer_Name");
                String customerAddress = resultSet.getString("Address");
                String customerPostalCode = resultSet.getString("Postal_Code");
                String customerPhone = resultSet.getString("Phone");
                int divisionId = resultSet.getInt("Division_ID");
                int countryId = resultSet.getInt("COUNTRY_ID");

                //create a customer based on each column data
                Customer customer = new Customer(customerId, customerName, customerAddress, customerPostalCode, customerPhone, divisionId, countryId);

                //add each new customer object to observable list
                customerList.add(customer);

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;

    }

}
