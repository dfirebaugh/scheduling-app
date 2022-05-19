package models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;

import services.Logger;

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
    private Timestamp start;
    private Timestamp end;
    private Timestamp createDate;
    private String createdBy;
    private Timestamp lastUpdated;
    private String lastUpdatedBy;
    private Integer customerID;
    private Integer userID;
    private Integer contactID;

    public Appointment() {
    }

    public Appointment(int id, String title, String description, String location, String type, Timestamp start, Timestamp end,
            Timestamp createDate, String createdBy, Timestamp lastUpdated, String lastUpdatedBy, int customerID, int userID,
            int contactID, Integer UserID) {
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
        this.userID = userID;
    }

    public Appointment(Integer id, String title, String description, String location, String type, Timestamp start, Timestamp end, Integer customerID, Integer contactID, Integer userID) {
        setID(id);
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.customerID = customerID;
        this.contactID = contactID;
        this.start = start;
        this.end = end;
        this.userID = userID;
    }

    public Appointment(String title, String description, String location, String type, Timestamp start, Timestamp end, Integer customerID, Integer contactID, Integer userID) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.customerID = customerID;
        this.contactID = contactID;
        this.start = start;
        this.end = end;
        this.userID = userID;
    }

    public Appointment(ResultSet result) {
        try {
            setID(result.getInt("Appointment_ID"));
            this.title = result.getString("Title");
            this.description = result.getString("Description");
            this.location = result.getString("Location");
            this.type = result.getString("Type");
            this.start = result.getTimestamp("Start");
            this.end = result.getTimestamp("End");
            this.createDate = result.getTimestamp("Create_Date");
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

    public String toString() {
        return String.format("appointment ID: %s, title: %s, description: %s, location: %s, type: %s, start: %s, end: %s, createDate: %s, createdBy: %s, lastUpdated: %s, lastUpdatedBy: %s, customerID: %s, userID: %s, contactID: %s", 
                this.id.toString(), this.title, this.description, this.location, this.type,
                this.start, this.end, this.createDate, this.createdBy, this.lastUpdated,
                this.lastUpdatedBy, this.customerID, this.userID, this.contactID);
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

    public String getCreateDate() {
        return formatDateTime(createDate);
    }

    public String getStart() {
        return formatDateTime(start);
    }
    public String getStartDate() {
        return formatDate(start);
    }
    public String getStartTime() {
        return formatTime(start);
    }
    public String getEndDate() {
        return formatDate(end);
    }
    public String getEndTime() {
        return formatTime(end);
    }

    public String getStartUTC() {
        return formatDateTimeUTC(start);
    }
    public String getEndUTC() {
        return formatDateTimeUTC(end);
    }

    public String getEnd() {
        return formatDateTime(end);
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getLastUpdated() {
        return formatDateTime(lastUpdated);
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

    private String formatDateTime(Timestamp timestamp) {
        DateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm" );
        return format.format( timestamp );
    }
    private String formatDateTimeUTC(Timestamp timestamp) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        ZonedDateTime z = timestamp.toInstant().atZone(ZoneId.of("UTC"));

        return z.format(fmt);
    }

    private String formatTime(Timestamp timestamp) {
        DateFormat format = new SimpleDateFormat( "HH:mm" );
        return format.format( timestamp );
    }
    private String formatDate(Timestamp timestamp) {
        DateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
        return format.format( timestamp );
    }

    private boolean isEntirelyAfter(Date s, Date e) {
        Date e1 = new Date(end.getTime());
        Date s1 = new Date(start.getTime());
        return s.after(s1) && s.after(e1) && e.after(e1);
    }
    private boolean isEntirelyBefore(Date s, Date e) {
        Date e1 = new Date(end.getTime());
        Date s1 = new Date(start.getTime());
        return s.before(s1) && s.before(e1) && e.before(s1);
    }

    public boolean IsOverlapping(Date s, Date e) {
        if (isEntirelyAfter(s, e)) {
            return false;
        }
        if (isEntirelyBefore(s, e)) {
            return false;
        }

        return true;
    }
}
