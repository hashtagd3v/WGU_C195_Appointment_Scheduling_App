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
import java.util.Optional;
import java.util.ResourceBundle;

/** This class enables user to edit/update information for appointment that was selected from
 * appointment table view list. This screen will show a form with text fields for title, description and
 * location of the appointment. It also has combo boxes for customer ID, user ID, type of appointment and contact
 * assigned for appointment where user is able to make choices from list. This screen also has a date picker
 * where user is able to select appointment date, and spinner boxes for start and end times for the appointment.*/
public class UpdateAppointmentController implements Initializable {

    public TextField updateApptIDText;
    public TextField updateApptTitleText;
    public TextField updateApptDescriptionText;
    public TextField updateApptLocationText;
    public ComboBox<Contact> updateApptContactCombo;
    public ComboBox<Customer> updateApptCustomerIDCombo;
    public ComboBox<User> updateApptUserIDCombo;
    public DatePicker updateApptDatePicker;
    public TextField updateApptTypeText;
    public ComboBox<LocalTime> updateApptStartTimeCombo;
    public ComboBox<LocalTime> updateApptEndTimeCombo;

    Stage stage;
    Parent scene;
    private int apptId;
    private String apptType;
    private final LocalTime absoluteStart = LocalTime.of(8, 0);
    private final LocalTime absoluteEnd = LocalTime.of(22, 0);
    private LocalDateTime startLDT;
    private LocalDateTime endLDT;
    private int customerId;

    /** This method initializes the update appointment screen with combo box selections.
     * @param url the location
     * @param resourceBundle the resources.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Displays all customers in Combo Box:
        updateApptCustomerIDCombo.setItems(DBCustomer.getAllCustomers());
        //Displays all users in Combo Box:
        updateApptUserIDCombo.setItems(DBUser.getAllUsers());
        //Displays all contacts in Combo Box:
        updateApptContactCombo.setItems(DBContact.getAllContacts());

        //List of appointment times 8AM-10PM; 15 minute time increments:
        LocalTime start1 = absoluteStart;
        LocalTime end1 = absoluteEnd.minusMinutes(15); //can only schedule appt until 9:45PM d/t business hours constraint

        while (start1.isBefore(end1.plusSeconds(1))){
            updateApptStartTimeCombo.getItems().add(start1);
            start1 = start1.plusMinutes(15);
        }

        LocalTime start2 = absoluteStart.plusMinutes(15); //end time appt starts 15 minutes after first start time slot: 8AM
        LocalTime end2 = absoluteEnd;

        while(start2.isBefore(end2.plusMinutes(15))){
            updateApptEndTimeCombo.getItems().add(start2);
            start2 = start2.plusMinutes(15);
        }

    }

    /** This method gets the info for selected appointment from the appointment table view list.
     * @param appointment the selected appointment.*/
    public void getAppt(Appointment appointment) {

        apptId = appointment.getAppointmentId(); // Auto generated
        apptType = appointment.getType();

        //Get LocalDateTime for start and end date/times of the appointment:
        LocalDateTime start = appointment.getStart();
        LocalDateTime end = appointment.getEnd();

        //Convert LocalDateTime start and end to LocalTime and LocalDate only:
        LocalDate date = start.toLocalDate();
        LocalTime startTime = start.toLocalTime();
        LocalTime endTime = end.toLocalTime();

        //******* SET VALUES ON FIELDS****************************:

        //Match customerID from list in combo box to appointment's customerId:
        for (Customer c : updateApptCustomerIDCombo.getItems()) {
            if (c.getCustomerId() == appointment.getCustomerId()) {
                updateApptCustomerIDCombo.setValue(c);
            }
        }

        //Match userId from list in combo box to appointment's userId:
        for (User u : updateApptUserIDCombo.getItems()) {
            if (u.getUserId() == appointment.getUserId()) {
                updateApptUserIDCombo.setValue(u);
            }
        }

        updateApptTypeText.setText(apptType);
        updateApptTitleText.setText(appointment.getTitle());
        updateApptDescriptionText.setText(appointment.getDesc());
        updateApptLocationText.setText(appointment.getLocation());

        //Match contacts from list in combo box to appointment's contact ID:
        for (Contact c : updateApptContactCombo.getItems()) {
            if (c.getContactId() == appointment.getContactId()) {
                updateApptContactCombo.setValue(c);
                break;
            }
        }

        updateApptDatePicker.setValue(date);
        updateApptStartTimeCombo.setValue(startTime);
        updateApptEndTimeCombo.setValue(endTime);

    }

    /** This method enables user to cancel updating appointment.
     * Cancel button takes user back to previous screen: Appointment Table View list.
     * @param actionEvent the event or mouse click on Cancel button.*/
    public void onActionUpdateApptCancelBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/ApptTableView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** This method allows user to delete selected appointment.
     * Delete button enables user to delete appointment information shown on screen.
     * Also takes user back to previous screen: Appointment Table View list.
     * @param actionEvent the event or mouse click on Delete button.*/
    public void onActionUpdateApptDeleteBtn(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting A Customer");
        alert.setHeaderText("You are about to delete an appointment.");
        alert.setContentText("Are you sure you want to proceed?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){

            DBAppointment.deleteAppt(apptId);

            //Show delete confirmation on GUI with id and type of appointment:
            Alert alertDeleteConfirmation = new Alert(Alert.AlertType.INFORMATION);
            alertDeleteConfirmation.setTitle("Appointment Deleted");
            alertDeleteConfirmation.setHeaderText(null);
            alertDeleteConfirmation.setContentText("Appointment has been deleted. \n" + "Appointment ID:" +
                    " " + apptId + ".\n" + "Appointment Type: " + apptType + ".");

            alertDeleteConfirmation.showAndWait();

            stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/ApptTableView.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        } else {

            return;

        }

    }

    /** This class allows user to edit/update appointment information that was previously scheduled and is
     * shown on screen. Also takes user back to previous screen: Appointment Table View list with new updated
     * appointment information saved by user.
     * @param actionEvent the event or mouse click on Update button.*/
    public void onActionUpdateApptBtn(ActionEvent actionEvent) throws IOException {

        //Grab information based on user selection/information typed:
        Customer customer = updateApptCustomerIDCombo.getSelectionModel().getSelectedItem();
        customerId = customer.getCustomerId();

        User user = updateApptUserIDCombo.getSelectionModel().getSelectedItem();
        int userId = user.getUserId();

        String type = updateApptTypeText.getText();
        String title = updateApptTitleText.getText();
        String desc = updateApptDescriptionText.getText();
        String location = updateApptLocationText.getText();

        Contact contact = updateApptContactCombo.getSelectionModel().getSelectedItem();
        int contactId = contact.getContactId();

        LocalDate date = updateApptDatePicker.getValue();

        LocalTime start = updateApptStartTimeCombo.getValue();
        LocalTime end = updateApptEndTimeCombo.getValue();

        //Convert appointment start/end date and time to combined start/end LocalDateTime:
        startLDT = LocalDateTime.of(date, start);
        endLDT = LocalDateTime.of(date, end);

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

            //Check for missing fields:
            if (blankField() == true) {

                return;

            }

            //Update appointment in database:
            DBAppointment.updateAppt(apptId, title, desc, location, type, startLDT, endLDT, customerId, userId, contactId);

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
        ObservableList<Appointment> apptMatches = DBAppointment.getApptByCustomer(customerId);

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
                updateApptLocationText.getText().isEmpty()      ||
                updateApptTitleText.getText().isEmpty()         ||
                updateApptDescriptionText.getText().isEmpty()   ||
                updateApptLocationText.getText().isEmpty()

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
