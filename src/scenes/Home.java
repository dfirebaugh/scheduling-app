package scenes;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import models.Appointment;
import models.Customer;
import services.AppointmentService;
import services.CustomerService;
import services.Logger;

public class Home extends AbstractScene {
    @FXML private TableView<Appointment> monthAppointmentTable;
    @FXML private TableView<Appointment> weekAppointmentTable;
    @FXML private TableView<Customer> customerTable;

    public Home(GridPane p, SceneController sm, AppointmentService as, CustomerService cs) {
        super(p, sm, as, cs, "Home.fxml");
        populateMonthTable();
        generateCustomerTable();
        generateAppointmentsTable();
    }

    private TableColumn<Customer, Integer> customerColumnIntegerFactory(String label, String key) {
        TableColumn<Customer, Integer> tableColumn = new TableColumn<>(label);
        tableColumn.setCellValueFactory(
                new PropertyValueFactory<Customer, Integer>(key));

        return tableColumn;
    }
    private TableColumn<Customer, String> customerColumnStringFactory(String label, String key) {
        TableColumn<Customer, String> tableColumn = new TableColumn<>(label);
        tableColumn.setCellValueFactory(
                new PropertyValueFactory<Customer, String>(key));

        return tableColumn;
    }
    private TableColumn<Appointment, Integer> appointmentColumnIntegerFactory(String label, String key) {
        TableColumn<Appointment, Integer> tableColumn = new TableColumn<>(label);
        tableColumn.setCellValueFactory(
                new PropertyValueFactory<Appointment, Integer>(key));

        return tableColumn;
    }
    private TableColumn<Appointment, String> appointmentColumnStringFactory(String label, String key) {
        TableColumn<Appointment, String> tableColumn = new TableColumn<>(label);
        tableColumn.setCellValueFactory(
                new PropertyValueFactory<Appointment, String>(key));

        return tableColumn;
    }

    /**
     * generateCustomerTable
     * generates the columns for customer table
     */
    void generateCustomerTable() {
        customerTable.getColumns().addAll(
            customerColumnIntegerFactory("ID", "id"), 
            customerColumnStringFactory("Name", "name"),
            customerColumnStringFactory("Address", "address"),
            customerColumnStringFactory("Postal Code", "postalCode"),
            customerColumnStringFactory("Date Created", "createDate"),
            customerColumnStringFactory("Created By", "createdBy"),
            customerColumnStringFactory("Last Updated", "lastUpdated"),
            customerColumnIntegerFactory("Division ID", "divisionID")
            );
    }
    /**
     * generateAppointmentsTable
     * generates the columns for appointments table
     */
    void generateAppointmentsTable() {
        monthAppointmentTable.getColumns().addAll(
            appointmentColumnIntegerFactory("ID", "id"),
            appointmentColumnStringFactory("Title", "title"),
            appointmentColumnStringFactory("Description", "description"),
            appointmentColumnStringFactory("Location", "location"),
            appointmentColumnStringFactory("Type", "type"),
            appointmentColumnStringFactory("Start", "start"),
            appointmentColumnStringFactory("End", "end"),
            appointmentColumnStringFactory("Date Created", "createDate"),
            appointmentColumnStringFactory("Created By", "createdBy"),
            appointmentColumnStringFactory("Last Updated", "lastUpdated"),
            appointmentColumnIntegerFactory("Customer ID", "customerID"),
            appointmentColumnIntegerFactory("User ID", "userID"),
            appointmentColumnIntegerFactory("Contact ID", "contactID")
            );
    }

    private void populateMonthTable() {
        monthAppointmentTable.setItems(this.appointmentService.get());
        monthAppointmentTable.refresh();
        customerTable.setItems(this.customerService.get());
        customerTable.refresh();
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
        // if (!util.confirmDelete(partsTable.getSelectionModel().getSelectedItem().getName())) return;
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
        // if (!util.confirmDelete(partsTable.getSelectionModel().getSelectedItem().getName())) return;
    }
}
