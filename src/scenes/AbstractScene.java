package scenes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import services.UserService;
import services.AppointmentService;
import services.CustomerService;
import services.CountryService;
import services.DivisionService;
import services.ContactService;
import services.Logger;

/**
 * Abstract class that most scenes will have.
 * This class instantiates several services and helps to reduce code redundancy
 */
public abstract class AbstractScene extends Scene {
    public GridPane parent;
    public SceneController sceneManger;
    public UserService userService;
    public AppointmentService appointmentService;
    public CustomerService customerService;
    public DivisionService divisionService;
    public CountryService countryService;
    public ContactService contactService;

    public String operationType;

    public AbstractScene(GridPane p, SceneController sm, String filepath) {
        super(p);
        initFX(p, sm, filepath);
    }

    public AbstractScene(String fxmlfilepath, GridPane gridPane, SceneController sceneController,
            UserService userService, AppointmentService appointmentService, CustomerService customerService,
            ContactService contactService, DivisionService divisionService, CountryService countryService) {
        super(gridPane);
        this.userService = userService;
        this.contactService = contactService;
        this.customerService = customerService;
        this.appointmentService = appointmentService;
        this.divisionService = divisionService;
        this.countryService = countryService;
        initFX(gridPane, sceneController, fxmlfilepath);
    }

    private void initFX(GridPane p, SceneController sm, String filepath) {
        parent = p;
        sceneManger = sm;
        parent.setMinWidth(300);
        parent.setMinHeight(275);

        try {
            load(parent, filepath);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    private void load(GridPane parent, String filepath) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(filepath));
        loader.setController(this);
        parent.add(loader.load(), 0, 0);
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
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
