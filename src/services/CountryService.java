package services;

import java.sql.SQLException;

import datastore.CountryStore;
import javafx.collections.ObservableList;
import models.Country;

public class CountryService {
    /**
     * get all countries
     * @return
     */
    public ObservableList<Country> get() {
        try {
            return CountryStore.get();
        } catch (SQLException e) {
            Logger.error(e);
            return null;
        }
    }
    /**
     * get a specific country based on ID
     * @param countryID
     * @return
     */
    public Country get(Integer countryID) {
        try {
            return CountryStore.get(countryID);
        } catch (SQLException e) {
            Logger.error(e);
            return null;
        }
    }

    public Country get(String countryName) {
        try {
            return CountryStore.get(countryName);
        } catch (SQLException e) {
            Logger.error(e);
            return null;
        }
    }
}
