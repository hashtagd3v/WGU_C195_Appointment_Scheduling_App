package View_Controller;

import DBAccess.DBAppointment;
import DBAccess.DBContact;
import DBAccess.DBCustomer;
import DBAccess.DBUser;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
import Model.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private int customerIdCombo;
    private LocalDateTime startLDT;
    private LocalDateTime endLDT;

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

        //Check for missing fields:
        if (blankField() == true) {
            return;
        }

        Customer customer = addApptCustomerIDCombo.getSelectionModel().getSelectedItem();
        //Obtain int customerId based on combo box selection:
        customerIdCombo = customer.getCustomerId();

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
        startLDT = LocalDateTime.of(datePicker, startApptTime);
        endLDT = LocalDateTime.of(datePicker, endApptTime);

        //Use system default zone Id:
        ZoneId myZoneId = ZoneId.systemDefault();

        //Assign customer selected times to system default ZoneId:
        ZonedDateTime myZoneDateTimeStart = ZonedDateTime.of(startLDT, myZoneId);
        ZonedDateTime myZoneDateTimeEnd = ZonedDateTime.of(endLDT, myZoneId);

        //Assign variable for eastern time zone:
        ZoneId estZoneId = ZoneId.of("US/Eastern");

        //Convert times user picked from system default time to Eastern time:
        ZonedDateTime estZoneDateTimeStart = myZoneDateTimeStart.withZoneSameInstant(estZoneId);
        ZonedDateTime estZoneDateTimeEnd = myZoneDateTimeEnd.withZoneSameInstant(estZoneId);

        //Convert Eastern time zone to LocalDateTime again to compare to final LocalTime absoluteStart/End:
        LocalTime proposedStartEST = estZoneDateTimeStart.toLocalDateTime().toLocalTime();
        LocalTime proposedEndEST = estZoneDateTimeEnd.toLocalDateTime().toLocalTime();

        //Validate times in order/doesn't cross:
        if(proposedStartEST.isAfter(proposedEndEST) || proposedStartEST.equals(proposedEndEST)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Selected appointment start time is after or equal to end time.");
            alert.setContentText("Please select different appointment start and/or end time slot.");

            alert.showAndWait();
            return;
        }

        //Compare converted Eastern time zone appt times picked by user to set business hours in EST:
        if (proposedStartEST.isBefore(absoluteStart) || proposedEndEST.isAfter(absoluteEnd)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Selected appointment times are outside of business hours.");
            alert.setContentText("Please select different appointment times. Business hours are between 8:00AM-10:00PM EST.");

            alert.showAndWait();
            return;
        }

        //Check if there is a previously scheduled appointment for customer that will overlap with new appt:
        if (matchCustomerAppt() == true) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Scheduling Appointment.");
            alert.setHeaderText("Time will overlap with another appointment for this customer.");
            alert.setContentText("Please select another appointment dates and/or time.");

            alert.showAndWait();

            return;

        } else {

            DBAppointment.createAppt(title, desc, location, type, startLDT, endLDT, customerIdCombo, userIdCombo, contactIdCombo);

            stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/ApptTableView.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        }

    }

    /** This method determines if customer has an overlapping appointment that is
     * previously scheduled.
     * @return Returns match that contains a boolean value. True if there is an overlapping appointment.
     * False if there is none.*/
    private boolean matchCustomerAppt() {
        ObservableList<Appointment> apptMatches = DBAppointment.getApptByCustomer(customerIdCombo);

        boolean match = false;

        for (int i = 0; i < apptMatches.size(); i++) {

            Appointment appt = apptMatches.get(i);
            LocalDateTime startAppt = appt.getStart();
            LocalDateTime endAppt = appt.getEnd();

            if ( startLDT.isAfter(startAppt.minusMinutes(1)) && startLDT.isBefore(endAppt.plusMinutes(1)) )  {

                match = true;

                break;

            } else if ( endLDT.isAfter(startAppt.minusMinutes(1)) && endLDT.isBefore(endAppt.plusMinutes(1)) ) {

                match = true;

                break;

            } else if ( startLDT.isBefore(startAppt.plusMinutes(1)) && endLDT.isAfter(endAppt.minusMinutes(1))) {

                match = true;

                break;

            } else {

                match = false;

                continue;

            }
        }

        return match;

    }

    /** This method checks all fields if they are empty/null.
     * @return Returns blankField that contains a boolean value.
     * True if a field is empty and false if all fields are filled.*/
    private boolean blankField() {

        boolean blankField = false;

        if (
                addApptCustomerIDCombo.getValue() == null    ||
                addApptUserIDCombo.getValue() == null        ||
                addApptTypeText.getText().isEmpty()          ||
                addApptTitleText.getText().isEmpty()         ||
                addApptDescriptionText.getText().isEmpty()   ||
                addApptLocationText.getText().isEmpty()      ||
                addApptContactCombo.getItems().isEmpty()     ||
                addApptDatePicker.getValue() == null         ||
                addApptStartTimeCombo.getValue() == null     ||
                addApptEndTimeCombo.getValue() == null

        ) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR!");
            alert.setHeaderText("Missing or blank fields.");
            alert.setContentText("Please fill out all fields.");

            alert.showAndWait();

            blankField = true;

        } else {

            blankField = false;

        }

        return blankField;

    }



}
