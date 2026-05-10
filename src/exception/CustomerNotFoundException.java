package exception;

/**
 * Thrown when a customer with the given phone number does not exist in the registry.
 * This is a checked exception because the caller must decide how to handle a missing customer.
 */
public class CustomerNotFoundException extends Exception {

    /**
     * Creates a new instance with the phone number that was not found.
     *
     * @param phone The phone number that did not match any customer.
     */
    public CustomerNotFoundException(String phone) {
        super("No customer found with phone number: " + phone);
    }
}
