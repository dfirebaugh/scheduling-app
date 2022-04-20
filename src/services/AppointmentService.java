package services;

import datastore.AppointmentStore;
import javafx.collections.ObservableList;
import models.Appointment;
import models.Customer;

public class AppointmentService {
    public AppointmentService() {}
    public ObservableList<Appointment> get() {
        return AppointmentStore.get();
    }
    public void add(Appointment appointment) {}
    public void update(Appointment appointment) {}
    public void delete(Appointment appointment) {}
    public void deleteAllCustomersAppointments(Customer customer) {}
}
