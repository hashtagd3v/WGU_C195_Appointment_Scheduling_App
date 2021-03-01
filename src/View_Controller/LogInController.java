package View_Controller;

import DBAccess.DBAppointment;
import DBAccess.DBUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/** This class allows user to log in to the app.
 * Log In screen will ask user for username and password. It also has a log in button
 * that the user will click to enter the app. When in log in screen, location label will
 * show user's current location based on GPS. This will enable app to determine whether
 * language used will be English or French.*/
public class LogInController implements Initializable {

    public Label userLocationLabel;
    public TextField userIdText;
    public TextField passwordText;
    public Label logInLocationLbl;
    public Label userIdLbl;
    public Label passwordLbl;
    public Button logInBtnLbl;
    public Label titleLbl;

    Stage stage;
    Parent scene;

    /** This method initializes the LogInScreen showing user location based on system default locale.
     * @param url the location
     * @param resourceBundle the resources.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Detect system default Zone Id and display on user location label:
        ZoneId myZoneId = ZoneId.systemDefault();
        userLocationLabel.setText(String.valueOf(myZoneId));

        ResourceBundle rb = ResourceBundle.getBundle("Trans", Locale.getDefault());

        //Detect if system default language is french and translate:
        if (Locale.getDefault().getLanguage().equals("fr")) {
            userIdLbl.setText(rb.getString("userId"));
            passwordLbl.setText(rb.getString("password"));
            logInLocationLbl.setText(rb.getString("location"));
            logInBtnLbl.setText(rb.getString("logIn"));
            userIdText.setPromptText(rb.getString("typeId"));
            passwordText.setPromptText(rb.getString("typePassword"));
            titleLbl.setText(rb.getString("title"));
        }

    }

    /** This method allows user to log in to the app.
     * This is the Log In button. If username and password are correct, clicking button will log
     * user to app. Program switches screen to the Welcome screen where one can choose to go to
     * Customer list or Appointments list.
     * @param actionEvent the event or mouse click on Log In button.*/
    public void onActionLogIn(ActionEvent actionEvent) throws IOException {

        //Get string input from text fields:
        String userName = userIdText.getText();
        String password = passwordText.getText();

        //Get date and time now:
        LocalDate now = LocalDate.now();
        LocalTime timeNow = LocalTime.now();

        String filename = "login_activity.txt";

        FileWriter fileWriter = new FileWriter(filename, true);

        PrintWriter outputFile = new PrintWriter(fileWriter);

        //Determine if SQL query has matched username and password from user input:
        if (DBUser.getUserMatch(userName, password) == 0) {

            //Append login_activity.txt with failed log in:
            outputFile.append(userName + " has failed logging in on " + now
            + " " + timeNow + ".\n");

            outputFile.close();

            return;

        } else {

            //Check if user has an upcoming appointment within 15 minutes of logging in:
            if (DBUser.getUserObjectMatch(userName, password). size() == 1) {

                //Get userId of user logging in:
                int userId = DBUser.getUserObjectMatch(userName, password).get(0).getUserId();

                if (DBAppointment.getApptWithinFifteenMins(userId).isEmpty()) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("No appointment.");
                    alert.setHeaderText(null);
                    alert.setContentText("No upcoming appointment for you.");

                    alert.showAndWait();

                } else {

                    //There is a matching appointment within 15 mins here.
                    //Get the matching appointment's ID, date and time to display on alert box:
                    int apptId = DBAppointment.getApptWithinFifteenMins(userId).get(0).getAppointmentId();

                    LocalDateTime start = DBAppointment.getApptWithinFifteenMins(userId).get(0).getStart();

                    LocalDate startDate = start.toLocalDate();
                    LocalTime startTime = start.toLocalTime();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("You have an upcoming appointment!");
                    alert.setHeaderText(null);
                    alert.setContentText("Appointment ID is " + apptId + "." + "\n" + "Start Date is " + startDate + " and Start Time is: " + startTime + ".");

                    alert.showAndWait();

                }


            }

            //Append login_activity.txt with successful log in:
            outputFile.append(userName + " has successfully logged in on " + now
                    + " " + timeNow + ".\n");

            outputFile.close();

            stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/Welcome.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        }

    }

}
