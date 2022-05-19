package services;

import java.sql.SQLException;

import datastore.DivisionStore;
import javafx.collections.ObservableList;
import models.Division;

public class DivisionService {
    /**
     * get all divisions
     * @return
     */
    public ObservableList<Division> get() {
        try {
            return DivisionStore.get();
        } catch (SQLException e) {
            Logger.error(e);
            return null;
        }
    }
    /**
     * get divisions of a country based on countryID
     * @param countryID
     * @return
     */
    public ObservableList<Division> get(Integer countryID) {
        try {
            return DivisionStore.get(countryID);
        } catch (SQLException e) {
            Logger.error(e);
            return null;
        }
    }
    /**
     * get a specific division based on divisionID
     * @param divisionID
     * @return
     */
    public Division getOne(Integer divisionID) {
        try {
            return DivisionStore.getOne(divisionID);
        } catch (SQLException e) {
            Logger.error(e);
            return null;
        }
    }
}
