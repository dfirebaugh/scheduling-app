package scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import models.Appointment;
import models.Customer;
import services.UserService;
import services.AppointmentService;
import services.CustomerService;
import services.CountryService;
import services.DivisionService;
import services.ContactService;;

public class Home extends AbstractScene {
    private static final String fxmlFilePath = "Home.fxml";

    @FXML
    private TableView<Appointment> appointmentTable;
    @FXML
    private TableUpdater<Appointment> appointmentTableUpdater;
    @FXML
    private TableView<Appointment> weekAppointmentTable;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableUpdater<Customer> customerTableUpdater;
    @FXML
    private Label toastNotification;

    public Home(GridPane gridPane, SceneController sceneController, UserService userService,
            AppointmentService appointmentService, CustomerService customerService, ContactService contactService,
            DivisionService divisionService, CountryService countryService) {
        super(fxmlFilePath, gridPane, sceneController, userService, appointmentService, customerService, contactService,
                divisionService, countryService);

        initTables();
    }

    private void initTables() {
        appointmentTableUpdater = new TableUpdater<Appointment>(appointmentTable);
        appointmentTableUpdater.initColumns(Appointment.getKeys());
        appointmentService.registerListener(appointmentTableUpdater);
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
        if (checkError(toastNotification, TableUpdater.getSelected(appointmentTable) == null,
                "you must select an appointment to modify"))
            return;
        this.sceneManger.switchToAppointment(AppointmentScene.ModifyAppointmentOperation,
                TableUpdater.getSelected(appointmentTable));
    }

    public void handleMonthDeleteAppointment() {
        Appointment selected = TableUpdater.getSelected(appointmentTable);
        if (checkError(toastNotification, TableUpdater.isNullSelection(appointmentTable),
                "you must select an appointment to delete"))
            return;
        this.appointmentService.delete(selected);
        sendNotification(toastNotification,
                "Appointment " + selected.getID() + " of type: " + selected.getType() + " has been deleted");
    }

    public void handleAddCustomer() {
        this.sceneManger.switchToCustomer();
    }

    public void handleModifyCustomer() {
        if (checkError(toastNotification, TableUpdater.isNullSelection(customerTable),
                "you must select a customer to modify"))
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
        sendNotification(toastNotification, "Customer " + selected.getID() + " has been deleted");
    }
}
