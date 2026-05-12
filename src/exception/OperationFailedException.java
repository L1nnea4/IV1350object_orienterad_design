package exception;

/**
 * Exception thrown when an operation fails due to an underlying issue.
 * This is a checked exception because the caller must decide how to handle the failure.
 */
public class OperationFailedException extends Exception {
        /**
        * Creates a new instance with a message and the original cause.
        *
        * @param msg The error message
        * @param cause The original exception that caused the failure.
        */
    public OperationFailedException(String msg, Exception cause) {
        super(msg, cause);
    }
}