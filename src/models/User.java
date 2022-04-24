package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import services.Logger;

public class User {
    private Integer id;       // User_ID
    private String name;     // User_Name
    private String password; // Password

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(ResultSet result) {
        try {
            this.id = result.getInt("User_ID"); // Contact_ID
            this.name = result.getString("User_Name"); // Contact_Name
            this.password = result.getString("Password"); // Email
        } catch (SQLException e) {
            Logger.error("SQLException: " + e.getMessage());
        }
    }

    public Integer getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
