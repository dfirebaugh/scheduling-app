package datastore;

import models.User;

public class UserQueries extends AbstractQuery {
    public static final String modifiedBy = "scheduling-app";
    public static String get() {
        return String.format("SELECT `users`.`User_ID`, `users`.`User_Name`, `users`.`Password`, `users`.`Create_Date`, `users`.`Created_By`, `users`.`Last_Update`, `users`.`Last_Updated_By` FROM `client_schedule`.`users`;");
    }
    public static String getByID(User user) {
        return String.format("SELECT `users`.`User_ID`, `users`.`User_Name`, `users`.`Password`, `users`.`Create_Date`, `users`.`Created_By`, `users`.`Last_Update`, `users`.`Last_Updated_By` FROM `client_schedule`.`users` WHERE `USER_ID` = %d;", user.getID());
    }
    public static String get(User user) {
        return String.format("SELECT `users`.`User_ID`, `users`.`User_Name`, `users`.`Password` FROM `client_schedule`.`users` WHERE `User_Name` = '%s' && `Password` = '%s';", user.getName(), user.getPassword());
    }
}
