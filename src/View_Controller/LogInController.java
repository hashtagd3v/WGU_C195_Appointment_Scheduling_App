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

public class LogInController {

    public Label userLocationLabel;
    public TextField userIdText;
    public TextField passwordText;

    Stage stage;
    Parent scene;

    /** This is the Log In button. If username and password are correct, clicking button will log user to app.
     * Program switches screen to the Welcome screen where one can choose to go to Customer list or Appointments
     * list. */
    public void onActionLogIn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/Welcome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
}
