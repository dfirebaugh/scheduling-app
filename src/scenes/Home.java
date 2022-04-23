package scenes;

import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import models.Appointment;
import models.Customer;
import services.AppointmentService;
import services.CustomerService;

public class Home extends AbstractScene {
    private static final String fxmlFilePath = "Home.fxml";

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
        super(fxmlFilePath, p, sm, as, cs);
        initTables();

    }

    private void initTables() {
        monthAppointmentTableUpdater = new TableUpdater<Appointment>(monthAppointmentTable);
        monthAppointmentTableUpdater.initColumns(Appointment.getKeys());
        appointmentService.registerListener(monthAppointmentTableUpdater);
        appointmentService.get();

        customerTableUpdater = new TableUpdater<Customer>(customerTable);
        customerTableUpdater.initColumns(Customer.getKeys());
        customerService.registerListener(customerTableUpdater);
        customerService.get();
    }

    public void handleAddAppointment() {
        this.sceneManger.switchToAppointment();
    }

    public void handleModifyAppointment() {
        if (checkError(toastNotification, TableUpdater.getSelected(monthAppointmentTable) == null,
                "you must select an appointment to modify"))
            return;
        this.sceneManger.switchToAppointment(AppointmentScene.ModifyAppointmentOperation,
                TableUpdater.getSelected(monthAppointmentTable));
    }

    public void handleMonthDeleteAppointment() {
        Appointment selected = TableUpdater.getSelected(monthAppointmentTable);
        if (checkError(toastNotification, TableUpdater.isNullSelection(monthAppointmentTable), "you must select an appointment to delete"))
            return;
        this.appointmentService.delete(selected);
        sendNotification(toastNotification, "Appointment " + selected.getId() + " has been deleted");
    }

    public void handleAddCustomer() {
        this.sceneManger.switchToCustomer();
    }

    public void handleModifyCustomer() {
        if (checkError(toastNotification, TableUpdater.isNullSelection(customerTable), "you must select a customer to modify"))
            return;
        TableUpdater.getSelected(customerTable).print();
        this.sceneManger.switchToCustomer(TableUpdater.getSelected(customerTable),
                CustomerScene.ModifyCustomerOperation);
    }

    public void handleLogout() {
        this.sceneManger.switchToLogin();
    }

    public void handleDeleteCustomer() {
        Customer selected = TableUpdater.getSelected(customerTable);
        if (checkError(toastNotification, selected == null, "you must select a customer to delete"))
            return;
        this.customerService.delete(selected);
        sendNotification(toastNotification, "Customer " + selected.getId() + " has been deleted");
    }
}
