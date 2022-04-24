package datastore;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Contact;

public class ContactStore extends AbstractStore {
    public static ObservableList<Contact> get() throws SQLException {
        ResultSet result = CustomerQueries.executeQuery(ContactQueries.get());

        ObservableList<Contact> customers = FXCollections.observableArrayList();
        while (result.next()) {
            customers.add(new Contact(result));
        }
        return customers;
    }
    public static Contact get(Contact lookup) throws SQLException {
        if (lookup.getContactID() == 0 && lookup.getName().length() > 0)
            return ContactStore.getByName(lookup);

        return ContactStore.get(lookup);
    }
    public static Contact get(Integer contactID) throws SQLException {
        return new Contact(getFirst(ContactQueries.executeQuery(ContactQueries.get(contactID))));
    }

    public static Contact getByName(Contact lookup) throws SQLException {
        return new Contact(getFirst(ContactQueries.executeQuery(ContactQueries.get(lookup))));
    }
}
