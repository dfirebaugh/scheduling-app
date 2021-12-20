package datastore;

public class InMemory implements DataStore {
        @Override
        public boolean login(String username, String password) {
            return username.matches("test") && password.matches("test");
        }
}
