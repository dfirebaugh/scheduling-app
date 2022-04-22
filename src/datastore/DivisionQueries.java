package datastore;

public class DivisionQueries extends AbstractQuery {
    public static String get() {
        return String.format("SELECT * FROM first_level_divisions;");
    }
}
