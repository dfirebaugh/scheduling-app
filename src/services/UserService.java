package services;

import datastore.UserStore;

public class UserService {
    public boolean login(String username, String password) {
        boolean ok = UserStore.login(username, password);
        AuthLogService.loginAttempt(username, ok);
        return ok;
    }
}
