package scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import services.AppointmentService;
import services.CustomerService;
import services.CountryService;
import services.DivisionService;
import services.Logger;

public abstract class AbstractScene extends Scene {
    public GridPane parent;
    public SceneController sceneManger;
    public AppointmentService appointmentService;
    public CustomerService customerService;
    public DivisionService divisionService;
    public CountryService countryService;
    public String fxmlFile;

    public String mode;

    public AbstractScene(GridPane p, SceneController sm, String filepath) {
        super(p);
        initFX(p, sm, filepath);
    }

    public AbstractScene(String fxmlfilepath, GridPane gridPane, SceneController sceneController,
            AppointmentService appointmentService, CustomerService customerService, DivisionService divisionService,
            CountryService countryService) {
        super(gridPane);
        this.customerService = customerService;
        this.appointmentService = appointmentService;
        this.divisionService = divisionService;
        this.countryService = countryService;
        initFX(gridPane, sceneController, fxmlfilepath);
    }

    public AbstractScene(String fxmlfilepath, GridPane gridPane, SceneController sceneController,
            AppointmentService appointmentService) {
        super(gridPane);
        this.appointmentService = appointmentService;
        initFX(gridPane, sceneController, fxmlfilepath);
    }

    public AbstractScene(String fxmlfilepath, GridPane gridPane, SceneController sceneController,
            AppointmentService appointmentService, CustomerService customerService) {
        super(gridPane);
        this.customerService = customerService;
        this.appointmentService = appointmentService;
        initFX(gridPane, sceneController, fxmlfilepath);
    }

    public AbstractScene(String fxmlfilepath, GridPane gridPane, SceneController sceneController,
            CustomerService customerService, DivisionService divisionService, CountryService countryService) {
        super(gridPane);
        this.customerService = customerService;
        // this.appointmentService = appointmentService;
        this.divisionService = divisionService;
        this.countryService = countryService;
        initFX(gridPane, sceneController, fxmlfilepath);
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
        parent.add(loader.load(), 0, 0);
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean checkError(Label toastNotification, boolean expression, String errorMessage) {
        if (!expression)
            return false;

        Notification.showToast(Notification.TOAST_ERROR, toastNotification, errorMessage);
        return true;
    }

    public void sendNotification(Label toastNotification, String message) {
        Notification.showToast(Notification.TOAST_SUCCESS, toastNotification, message);
    }

    public void sendWarning(Label toastNotification, String message) {
        Notification.showToast(Notification.TOAST_SUCCESS, toastNotification, message);
    }
}
