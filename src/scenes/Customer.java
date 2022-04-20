package scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import services.CustomerService;

public class Customer extends AbstractScene {
    private String mode;

    @FXML private Label titleLabel;

    public Customer(GridPane p, SceneController sm, CustomerService cs, String m) {
        super(p, sm, cs, "Customer.fxml");

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
