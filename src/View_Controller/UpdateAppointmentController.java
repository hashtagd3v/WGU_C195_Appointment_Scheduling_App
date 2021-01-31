package View_Controller;

import DBAccess.DBAppointment;
import DBAccess.DBContact;
import DBAccess.DBCustomer;
import DBAccess.DBUser;
import Model.Appointment;
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

        LocalTime absoluteStart = LocalTime.of(8, 0); //TODO: convert times to Eastern times
        LocalTime absoluteEnd = LocalTime.of(22, 0);


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

        updateApptTypeText.setText(appointment.getType());
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

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/ApptTableView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** This class allows user to edit/update appointment information that was previously scheduled and is
     * shown on screen. Also takes user back to previous screen: Appointment Table View list with new updated
     * appointment information saved by user.
     * @param actionEvent the event or mouse click on Update button.*/
    public void onActionUpdateApptBtn(ActionEvent actionEvent) throws IOException {

        Customer customer = updateApptCustomerIDCombo.getSelectionModel().getSelectedItem();
        int customerId = customer.getCustomerId();
        System.out.println(customerId);

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

        LocalDateTime startDT = LocalDateTime.of(date, start);
        LocalDateTime endDT = LocalDateTime.of(date, end);

        DBAppointment.updateAppt(apptId, title, desc, location, type, startDT, endDT, customerId, userId, contactId);

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/ApptTableView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

}
