package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DBConnection;

import java.sql.Connection;
import java.util.Locale;

/** This is an appointment scheduling app.*/
public class Main extends Application {

    /** Sets initial scene to log-in screen.
     * @param primaryStage primary stage object for log in screen.*/
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View_Controller/LogInScreen.fxml"));
        primaryStage.setTitle("JM Appointment Scheduling Application");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /** This is the main method.
     * This is the method that gets called first when you run the program.
     * The Log In Screen pops up where user inputs their user ID and password when main method is run.
     * @param args the command line arguments.
     * */
    public static void main(String[] args) {

        //FIXME: Set default locale to FR/Erase this code snippet prior to submitting:
        Locale.setDefault(new Locale("fr"));

        DBConnection.startConnection();
        launch(args);
        DBConnection.closeConnection();
    }

}
