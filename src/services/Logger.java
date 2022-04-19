package services;

public class Logger {
    public static int level = 0;
    public static int LogLevelDebug = 1;
    public static int LogLevelInfo = 2;
    public static int LogLevelError = 0;

    public static void info(String input) {
        if (Logger.level == Logger.LogLevelError) return;

        System.out.println("[INFO]: " + input);
    }
    public static void debug(String input) {
        if (Logger.level < Logger.LogLevelDebug) return;

        System.out.println("[DEBUG]: " + input);
    }
    public static void error(String input) {
        System.out.println("[ERROR]: " + input);
    }
    public static void print(String input) {
        System.out.println("[PRINT]: " + input);
    }
}
