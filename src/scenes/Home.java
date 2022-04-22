package scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import models.Appointment;
import models.Customer;
import services.AppointmentService;
import services.CustomerService;

public class Home extends AbstractScene {
    @FXML
    private TableView<Appointment> monthAppointmentTable;
    @FXML
    private TableUpdater<Appointment> monthAppointmentTableUpdater;
    @FXML
    private TableView<Appointment> weekAppointmentTable;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableUpdater<Customer> customerTableUpdater;
    @FXML
    private Label toastNotification;

    public Home(GridPane p, SceneController sm, AppointmentService as, CustomerService cs) {
        super("Home.fxml", p, sm, as, cs);
        requestUpdate();
    }

    private void initTables() {
        customerTableUpdater = new TableUpdater<Customer>(customerTable);
        customerTableUpdater.initColumns(Customer.getKeys());
        monthAppointmentTableUpdater = new TableUpdater<Appointment>(monthAppointmentTable);
        monthAppointmentTableUpdater.initColumns(Appointment.getKeys());
    }

    public void requestUpdate() {
        initTables();
        monthAppointmentTableUpdater.requestUpdate(appointmentService.get());
        customerTableUpdater.requestUpdate(customerService.get());
    }

    public void handleAddAppointment() {
        this.sceneManger.switchToAppointment("Add Appointment");
    }

    public void handleModifyAppointment() {
        if (checkError(toastNotification, monthAppointmentTable.getSelectionModel().getSelectedItem() == null,
                "you must select an appointment to modify"))
            return;
        this.sceneManger.switchToAppointment("Edit Appointment",
                monthAppointmentTable.getSelectionModel().getSelectedItem());
    }

    public void handleMonthDeleteAppointment() {
        Appointment selected = monthAppointmentTable.getSelectionModel().getSelectedItem();
        if (checkError(toastNotification, monthAppointmentTable.getSelectionModel().getSelectedItem() == null,
                "you must select an appointment to delete"))
            return;
        this.appointmentService.delete(selected);
        sendNotification(toastNotification, "Appointment " + selected.getId() + " has been deleted");
        requestUpdate();
    }

    public void handleAddCustomer() {
        this.sceneManger.switchToCustomer("Add Customer");
    }

    public void handleModifyCustomer() {
        if (checkError(toastNotification, customerTable.getSelectionModel().getSelectedItem() == null,
                "you must select a customer to modify"))
            return;
        this.sceneManger.switchToCustomer("Edit Customer", customerTable.getSelectionModel().getSelectedItem());
    }

    public void handleLogout() {
        this.sceneManger.switchToLogin();
    }

    public void handleDeleteCustomer() {
        Customer selected = customerTable.getSelectionModel().getSelectedItem();
        if (checkError(toastNotification, selected == null, "you must select a customer to delete"))
            return;
        this.customerService.delete(selected);
        sendNotification(toastNotification, "Customer " + selected.getId() + " has been deleted");
        requestUpdate();
    }
}
