package exception;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Logs exception information to a file so that developers can inspect errors.
 */
public class ExceptionLogger {
    private static final String LOG_FILE = "bikerepair-error.log";

    /**
     * Writes the exception and a timestamp to the log file.
     *
     * @param e The exception to log.
     */
    public void log(Exception e) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println("[" + LocalDateTime.now() + "] " + e.getMessage());
            e.printStackTrace(writer);
            writer.println();
        } catch (IOException ioException) {
            System.err.println("Could not write to log file: " + ioException.getMessage());
        }
    }
}
