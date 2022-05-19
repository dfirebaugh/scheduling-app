package scenes;

import datastore.JDBC;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Appointment;
import models.Customer;
import services.UserService;
import services.AppointmentService;
import services.CountryService;
import services.CustomerService;
import services.DivisionService;
import services.ContactService;
import services.Logger;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class SceneController {
    Stage primaryStage;

    UserService userService;
    CustomerService customerService;
    AppointmentService appointmentService;
    DivisionService divisionService;
    CountryService countryService;
    ContactService contactService;

    private Login loginScene;
    private Home homeScene;
    private AppointmentScene appointmentScene;
    private CustomerScene customerScene;

    /**
     * sets logLevel for the app
     */
    private void initLogLevel() {
        Logger.level = Logger.LogLevelError;
    }

    /**
     * initializes all services
     */
    private void initServices() {
        userService = new UserService();
        appointmentService = new AppointmentService();
        divisionService = new DivisionService();
        countryService = new CountryService();
        customerService = new CustomerService(appointmentService);
        contactService = new ContactService();
        userService = new UserService();
    }

    /**
     * initializes the javafx stage
     * @param ps
     */
    private void initStage(Stage ps) {
        primaryStage = ps;
        primaryStage.setTitle("Scheduling App");
    }

    /**
     * initialize scenes that we can switch between
     */
    private void initScenes() {
        loginScene = new Login(new GridPane(), this, userService);
        homeScene = new Home(new GridPane(), this, userService, appointmentService, customerService, contactService,
                divisionService, countryService);
        appointmentScene = new AppointmentScene(new GridPane(), this, userService, appointmentService, customerService,
                contactService, divisionService, countryService);
        customerScene = new CustomerScene(new GridPane(), this, userService, appointmentService, customerService,
                contactService, divisionService, countryService);
    }

    /**
     * Class constructor
     * @param ps
     */
    public SceneController(Stage ps) {
        initLogLevel();
        initServices();
        JDBC.makeConnection();
        initStage(ps);
        initScenes();

        switchToLogin();
        primaryStage.setOnCloseRequest(e -> closeProgram());

    }

    /**
     * Checks to see if we have any upcoming appointments
     */
    public void checkUpComing() {
        Appointment upcoming = appointmentService.getUpComing();

        if (upcoming == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Alert!");
            alert.setHeaderText("You have no upcoming appointments in the next 15 minutes.");
            alert.show();
            return;
        }

        Logger.info("upcoming: ");
        upcoming.print();

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Alert!");
        alert.setHeaderText("You have an upcoming appointment.");
        alert.setContentText(upcoming.toString());
        alert.show();
    }

    /**
     * switch to login scene
     */
    public void switchToLogin() {
        setScene(loginScene);
    }

    /**
     * switch to home scene
     */
    public void switchToHome() {
        checkUpComing();
        setScene(homeScene);
    }

    /**
     * switch to appointment Scene
     */
    public void switchToAppointment() {
        appointmentScene.setCurrentAppointment(new Appointment(), AppointmentScene.AddAppointmentOperation);
        setScene(appointmentScene);
    }

    /**
     * switch to appointment scene
     * @param operationType
     * @param appointment
     */
    public void switchToAppointment(String operationType, Appointment appointment) {
        appointmentScene.setCurrentAppointment(appointment, operationType);
        setScene(appointmentScene);
    }

    /**
     * switch to customer scene
     */
    public void switchToCustomer() {
        customerScene.setCurrentCustomer(new Customer(), CustomerScene.AddCustomerOperation);
        setScene(customerScene);
    }

    /**
     * switch to customer scene
     * @param customer
     * @param operationType
     */
    public void switchToCustomer(Customer customer, String operationType) {
        customerScene.setCurrentCustomer(customer, operationType);
        setScene(customerScene);
    }

    /**
     * sets the scene to whatever we pass in as an argument
     * @param scene
     */
    public void setScene(Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * exits the app
     */
    private void closeProgram() {
        Logger.info("closing...");
        JDBC.closeConnection();
    }
}
