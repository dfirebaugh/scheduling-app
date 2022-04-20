package scenes;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import models.Appointment;
import services.AppointmentService;
import services.Logger;

public class Home extends Scene {
    GridPane parent;
    SceneController sceneManger;
    private AppointmentService appointmentService;

    @FXML private TableView<Appointment> monthTable;

    public Home(GridPane p, SceneController sm, AppointmentService as) {
        super(p);
        parent = p;
        sceneManger = sm;
        appointmentService = as;
        parent.setMinWidth(300);
        parent.setMinHeight(275);

        try {
            load(parent);
            populateMonthTable();
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    private void load(GridPane parent) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        loader.setController(this);
        parent.add(loader.load(), 0,0);
    }

    private void populateMonthTable() {
        monthTable.setItems(this.appointmentService.get());
        monthTable.refresh();
    }
}
