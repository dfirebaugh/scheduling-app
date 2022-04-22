package services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthLogService {
    private static void write(final String s) throws IOException {
        try (FileWriter fw = new FileWriter("login_activity.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(s);
        } catch (IOException e) {
            Logger.error(e);
        }
    }

    public static void loginAttempt(String username, boolean wasSuccessful) {
        try {
            AuthLogService.write(String.format("[%s: login-attempt] username: %s, successful: %b",
            new java.util.Date(), username, wasSuccessful));
        } catch (IOException e) {
            Logger.error(e);
        }
    }
}
