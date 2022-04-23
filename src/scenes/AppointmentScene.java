package scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import services.AppointmentService;
import models.Appointment;

public class AppointmentScene extends AbstractScene {
    private static final String fxmlFilePath = "Appointment.fxml";

    public static final String AddAppointmentOperation = "Add Appointment";
    public static final String ModifyAppointmentOperation = "Modify Appointment";

    @FXML
    private Label titleLabel;

    public Appointment currentAppointment;

    public AppointmentScene(GridPane p, SceneController sm, AppointmentService as) {
        super(fxmlFilePath, p, sm, as);
        titleLabel.setText(operationType);
    }

    public void handleClose() {
        this.sceneManger.switchToHome();
    }

    public void handleSave() {
        this.sceneManger.switchToHome();
    }

    public void setCurrentAppointment(Appointment appointment, String operationType) {
        titleLabel.setText(operationType);
        this.currentAppointment = appointment;
        if (operationType == AddAppointmentOperation)
            clear();
        if (operationType == ModifyAppointmentOperation)
            populateExistingAppointment(appointment);
    }

    private void populateExistingAppointment(Appointment appointment) {
        // idField.setText(customer.getId().toString());
        // nameField.setText(customer.getName());
        // addressField.setText(customer.getAddress());
        // postalCodeField.setText(customer.getPostalCode());
        // phoneField.setText(customer.getPhone());
        // countryComboBox.setValue(countryService.get(divisionService.getOne(customer.getDivisionID()).getCountryID()));
        // divisionComboBox.setValue(divisionService.getOne(customer.getDivisionID()));
    }

    public void clear() {
        // idField.setText("");
        // nameField.setText("");
        // addressField.setText("");
        // postalCodeField.setText("");
        // phoneField.setText("");
        // populateDivisionComboBox();
    }
}
