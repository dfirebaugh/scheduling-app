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
            Logger.error(e);
            return null;
        }
    }

    public Customer get(Customer lookup) {
        try {
            return CustomerStore.get(lookup);
        } catch (SQLException e) {
            Logger.error(e);
            return null;
        }
    }

    public void add(Customer customer) {
        try {
            Logger.info("attempting to add costumer");
            Logger.info("insterted " + CustomerStore.add(customer) + "rows");
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    public void update(Customer customer) {
        try {
            CustomerStore.update(customer);
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    public void delete(Customer customer) {
        try {
            this.appointmentService.deleteAllCustomersAppointments(customer);
            CustomerStore.delete(customer);
        } catch (SQLException e) {
            Logger.error(e);
        }
    }
}
