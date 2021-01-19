package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class enables user to select buttons for going back to previous screen, adding/updating/deleting appointments.
 * Appointment Table View screen displays list of all scheduled appointments in table view.*/
public class ApptTableViewController implements Initializable {
    
    public TextField apptListSearchText;
    public TableView apptTableView;
    public TableColumn apptIdCol;
    public TableColumn apptTitleCol;
    public TableColumn apptDescriptionCol;
    public TableColumn apptLocationCol;
    public TableColumn apptContactCol;
    public TableColumn apptTypeCol;
    public TableColumn apptStartDateTimeCol;
    public TableColumn apptEndDateTimeCol;
    public TableColumn apptCustomerIDCol;

    Stage stage;
    Parent scene;

    /** This method allows user to filter appointments by month.
     * Radio button selected will filter list to appointments scheduled only for current month.
     * @param actionEvent the event or mouse click on By Month radio button.*/
    public void onActionFilterByMonthBtn(ActionEvent actionEvent) {

    }

    /** This method allows user to filter appointments by week.
     * Radio button selected will filter list to appointments occurring for current week.
     * @param actionEvent the event or mouse click on By Week radio button.*/
    public void onActionFilterByWeekBtn(ActionEvent actionEvent) {

    }

    /** This method allows user to list back all appointments.
     * Radio button selected will list all scheduled appointments.
     * @param actionEvent the event or mouse click on All radio button.*/
    public void onActionFilterAllBtn(ActionEvent actionEvent) {

    }

    /** This method button enables user to go back to previous screen: Welcome screen.
     * @param actionEvent the event or mouse click on Back button.*/
    public void onActionApptBackBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/Welcome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** This method button enables user to add a new customer.
     * Switches to next screen: Add Appointment screen.
     * @param actionEvent the event or mouse click on Add button.*/
    public void onActionApptAddBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddAppointmentScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** This method enables user to update appointment information for appointment selected from the
     * table view list. This jumps to next screen: Update Appointment screen.
     * @param actionEvent the event or mouse click on Update button.*/
    public void onActionApptUpdateBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/UpdateAppointmentScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** This method button enables user to delete current appointment selected.
     * @param actionEvent the event or mouse click on Delete button.*/
    public void onActionApptDeleteBtn(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
