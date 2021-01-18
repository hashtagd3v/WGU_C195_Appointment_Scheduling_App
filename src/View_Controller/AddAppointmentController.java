package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/** This class enables user to add new appointment. Users fill out a form with text fields for
 * title of the appointment, description and location. New Appointment ID is auto generated in the Appt ID
 * text field and is disabled. There are also combo boxes for Customer ID, User ID and Contact to be assigned
 * to the appointment. This enables user to choose from pre-existing list of choices. There is also a date picker
 * for user to select date of appointment, and spinner boxes for user to select start and end time for appointment.*/
public class AddAppointmentController {

    public TextField addApptIDText;
    public TextField addApptTitleText;
    public TextField addApptDescriptionText;
    public TextField addApptLocationText;
    public ComboBox addApptContactCombo;
    public Spinner addApptStartHour;
    public Spinner addApptStartMin;
    public Spinner addApptEndHour;
    public Spinner addApptEndMin;
    public ComboBox addApptCustomerIDCombo;
    public ComboBox addApptUserIDCombo;
    public ComboBox addApptTypeCombo;
    public DatePicker addApptDatePicker;

    Stage stage;
    Parent scene;

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

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/ApptTableView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

}
