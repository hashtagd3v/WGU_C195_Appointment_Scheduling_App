package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateCustomerController {

    public TextField updateIDText;
    public TextField updateNameText;
    public TextField updateAddressText;
    public ComboBox updateFirstLdCombo;
    public ComboBox updateCountryCombo;
    public TextField updatePostalText;
    public TextField updatePhoneText;

    Stage stage;
    Parent scene;

    /** Cancel button enables user to cancel and switch back to previous screen: Customers
     * Table View list.*/
    public void onActionUpdateCustomerCancelBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/CustomerTableView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Delete button enables user to delete current user information shown on screen. This will also
     * switch back to previous screen: Customers Table View List.*/
    public void onActionUpdateCustomerDeleteBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/CustomerTableView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Update button enables user to submit edited/updated customer information to the database. This will
     * also switch user back to previous screen: Customer Table View list but with a list updated with
     * the new customer information updated by user.*/
    public void onActionUpdateCustomerBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/CustomerTableView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

}
