package DBAccess;

import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/** This class obtains appointment data from the appointments database.*/
public class DBAppointment {

    /** This method obtains a list of all appointments from appointments database.
     * @return Returns a list of all appointments.*/
    public static ObservableList<Appointment> getAllAppointments() {
        ObservableList<Appointment> apptList = FXCollections.observableArrayList();

        try {

            String sql = "SELECT Appointment_ID, Title, Description, Location, contacts.Contact_Name, Type, Start, End, customers.Customer_ID " +
                    "FROM appointments, contacts, customers WHERE appointments.Contact_ID=contacts.Contact_ID AND appointments.Customer_ID=customers.Customer_ID";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int appointmentId = resultSet.getInt("Appointment_ID");
                String title = resultSet.getString("Title");
                String desc = resultSet.getString("Description");
                String location = resultSet.getString("Location");
                String contact = resultSet.getString("Contact_Name");
                String type = resultSet.getString("Type");
                LocalDateTime start = resultSet.getTimestamp("Start").toLocalDateTime();     //UTC
                LocalDateTime end  = resultSet.getTimestamp("End").toLocalDateTime();       //UTC
                int customerId = resultSet.getInt("Customer_ID");

                Appointment appointment = new Appointment(appointmentId, title, desc, location, contact, type, start, end, customerId);
                apptList.add(appointment);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apptList;
    }

    public static ObservableList<Appointment> getAppointmentsByCurrentMonth() {
        ObservableList<Appointment> apptMonthList = FXCollections.observableArrayList();

        try {

            String sql = "SELECT Appointment_ID, Title, Description, Location, contacts.Contact_Name, Type, Start, End, customers.Customer_ID " +
                    "FROM appointments, contacts, customers WHERE appointments.Contact_ID=contacts.Contact_ID AND appointments.Customer_ID=customers.Customer_ID " +
                    "AND month(Start) = month(now())";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int appointmentId = resultSet.getInt("Appointment_ID");
                String title = resultSet.getString("Title");
                String desc = resultSet.getString("Description");
                String location = resultSet.getString("Location");
                String contact = resultSet.getString("Contact_Name");
                String type = resultSet.getString("Type");
                LocalDateTime start = resultSet.getTimestamp("Start").toLocalDateTime();     //UTC
                LocalDateTime end  = resultSet.getTimestamp("End").toLocalDateTime();       //UTC
                int customerId = resultSet.getInt("Customer_ID");

                Appointment appointment = new Appointment(appointmentId, title, desc, location, contact, type, start, end, customerId);
                apptMonthList.add(appointment);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apptMonthList;
    }

}
