package services;

import java.sql.SQLException;

import datastore.DivisionStore;
import javafx.collections.ObservableList;
import models.Division;

public class DivisionService {
    public ObservableList<Division> get() {
        try {
            return DivisionStore.get();
        } catch (SQLException e) {
            Logger.error(e);
            return null;
        }
    }
}
