package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import services.Logger;

public class Division {
    private Integer id;
    private String division;
    private Date createDate;
    private String createdBy;
    private Date lastUpdate;
    private String lastUpdatedBy;
    private Integer countryID;

    public Division(int id) {
        this.id = id;
    }

    public Division(String division) {
        this.division = division;
    }

    public Division(
        Integer id,
        String division,
        Date createDate,
        String createdBy,
        Date lastUpdated,
        String lastUpdatedBy,
        Integer countryID) {
            this.id = id;
            this.division = division;
            this.createDate = createDate;
            this.createdBy = createdBy;
            this.lastUpdate = lastUpdated;
            this.lastUpdatedBy = lastUpdatedBy;
            this.countryID = countryID;
    }

    public Division(ResultSet result) {
        try {
            this.id = result.getInt("Division_ID");
            this.division = result.getString("Division");
            this.createDate = result.getDate("Create_Date");
            this.createdBy = result.getString("Created_By");
            this.lastUpdate = result.getDate("Last_Update");
            this.lastUpdatedBy = result.getString("Last_Updated_By");
            this.countryID = result.getInt("Country_ID");
        } catch (SQLException e) {
            Logger.error("SQLException: " + e.getMessage());
        }
    }

    public void print() {
        Logger.info(
            id + " " +
            division + " " +
            createDate + " " +
            createdBy + " " +
            lastUpdate + " " +
            lastUpdatedBy + " " +
            countryID);
    }

    /**
     * @return the id
     */
    public Integer getID() {
        return this.id;
    }

    public String getDivision() {
        return this.division;
    };

    public Date getCreateDate() {
        return this.createDate;
    };

    public String getCreatedBy() {
        return this.createdBy;
    };

    public Date getLastUpdate() {
        return this.lastUpdate;
    };

    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    };
    public Integer getCountryID() {
        return this.countryID;
    };


    @Override
    public String toString() {
        return this.getDivision();
    }
}
