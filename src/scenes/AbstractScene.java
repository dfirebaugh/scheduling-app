package scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import services.AppointmentService;
import services.CustomerService;
import services.Logger;

public abstract class AbstractScene extends Scene {
    public GridPane parent;
    public SceneController sceneManger;
    public AppointmentService appointmentService;
    public CustomerService customerService;
    public String fxmlFile;

    public AbstractScene(GridPane p, SceneController sm, String filepath) {
        super(p);
        initFX(p, sm, filepath);
    }
    public AbstractScene(GridPane p, SceneController sm, AppointmentService as, CustomerService cs, String filepath) {
        super(p);
        customerService = cs;
        appointmentService = as;
        initFX(p, sm, filepath);
    }
    public AbstractScene(GridPane p, SceneController sm, AppointmentService as, String filepath) {
        super(p);
        appointmentService = as;
        initFX(p, sm, filepath);
    }

    public AbstractScene(GridPane p, SceneController sm, CustomerService cs, String filepath) {
        super(p);
        customerService = cs;
        initFX(p, sm, filepath);
    }

    private void initFX(GridPane p, SceneController sm, String filepath) {
        fxmlFile = filepath;
        parent = p;
        sceneManger = sm;
        parent.setMinWidth(300);
        parent.setMinHeight(275);

        try {
            load(parent);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    private void load(GridPane parent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        loader.setController(this);
        parent.add(loader.load(), 0,0);
    }
}
