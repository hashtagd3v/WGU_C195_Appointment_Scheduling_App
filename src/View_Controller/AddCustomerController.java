package View_Controller;

import DBAccess.DBCustomer;
import Model.Country;
import Model.FirstLevelDivision;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ResourceBundle;

import static DBAccess.DBCountry.getAllCountry;
import static DBAccess.DBFirstLevelDiv.getAllFirstLevelDiv;

/** This class Customer screen enables user to add new customer to the database. Screen is made up of a form that
 * has text fields for Customer ID, Name, Address, Postal Code, and Phone Number. It also has two combo boxes.
 * One for First Level Division and one for Country which allows user to choose from the list.*/
public class AddCustomerController implements Initializable {
    
    public TextField addCustomerIDText;
    public TextField addCustomerNameText;
    public TextField addCustomerAddressText;
    public ComboBox<FirstLevelDivision> addCustomerFirstLdCombo;
    public ComboBox<Country> addCustomerCountryCombo;
    public TextField addCustomerPostalText;
    public TextField addCustomerPhoneText;

    Stage stage;
    Parent scene;

    /** This method initializes add customer screen with list of first level division and country
     * from database to combo boxes.
     * Combo boxes show user choices of available first level division and countries.
     * @param url the location
     * @param resourceBundle the resources.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addCustomerFirstLdCombo.setItems(getAllFirstLevelDiv());
        addCustomerCountryCombo.setItems(getAllCountry());

    }

    /** This method allows user to cancel adding customer.
     * When button is clicked, this switches screen back to Customer table view list.
     * @param actionEvent the event or mouse click on Cancel button.*/
    public void onActionAddCustomerCancelBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/CustomerTableView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** This method allows user to save customer information.
     * When button is clicked, user is able to add customer information to database.
     * Screen will switch back to Customer table view list with new customer added to
     * the list of customers.
     * @param actionEvent the event or mouse click on Add button.*/
    public void onActionAddCustomerAddBtn(ActionEvent actionEvent) throws IOException {
        
        String customerName = addCustomerNameText.getText();
        String customerAddress = addCustomerAddressText.getText();
        String postalCode = addCustomerPostalText.getText();
        String customerPhoneNo = addCustomerPhoneText.getText();

        //Grabs first level division selected from combo box:
        FirstLevelDivision division = addCustomerFirstLdCombo.getSelectionModel().getSelectedItem();
        //Grabs ONLY the division ID:
        int divId = division.getFirstLevelDivId();

        //Grabs country selected from combo box:
        Country country = addCustomerCountryCombo.getSelectionModel().getSelectedItem();

        //Combo box selection validation:
        if (division == null || country == null) {
            return;
        }

        //Create new customer:
        DBCustomer.createCustomer(customerName, customerAddress, postalCode, customerPhoneNo, divId);

        //TODO: Filter First Level Division combo box list based on country selection.

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/CustomerTableView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

}
