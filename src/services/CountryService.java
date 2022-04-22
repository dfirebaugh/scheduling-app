package services;

import java.sql.SQLException;

import datastore.CountryStore;
import javafx.collections.ObservableList;
import models.Country;

public class CountryService {
    public ObservableList<Country> get() {
        try {
            return CountryStore.get();
        } catch (SQLException e) {
            Logger.error(e);
            return null;
        }
    }
}
