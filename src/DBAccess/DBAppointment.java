package DBAccess;

import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

    /** This method returns a list of all appointments for current month.
     * @return Returns list of appointments for current month.*/
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

    /** This method creates a new appointment and adds it to the database.
     * @param title The appointment title
     * @param desc The appointment description
     * @param location The appointment location
     * @param type The appointment type
     * @param start The appointment start date and time
     * @param end The appointment end date and time
     * @param customerId The id of customer with appointment
     * @param userId The id of user related to the appointment
     * @param contactId The contact related to the appointment.*/
    public static void createAppt(String title, String desc, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) {

        try {
            String sql = "INSERT INTO appointments VALUES(NULL, ?, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, ?, ?, ?)";

            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,title);
            preparedStatement.setString(2, desc);
            preparedStatement.setString(3, location);
            preparedStatement.setString(4, type);
            preparedStatement.setTimestamp(5, Timestamp.valueOf(start));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(end));
            preparedStatement.setInt(7, customerId);
            preparedStatement.setInt(8, userId);
            preparedStatement.setInt(9 , contactId);

            preparedStatement.execute();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
