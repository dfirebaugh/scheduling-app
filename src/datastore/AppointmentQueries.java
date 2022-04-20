package datastore;

import java.sql.SQLException;

import models.Appointment;

public class AppointmentQueries extends AbstractQuery {
    public static String get() {
        return String.format("SELECT * FROM Appointments;");
    }
    public static String getById(Appointment lookup) {
        return String.format("SELECT * FROM Appointments WHERE `Appointment_ID` = %d;", lookup.id);
    }
    public static String getByTitle(Appointment lookup) {
        return String.format("SELECT * FROM Appointments WHERE `Appointment_Title` = '%s';", lookup.title);
    }
    public static String add(Appointment appointment) {
        return String.format(
            "INSERT INTO `client_schedule`.`appointments` (`Title`,`Description`,`Location`,`Type`,`Start`,`End`,`Create_Date`,`Created_By`,`Last_Update`,`Last_Updated_By`,`Customer_ID`,`User_ID`,`Contact_ID`) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');", 
            appointment.title,
            appointment.description, appointment.location,
            appointment.type, appointment.start,
            appointment.end, appointment.createDate,
            appointment.createdBy, appointment.lastUpdated,
            appointment.customerID, appointment.userID,
            appointment.contactID
        );
    }
    public static String update(Appointment update) {
        return String.format(
            "UPDATE `client_schedule`.`appointments` SET `Title` = '%s', `Description` = '%s', `Location` = '%s', `Type` = '%s', `Start` = '%s', `End` = '%s', `Create_Date` = '%s', `Created_By` = '%s', `Last_Update` = '%s', `Last_Updated_By` = '%s', `Customer_ID` = '%s', `User_ID` = '%s', `Contact_ID` = '%s' WHERE `Appointment_ID` = '%d';", 
            update.title,
            update.description, update.location,
            update.type, update.start,
            update.end, update.createDate,
            update.createdBy, update.lastUpdated,
            update.customerID, update.userID,
            update.contactID, update.id
        );
    }
    public static String delete(Appointment appointment) {
        return String.format(
            "DELETE FROM `client_schedule`.`appointments` WHERE `Appointment_ID` = %d;", 
            appointment.id
        );
    }
}
