package scenes;

import datastore.JDBC;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Appointment;
import models.Customer;
import services.AppointmentService;
import services.CountryService;
import services.CustomerService;
import services.DivisionService;
import services.Logger;
import services.UserService;

public class SceneController {
    Stage primaryStage;

    UserService userService;
    CustomerService customerService;
    AppointmentService appointmentService;
    DivisionService divisionService;
    CountryService countryService;

    private Login loginScene;
    private Home homeScene;
    private AppointmentScene appointmentScene;
    private CustomerScene customerScene;

    private void initLogLevel() {
        Logger.level = Logger.LogLevelInfo;
    }

    private void initServices() {
        this.userService = new UserService();
        this.appointmentService = new AppointmentService();
        this.divisionService = new DivisionService();
        this.countryService = new CountryService();
        this.customerService = new CustomerService(new AppointmentService());
    }

    private void initStage(Stage ps){
        primaryStage = ps;
        primaryStage.setTitle("Hello World");
    }

    private void initScenes() {
        loginScene = new Login(new GridPane(), this, this.userService);
        homeScene = new Home(new GridPane(), this, this.appointmentService, this.customerService);
        appointmentScene = new AppointmentScene(new GridPane(), this, this.appointmentService);
        customerScene = new CustomerScene(new GridPane(), this, this.customerService, this.divisionService, this.countryService);
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

    public void switchToLogin() {
        this.setScene(loginScene);
    }
    public void switchToHome() {
        homeScene.requestUpdate();
        this.setScene(homeScene);
    }
    public void switchToAppointment(String mode) {
        appointmentScene.setMode(mode);
        this.setScene(appointmentScene);
    }
    public void switchToAppointment(String mode, Appointment appointment) {
        appointmentScene.setMode(mode);
        appointmentScene.setCurrentAppointment(appointment);
        this.setScene(appointmentScene);
    }
    public void switchToCustomer(String mode) {
        customerScene.setMode(mode);
        this.setScene(customerScene);
    }
    public void switchToCustomer(String mode, Customer customer) {
        customerScene.setMode(mode);
        customerScene.setCurrentCustomer(customer);
        this.setScene(customerScene);
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
