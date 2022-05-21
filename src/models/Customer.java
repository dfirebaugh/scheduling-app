package models;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.stream.Stream;

// import javafx.beans.property.SimpleStringProperty;
// import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import services.Logger;

public class Customer {
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

    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private Date createDate;
    private String createdBy;
    private Timestamp lastUpdated;
    private String lastUpdatedBy;
    private Integer divisionID;
    private String country;
    private String division;

    public Customer(int id) {
        setID(id);
    }

    public Customer(String name) {
        this.name = name;
    }

    public Customer(Integer id, String name, String address, String postalCode, String phone, Integer divisionID) {
        setID(id);
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionID = divisionID;
    }

    public Customer() {
    }

    public Customer(String name, String address, String postalCode, String phone, Integer divisionID) {
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createdBy = "scheduling-app";
        this.lastUpdatedBy = "scheduling-app";
        this.divisionID = divisionID;
    }

    public Customer(Integer id, String name, String address, String postalCode, String phone) {
        setID(id);
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createdBy = "scheduling-app";
        this.lastUpdatedBy = "scheduling-app";
    }

    public Customer(ResultSet result) {
        try {
            setID(result.getInt("Customer_ID"));
            this.name = result.getString("Customer_Name");
            this.address = result.getString("Address");
            this.postalCode = result.getString("Postal_Code");
            this.phone = result.getString("Phone");
            this.createDate = result.getDate("Create_Date");
            this.createdBy = result.getString("Created_By");
            this.lastUpdated = result.getTimestamp("Last_Update");
            this.lastUpdatedBy = result.getString("Last_Updated_By");
            this.divisionID = result.getInt("Division_ID");
            this.country = result.getString("Country");
            this.division = result.getString("Division");
        } catch (SQLException e) {
            Logger.error("SQLException: " + e.getMessage());
        }
    }

    public void print() {
        Logger.info(toString());
    }

    public String toString() {
        return String.format("name: %s, country: %s, division: %d", name, country, divisionID);
    }

    public static Stream<String> getKeys() {
        String[] keys = { "id", "name", "address", "postalCode", "phone", "createDate", "createdBy", "lastUpdated",
                "lastUpdatedBy", "divisionID", "country", "division" };
        return Arrays.stream(keys);
    }

    public String getIdProperty() {
        return id.toString();
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

    public String getCountry() {
        return this.country;
    }

    public String getDivision() {
        return this.division;
    }

    public String getDivisionIDProperty() {
        return this.divisionID.toString();
    };
}
