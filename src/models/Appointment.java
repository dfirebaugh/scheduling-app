package models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.stream.Stream;

// import javafx.beans.property.SimpleStringProperty;
// import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;

import java.util.TimeZone;

import services.Logger;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Appointment {
    private final IntegerProperty id = new SimpleIntegerProperty();

    public final IntegerProperty idProperty() {
        return id;
    }

    public final Integer getID() {
        return id.get();
    }

    public final void setID(Integer value) {
        id.set(value);
    }

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

    public Appointment() {
    }

    public Appointment(int id, String title, String description, String location, String type, Date start, Date end,
            Date createDate, String createdBy, Timestamp lastUpdated, String lastUpdatedBy, int customerID, int userID,
            int contactID) {
        setID(id);
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

    public Appointment(Integer id, String title, String description, String location, String type, String startDate,
            String startTime, String endDate, String endTime, Integer customerID, Integer contactID) {
        setID(id);
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.customerID = customerID;
        this.contactID = contactID;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Logger.info(startDate + " " + startTime);

        try {
            java.util.Date parsedStart = sdf.parse(sdf.format(startDate + " " + startTime));
            java.util.Date parsedEnd = sdf.parse(sdf.format(endDate + " " + endTime));
            this.start = new Date(parsedStart.getTime());
            this.end = new Date(parsedEnd.getTime());
        } catch (java.text.ParseException e) {
            Logger.error(e);
        }
        // this.userID = userID;
    }

    public Appointment(String title, String description, String location, String type, String startDate,
            String startTime, String endDate, String endTime, Integer customerID, Integer contactID) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.customerID = customerID;
        this.contactID = contactID;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Logger.info(startDate + " " + startTime);
        String dateString = "2020-05-29 12:00";
        Logger.info(dateString);
        java.util.Date parsedStart;
        java.util.Date parsedEnd;
        try {
            parsedStart = sdf.parse(sdf.format(dateString));
            parsedEnd = sdf.parse(sdf.format(dateString));
        } catch (java.text.ParseException e) {
            Logger.error(e);
            return;
        }
        this.start = new Date(parsedStart.getTime());
        this.end = new Date(parsedEnd.getTime());
        // this.userID = userID;
    }

    public Appointment(ResultSet result) {
        try {
            setID(result.getInt("Appointment_ID"));
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
        Logger.info(this.id + " " + this.title + " " + this.description + " " + this.location + " " + this.type + " "
                + this.start + " " + this.end + " " + this.createDate + " " + this.createdBy + " " + this.lastUpdated
                + " " + this.lastUpdatedBy + " " + this.customerID + " " + this.userID + " " + this.contactID);
    }

    public static Stream<String> getKeys() {
        String[] keys = { "id", "title", "description", "location", "type", "start", "end", "createDate", "createdBy",
                "lastUpdated", "lastUpdatedBy", "customerID", "userID", "contactID" };
        return Arrays.stream(keys);
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

    public String getStartDate() {
        return start.toString();
    }

    public String getStartTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return sdf.parse(sdf.format(start)).toString();
        } catch (java.text.ParseException e) {
            Logger.error(e);
        }

        return null;
    }

    public Date getEnd() {
        return end;
    }

    public String getEndDate() {
        return end.toString();
    }

    public String getEndTime() {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return sdf.parse(sdf.format(end)).toString();
        } catch (java.text.ParseException e) {
            Logger.error(e);
        }
        return null;
    }

    public String getCreateDate() {
        return createDate.toLocalDate().toString();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getLastUpdated() {
        return lastUpdated.toString();
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public String getUserID() {
        return userID.toString();
    }

    public Integer getContactID() {
        return contactID;
    }
}
