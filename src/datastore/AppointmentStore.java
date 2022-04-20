package datastore;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Appointment;

public class AppointmentStore {

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
    public static Appointment get(Appointment lookup) throws SQLException {
        if (lookup.getId() != 0)
            return AppointmentStore.getByID(lookup);

        if (lookup.getTitle() != "")
            return AppointmentStore.getByTitle(lookup);

        return null;
    }

    public static Appointment getByID(Appointment lookup) throws SQLException {
        ResultSet result = AppointmentQueries.executeQuery(AppointmentQueries.getById(lookup));

        while (result.next()) {
            return new Appointment(result);
        }
        return null;
    }

    public static Appointment getByTitle(Appointment lookup) throws SQLException {
        ResultSet result = AppointmentQueries.executeQuery(AppointmentQueries.getByTitle(lookup));

        while (result.next()) {
            return new Appointment(result);
        }
        return null;
    }

    public static int add(Appointment appointment) throws SQLException {
        return AppointmentQueries.executeUpdate(AppointmentQueries.add(appointment));
    }

    public static int update(Appointment update) throws SQLException {
        return AppointmentQueries.executeUpdate(AppointmentQueries.update(update));
    }

    public static int delete(Appointment appointment) throws SQLException {
        Appointment lookup = AppointmentStore.get(appointment);

        return AppointmentQueries.executeUpdate(AppointmentQueries.delete(lookup));
    }
}
