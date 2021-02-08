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
import java.time.*;
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
    private final LocalTime absoluteStart = LocalTime.of(8, 0);
    private final LocalTime absoluteEnd = LocalTime.of(22, 0);

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
        LocalTime start1 = absoluteStart;
        LocalTime end1 = absoluteEnd.minusMinutes(15); //can only schedule appt until 9:45PM d/t business hours constraint

        while (start1.isBefore(end1.plusSeconds(1))){
            addApptStartTimeCombo.getItems().add(start1);
            start1 = start1.plusMinutes(15);
        }

        LocalTime start2 = absoluteStart.plusMinutes(15); //end time appt starts 15 minutes after first start time slot: 8AM
        LocalTime end2 = absoluteEnd;

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
        int contactIdCombo = contact.getContactId();

        //Grab date selected by user from date picker:
        LocalDate datePicker = addApptDatePicker.getValue();

        //Grab the start appointment time selected:
        LocalTime startApptTime = addApptStartTimeCombo.getValue();

        //Grab the end appointment time selected:
        LocalTime endApptTime = addApptEndTimeCombo.getValue();

        //Combine local date and local time to create local datetime:
        LocalDateTime startLocalDT = LocalDateTime.of(datePicker, startApptTime);
        LocalDateTime endLocalDT = LocalDateTime.of(datePicker, endApptTime);

        ZoneId myZoneId = ZoneId.systemDefault();
        System.out.println("My Zone Id: " + myZoneId);

        ZonedDateTime myZoneDateTimeStart = ZonedDateTime.of(startLocalDT, myZoneId);
        ZonedDateTime myZoneDateTimeEnd = ZonedDateTime.of(endLocalDT, myZoneId);
        System.out.println("User time start: " + myZoneDateTimeStart);
        System.out.println("User time end: " + myZoneDateTimeEnd);

        ZoneId estZoneId = ZoneId.of("US/Eastern");

        //Convert times user picked from local time to Eastern time:
        ZonedDateTime estZoneDateTimeStart = myZoneDateTimeStart.withZoneSameInstant(estZoneId);
        System.out.println("Eastern Time Start  - Converted: " + estZoneDateTimeStart);

        ZonedDateTime estZoneDateTimeEnd = myZoneDateTimeEnd.withZoneSameInstant(estZoneId);
        System.out.println("Eastern Time End  - Converted: " + estZoneDateTimeEnd);

        //Convert Eastern time zone to LocalDateTime again prior to storing in DB:
        LocalTime proposedStartEST = estZoneDateTimeStart.toLocalDateTime().toLocalTime();
        LocalTime proposedEndEST = estZoneDateTimeEnd.toLocalDateTime().toLocalTime();

                                                                                                                        //TODO: Validate times in order/doesn't cross.

        if (proposedStartEST.isBefore(absoluteStart) || proposedEndEST.isAfter(absoluteEnd)) {
            System.out.println("Not in business hours.");                                                               //TODO: Create alert for this.
            return;
        }

        DBAppointment.createAppt(title, desc, location, type, startLocalDT, endLocalDT, customerIdCombo, userIdCombo, contactIdCombo);

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/ApptTableView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

}
