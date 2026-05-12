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
     * @param msg The error message.
     */
    public void showErrorMsg(String msg) {

        StringBuilder builder = new StringBuilder();
        builder.append(createTime());
        builder.append(" ERROR: ");
        builder.append(msg);
        System.out.println(builder);
    }

    /**
     * Creates formatted current time.
     *
     * @return Current time as text.
     */
    private String createTime() {

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofLocalizedTime(
                        FormatStyle.MEDIUM);

        return now.format(formatter);
    }
}