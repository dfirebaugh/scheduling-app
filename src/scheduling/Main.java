package scheduling;

import javafx.application.Application;
import javafx.stage.Stage;
import scenes.SceneController;

public class Main extends Application {
    SceneController sm;
    @Override
    public void start(Stage primaryStage) throws Exception{
        sm = new SceneController(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
