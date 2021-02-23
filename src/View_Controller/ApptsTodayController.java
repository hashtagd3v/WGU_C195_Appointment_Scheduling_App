package View_Controller;

import DBAccess.DBAppointment;
import Model.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class enables user to show a generated report of appointments scheduled for current day.*/
public class ApptsTodayController implements Initializable {

    public TableView<Appointment> apptTableView;
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

    /** This method initializes screen with a table view list of scheduled appointments
     * for current day.
     * @param url the location
     * @param resourceBundle the resources.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        apptTableView.setItems(DBAppointment.getAppointmentsToday());
        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        apptLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptContactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptStartDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptEndDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));

    }

    /** This method enables user to go back to previous screen: Select Reports screen.
     * @param actionEvent the event or mouse click on delete button.*/
    public void onActionApptBackBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/SelectReports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

}
