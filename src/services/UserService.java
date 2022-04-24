package services;

import java.sql.SQLException;

import datastore.UserStore;
import models.User;

public class UserService {
    private User loggedInUser;

    public boolean login(String username, String password) {
        try {
            loggedInUser = UserStore.login(username, password);

            boolean ok = loggedInUser != null;
            AuthLogService.loginAttempt(username, ok);

            return ok;
        } catch (SQLException e) {
            Logger.error(e);
        }
        return false;
    }

    public User getCurrentLoggedInUser() {
        return loggedInUser;
    }

    public void logout() {
        loggedInUser = null;
    }
}
