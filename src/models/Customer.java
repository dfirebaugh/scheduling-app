package models;

import java.sql.Timestamp;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import services.Logger;

public class Customer {
    private Integer id;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private Date createDate;
    private String createdBy;
    private Timestamp lastUpdated;
    private String lastUpdatedBy;
    private Integer divisionID;

    public Customer(int id) {
        this.id = id;
    }

    public Customer(String name) {
        this.name = name;
    }

    public Customer(
            Integer id,
            String name,
            String address,
            String postalCode,
            String phone,
            Date createDate,
            String createdBy,
            Timestamp lastUpdated,
            String lastUpdatedBy,
            Integer divisionID) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
    }

    public Customer(ResultSet result) {
        try {
            this.id = result.getInt("Customer_ID");
            this.name = result.getString("Customer_Name");
            this.address = result.getString("Address");
            this.postalCode = result.getString("Postal_Code");
            this.phone = result.getString("Phone");
            this.createDate = result.getDate("Create_Date");
            this.createdBy = result.getString("Created_By");
            this.lastUpdated = result.getTimestamp("Last_Update");
            this.lastUpdatedBy = result.getString("Last_Updated_By");
            this.divisionID = result.getInt("Division_ID");
        } catch (SQLException e) {
            Logger.error("SQLException: " + e.getMessage());
        }
    }

    public void print() {
        Logger.info(
                this.id + " " +
                        this.name + " " +
                        this.address + " " +
                        this.postalCode + " " +
                        this.phone + " " +
                        this.createDate + " " +
                        this.createdBy + " " +
                        this.lastUpdated + " " +
                        this.lastUpdatedBy + " " +
                        this.divisionID);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    };

    public String getAddress() {
        return this.address;
    };

    public String getPostalCode() {
        return this.postalCode;
    };

    public String getPhone() {
        return this.phone;
    };

    public Date getCreateDate() {
        return this.createDate;
    };

    public String getCreatedBy() {
        return this.createdBy;
    };

    public Timestamp getLastUpdated() {
        return this.lastUpdated;
    };

    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    };

    public Integer getDivisionID() {
        return this.divisionID;
    };

}
