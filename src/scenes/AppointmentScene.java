package scenes;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import services.UserService;
import services.AppointmentService;
import services.CustomerService;
import services.CountryService;
import services.DivisionService;
import services.ContactService;
import services.Logger;
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

    public AppointmentScene(GridPane gridPane, SceneController sceneController, UserService userService,
            AppointmentService appointmentService, CustomerService customerService, ContactService contactService,
            DivisionService divisionService, CountryService countryService) {
        super(fxmlFilePath, gridPane, sceneController, userService, appointmentService, customerService, contactService,
                divisionService, countryService);
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

    private Timestamp getStartTimeStamp() {
        return java.sql.Timestamp.valueOf(startDateField.getText() + " " + startTimeField.getText() + ":00");
    }

    private Timestamp getEndTimeStamp() {
        return java.sql.Timestamp.valueOf(endDateField.getText() + " " + endTimeField.getText() + ":00");
    }

    private void addAppointment() {
        appointmentService.add(new Appointment(titleField.getText(), descriptionField.getText(),
                locationField.getText(), typeField.getText(), getStartTimeStamp(), getEndTimeStamp(),
                customerSelector.getValue().getID(), contactSelector.getValue().getID(),
                userService.getCurrentLoggedInUser().getID()));
    }

    private void updateAppointment() {
        appointmentService.update(new Appointment(currentAppointment.getID(), titleField.getText(),
                descriptionField.getText(), locationField.getText(), typeField.getText(), getStartTimeStamp(),
                getEndTimeStamp(), customerSelector.getValue().getID(), contactSelector.getValue().getID(),
                userService.getCurrentLoggedInUser().getID()));
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

    /**
     * isWithinBusinessHours checks that the appointment is within defined business hours 8am - 10pm EST
     */
    private boolean isWithinBusinessHours() {
        ZonedDateTime startTime = getStartTimeStamp().toInstant().atZone(ZoneId.of(TimeZone.getDefault().getID()));
        ZonedDateTime endTime = getEndTimeStamp().toInstant().atZone(ZoneId.of(TimeZone.getDefault().getID()));
        int startEST = startTime.withZoneSameInstant(ZoneId.of("America/New_York")).getHour();
        int endEST = endTime.withZoneSameInstant(ZoneId.of("America/New_York")).getHour();

        if (startEST < 8) {
            return false;
        };
        if (startEST > 22) {
            return false;
        };
        if (endEST < 8) {
            return false;
        };
        if (endEST > 22) {
            return false;
        };
        return true;
    }

    /**
     * isOverlapping checks appointment times against existing appointments to verify
     * that there is no overlapping apppointments.
     */
    private boolean isOverlapping() {
        ObservableList<Appointment> appointments = appointmentService.getAll();

        for (Appointment a : appointments) {
            if (a.IsOverlapping(new Date(getStartTimeStamp().getTime()), new Date(getEndTimeStamp().getTime()))) {
                return true;
            }
        }

        return false;
    }

    private String formatDateTimeEST(Timestamp timestamp) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        ZonedDateTime z = timestamp.toInstant().atZone(ZoneId.of("EST"));

        return z.format(fmt);
    }

    private boolean isValid() {
        Pattern datePattern = Pattern.compile("\\d{4}\\-\\d{2}\\-\\d{2}");
        Pattern timePattern = Pattern.compile("\\d{2}\\:\\d{2}");

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
        if (checkError(toastNotification, !datePattern.matcher(startDateField.getText()).find(),
                "must have a valid start date")) {
            return false;
        }
        if (checkError(toastNotification, !timePattern.matcher(startTimeField.getText()).find(),
                "must have a valid start time")) {
            return false;
        }
        if (checkError(toastNotification, !datePattern.matcher(endDateField.getText()).find(),
                "must have a valid end date")) {
            return false;
        }
        if (checkError(toastNotification, !timePattern.matcher(endTimeField.getText()).find(),
                "must have a valid end time")) {
            return false;
        }
        if (checkError(toastNotification, contactSelector.getValue() == null, "must have a valid contact selected")) {
            return false;
        }
        if (checkError(toastNotification, customerSelector.getValue() == null, "must have a valid customer selected")) {
            return false;
        }

        if (checkError(toastNotification, !isWithinBusinessHours(),
                "the appointment cannot be scheduled outside of business hours (i.e. 8am-10pm est)")) {
            return false;
        }
        if (checkError(toastNotification, isOverlapping(),
                "the appointment cannot overlap with an existing appointment.")) {
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
