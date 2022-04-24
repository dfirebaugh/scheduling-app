package models;

import java.util.Arrays;
import java.util.stream.Stream;
import java.sql.ResultSet;
import java.sql.SQLException;

import services.Logger;

public class Contact {
    private Integer id; // Contact_ID
    private String contactName; // Contact_Name
    private String email; // Email

    public Contact() {
    }

    public Contact(int id) {
        this.id = id;
    }

    public Contact(String name, String email) {
        this.contactName = name;
        this.email = email;
    }

    public Contact(ResultSet result) {
        try {
            this.id = result.getInt("Contact_ID"); // Contact_ID
            this.contactName = result.getString("Contact_Name"); // Contact_Name
            this.email = result.getString("Email"); // Email
        } catch (SQLException e) {
            Logger.error("SQLException: " + e.getMessage());
        }
    }

    public void print() {
        Logger.info(toString());
    }

    public String toString() {
        return contactName;
    }

    public static Stream<String> getKeys() {
        String[] keys = { "id", "contactName", "email" };
        return Arrays.stream(keys);
    }

    public String getName() {
        return contactName;
    }

    public Integer getID() {
        return id;
    }
    public String getIDProperty() {
        return id.toString();
    }

    public Integer getContactID() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
