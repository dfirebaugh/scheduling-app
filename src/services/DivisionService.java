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
    public ObservableList<Division> get(Integer countryID) {
        try {
            return DivisionStore.get(countryID);
        } catch (SQLException e) {
            Logger.error(e);
            return null;
        }
    }
}
