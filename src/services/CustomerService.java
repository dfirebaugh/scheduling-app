package services;

import models.Customer;

public class CustomerService {
    private final AppointmentService appointmentService;

    CustomerService(final AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    public void add(Customer customer) {
        // this.store.addCustomer(customer);
    }
    public void update(Customer customer) {
        // this.store.updateCustomer(customer);
    }
    public void delete(Customer customer) {
        // this.store.deleteCustomer(customer);
    }
}
