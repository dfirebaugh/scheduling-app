package scenes;

import datastore.DataStore;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Home extends Scene {
    SceneController sceneManger;
    DataStore dataStore;
    public Home(GridPane parent, datastore.DataStore ds, SceneController sm) {
        super(parent);
        sceneManger = sm;
        dataStore = ds;
        parent.setMinWidth(300);
        parent.setMinHeight(275);

        initNodes(parent);
    }

    private void initNodes(GridPane parent) {
        var someLabel = new Label("home");

        parent.setAlignment(Pos.CENTER);
        parent.add(someLabel, 0,0);
    }
}
