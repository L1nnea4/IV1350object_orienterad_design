package view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Handles error messages shown to the user.
 */
public class ErrorMessageHandler {

    /**
     * Shows the specified error message.
     *
     * @param message The error message.
     */

    public void showErrorMsg(String message) {
        System.out.println("[" + createTime() + "] "+ "[ERROR] " + message);
    }

    /**
     * Creates formatted current time.
     *
     * @return Current time as text.
     */
    private String createTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter =
                DateTimeFormatter.ofLocalizedTime(
                        FormatStyle.MEDIUM);
        return currentTime.format(formatter);
    }
}