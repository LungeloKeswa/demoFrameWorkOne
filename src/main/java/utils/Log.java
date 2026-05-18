package utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Log {

    // logger object
    private static final Logger loggerTwo = Logger.getLogger(Log.class.getName());

    static {

        try {

            // create a log file
            FileHandler fileHandler = new FileHandler("test-out.log", true);

            // attach file to logger
            loggerTwo.addHandler(fileHandler);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // logging functions
    public static void info(String message) {
        loggerTwo.info(message);
    }

    public static void warn(String message) {
        loggerTwo.warning(message);
    }

    public static void error(String message) {
        loggerTwo.severe(message);
    }

}
