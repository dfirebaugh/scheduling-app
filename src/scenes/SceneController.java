package scenes;

import datastore.JDBC;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Appointment;
import models.Customer;
import services.AppointmentService;
import services.CustomerService;
import services.Logger;
import services.UserService;

public class SceneController {
    Stage primaryStage;

    UserService userService;
    CustomerService customerService;
    AppointmentService appointmentService;

    private void initLogLevel() {
        Logger.level = Logger.LogLevelInfo;
    }

    private void initServices() {
        this.userService = new UserService();
        this.appointmentService = new AppointmentService();
        this.customerService = new CustomerService(appointmentService);
    }

    private void initStage(Stage ps){
        primaryStage = ps;
        primaryStage.setTitle("Hello World");
    }

    public SceneController(Stage ps) {
        initLogLevel();
        initServices();
        JDBC.makeConnection();
        initStage(ps);

        switchToLogin();
        primaryStage.setOnCloseRequest(e -> closeProgram());
    }

    public void switchToLogin() {
        this.setScene(new Login(new GridPane(), this, this.userService));
    }
    public void switchToHome() {
        this.setScene(new Home(new GridPane(), this, this.appointmentService, this.customerService));
    }
    public void switchToAppointment(String mode) {
        this.setScene(new AppointmentScene(new GridPane(), this, this.appointmentService, mode));
    }
    public void switchToAppointment(String mode, Appointment appointment) {
        this.setScene(new AppointmentScene(new GridPane(), this, this.appointmentService, mode, appointment));
    }
    public void switchToCustomer(String mode) {
        this.setScene(new CustomerScene(new GridPane(), this, this.customerService, mode));
    }
    public void switchToCustomer(String mode, Customer customer) {
        this.setScene(new CustomerScene(new GridPane(), this, this.customerService, mode, customer));
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
