package services;

import java.sql.SQLException;

import datastore.CustomerStore;
import javafx.collections.ObservableList;
import models.Customer;

public class CustomerService {
    private final AppointmentService appointmentService;

    public CustomerService(final AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public ObservableList<Customer> get() {
        try {
            return CustomerStore.get();
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            return null;
        }
    }

    public Customer get(Customer lookup) {
        try {
            return CustomerStore.get(lookup);
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            return null;
        }
    }

    public Customer add(Customer customer) {
        try {
            CustomerStore.add(customer);
            return CustomerStore.get(customer);
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            Logger.error("stack: " + e.getStackTrace().toString());
            return null;
        }
    }

    public Customer update(Customer customer) {
        try {
            CustomerStore.update(customer);
            return CustomerStore.get(customer);
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            return null;
        }
    }

    public void delete(Customer customer) {
        try {
            this.appointmentService.deleteAllCustomersAppointments(customer);
            CustomerStore.delete(customer);
        } catch (SQLException e) {
            Logger.error(e.getMessage());
        }
    }
}
