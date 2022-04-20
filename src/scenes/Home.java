package scenes;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import models.Appointment;
import models.Customer;
import services.AppointmentService;
import services.CustomerService;

public class Home extends AbstractScene {
    @FXML private TableView<Appointment> monthAppointmentTable;
    @FXML private TableView<Customer> customerTable;

    public Home(GridPane p, SceneController sm, AppointmentService as, CustomerService cs) {
        super(p, sm, as, cs, "Home.fxml");
        populateMonthTable();
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
    public void handleAddCustomer() {
        this.sceneManger.switchToCustomer("Add Customer");
    }
    public void handleModifyAppointment() {
        this.sceneManger.switchToAppointment("Edit Appointment");
    }
    public void handleModifyCustomer() {
        this.sceneManger.switchToCustomer("Edit Customer");
    }
}
