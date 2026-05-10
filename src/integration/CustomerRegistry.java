package integration;

import exception.CustomerNotFoundException;
import exception.DatabaseFailureException;
import model.Customer;
import model.PhoneNumber;

/**
 * Represents customer data storage.
 */
public class CustomerRegistry {
    private static final String DB_FAILURE_NUMBER = "999999999";
    private static final String KNOWN_CUSTOMER_NUMBER = "0701234567";

    /**
     * Finds a customer by phone number.
     * Throws an exception if the number does not exist or if a database error occurs.
     *
     * @param phone The phone number to search for.
     * @return The found customer.
     * @throws CustomerNotFoundException if no customer has the given phone number.
     * @throws DatabaseFailureException  if the database cannot be reached.
     */
    public Customer findCustomer(PhoneNumber phone) throws CustomerNotFoundException {
        String number = phone.toString();
        if (number.equals(DB_FAILURE_NUMBER)) {
            throw new DatabaseFailureException(number);
        }
        if (!number.equals(KNOWN_CUSTOMER_NUMBER)) {
            throw new CustomerNotFoundException(number);
        }
        return new Customer("Linnea", "test@mail.com", phone);
    }
}