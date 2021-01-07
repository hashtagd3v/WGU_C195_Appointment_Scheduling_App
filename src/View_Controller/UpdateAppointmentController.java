package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/** Update Appointment screen enables user to edit/update information for appointment that was selected from
 * appointment table view list. This screen will show a form with text fields for title, description and
 * location of the appointment. It also has combo boxes for customer ID, user ID, type of appointment and contact
 * assigned for appointment where user is able to make choices from list. This screen also has a date picker
 * where user is able to select appointment date, and spinner boxes for start & end times for the appointment.*/
public class UpdateAppointmentController {

    public TextField updateApptIDText;
    public TextField updateApptTitleText;
    public TextField updateApptDescriptionText;
    public TextField updateApptLocationText;
    public ComboBox updateApptContactCombo;
    public Spinner updateApptStartHour;
    public Spinner updateApptStartMin;
    public Spinner updateApptEndHour;
    public Spinner updateApptEndMin;
    public ComboBox updateApptCustomerIDCombo;
    public ComboBox updateApptUserIDCombo;
    public ComboBox updateApptTypeCombo;
    public DatePicker updateApptDatePicker;

    Stage stage;
    Parent scene;

    /** Cancel button takes user back to previous screen: Appointment Table View list.*/
    public void onActionUpdateApptCancelBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/ApptTableView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Delete button enables user to delete appointment information shown on screen. Also takes user back
     * to previous screen: Appointment Table View list.*/
    public void onActionUpdateApptDeleteBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/ApptTableView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Update button allows user to edit/update appointment information that was previously scheduled and is
     * shown on screen. Also takes user back to previous screen: Appointment Table View list with new updated
     * appointment information saved by user.*/
    public void onActionUpdateApptBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/ApptTableView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

}
