package datastore;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Customer;

public class CustomerStore {
    public static ObservableList<Customer> get() throws SQLException {
        ResultSet result = CustomerQueries.executeQuery(CustomerQueries.get());

        ObservableList<Customer> customers = FXCollections.observableArrayList();
        while (result.next()) {
            customers.add(new Customer(result));
        }
        return customers;
    }

    public static Customer get(Customer lookup) throws SQLException {
        if (lookup.id == 0 && lookup.name.length() > 0)
            return CustomerStore.getByName(lookup);

        return CustomerStore.getByID(lookup);
    }

    public static Customer getByID(Customer lookup) throws SQLException {
        ResultSet result = CustomerQueries.executeQuery(CustomerQueries.getById(lookup));

        while (result.next()) {
            return new Customer(result);
        }
        return null;
    }

    public static Customer getByName(Customer lookup) throws SQLException {
        ResultSet result = CustomerQueries.executeQuery(CustomerQueries.getByName(lookup));

        while (result.next()) {
            return new Customer(result);
        }

        return null;
    }

    public static int add(Customer customer) throws SQLException {
        return CustomerQueries.executeUpdate(CustomerQueries.add(customer));
    }

    public static int update(Customer update) throws SQLException {
        return CustomerQueries.executeUpdate(CustomerQueries.update(update));
    }

    public static int delete(Customer customer) throws SQLException {
        Customer lookup = CustomerStore.get(customer);

        return CustomerQueries.executeUpdate(CustomerQueries.delete(lookup));
    }
}
