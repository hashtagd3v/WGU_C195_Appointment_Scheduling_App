package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/** Appointment Table View screen displays list of all scheduled appointments in table view. In this screen,
 * user will be able to select buttons for going back to previous screen, adding/updating/deleting appointments.*/
public class ApptTableViewController {
    
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

    /** Radio button selected will filter list to appointments scheduled only for current month.*/
    public void onActionFilterByMonthBtn(ActionEvent actionEvent) {

    }

    /** Radio button selected will filter list to appointments occurring for current week.*/
    public void onActionFilterByWeekBtn(ActionEvent actionEvent) {

    }

    /** Radio button selected will list all scheduled appointments.*/
    public void onActionFilterAllBtn(ActionEvent actionEvent) {

    }

    /** Back button enables user to go back to previous screen: Welcome screen.*/
    public void onActionApptBackBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/Welcome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Add button enables user to add a new customer. Switches to next screen: Add Appointment screen.*/
    public void onActionApptAddBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddAppointmentScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Update button enables user to update appointment information for appointment selected from the
     * table view list. This jumps to next screen: Update Appointment screen.*/
    public void onActionApptUpdateBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/UpdateAppointmentScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** Delete button enables user to delete current appointment selected.*/
    public void onActionApptDeleteBtn(ActionEvent actionEvent) {

    }

}
