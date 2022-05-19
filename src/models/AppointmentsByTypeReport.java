
package models;

import services.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AppointmentsByTypeReport {
    String Type;
    Integer Count;
    Integer Month;
    public AppointmentsByTypeReport(String t, Integer c, Integer m) {
        Type = t;
        Count = c;
        Month = m;
    }

    public AppointmentsByTypeReport(ResultSet result) {
        try {
            Type = result.getString("Type");
            Count = result.getInt("Count");
            Month = result.getInt("Month");
        } catch (SQLException e) {
            Logger.error("SQLException: " + e.getMessage());
        }
    }
    public String toString() {
        return String.format("type: %s, count: %d, month: %d", Type, Count, Month);
    }
}
