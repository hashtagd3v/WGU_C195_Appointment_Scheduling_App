package View_Controller;

import DBAccess.DBAppointment;
import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class allows user to run report for number of customer appointments by month and type.*/
public class CustomerApptCount implements Initializable {

    public TableView<Appointment> apptByMonthTypeTableView;
    public TableColumn typeCol;
    public TableColumn countCol;
    public ComboBox<String> monthComboBox;
    private static final ObservableList<String> allMonths = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December");

    Stage stage;
    Parent scene;
    private int month;

    /** This method initializes the screen with a list of months of the year available for user selection
     * in the SELECT MONTH combo box.
     * @param url the location
     * @param resourceBundle the resources.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Populate combo box with months of the year:
        monthComboBox.setItems(allMonths);

    }

    /** This method takes user back to previous screen: Select Reports screen.
     * @param actionEvent the event or mouse click on back button.*/
    public void onActionBackBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/ApptTableView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** This method allows user to generate customer appointment numbers by month and type.
     * @param actionEvent the event or mouse click on generate report button.*/
    public void onActionGenerateBtn(ActionEvent actionEvent) {

        //Grab month selected from combo box plus one because list is zero based index:
        month = monthComboBox.getSelectionModel().getSelectedIndex() + 1;

        if (DBAppointment.getApptByMonthAndType(month).isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Appointments");
            alert.setHeaderText(null);
            alert.setContentText("No appointments for selected month.");

            alert.showAndWait();

        }

        //Set table view to query result (passing in month selected):
        apptByMonthTypeTableView.setItems(DBAppointment.getApptByMonthAndType(month));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        countCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));


    }

}
