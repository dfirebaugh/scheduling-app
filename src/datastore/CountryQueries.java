package datastore;

public class CountryQueries extends AbstractQuery {
    public static String get() {
        return String.format("SELECT * FROM countries;");
    }
    public static String get(Integer countryID) {
        return String.format("SELECT * FROM countries WHERE `Country_ID` = %d;", countryID);
    }
    public static String get(String countryName) {
        return String.format("SELECT * FROM countries WHERE `Country` = '%s';", countryName);
    }
}
