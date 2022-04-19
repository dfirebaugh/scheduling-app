package datastore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Customer;
import services.Logger;

public class CustomerStore {
    public static Customer getByID(Customer lookup) {
        try {
            String query = String.format("SELECT * FROM customers WHERE `Customer_ID` = %d;", lookup.id);
            PreparedStatement stmt = JDBC.getConnection().prepareStatement(query);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                return new Customer(result);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return null;
    }
    public static Customer getByName(Customer lookup) {
        try {
            String query = String.format("SELECT * FROM customers WHERE `Customer_Name` = '%s';", lookup.name);
            PreparedStatement stmt = JDBC.getConnection().prepareStatement(query);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                return new Customer(result);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return null;
    }
    public static Customer get(Customer lookup) {
        if (lookup.id == 0 && lookup.name.length() > 0)
            return CustomerStore.getByName(lookup);

        return CustomerStore.getByID(lookup);
    }
    public static boolean add(Customer customer) {
        try {
            String query = String.format(
                "INSERT INTO `client_schedule`.`customers` (`Customer_Name`,`Address`,`Postal_Code`,`Phone`,`Create_Date`,`Created_By`,`Last_Update`,`Last_Updated_By`,`Division_ID`) VALUES ('%s', '%s', '%s', '%s', NOW(), '%s', NOW(), '%s', %d);", 
                customer.name,
                customer.address, customer.postalCode,
                customer.phone, 
                customer.createdBy, 
                customer.lastUpdatedBy, customer.divisionID
            );

            Logger.info("instert query:" + query);

            PreparedStatement stmt = JDBC.getConnection().prepareStatement(query);
            int affected = stmt.executeUpdate();
            Logger.info(String.format("%d", affected));
            return affected > 0;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return false;
    }
    public static boolean update(Customer update) {
        try {
            String query = String.format(
                "UPDATE `client_schedule`.`customers` SET `Customer_Name` = '%s', `Address` = '%s', `Postal_Code` = '%s', `Phone` = '%s', `Created_By` = '%s', `Last_Update` = NOW(), `Last_Updated_By` = '%s', `Division_ID` = %d WHERE `Customer_ID` = %d;", 
                update.name, 
                update.address, update.postalCode, 
                update.phone,
                update.createdBy,
                update.lastUpdatedBy, update.divisionID,
                update.id
            );

            PreparedStatement stmt = JDBC.getConnection().prepareStatement(query);
            int affected = stmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }

        return false;
    }
    public static boolean delete(Customer customer) {
        try {
            Customer lookup = CustomerStore.get(customer);
            String query = String.format(
                "DELETE FROM `client_schedule`.`customers` WHERE `Customer_ID` = %d;", 
                lookup.id
            );

            PreparedStatement stmt = JDBC.getConnection().prepareStatement(query);
            int affected = stmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return false;
    }
}
