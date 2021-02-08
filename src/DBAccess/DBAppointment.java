package DBAccess;

import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.sql.Timestamp.*;

/** This class obtains appointment data from the appointments database.*/
public class DBAppointment {

    /** This method obtains a list of all appointments from appointments database.
     * @return Returns a list of all appointments.*/
    public static ObservableList<Appointment> getAllAppointments() {
        ObservableList<Appointment> apptList = FXCollections.observableArrayList();

        try {

            String sql = "SELECT Appointment_ID, Title, Description, Location, contacts.Contact_Name, contacts.Contact_ID, Type, Start, End, customers.Customer_ID, User_ID " +
                    "FROM appointments, contacts, customers WHERE appointments.Contact_ID=contacts.Contact_ID AND appointments.Customer_ID=customers.Customer_ID";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int appointmentId = resultSet.getInt("Appointment_ID");
                String title = resultSet.getString("Title");
                String desc = resultSet.getString("Description");
                String location = resultSet.getString("Location");
                String type = resultSet.getString("Type");
                LocalDateTime start = resultSet.getTimestamp("Start").toLocalDateTime();     //UTC
                LocalDateTime end  = resultSet.getTimestamp("End").toLocalDateTime();       //UTC
                int customerId = resultSet.getInt("Customer_ID");
                int userId = resultSet.getInt("User_ID");
                int contactId = resultSet.getInt("Contact_ID");
                String contactName = resultSet.getString("Contact_Name");

                Appointment appointment = new Appointment(appointmentId, title, desc, location, type, start, end, customerId, userId, contactId, contactName);
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

            String sql = "SELECT Appointment_ID, Title, Description, Location, contacts.Contact_ID, contacts.Contact_Name, Type, Start, End, customers.Customer_ID, User_ID " +
                    "FROM appointments, contacts, customers WHERE appointments.Contact_ID=contacts.Contact_ID AND appointments.Customer_ID=customers.Customer_ID " +
                    "AND month(Start) = month(now())";
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int appointmentId = resultSet.getInt("Appointment_ID");
                String title = resultSet.getString("Title");
                String desc = resultSet.getString("Description");
                String location = resultSet.getString("Location");
                String type = resultSet.getString("Type");
                LocalDateTime start = resultSet.getTimestamp("Start").toLocalDateTime();     //UTC
                LocalDateTime end  = resultSet.getTimestamp("End").toLocalDateTime();       //UTC
                int customerId = resultSet.getInt("Customer_ID");
                int userId = resultSet.getInt("User_ID");
                int contactId = resultSet.getInt("Contact_ID");
                String contactName = resultSet.getString("Contact_Name");

                Appointment appointment = new Appointment(appointmentId, title, desc, location, type, start, end, customerId, userId, contactId, contactName);
                apptMonthList.add(appointment);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apptMonthList;
    }

    /** This method filters appointment table view list by current month.
     * @return Returns appointments within current week.*/
    public static ObservableList<Appointment> getAppointmentsByCurrentWeek() {
        ObservableList<Appointment> apptWeekList = FXCollections.observableArrayList();

        try {

            String sql = "SELECT Appointment_ID, Title, Description, Location, contacts.Contact_ID, contacts.Contact_Name, Type, Start, End, customers.Customer_ID, User_ID " +
                    "FROM appointments, contacts, customers WHERE appointments.Contact_ID=contacts.Contact_ID AND appointments.Customer_ID=customers.Customer_ID AND " +
                    "Start >= ? AND Start <= date_add(?, interval 7 day)"; //TODO: Change * to select one by one - join

            //******************Get Monday before date now**********************:

            LocalDate today = LocalDate.now();
            LocalTime midnight = LocalTime.MIDNIGHT;

            //Go backwards until reaching Monday before today:
            LocalDate monday = today;

            while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
                monday = monday.minusDays(1);
            }

            LocalDateTime mondayMidnight = LocalDateTime.of(monday, midnight);
            Timestamp timestamp = valueOf(mondayMidnight);

            //************End of code snippet to get Monday*********************

            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            preparedStatement.setTimestamp(1, timestamp);
            preparedStatement.setTimestamp(2, timestamp);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int appointmentId = resultSet.getInt("Appointment_ID");
                String title = resultSet.getString("Title");
                String desc = resultSet.getString("Description");
                String location = resultSet.getString("Location");
                String type = resultSet.getString("Type");
                LocalDateTime start = resultSet.getTimestamp("Start").toLocalDateTime();     //UTC
                LocalDateTime end  = resultSet.getTimestamp("End").toLocalDateTime();       //UTC
                int customerId = resultSet.getInt("Customer_ID");
                int userId = resultSet.getInt("User_ID");
                int contactId = resultSet.getInt("Contact_ID");
                String contactName = resultSet.getString("Contact_Name");

                Appointment appointment = new Appointment(appointmentId, title, desc, location, type, start, end, customerId, userId, contactId, contactName); //FIXME
                apptWeekList.add(appointment);

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return apptWeekList;

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
            preparedStatement.setTimestamp(5, valueOf(start));
            preparedStatement.setTimestamp(6, valueOf(end));
            preparedStatement.setInt(7, customerId);
            preparedStatement.setInt(8, userId);
            preparedStatement.setInt(9 , contactId);

            preparedStatement.execute();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /** This method updates selected appointment information and sends updates to the database.
     * @param ApptId the appointment Id
     * @param title the appointment title
     * @param desc the appointment description
     * @param location the appointment location
     * @param type the appointment type
     * @param start the appointment start date and time
     * @param end the appointment end date and time
     * @param customerId the customer id for the selected appointment
     * @param userId the user id related to the appointment
     * @param contactId the contact id related to the appointment.*/
    public static void updateAppt(int ApptId, String title, String desc, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) {

        try {

            String sql = "UPDATE appointments set Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? " +
                    "WHERE Appointment_ID = ?";

            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, desc);
            preparedStatement.setString(3, location);
            preparedStatement.setString(4, type);
            preparedStatement.setTimestamp(5, valueOf(start));
            preparedStatement.setTimestamp(6, valueOf(end));
            preparedStatement.setInt(7, customerId);
            preparedStatement.setInt(8, userId);
            preparedStatement.setInt(9, contactId);
            preparedStatement.setInt(10, ApptId);
            preparedStatement.execute();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /** This method deletes an appointment.
     * @param ApptId the selected appointment Id.*/
    public static void deleteAppt(int ApptId) {

        try {

            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";

            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, ApptId);
            preparedStatement.execute();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
