package scenes;

import datastore.JDBC;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.Logger;
import services.UserService;

public class SceneController {
    Stage primaryStage;
    public Scene loginScene;
    public Scene homeScene;

    public SceneController(Stage ps) {
        Logger.level = Logger.LogLevelInfo;
        JDBC.makeConnection();

        GridPane layout = new GridPane();

        primaryStage = ps;
        primaryStage.setTitle("Hello World");

        loginScene = new Login(layout, this, new UserService());

        setScene(loginScene);

        primaryStage.setOnCloseRequest(e -> closeProgram());
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
