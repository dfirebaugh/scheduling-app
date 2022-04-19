package services;

import datastore.CustomerStore;
import models.Customer;

public class CustomerService {
    private final AppointmentService appointmentService;

    public CustomerService(final AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public Customer get(Customer lookup) {
        return CustomerStore.get(lookup);
    }
    public Customer add(Customer customer) {
        CustomerStore.add(customer);
        return this.get(customer);
    }
    public Customer update(Customer customer) {
        CustomerStore.update(customer);
        return this.get(customer);
    }
    public void delete(Customer customer) {
        this.appointmentService.deleteAllCustomersAppointments(customer);
        CustomerStore.delete(customer);
    }
}
