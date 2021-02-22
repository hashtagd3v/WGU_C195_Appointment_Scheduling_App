package View_Controller;

import DBAccess.DBUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
        }

    }

    /** This method allows user to log in to the app.
     * This is the Log In button. If username and password are correct, clicking button will log
     * user to app. Program switches screen to the Welcome screen where one can choose to go to
     * Customer list or Appointments list.
     * @param actionEvent the event or mouse click on Log In button.*/
    public void onActionLogIn(ActionEvent actionEvent) throws IOException {

        //FIXME: password has to match exactly before letting user log in!!!

        //Get string input from text fields:
        String userId = userIdText.getText();
        String password = passwordText.getText();

        //Determine if SQL query has matched username and password from user input:
        if (DBUser.getUserMatch(userId, password) == 0) {
            return;
        } else {
            stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View_Controller/Welcome.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

}
