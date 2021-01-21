package DBAccess;

import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This is the Customer DB access class.
 * This class obtains a list of all customers from customers database table.*/
public class DBCustomer {

    /**@return Returns array list of all customers.*/
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

    /** This method creates a new customer and adds it to the customers database.
     * @param name The customer name
     * @param address The customer address
     * @param postalCode The customer's postal code
     * @param phone The customer's phone number
     * @param divId The customer's first level division ID.*/
    public static void createCustomer(String name, String address, String postalCode, String phone, int divId) {

        try {
            String sql = "INSERT INTO customers VALUES(NULL, ?, ?, ?, ?, NULL, NULL, NULL, NULL, ?)";

            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, postalCode);
            preparedStatement.setString(4, phone);
            preparedStatement.setInt(5, divId);

            preparedStatement.execute();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /** This method updates customer information for selected customer instance from customer table view.
     * @param customerId The customer id
     * @param name The customer name
     * @param address The customer address
     * @param phone The customer phone number
     * @param postalCode The customer's postal code
     * @param divId The customer's division ID.*/
    public static void updateCustomer(int customerId, String name, String address, String postalCode, String phone, int divId) {

        try {

            String sql = "UPDATE customers set Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";

            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, postalCode);
            preparedStatement.setString(4, phone);
            preparedStatement.setInt(5, divId);
            preparedStatement.setInt(6, customerId);
            preparedStatement.execute();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /** This method deletes customer instance selected from customer table view.
     * @param customerId The selected customer ID.*/
    public static void deleteCustomer(int customerId){

        try{

            String sql = "DELETE FROM customers WHERE Customer_ID = ?";

            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, customerId);
            preparedStatement.execute();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
