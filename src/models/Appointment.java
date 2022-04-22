package models;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.stream.Stream;

import services.Logger;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Appointment {
    private Integer id;
    private String title;
    private String description;
    private String location;
    private String type;
    private Date start;
    private Date end;
    private Date createDate;
    private String createdBy;
    private Timestamp lastUpdated;
    private String lastUpdatedBy;
    private Integer customerID;
    private Integer userID;
    private Integer contactID;

    public Appointment(int id, String title, String description, String location, String type, Date start, Date end,
            Date createDate, String createdBy, Timestamp lastUpdated, String lastUpdatedBy, int customerID, int userID,
            int contactID) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    public Appointment(ResultSet result) {
        try {
            this.id = result.getInt("Appointment_ID");
            this.title = result.getString("Title");
            this.description = result.getString("Description");
            this.location = result.getString("Location");
            this.type = result.getString("Type");
            this.start = result.getDate("Start");
            this.end = result.getDate("End");
            this.createDate = result.getDate("Create_Date");
            this.createdBy = result.getString("Created_By");
            this.lastUpdated = result.getTimestamp("Last_Update");
            this.lastUpdatedBy = result.getString("Last_Updated_By");
            this.customerID = result.getInt("Customer_ID");
            this.userID = result.getInt("User_ID");
            this.contactID = result.getInt("Contact_ID");
        } catch (SQLException e) {
            Logger.error("SQLException: " + e.getMessage());
        }
    }

    public void print() {
        Logger.info(
                this.id + " " +
                        this.title + " " +
                        this.description + " " +
                        this.location + " " +
                        this.type + " " +
                        this.start + " " +
                        this.end + " " +
                        this.createDate + " " +
                        this.createdBy + " " +
                        this.lastUpdated + " " +
                        this.lastUpdatedBy + " " +
                        this.customerID + " " +
                        this.userID + " " +
                        this.contactID);
    }

    public static Stream<String> getKeys() {
        String[] keys = {
                "id",
                "title",
                "description",
                "location",
                "type",
                "start",
                "end",
                "createDate",
                "createdBy",
                "lastUpdated",
                "lastUpdatedBy",
                "customerID",
                "userID",
                "contactID"
        };
        return Arrays.stream(keys);
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public Integer getUserID() {
        return userID;
    }

    public Integer getContactID() {
        return contactID;
    }
}
