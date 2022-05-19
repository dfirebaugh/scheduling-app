package services;

import java.sql.SQLException;

import datastore.CustomerStore;
import javafx.collections.ObservableList;
import models.Customer;

public class CustomerService {
    private final AppointmentService appointmentService;

    /**
     * listener for triggering updates reactively
     */
    public ServiceSubscriber<Customer> listener;

    /**
     * class constructor
     * @param appointmentService
     */
    public CustomerService(final AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    /**
     * get all customers
     * @return
     */
    public ObservableList<Customer> get() {
        try {
            return listener.requestUpdate(CustomerStore.get());
        } catch (SQLException e) {
            Logger.error(e);
            return null;
        }
    }

    /**
     * get a specific customer
     * @param lookup
     * @return
     */
    public Customer get(Customer lookup) {
        try {
            return CustomerStore.get(lookup);
        } catch (SQLException e) {
            Logger.error(e);
            return null;
        }
    }
    /**
     * get specific customer based on customer ID
     * @param customerID
     * @return
     */
    public Customer get(Integer customerID) {
        try {
            return CustomerStore.get(customerID);
        } catch (SQLException e) {
            Logger.error(e);
            return null;
        }
    }

    /**
     * add a customer to the DB
     * @param customer
     */
    public void add(Customer customer) {
        try {
            Logger.info("attempting to add costumer");
            Logger.info("insterted " + CustomerStore.add(customer) + "rows");

            listener.requestUpdate(get());
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    /**
     * update a specific customer
     * @param customer
     */
    public void update(Customer customer) {
        try {
            CustomerStore.update(customer);
            listener.requestUpdate(get());
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    /**
     * delete a customer.
     * > Note: we have to delete all of the customers appointments before we can actually delete them
     * @param customer
     */
    public void delete(Customer customer) {
        try {
            this.appointmentService.deleteAllCustomersAppointments(customer);
            CustomerStore.delete(customer);
            listener.requestUpdate(get());
        } catch (SQLException e) {
            Logger.error(e);
        }
    }

    /**
     * registers a listener so that we can trigger updates in a reactive manner.
     * @param listener
     */
    public void registerListener(ServiceSubscriber<Customer> listener) {
        this.listener = listener;
    }

}
