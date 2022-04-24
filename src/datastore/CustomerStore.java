package datastore;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Customer;
import services.Logger;

public class CustomerStore extends AbstractStore {
    public static ObservableList<Customer> get() throws SQLException {
        ResultSet result = CustomerQueries.executeQuery(CustomerQueries.get());

        ObservableList<Customer> customers = FXCollections.observableArrayList();
        while (result.next()) {
            customers.add(new Customer(result));
        }
        return customers;
    }

    public static Customer get(Customer lookup) throws SQLException {
        if (lookup.getID() == 0 && lookup.getName().length() > 0)
            return CustomerStore.getByName(lookup);

        return CustomerStore.getByID(lookup);
    }
    public static Customer get(Integer customerID) throws SQLException {
        return new Customer(getFirst(CustomerQueries.executeQuery(CustomerQueries.getById(customerID))));
    }
    public static Customer getByID(Customer lookup) throws SQLException {
        return new Customer(getFirst(CustomerQueries.executeQuery(CustomerQueries.getById(lookup))));
    }

    public static Customer getByName(Customer lookup) throws SQLException {
        return new Customer(getFirst(CustomerQueries.executeQuery(CustomerQueries.getByName(lookup))));
    }

    public static int add(Customer customer) throws SQLException {
        return CustomerQueries.executeUpdate(CustomerQueries.add(customer));
    }

    public static int update(Customer update) throws SQLException {
        String query = CustomerQueries.update(update);
        Logger.info(query);
        return CustomerQueries.executeUpdate(query);
    }

    public static int delete(Customer customer) throws SQLException {
        Customer lookup = CustomerStore.get(customer);

        return CustomerQueries.executeUpdate(CustomerQueries.delete(lookup));
    }
}
