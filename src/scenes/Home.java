package scenes;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import models.Appointment;
import models.Customer;
import services.AppointmentService;
import services.CustomerService;
import services.Logger;

public class Home extends AbstractScene {
    @FXML private TableView<Appointment> monthAppointmentTable;
    @FXML private TableUpdater<Appointment> monthAppointmentTableUpdater;
    @FXML private TableView<Appointment> weekAppointmentTable;
    @FXML private TableView<Customer> customerTable;
    @FXML private TableUpdater<Customer> customerTableUpdater;

    public Home(GridPane p, SceneController sm, AppointmentService as, CustomerService cs) {
        super(p, sm, as, cs, "Home.fxml");
        initTables();
    }

    private void initTables() {
        customerTableUpdater = new TableUpdater<Customer>(customerTable);
        customerTableUpdater.initColumns(Customer.getKeys());
        customerTableUpdater.requestUpdate(customerService.get());
        monthAppointmentTableUpdater = new TableUpdater<Appointment>(monthAppointmentTable);
        monthAppointmentTableUpdater.initColumns(Appointment.getKeys());
        monthAppointmentTableUpdater.requestUpdate(appointmentService.get());
    }

    public void handleAddAppointment() {
        this.sceneManger.switchToAppointment("Add Appointment");
    }
    public void handleModifyAppointment() {
        if (monthAppointmentTable.getSelectionModel().getSelectedItem() == null) return;
        this.sceneManger.switchToAppointment("Edit Appointment", monthAppointmentTable.getSelectionModel().getSelectedItem());
    }
    public void handleMonthDeleteAppointment() {
        if (monthAppointmentTable.getSelectionModel().getSelectedItem() == null) return;
        Logger.info("handle Delete: " + monthAppointmentTable.getSelectionModel().getSelectedItem().getId());
        this.appointmentService.delete(monthAppointmentTable.getSelectionModel().getSelectedItem());
        monthAppointmentTableUpdater.requestUpdate(appointmentService.get());
    }
    public void handleAddCustomer() {
        this.sceneManger.switchToCustomer("Add Customer");
    }
    public void handleModifyCustomer() {
        if (customerTable.getSelectionModel().getSelectedItem() == null) return;
        this.sceneManger.switchToCustomer("Edit Customer", customerTable.getSelectionModel().getSelectedItem());
    }
    public void handleLogout() {
        this.sceneManger.switchToLogin();
    }
    public void handleDeleteCustomer() {
        if (customerTable.getSelectionModel().getSelectedItem() == null) return;
        Logger.info("handle Delete: " + customerTable.getSelectionModel().getSelectedItem().getId());
        this.customerService.delete(customerTable.getSelectionModel().getSelectedItem());
        customerTableUpdater.requestUpdate(customerService.get());
    }
}
