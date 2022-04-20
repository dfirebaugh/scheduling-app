package datastore;

import models.Customer;

public class CustomerQueries extends AbstractQuery {
    public static String get() {
        return String.format("SELECT * FROM customers;");
    }
    public static String getById(Customer lookup) {
        return String.format("SELECT * FROM customers WHERE `Customer_ID` = %d;", lookup.getId());
    }
    public static String getByName(Customer lookup) {
        return String.format("SELECT * FROM customers WHERE `Customer_Name` = '%s';", lookup.getName());
    }
    public static String add(Customer customer) {
        return String.format(
            "INSERT INTO `client_schedule`.`customers` (`Customer_Name`,`Address`,`Postal_Code`,`Phone`,`Create_Date`,`Created_By`,`Last_Update`,`Last_Updated_By`,`Division_ID`) VALUES ('%s', '%s', '%s', '%s', NOW(), '%s', NOW(), '%s', %d);",
            customer.getName(),
            customer.getAddress(), customer.getPostalCode(),
            customer.getPhone(),
            customer.getCreatedBy(),
            customer.getLastUpdatedBy(), customer.getDivisionID());
    }
    public static String update(Customer update) {
        return String.format(
            "UPDATE `client_schedule`.`customers` SET `Customer_Name` = '%s', `Address` = '%s', `Postal_Code` = '%s', `Phone` = '%s', `Created_By` = '%s', `Last_Update` = NOW(), `Last_Updated_By` = '%s', `Division_ID` = %d WHERE `Customer_ID` = %d;",
            update.getName(),
            update.getAddress(), update.getPostalCode(),
            update.getPhone(),
            update.getCreatedBy(),
            update.getLastUpdatedBy(), update.getDivisionID(),
            update.getId());
    }
    public static String delete(Customer lookup) {
        return String.format(
            "DELETE FROM `client_schedule`.`customers` WHERE `Customer_ID` = %d;",
            lookup.getId());
    }
}
