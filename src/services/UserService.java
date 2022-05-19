package services;

import java.sql.SQLException;

import datastore.UserStore;
import models.User;

public class UserService {
    private User loggedInUser;

    /**
     * handle user login
     * @param username
     * @param password
     * @return
     */
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

    /**
     * gets the current logged in user
     * @return
     */
    public User getCurrentLoggedInUser() {
        return loggedInUser;
    }

    /**
     * handles user logout
     */
    public void logout() {
        loggedInUser = null;
    }
}
