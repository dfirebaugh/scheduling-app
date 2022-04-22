package datastore;

public class CountryQueries extends AbstractQuery {
    public static String get() {
        return String.format("SELECT * FROM countries;");
    }
}
