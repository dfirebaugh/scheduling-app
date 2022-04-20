package datastore;

import models.Customer;

public class CustomerQueries extends AbstractQuery {
    public static String get() {
        return String.format("SELECT * FROM customers;");
    }
    public static String getById(Customer lookup) {
        return String.format("SELECT * FROM customers WHERE `Customer_ID` = %d;", lookup.id);
    }
    public static String getByName(Customer lookup) {
        return String.format("SELECT * FROM customers WHERE `Customer_Name` = '%s';", lookup.name);
    }
    public static String add(Customer customer) {
        return String.format(
            "INSERT INTO `client_schedule`.`customers` (`Customer_Name`,`Address`,`Postal_Code`,`Phone`,`Create_Date`,`Created_By`,`Last_Update`,`Last_Updated_By`,`Division_ID`) VALUES ('%s', '%s', '%s', '%s', NOW(), '%s', NOW(), '%s', %d);",
            customer.name,
            customer.address, customer.postalCode,
            customer.phone,
            customer.createdBy,
            customer.lastUpdatedBy, customer.divisionID);
    }
    public static String update(Customer update) {
        return String.format(
            "UPDATE `client_schedule`.`customers` SET `Customer_Name` = '%s', `Address` = '%s', `Postal_Code` = '%s', `Phone` = '%s', `Created_By` = '%s', `Last_Update` = NOW(), `Last_Updated_By` = '%s', `Division_ID` = %d WHERE `Customer_ID` = %d;",
            update.name,
            update.address, update.postalCode,
            update.phone,
            update.createdBy,
            update.lastUpdatedBy, update.divisionID,
            update.id);
    }
    public static String delete(Customer lookup) {
        return String.format(
            "DELETE FROM `client_schedule`.`customers` WHERE `Customer_ID` = %d;",
            lookup.id);
    }
}
