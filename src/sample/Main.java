package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    Scene loginScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        GridPane layout = new GridPane();

        loginScene = new LoginScene(layout);

        primaryStage.setScene(loginScene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> closeProgram());
    }

    private void closeProgram() {
        System.out.println("closing...");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
