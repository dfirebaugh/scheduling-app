package scenes;

import datastore.CustomerStore;
import datastore.JDBC;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Customer;
import services.Logger;

public class SceneController {
    Stage primaryStage;
    public Scene loginScene;
    public Scene homeScene;

    public SceneController(Stage ps) {
        JDBC.makeConnection();

        GridPane layout = new GridPane();

        primaryStage = ps;
        primaryStage.setTitle("Hello World");

        loginScene = new Login(layout, this);

        setScene(loginScene);

        primaryStage.setOnCloseRequest(e -> closeProgram());

        Customer testCustomer = CustomerStore.get(new Customer(1));
        testCustomer.name = "testUser";
        testCustomer.id = 0;
        testCustomer.print();
        CustomerStore.add(testCustomer);
        
        Customer lookupCustomer = CustomerStore.get(testCustomer);
        lookupCustomer.print();
        CustomerStore.add(lookupCustomer);
        
        lookupCustomer.name = "updatedName";
        lookupCustomer.print();
        CustomerStore.update(lookupCustomer);

        boolean ok = CustomerStore.delete(lookupCustomer);
        Logger.info("delete ran successfully: " + ok);
        boolean ok1 = CustomerStore.delete(testCustomer);
        Logger.info("delete ran successfully: " + ok1);
    }

    public void switchToHome() {
        GridPane layout = new GridPane();
        homeScene = new Home(layout, this);

        primaryStage.setScene(homeScene);
        primaryStage.show();
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
