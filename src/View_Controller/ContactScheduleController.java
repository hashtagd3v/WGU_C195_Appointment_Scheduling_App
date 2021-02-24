package View_Controller;

import DBAccess.DBAppointment;
import DBAccess.DBContact;
import Model.Appointment;
import Model.Contact;
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
import java.util.ResourceBundle;

/** This class enables user to generate an appointment schedule for selected contact only.*/
public class ContactScheduleController implements Initializable {
    
    public TableView<Appointment> apptTableView;
    public TableColumn apptIdCol;
    public TableColumn apptTitleCol;
    public TableColumn apptDescriptionCol;
    public TableColumn apptLocationCol;
    public TableColumn apptTypeCol;
    public TableColumn apptStartDateTimeCol;
    public TableColumn apptEndDateTimeCol;
    public TableColumn apptCustomerIDCol;
    public ComboBox<Contact> selectContactCombo;

    Stage stage;
    Parent scene;
    private int contactId;

    /** This method initializes the screen with a list of all contacts for the user to choose from
     * inside the combo box at the bottom of the screen.
     * @param url the location
     * @param resourceBundle the resources.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Set items/contact list on combo box:
        selectContactCombo.setItems(DBContact.getAllContacts());

    }

    /** This method enables user to go back to the previous screen: Select Reports screen.
     * @param actionEvent the event or mouse click on the back button.*/
    public void onActionApptBackBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/SelectReports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** This method enables user to make a contact selection.
     * @param actionEvent the event or mouse click on the combo box with the contact list.*/
    public void onActionContactChoiceCombo(ActionEvent actionEvent) {

        //Grab selected contact from combo box:
        Contact contact = selectContactCombo.getSelectionModel().getSelectedItem();

        //Get the contact id of the selected contact:
        contactId = contact.getContactId();

    }

    /** This method enables user to generate the appointment schedule for selected contact from
     * the contact combo box.
     * @param actionEvent the event or mouse click on the generate button.*/
    public void onActionGenerateContactReportBtn(ActionEvent actionEvent) {

        //Pass in selected contact's id from combo box:
        if (DBAppointment.getApptsByContact(contactId).isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Appointments for Selected Contact.");
            alert.setHeaderText(null);
            alert.setContentText("No scheduled appointments for this selected contact. Please choose another contact.");

            alert.showAndWait();
        }

        apptTableView.setItems(DBAppointment.getApptsByContact(contactId));
        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        apptLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));

    }

}
