package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/** This class enables user to edit/update information for appointment that was selected from
 * appointment table view list. This screen will show a form with text fields for title, description and
 * location of the appointment. It also has combo boxes for customer ID, user ID, type of appointment and contact
 * assigned for appointment where user is able to make choices from list. This screen also has a date picker
 * where user is able to select appointment date, and spinner boxes for start and end times for the appointment.*/
public class UpdateAppointmentController {

    public TextField updateApptIDText;
    public TextField updateApptTitleText;
    public TextField updateApptDescriptionText;
    public TextField updateApptLocationText;
    public ComboBox updateApptContactCombo;
    public ComboBox updateApptCustomerIDCombo;
    public ComboBox updateApptUserIDCombo;
    public DatePicker updateApptDatePicker;
    public TextField updateApptTypeText;
    public ComboBox updateApptStartTimeCombo;
    public ComboBox updateApptEndTimeCombo;

    Stage stage;
    Parent scene;

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

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/ApptTableView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

}
