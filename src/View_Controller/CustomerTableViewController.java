package View_Controller;

import DBAccess.DBCustomer;
import Model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static DBAccess.DBCustomer.getAllCustomers;

/** This class enables users able to add, update and delete a customer here.
 * Customer Table View Screen shows a table view list of customers from the database.*/
public class CustomerTableViewController implements Initializable {

    public TableView<Customer> customerTableView;
    public TableColumn customerIdCol;
    public TableColumn customerNameCol;
    public TableColumn customerAddressCol;
    public TableColumn customerFirstLevelDivCol;
    public TableColumn customerPostalCodeCol;
    public TableColumn customerCountryCol;
    public TableColumn customerPhoneNoCol;

    Stage stage;
    Parent scene;

    /** This method initializes the screen to show customer table view list.
     * When screen comes on, this shows the table view list of customers downloaded
     * from the database.
     * @param url the location
     * @param resourceBundle the resources.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerTableView.setItems(getAllCustomers());
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerPhoneNoCol.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNo"));
        customerFirstLevelDivCol.setCellValueFactory(new PropertyValueFactory<>("divisionId"));
        customerCountryCol.setCellValueFactory(new PropertyValueFactory<>("countryId"));

    }

    /** This method allows user to go back to Welcome screen.
     * Goes back to Welcome screen when clicked.
     * @param actionEvent the event or mouse click on Back button.*/
    public void onActionCustomerBackBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/Welcome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
    /** This method allows user to add customers.
     * When button is clicked, opens up Add Customer screen.
     * @param actionEvent the event or mouse click on Add button.*/
    public void onActionCustomerAddBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddCustomerScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** This method allows user to select customer and update information.
     * When button is clicked, opens up Update Customer screen. This enables
     * user to update information for customer selected in table view list.
     * @param actionEvent the event or mouse click on Update button.*/
    public void onActionCustomerUpdateBtn(ActionEvent actionEvent) throws IOException {

        Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View_Controller/UpdateCustomerScreen.fxml"));
        loader.load();

        UpdateCustomerController MODController = loader.getController();
        MODController.getCustomer(selectedCustomer);

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** This method enables user to delete selected customer.
     * When button is clicked, this will enable user to delete
     * selected customer from table view list.
     * @param actionEvent the event or mouse click on Delete button.*/
    public void onActionCustomerDeleteBtn(ActionEvent actionEvent) {

        Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        int customerId = selectedCustomer.getCustomerId();

        //Deletion confirmation box:
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting A Customer");
        alert.setHeaderText("You are about to delete a customer including all appointments associated" +
                " with this customer.");
        alert.setContentText("Are you sure you want to proceed?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            DBCustomer.deleteCustomer(customerId);

            //Update customer table view info after deleting selected customer:
            customerTableView.setItems(getAllCustomers());

        } else {

            return;

        }

    }

}
