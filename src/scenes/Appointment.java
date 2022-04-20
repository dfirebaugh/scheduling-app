package scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import services.AppointmentService;

public class Appointment extends AbstractScene {
    private String mode;
    @FXML private Label titleLabel;
    public Appointment(GridPane p, SceneController sm, AppointmentService as, String m) {
        super(p, sm, as, "Appointment.fxml");

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
