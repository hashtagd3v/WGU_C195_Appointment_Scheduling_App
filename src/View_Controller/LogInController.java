package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/** This class allows user to log in to the app.
 * Log In screen will ask user for username and password. It also has a log in button
 * that the user will click to enter the app. When in log in screen, location label will
 * show user's current location based on GPS. This will enable app to determine whether
 * language used will be English or French.*/
public class LogInController {

    public Label userLocationLabel;
    public TextField userIdText;
    public TextField passwordText;

    Stage stage;
    Parent scene;

    /** This method allows user to log in to the app.
     * This is the Log In button. If username and password are correct, clicking button will log
     * user to app. Program switches screen to the Welcome screen where one can choose to go to
     * Customer list or Appointments list.
     * @param actionEvent the event or mouse click on Log In button.*/
    public void onActionLogIn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/Welcome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
}
