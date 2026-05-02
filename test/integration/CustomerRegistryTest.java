package integration;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import model.Customer;
import model.PhoneNumber;

public class CustomerRegistryTest {

@Test
public void testFindCustomerReturnsCustomer() {
    CustomerRegistry registry = new CustomerRegistry();

    Customer customer = registry.findCustomer(new PhoneNumber("123"));

    assertNotNull(customer);
}
}