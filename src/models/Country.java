package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import services.Logger;

public class Country {
    private Integer id; // Country_ID
    private String country; // Country
    private Date createDate; // Create_Date
    private String createdBy; // Created_By
    private Date lastUpdate; // Last_Update
    private String lastUpdatedBy; // Last_Updated_By

    public Country(Integer id) {
        this.id = id;
    }

    public Country(String country) {
        this.country = country;
    }

    public Country(Integer id, String country, Date createDate, String createdBy, Date lastUpdate,
            String lastUpdatedBy) {
        this.id = id;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Country(ResultSet result) {
        try {
            this.id = result.getInt("Country_ID");
            this.country = result.getString("Country");
            this.createDate = result.getDate("Create_Date");
            this.createdBy = result.getString("Created_By");
            this.lastUpdate = result.getDate("Last_Update");
            this.lastUpdatedBy = result.getString("Last_Updated_By");
        } catch (SQLException e) {
            Logger.error("SQLException: " + e.getMessage());
        }
    }

    public void print() {
        Logger.info(id + " " + country + " " + createDate + " " + createdBy + " " + lastUpdate + " " + lastUpdatedBy);
    }

    public Integer getID() {
        return this.id;
    }

    public String getCountry() {
        return this.country;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Date getLastUpdate() {
        return this.lastUpdate;
    }

    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    @Override
    public String toString() {
        return this.getCountry();
    }
}
