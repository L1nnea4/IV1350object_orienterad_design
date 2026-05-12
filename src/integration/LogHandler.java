package integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Handles exception logging.
 */
public class LogHandler {

    private static final String LOG_FILE_NAME = "repair-log.txt";
    private PrintWriter logFile;

    /**
     * Creates a new log handler.
     */
    public LogHandler() {
        try {
            logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME, true));
        } catch (IOException exc) {
            System.out.println("Could not open log file.");
        }
    }

    /**
     * Logs a normal message.
     *
     * @param msg The message to log.
     */
    public void logMessage(String msg) {
        logFile.println(createTime() + ", Message: " + msg);
        logFile.flush();
    }

    /**
     * Logs the specified exception.
     *
     * @param exception The exception to log.
     */
    public void logException(Exception exception) {
        StringBuilder builder = new StringBuilder();
        builder.append(createTime());
        builder.append(", Exception was thrown: ");
        builder.append(exception.getMessage());
        logFile.println(builder);
        exception.printStackTrace(logFile);
        logFile.flush();
    }

    /**
     * Creates formatted current time.
     *
     * @return Current time.
     */
    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}