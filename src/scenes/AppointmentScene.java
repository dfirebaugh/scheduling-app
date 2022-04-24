package scenes;

import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import services.AppointmentService;
import services.CustomerService;
import services.ContactService;
import models.Appointment;
import models.Contact;
import models.Customer;

public class AppointmentScene extends AbstractScene {
    private static final String fxmlFilePath = "Appointment.fxml";

    public static final String AddAppointmentOperation = "Add Appointment";
    public static final String ModifyAppointmentOperation = "Modify Appointment";

    @FXML
    private TextField appointmentIDField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField locationField;
    @FXML
    private ComboBox<Contact> contactSelector;
    @FXML
    private ComboBox<Customer> customerSelector;
    @FXML
    private TextField typeField;

    @FXML
    private TextField startDateField;

    @FXML
    private TextField startTimeField;

    @FXML
    private TextField endDateField;

    @FXML
    private TextField endTimeField;

    @FXML
    private Label titleLabel;

    public Appointment currentAppointment;

    private void populateContactSelector() {
        contactSelector.getItems().clear();
        contactSelector.getItems().addAll(contactService.get().stream().collect(Collectors.toList()));
    }

    private void populateCustomerSelector() {
        customerSelector.getItems().clear();
        customerSelector.getItems().addAll(customerService.get().stream().collect(Collectors.toList()));
    }

    public AppointmentScene(GridPane p, SceneController sm, AppointmentService as, CustomerService customerService,
            ContactService contactService) {
        super(fxmlFilePath, p, sm, as, customerService, contactService);
    }

    public void init() {
        titleLabel.setText(operationType);
        populateContactSelector();
        populateCustomerSelector();
    }

    public void handleClose() {
        sceneManger.switchToHome();
    }

    public void handleSave() {
        if (operationType == AddAppointmentOperation)
            addAppointment();
        if (operationType == ModifyAppointmentOperation)
            updateAppointment();
        sceneManger.switchToHome();
    }

    private void addAppointment() {
        appointmentService.add(new Appointment(titleField.getText(), descriptionField.getText(),
                locationField.getText(), typeField.getText(), startDateField.getText(), startTimeField.getText(),
                endDateField.getText(), endTimeField.getText(), contactSelector.getValue().getID(),
                customerSelector.getValue().getID()));
    }
    private void updateAppointment() {
        appointmentService.update(new Appointment(currentAppointment.getID(), titleField.getText(), descriptionField.getText(),
                locationField.getText(), typeField.getText(), startDateField.getText(), startTimeField.getText(),
                endDateField.getText(), endTimeField.getText(), contactSelector.getValue().getID(),
                customerSelector.getValue().getID()));
    }

    public void setCurrentAppointment(Appointment appointment, String operationType) {
        setOperationType(operationType);
        init();
        currentAppointment = appointment;
        if (operationType == AddAppointmentOperation)
            clear();
        if (operationType == ModifyAppointmentOperation)
            populateExistingAppointment(appointment);
    }

    private void populateExistingAppointment(Appointment appointment) {
        contactSelector.setValue(contactService.get(appointment.getContactID()));
        customerSelector.setValue(customerService.get(appointment.getCustomerID()));
        appointmentIDField.setText(appointment.getID().toString());
        titleField.setText(appointment.getTitle());
        descriptionField.setText(appointment.getDescription());
        locationField.setText(appointment.getLocation());
        typeField.setText(appointment.getType());
        startDateField.setText(appointment.getStartDate());
        startTimeField.setText(appointment.getStartTime());
        endDateField.setText(appointment.getEndDate());
        endTimeField.setText(appointment.getEndTime());
    }

    public void clear() {
        contactSelector.setValue(null);
        customerSelector.setValue(null);
        appointmentIDField.setText("");
        titleField.setText("");
        descriptionField.setText("");
        locationField.setText("");
        typeField.setText("");
        startDateField.setText("");
        startTimeField.setText("");
        endDateField.setText("");
        endTimeField.setText("");
    }
}
