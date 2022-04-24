package datastore;

import models.Contact;

public class ContactQueries extends AbstractQuery {
    public static final String modifiedBy = "scheduling-app";
    public static String get() {
        return String.format("SELECT `contacts`.`Contact_ID`, `contacts`.`Contact_Name`, `contacts`.`Email` FROM `client_schedule`.`contacts`;");
        }
    public static String get(Contact lookup) {
        return String.format("SELECT `contacts`.`Contact_ID`, `contacts`.`Contact_Name`, `contacts`.`Email` FROM `client_schedule`.`contacts` WHERE `Contact_ID` = %d;", lookup.getID());
    }
    public static String get(Integer contactID) {
        return String.format("SELECT `contacts`.`Contact_ID`, `contacts`.`Contact_Name`, `contacts`.`Email` FROM `client_schedule`.`contacts` WHERE `Contact_ID` = %d;", contactID);
    }
}
