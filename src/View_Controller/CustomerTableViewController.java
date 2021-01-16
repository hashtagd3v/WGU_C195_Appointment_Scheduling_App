package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static DBAccess.DBCustomer.getAllCustomers;

/** Customer Table View Screen shows a table view list of customers in the database. Users are able
 * to add, update and delete a customer here.*/
public class CustomerTableViewController implements Initializable {

    public TableView customerTableView;
    public TableColumn customerIdCol;
    public TableColumn customerNameCol;
    public TableColumn customerAddressCol;
    public TableColumn customerFirstLevelDivCol;
    public TableColumn customerPostalCodeCol;
    public TableColumn customerCountryCol;
    public TableColumn customerPhoneNoCol;

    Stage stage;
    Parent scene;

    /** When screen comes on, this shows the table view list of customers from database.*/
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

    /** Goes back to Welcome screen when clicked.*/
    public void onActionCustomerBackBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/Welcome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
    /** When button is clicked, opens up Add Customer screen.*/
    public void onActionCustomerAddBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddCustomerScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** When button is clicked, opens up Update Customer screen. This enables user to update information
     * for customer selected in table view list.*/
    public void onActionCustomerUpdateBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/UpdateCustomerScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** When button is clicked, this will enable user to delete selected customer from table view list.*/
    public void onActionCustomerDeleteBtn(ActionEvent actionEvent) {

    }

}
