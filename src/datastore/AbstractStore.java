package datastore;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractStore {
    public static ResultSet getFirst(ResultSet result) throws SQLException {
        while (result.next()) {
            return result;
        }

        return null;
    }
}
