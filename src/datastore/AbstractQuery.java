package datastore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AbstractQuery {
    public static ResultSet executeQuery(String query) throws SQLException {
        PreparedStatement stmt = JDBC.getConnection().prepareStatement(query);
        return stmt.executeQuery();
    }
    public static int executeUpdate(String query) throws SQLException {
        PreparedStatement stmt = JDBC.getConnection().prepareStatement(query);
        return stmt.executeUpdate();
    }
}
