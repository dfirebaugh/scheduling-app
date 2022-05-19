package scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.util.Comparator;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import models.Appointment;
import models.AppointmentsByTypeReport;
import models.Customer;
import services.*;


public class Home extends AbstractScene {
    private static final String fxmlFilePath = "Home.fxml";

    @FXML
    private TableView<Appointment> appointmentTable;
    @FXML
    private TableUpdater<Appointment> appointmentTableUpdater;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableUpdater<Customer> customerTableUpdater;
    @FXML
    private Label toastNotification;
    @FXML
    private Label currentSetLabel;
    @FXML
    private TextArea byTypeText;
    @FXML
    private TextArea byContactText;
    @FXML
    private TextArea byCustomerText;

    public Home(GridPane gridPane, SceneController sceneController, UserService userService,
            AppointmentService appointmentService, CustomerService customerService, ContactService contactService,
            DivisionService divisionService, CountryService countryService) {
        super(fxmlFilePath, gridPane, sceneController, userService, appointmentService, customerService, contactService,
                divisionService, countryService);

        initTables();
    }

    private void initTables() {
        appointmentService.setCurrentSet(0);
        appointmentService.setIsMonth(true);

        appointmentTableUpdater = new TableUpdater<Appointment>(appointmentTable);
        appointmentTableUpdater.initColumns(Appointment.getKeys());
        appointmentService.registerListener(appointmentTableUpdater);
        appointmentService.get();

        customerTableUpdater = new TableUpdater<Customer>(customerTable);
        customerTableUpdater.initColumns(Customer.getKeys());
        customerService.registerListener(customerTableUpdater);
        customerService.get();

        setCurrentOperationTypeLabel();
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

    public void handleIncrement() {
        appointmentService.setCurrentSet(appointmentService.getCurrentSet()+1);
        currentSetLabel.setText(appointmentService.getTableLabel());
        appointmentService.get();
    }
    public void handleDecrement() {
        appointmentService.setCurrentSet(appointmentService.getCurrentSet()-1);
        currentSetLabel.setText(appointmentService.getTableLabel());
        appointmentService.get();
    }


    private void setCurrentOperationTypeLabel() {
        if (currentSetLabel == null) return;
        currentSetLabel.setText(appointmentService.getTableLabel());
    }
    public void handleSetIsWeek() {
        appointmentService.setCurrentSet(0);
        appointmentService.setIsMonth(false);
        setCurrentOperationTypeLabel();
        appointmentService.get();
    }
    public void handleSetIsMonth() {
        appointmentService.setCurrentSet(0);
        appointmentService.setIsMonth(true);
        setCurrentOperationTypeLabel();
        appointmentService.get();
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

    public void handleUpdateByTypeReport() {
        Logger.info("selected by Type report");
        String output = "";
        ObservableList<AppointmentsByTypeReport> report = appointmentService.getAppointmentsByTypeReport();
        for (AppointmentsByTypeReport entry : report) {
            output = output + entry.toString() + "\n";
        }
        byTypeText.setText(output);
    }
    public void handleUpdateByContact() {
        Logger.info("selected by Contact report");
        String output = "";

        Comparator<Appointment> byContactComparator = Comparator.comparing(Appointment::getContactID);
        SortedList<Appointment> sortedAppointments = new SortedList<>(appointmentService.getAll(), byContactComparator);

        for (Appointment entry : sortedAppointments) {
            output = output + entry.getContactID().toString() + ": " + entry.toString() + "\n";
        }
        byContactText.setText(output);
    }
    public void handleUpdateByCustomer() {
        Logger.info("selected by customer report");
        String output = "";

        Comparator<Appointment> byCustomerComparator = Comparator.comparing(Appointment::getCustomerID);
        SortedList<Appointment> sortedAppointments = new SortedList<>(appointmentService.getAll(), byCustomerComparator);

        for (Appointment entry : sortedAppointments) {
            output = output + entry.getCustomerID().toString() + ": " + entry.toString() + "\n";
        }
        byCustomerText.setText(output);
    }
}
