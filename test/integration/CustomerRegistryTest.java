package integration;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Customer;
import model.PhoneNumber;

/**
 * Tests the CustomerRegistry class.
 */
public class CustomerRegistryTest {

    private CustomerRegistry registry;

    /**
     * Creates a new CustomerRegistry object before each test.
     */
    @BeforeEach
    public void setUp() {
        registry = new CustomerRegistry();
    }

    /**
     * Clears the registry reference after each test.
     */
    @AfterEach
    public void tearDown() {
        registry = null;
    }

    /**
     * Verifies that findCustomer returns a customer.
     */
    @Test
    public void testFindCustomerReturnsCustomer() {
        Customer customer = registry.findCustomer(new PhoneNumber("123"));
        assertNotNull(customer);
    }
}