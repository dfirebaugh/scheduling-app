package scenes;

import java.sql.Timestamp;

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

    @FXML
    private Label toastNotification;

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
        if (!isValid())
            return;

        if (operationType == AddAppointmentOperation)
            addAppointment();
        if (operationType == ModifyAppointmentOperation)
            updateAppointment();
        sceneManger.switchToHome();
    }

    private void addAppointment() {
        Timestamp start = java.sql.Timestamp.valueOf(startDateField.getText() + " " + startTimeField.getText() + ":00");
        Timestamp end = java.sql.Timestamp.valueOf(endDateField.getText() + " " + endTimeField.getText() + ":00");

        appointmentService.add(new Appointment(titleField.getText(), descriptionField.getText(),
                locationField.getText(), typeField.getText(), start, end, customerSelector.getValue().getID(),
                contactSelector.getValue().getID()));
    }

    private void updateAppointment() {
        Timestamp start = java.sql.Timestamp.valueOf(startDateField.getText() + " " + startTimeField.getText() + ":00");
        Timestamp end = java.sql.Timestamp.valueOf(endDateField.getText() + " " + endTimeField.getText() + ":00");

        appointmentService.update(new Appointment(currentAppointment.getID(), titleField.getText(),
                descriptionField.getText(), locationField.getText(), typeField.getText(), start, end,
                customerSelector.getValue().getID(), contactSelector.getValue().getID()));
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

    private boolean isValid() {
        if (checkError(toastNotification, titleField.getText().length() < 1, "must have a valid title")) {
            return false;
        }
        if (checkError(toastNotification, descriptionField.getText().length() < 1, "must have a valid desription")) {
            return false;
        }
        if (checkError(toastNotification, locationField.getText().length() < 1, "must have a valid location")) {
            return false;
        }
        if (checkError(toastNotification, typeField.getText().length() < 1, "must have a valid type")) {
            return false;
        }
        if (checkError(toastNotification, startDateField.getText().length() < 1, "must have a valid start date")) {
            return false;
        }
        if (checkError(toastNotification, startTimeField.getText().length() < 1, "must have a valid start time")) {
            return false;
        }
        if (checkError(toastNotification, endDateField.getText().length() < 1, "must have a valid end date")) {
            return false;
        }
        if (checkError(toastNotification, endTimeField.getText().length() < 1, "must have a valid end time")) {
            return false;
        }
        if (checkError(toastNotification, contactSelector.getValue() == null, "must have a valid contact selected")) {
            return false;
        }
        if (checkError(toastNotification, customerSelector.getValue() == null, "must have a valid customer selected")) {
            return false;
        }

        return true;
    }

    private void populateExistingAppointment(Appointment appointment) {
        appointmentIDField.setText(appointment.getID().toString());
        titleField.setText(appointment.getTitle());
        descriptionField.setText(appointment.getDescription());
        locationField.setText(appointment.getLocation());
        typeField.setText(appointment.getType());
        startDateField.setText(appointment.getStartDate());
        startTimeField.setText(appointment.getStartTime());
        endDateField.setText(appointment.getEndDate());
        endTimeField.setText(appointment.getEndTime());
        contactSelector.setValue(contactService.get(appointment.getContactID()));
        customerSelector.setValue(customerService.get(appointment.getCustomerID()));
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
