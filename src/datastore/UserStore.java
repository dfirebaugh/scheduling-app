package datastore;

import java.sql.SQLException;
import java.sql.ResultSet;

import models.User;

public class UserStore extends AbstractStore {
    public static User login(String username, String password) throws SQLException {
        User u = new User(username, password);
        ResultSet result = getFirst(UserQueries.executeQuery(UserQueries.get(u)));
        if (result == null) {
            return null;
        }
        return new User(result);
    }
}
