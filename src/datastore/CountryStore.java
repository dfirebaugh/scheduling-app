package datastore;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Country;

public class CountryStore {
    public static ObservableList<Country> get() throws SQLException {
        ResultSet result = CountryQueries.executeQuery(CountryQueries.get());

        ObservableList<Country> countries = FXCollections.observableArrayList();
        while (result.next()) {
            countries.add(new Country(result));
        }
        return countries;
    }
}
