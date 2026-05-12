package integration;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.CustomerNotFoundException;
import exception.DatabaseFailureException;
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
    public void testFindCustomerReturnsCustomer() throws CustomerNotFoundException {
        Customer customer = registry.findCustomer(new PhoneNumber("0701234567"));
        assertNotNull(customer, "findCustomer should return a customer for a known phone number.");
    }

    /**
     * Verifies that an unknown phone number throws CustomerNotFoundException.
     */
    @Test
    public void testUnknownPhoneThrowsCustomerNotFoundException(){
        assertThrows(CustomerNotFoundException.class, () -> {
            registry.findCustomer(new PhoneNumber("0000000000"));
        },"Unknown phone numbers should throw CustomerNotFoundException.");
    }

    /**
     * Verifies that the database failure number throws DatabaseFailureException.
     */
    @Test
    public void testDatabaseFailureNumberThrowsDatabaseFailureException() {
        assertThrows(DatabaseFailureException.class, () -> {
            registry.findCustomer(new PhoneNumber("999999999"));
        },"Database failure number should throw DatabaseFailureException.");
    }
/**
 * Verifies that CustomerNotFoundException provides a not null error message.
 */
    @Test
    public void testCustomerNotFoundExceptionMessage() {
    CustomerNotFoundException exception =
        assertThrows(CustomerNotFoundException.class, () -> {
            registry.findCustomer(
                new PhoneNumber("0000000000")
            );
        }, "Unknown phone numbers should throw CustomerNotFoundException.");

    assertNotNull(exception.getMessage(),    "CustomerNotFoundException should contain an error message."
);
}

/**
 * Verifies that DatabaseFailureException
 * contains an error message.
 */
@Test
public void testDatabaseFailureExceptionMessage() {
    DatabaseFailureException exception =assertThrows(DatabaseFailureException.class,() -> registry.findCustomer(new PhoneNumber("999999999")));
    assertNotNull(exception.getMessage(), "DatabaseFailureException should contain an error message.");
}
}
