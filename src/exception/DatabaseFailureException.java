package exception;

/**
 * Thrown when the database cannot be reached, for example because the server is down.
 * This is an unchecked exception because it indicates a programming or infrastructure
 * error that cannot reasonably be recovered from in normal program flow.
 */
public class DatabaseFailureException extends RuntimeException {

    /**
     * Creates a new instance with the phone number that triggered the failure.
     *
     * @param phone The phone number that was searched when the failure occurred.
     */
    public DatabaseFailureException(String phone) {
        super("Database failure when searching for phone number: " + phone);
    }
}
