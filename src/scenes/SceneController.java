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

    private void initLogLevel() {
        Logger.level = Logger.LogLevelInfo;
    }

    private void initServices() {
        userService = new UserService();
        appointmentService = new AppointmentService();
        divisionService = new DivisionService();
        countryService = new CountryService();
        customerService = new CustomerService(appointmentService);
        contactService = new ContactService();
        userService = new UserService();
    }

    private void initStage(Stage ps) {
        primaryStage = ps;
        primaryStage.setTitle("Hello World");
    }

    private void initScenes() {
        loginScene = new Login(new GridPane(), this, userService);
        homeScene = new Home(new GridPane(), this, userService, appointmentService, customerService, contactService,
                divisionService, countryService);
        appointmentScene = new AppointmentScene(new GridPane(), this, userService, appointmentService, customerService,
                contactService, divisionService, countryService);
        customerScene = new CustomerScene(new GridPane(), this, userService, appointmentService, customerService,
                contactService, divisionService, countryService);
    }

    public SceneController(Stage ps) {
        initLogLevel();
        initServices();
        JDBC.makeConnection();
        initStage(ps);
        initScenes();

        switchToLogin();
        primaryStage.setOnCloseRequest(e -> closeProgram());

    }

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

    public void switchToLogin() {
        setScene(loginScene);
    }

    public void switchToHome() {
        checkUpComing();
        setScene(homeScene);
    }

    public void switchToAppointment() {
        appointmentScene.setCurrentAppointment(new Appointment(), AppointmentScene.AddAppointmentOperation);
        setScene(appointmentScene);
    }

    public void switchToAppointment(String operationType, Appointment appointment) {
        appointmentScene.setCurrentAppointment(appointment, operationType);
        setScene(appointmentScene);
    }

    public void switchToCustomer() {
        customerScene.setCurrentCustomer(new Customer(), CustomerScene.AddCustomerOperation);
        setScene(customerScene);
    }

    public void switchToCustomer(Customer customer, String operationType) {
        customerScene.setCurrentCustomer(customer, operationType);
        setScene(customerScene);
    }

    public void setScene(Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void closeProgram() {
        Logger.info("closing...");
        JDBC.closeConnection();
    }
}
