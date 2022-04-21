package scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import services.AppointmentService;
import models.Appointment;

public class AppointmentScene extends AbstractScene {
    private static final String fxmlFilePath = "Appointment.fxml";
    private String mode;
    @FXML private Label titleLabel;
    public AppointmentScene(GridPane p, SceneController sm, AppointmentService as, String m) {
        super(p, sm, as, fxmlFilePath);

        mode = m;
        titleLabel.setText(mode);
    }
    public AppointmentScene(GridPane p, SceneController sm, AppointmentService as, String m, Appointment appointment) {
        super(p, sm, as, fxmlFilePath);

        mode = m;
        titleLabel.setText(mode);
    }

    public void handleClose() {
        this.sceneManger.switchToHome();
    }
    public void handleSave() {
        this.sceneManger.switchToHome();
    }
}
