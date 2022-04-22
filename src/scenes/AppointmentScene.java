package scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import services.AppointmentService;
import models.Appointment;

public class AppointmentScene extends AbstractScene {
    private static final String fxmlFilePath = "Appointment.fxml";
    @FXML
    private Label titleLabel;

    public Appointment currentAppointment;

    public AppointmentScene(GridPane p, SceneController sm, AppointmentService as) {
        super(fxmlFilePath, p, sm, as);
        titleLabel.setText(mode);
    }

    public void handleClose() {
        this.sceneManger.switchToHome();
    }

    public void handleSave() {
        this.sceneManger.switchToHome();
    }

    public void setCurrentAppointment(Appointment appointment) {
        this.currentAppointment = appointment;
    }
}
