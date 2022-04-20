package models;

import java.sql.Timestamp;

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
            this.id = result.getInt("Customer_ID");
            this.title = result.getString("Customer_ID");
            this.description = result.getString("Customer_ID");
            this.location = result.getString("Customer_ID");
            this.type = result.getString("Customer_ID");
            this.start = result.getDate("Customer_ID");
            this.end = result.getDate("Customer_ID");
            this.createDate = result.getDate("Customer_ID");
            this.createdBy = result.getString("Customer_ID");
            this.lastUpdated = result.getTimestamp("Customer_ID");
            this.lastUpdatedBy = result.getString("Customer_ID");
            this.customerID = result.getInt("Customer_ID");
            this.userID = result.getInt("Customer_ID");
            this.contactID = result.getInt("Customer_ID");
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
