package services;

import java.sql.SQLException;

import datastore.ContactStore;
import javafx.collections.ObservableList;
import models.Contact;

public class ContactService {
    public ContactService() {}

    public ObservableList<Contact> get() {
        try {
            return ContactStore.get();
        } catch (SQLException e) {
            Logger.error(e);
            return null;
        }
    }

    public Contact get(Contact lookup) {
        try {
            return ContactStore.get(lookup);
        } catch (SQLException e) {
            Logger.error(e);
            return null;
        }
    }
    public Contact get(Integer contactID) {
        try {
            return ContactStore.get(contactID);
        } catch (SQLException e) {
            Logger.error(e);
            return null;
        }
    }
}
