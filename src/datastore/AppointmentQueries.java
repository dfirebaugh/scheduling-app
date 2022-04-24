package datastore;


import models.Appointment;
import models.Customer;

public class AppointmentQueries extends AbstractQuery {
    public static String get() {
        return String.format("SELECT * FROM Appointments;");
    }
    public static String getById(Appointment lookup) {
        return String.format("SELECT * FROM Appointments WHERE `Appointment_ID` = %d;", lookup.getID());
    }
    public static String getByTitle(Appointment lookup) {
        return String.format("SELECT * FROM Appointments WHERE `Appointment_Title` = '%s';", lookup.getTitle());
    }
    public static String add(Appointment appointment) {
        return String.format(
            "INSERT INTO `client_schedule`.`appointments` (`Title`,`Description`,`Location`,`Type`,`Start`,`End`,`Create_Date`,`Created_By`,`Last_Update`,`Last_Updated_By`,`Customer_ID`,`User_ID`,`Contact_ID`) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s');", 
            appointment.getTitle(),
            appointment.getDescription(), appointment.getLocation(),
            appointment.getType(), appointment.getStart(),
            appointment.getEnd(), appointment.getCreateDate(),
            appointment.getCreatedBy(), appointment.getLastUpdated(),
            appointment.getCustomerID(), appointment.getUserID(),
            appointment.getContactID()
        );
    }
    public static String update(Appointment update) {
        return String.format(
            "UPDATE `client_schedule`.`appointments` SET `Title` = '%s', `Description` = '%s', `Location` = '%s', `Type` = '%s', `Start` = '%s', `End` = '%s', `Create_Date` = '%s', `Created_By` = '%s', `Last_Update` = '%s', `Last_Updated_By` = '%s', `Customer_ID` = '%s', `User_ID` = '%s', `Contact_ID` = '%s' WHERE `Appointment_ID` = '%d';", 
            update.getTitle(),
            update.getDescription(), update.getLocation(),
            update.getType(), update.getStart(),
            update.getEnd(), update.getCreateDate(),
            update.getCreatedBy(), update.getLastUpdated(),
            update.getCustomerID(), update.getUserID(),
            update.getContactID(), update.getID()
        );
    }
    public static String delete(Appointment appointment) {
        return String.format(
            "DELETE FROM `client_schedule`.`appointments` WHERE `Appointment_ID` = %d;", 
            appointment.getID()
        );
    }
    public static String deleteAllCustomersAppointments(Customer customer) {
        return String.format(
            "DELETE FROM `client_schedule`.`appointments` WHERE `Customer_ID` = %d;", 
            customer.getID()
        );
    }
}
