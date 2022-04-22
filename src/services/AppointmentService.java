package services;

import java.sql.SQLException;

import datastore.AppointmentStore;
import javafx.collections.ObservableList;
import models.Appointment;
import models.Customer;

public class AppointmentService {
    public AppointmentService() {}
    public ObservableList<Appointment> get() {
        try {
            return AppointmentStore.get();
        } catch (SQLException e) {
            Logger.error(e);
        }
        return null;
    }
    public void add(Appointment appointment) {}
    public void update(Appointment appointment) {}
    public void delete(Appointment appointment) {
        try {
            AppointmentStore.delete(appointment);
        } catch (SQLException e) {
            Logger.error(e);
        }
    }
    public void deleteAllCustomersAppointments(Customer customer) {
        try {
            AppointmentStore.deleteAllCustomersAppointments(customer);
        } catch (SQLException e) {
            Logger.error(e);
        }
    }
}
