package datastore;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Division;

public class DivisionStore extends AbstractStore {
    public static ObservableList<Division> get() throws SQLException {
        ResultSet result = DivisionQueries.executeQuery(DivisionQueries.get());

        ObservableList<Division> divisions = FXCollections.observableArrayList();
        while (result.next()) {
            divisions.add(new Division(result));
        }
        return divisions;
    }
    public static ObservableList<Division> get(Integer countryID) throws SQLException {
        ResultSet result = DivisionQueries.executeQuery(DivisionQueries.get(countryID));

        ObservableList<Division> divisions = FXCollections.observableArrayList();
        while (result.next()) {
            divisions.add(new Division(result));
        }
        return divisions;
    }
    public static Division getOne(Integer divisionID) throws SQLException {
        return new Division(getFirst(DivisionQueries.executeQuery(DivisionQueries.getOne(divisionID))));
    }
}
