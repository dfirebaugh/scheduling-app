package datastore;

public class UserStore {
    public static boolean login(String username, String password) {
        return username.matches("test") && password.matches("test");
    }
}
