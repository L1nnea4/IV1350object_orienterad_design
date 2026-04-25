package integration;

import model.Customer;
import model.PhoneNumber;

/**
 * Represents customer data storage.
 */
public class CustomerRegistry {
    /**
     * Finds a customer by phone number.
     *
     * @param phone The phone number to search for.
     * @return A matching customer.
     */
    public Customer findCustomer(PhoneNumber phone) {
        return new Customer("Linnea", "test@mail.com", phone);
    }
}