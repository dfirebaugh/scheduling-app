package datastore;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Appointment;
import models.Customer;

public class AppointmentStore extends AbstractStore {
    public static ObservableList<Appointment> get() throws SQLException {
        ResultSet result = AppointmentQueries.executeQuery(AppointmentQueries.get());

        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        while (result.next()) {
            Appointment a = new Appointment(result);
            a.print();
            appointments.add(a);
        }
        return appointments;
    }
    public static ObservableList<Appointment> get(boolean isMonth, Integer currentSet) throws SQLException {
        ResultSet result = AppointmentQueries.executeQuery(AppointmentQueries.get(isMonth, currentSet));

        ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        while (result.next()) {
            Appointment a = new Appointment(result);
            a.print();
            appointments.add(a);
        }
        return appointments;
    }
    public static Appointment get(Appointment lookup) throws SQLException {
        if (lookup.getID() != 0)
            return AppointmentStore.getByID(lookup);

        if (lookup.getTitle() != "")
            return AppointmentStore.getByTitle(lookup);

        return null;
    }
    public static Appointment getByID(Appointment lookup) throws SQLException {
        return new Appointment(getFirst(AppointmentQueries.executeQuery(AppointmentQueries.getById(lookup))));
    }
    public static Appointment getByTitle(Appointment lookup) throws SQLException {
        return new Appointment(getFirst(AppointmentQueries.executeQuery(AppointmentQueries.getByTitle(lookup))));
    }
    public static int add(Appointment appointment) throws SQLException {
        return AppointmentQueries.executeUpdate(AppointmentQueries.add(appointment));
    }
    public static int update(Appointment update) throws SQLException {
        return AppointmentQueries.executeUpdate(AppointmentQueries.update(update));
    }
    public static int delete(Appointment appointment) throws SQLException {
        return AppointmentQueries.executeUpdate(AppointmentQueries.delete(AppointmentStore.get(appointment)));
    }
    public static int deleteAllCustomersAppointments(Customer customer) throws SQLException {
        return AppointmentQueries.executeUpdate(AppointmentQueries.deleteAllCustomersAppointments(customer));
    }
}
