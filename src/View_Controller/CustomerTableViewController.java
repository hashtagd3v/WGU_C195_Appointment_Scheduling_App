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

/** Customer Table View Screen shows a table view list of customers in the database. Users are able
 * to add, update and delete a customer here.*/
public class CustomerTableViewController {

    public TextField customerListSearchText;
    public TableView customerTableView;
    public TableColumn customerIdCol;
    public TableColumn customerNameCol;
    public TableColumn customerAddressCol;
    public TableColumn customerFirstLevelDivCol;
    public TableColumn customerPostalCodeCol;
    public TableColumn customerCountryCol;

    Stage stage;
    Parent scene;

    /** Goes back to Welcome screen when clicked.*/
    public void onActionCustomerBackBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/Welcome.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }
    /** When button is clicked, opens up Add Customer screen.*/
    public void onActionCustomerAddBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/AddCustomerScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** When button is clicked, opens up Update Customer screen. This enables user to update information
     * for customer selected in table view list.*/
    public void onActionCustomerUpdateBtn(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View_Controller/UpdateCustomerScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** When button is clicked, this will enable user to delete selected customer from table view list.*/
    public void onActionCustomerDeleteBtn(ActionEvent actionEvent) {

    }

}
