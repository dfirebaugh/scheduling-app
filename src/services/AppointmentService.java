package services;

import java.sql.SQLException;

import datastore.AppointmentStore;
import javafx.collections.ObservableList;
import models.Appointment;
import models.Customer;

public class AppointmentService {
    public AppointmentService() {
    }

    public ServiceSubscriber<Appointment> listener;

    public void registerListener(ServiceSubscriber<Appointment> listener) {
        this.listener = listener;
    }

    public ObservableList<Appointment> get() {
        try {
            return listener.requestUpdate(AppointmentStore.get());
        } catch (SQLException e) {
            Logger.error(e);
        }
        return null;
    }

    public void add(Appointment appointment) {
        try {
            AppointmentStore.add(appointment);
            listener.requestUpdate(AppointmentStore.get());
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    public void update(Appointment appointment) {
        try {
            AppointmentStore.update(appointment);
            listener.requestUpdate(AppointmentStore.get());
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    public void delete(Appointment appointment) {
        try {
            AppointmentStore.delete(appointment);
            listener.requestUpdate(AppointmentStore.get());
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    public void deleteAllCustomersAppointments(Customer customer) {
        try {
            AppointmentStore.deleteAllCustomersAppointments(customer);
            listener.requestUpdate(AppointmentStore.get());
        } catch (SQLException e) {
            Logger.error(e);
        }
    }
}
