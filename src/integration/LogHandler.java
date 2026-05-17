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
            System.err.println("Could not open log file.");
        }
    }

    /**
     * Logs a normal message.
     *
     * @param message The message to log.
     */
    public void logMessage(String message) {
        logFile.println("[" + createTime() + "] " + " Message: " + message);
        logFile.flush();
    }
    
    /**
     * Writes exception information to the log file.
     *
     * @param exception The exception to log.
     */
    public void logException(Exception exception) {
      logFile.println("[" + createTime() + "] " + "Exception: "+ exception.getMessage());
        exception.printStackTrace(logFile);
        logFile.println();
        logFile.flush();
    }

    /**
     * Creates formatted current time.
     *
     * @return Current time.
     */
    private String createTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
        return currentTime.format(formatter);
    }
}