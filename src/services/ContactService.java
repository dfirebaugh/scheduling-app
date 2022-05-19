package services;

import java.sql.SQLException;

import datastore.ContactStore;
import javafx.collections.ObservableList;
import models.Contact;

public class ContactService {
    public ContactService() {}

    /**
     * get all contacts
     * @return
     */
    public ObservableList<Contact> get() {
        try {
            return ContactStore.get();
        } catch (SQLException e) {
            Logger.error(e);
            return null;
        }
    }

    /**
     * get a specific contact
     * @param lookup lookup is of `Contact` type. However, it will likely only have the ContactID populated.
     * @return
     */
    public Contact get(Contact lookup) {
        try {
            return ContactStore.get(lookup);
        } catch (SQLException e) {
            Logger.error(e);
            return null;
        }
    }
    /**
     * get a specific contact by contact ID
     * @param contactID
     * @return
     */
    public Contact get(Integer contactID) {
        try {
            return ContactStore.get(contactID);
        } catch (SQLException e) {
            Logger.error(e);
            return null;
        }
    }
}
