package datastore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Appointment;
import services.Logger;

public class AppointmentStore {
    public static Appointment getByID(Appointment lookup) {
        try {
            String query = String.format("SELECT * FROM Appointments WHERE `Appointment_ID` = %d;", lookup.id);
            PreparedStatement stmt = JDBC.getConnection().prepareStatement(query);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                return new Appointment(result);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return null;
    }
    public static Appointment getByTitle(Appointment lookup) {
        try {
            String query = String.format("SELECT * FROM Appointments WHERE `Appointment_Title` = '%s';", lookup.title);
            PreparedStatement stmt = JDBC.getConnection().prepareStatement(query);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                return new Appointment(result);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return null;
    }
    public static ObservableList<Appointment> get() {
        try {
            String query = String.format("SELECT * FROM Appointments;");
            PreparedStatement stmt = JDBC.getConnection().prepareStatement(query);
            ResultSet result = stmt.executeQuery();

            ObservableList<Appointment> appointments = FXCollections.observableArrayList();
            while (result.next()) {
                Appointment a = new Appointment(result);
                a.print();
                appointments.add(a);
            }
            return appointments;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return null;
    }
    public static Appointment get(Appointment lookup) {
        if (lookup.id != 0)
            return AppointmentStore.getByID(lookup);

        if (lookup.title != "")
            return AppointmentStore.getByTitle(lookup);

        return null;
    }
    public static boolean add(Appointment appointment) {
        try {
            String query = String.format(
                "INSERT INTO `client_schedule`.`appointments` (`Title`,`Description`,`Location`,`Type`,`Start`,`End`,`Create_Date`,`Created_By`,`Last_Update`,`Last_Updated_By`,`Customer_ID`,`User_ID`,`Contact_ID`) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');", 
                appointment.title,
                appointment.description, appointment.location,
                appointment.type, appointment.start,
                appointment.end, appointment.createDate,
                appointment.createdBy, appointment.lastUpdated,
                appointment.customerID, appointment.userID,
                appointment.contactID
            );

            Logger.info("instert query:" + query);

            PreparedStatement stmt = JDBC.getConnection().prepareStatement(query);
            int affected = stmt.executeUpdate();
            Logger.info(String.format("%d", affected));

            return affected > 0;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return false;
    }
    public static boolean update(Appointment update) {
        try {
            String query = String.format(
                "UPDATE `client_schedule`.`appointments` SET `Title` = '%s', `Description` = '%s', `Location` = '%s', `Type` = '%s', `Start` = '%s', `End` = '%s', `Create_Date` = '%s', `Created_By` = '%s', `Last_Update` = '%s', `Last_Updated_By` = '%s', `Customer_ID` = '%s', `User_ID` = '%s', `Contact_ID` = '%s' WHERE `Appointment_ID` = '%d';", 
                update.title,
                update.description, update.location,
                update.type, update.start,
                update.end, update.createDate,
                update.createdBy, update.lastUpdated,
                update.customerID, update.userID,
                update.contactID, update.id
            );

            PreparedStatement stmt = JDBC.getConnection().prepareStatement(query);
            int affected = stmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return false;
    }
    public static boolean delete(Appointment appointment) {
        try {
            Appointment lookup = AppointmentStore.get(appointment);
            String query = String.format(
                "DELETE FROM `client_schedule`.`appointments` WHERE `Appointment_ID` = %d;", 
                lookup.id
            );

            PreparedStatement stmt = JDBC.getConnection().prepareStatement(query);
            int affected = stmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return false;
    }
}
