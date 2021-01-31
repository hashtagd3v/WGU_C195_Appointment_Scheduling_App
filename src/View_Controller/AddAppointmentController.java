package View_Controller;

import DBAccess.DBAppointment;
import DBAccess.DBContact;
import DBAccess.DBCustomer;
import DBAccess.DBUser;
import Model.Contact;
import Model.Customer;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/** This class enables user to add new appointment. Users fill out a form with text fields for
 * title of the appointment, description, type and location. New Appointment ID is auto generated in the Appt ID
 * text field and is disabled. There are also combo boxes for Customer ID, User ID and Contact to be assigned
 * to the appointment. This enables user to choose from pre-existing list of choices. There is also a date picker
 * for user to select date of appointment, and spinner boxes for user to select start and end time for appointment.*/
public class AddAppointmentController implements Initializable {

    public TextField addApptIDText;
    public TextField addApptTitleText;
    public TextField addApptDescriptionText;
    public TextField addApptLocationText;
    public ComboBox<Contact> addApptContactCombo;
    public ComboBox<Customer> addApptCustomerIDCombo;
    public ComboBox<User> addApptUserIDCombo;
    public DatePicker addApptDatePicker;
    public TextField addApptTypeText;
    public ComboBox<LocalTime> addApptStartTimeCombo;
    public ComboBox<LocalTime> addApptEndTimeCombo;

    Stage stage;
    Parent scene;

    /** This method initializes add appointment screen combo boxes with a list of selection.
     * @param url the location
     * @param resourceBundle the resources.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Displays all customers in Combo Box:
        addApptCustomerIDCombo.setItems(DBCustomer.getAllCustomers());
        //Displays all users in Combo Box:
        addApptUserIDCombo.setItems(DBUser.getAllUsers());
        //Displays all contacts in Combo Box:
        addApptContactCombo.setItems(DBContact.getAllContacts());

        //List of appointment times 8AM-10PM; 15 minute time increments:
        LocalTime start1 = LocalTime.of(8,0);
        LocalTime end1 = LocalTime.of(21, 45); //can only schedule appt until 9:45PM d/t business hours constraint

        while (start1.isBefore(end1.plusSeconds(1))){
            addApptStartTimeCombo.getItems().add(start1);
            start1 = start1.plusMinutes(15);
        }

        LocalTime start2 = LocalTime.of(8,15); //end time appt starts 15 minutes after first start time slot: 8AM
        LocalTime end2 = LocalTime.of(22, 0);

        while(start2.isBefore(end2.plusMinutes(15))){
            addApptEndTimeCombo.getItems().add(start2);
            start2 = start2.plusMinutes(15);
        }

    }

    /** This method allows user to cancel adding an appointment.
     * Cancel button switches user back to previous screen: Appointment Table View list.
     * @param actionEvent the event or mouse click on Cancel button.*/
    public void onActionAddApptCancelBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/ApptTableView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** This method enables user to add new appointment to database.
     * This also switches user back to previous screen: Appointment Table View list.
     * @param actionEvent the event or mouse click on Add button.*/
    public void onActionAddApptBtn(ActionEvent actionEvent) throws IOException {

        Customer customer = addApptCustomerIDCombo.getSelectionModel().getSelectedItem();
        //Obtain int customerId based on combo box selection:
        int customerIdCombo = customer.getCustomerId();

        User user = addApptUserIDCombo.getSelectionModel().getSelectedItem();
        //Obtain int userId based on combo box selection:
        int userIdCombo = user.getUserId();

        String type = addApptTypeText.getText();
        String title = addApptTitleText.getText();
        String desc = addApptDescriptionText.getText();
        String location = addApptLocationText.getText();

        Contact contact = addApptContactCombo.getSelectionModel().getSelectedItem();
        //Obtain String contact name based on combo box selection:
        int contactCombo = contact.getContactId();

        //Grab date selected by user from date picker:
        LocalDate datePicker = addApptDatePicker.getValue();

        //Grab the start appointment time selected:
        LocalTime startApptTime = addApptStartTimeCombo.getValue();

        //Grab the end appointment time selected:
        LocalTime endApptTime = addApptEndTimeCombo.getValue();

        //Combine local date and local time to create local datetime:
        LocalDateTime startLocalDT = LocalDateTime.of(datePicker, startApptTime);
        LocalDateTime endLocalDT = LocalDateTime.of(datePicker, endApptTime);

        DBAppointment.createAppt(title, desc, location, type, startLocalDT, endLocalDT, customerIdCombo, userIdCombo, contactCombo);

//        //Check local time now/TESTING (working):
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
//        LocalTime now = LocalTime.now();
//        System.out.println("Current local time " + dtf.format(now)); //Formats localtime to HH:mm --> format dtf.format()
//        TODO:Delete above code snippet later.

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/ApptTableView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

}
