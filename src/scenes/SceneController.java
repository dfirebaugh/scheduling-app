package scenes;

import datastore.DataStore;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SceneController {
    Stage primaryStage;
    public Scene loginScene;
    public Scene homeScene;
    DataStore inmemory;

    public SceneController(Stage ps) {
        GridPane layout = new GridPane();
        inmemory = new datastore.InMemory();

        primaryStage = ps;
        primaryStage.setTitle("Hello World");

        loginScene = new Login(layout, inmemory, this);

        setScene(loginScene);

        primaryStage.setOnCloseRequest(e -> closeProgram());
    }

    public void switchToHome() {
        GridPane layout = new GridPane();
        homeScene = new Home(layout, inmemory, this);

        primaryStage.setScene(homeScene);
        primaryStage.show();
    }

    public void setScene(Scene scene) {
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void closeProgram() {
        System.out.println("closing...");
    }
}
