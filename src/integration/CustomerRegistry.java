package integration;

import model.*;

public class CustomerRegistry {

    public Customer findCustomer(PhoneNumber phone) {
        return new Customer("John Doe", "test@mail.com", phone);
    }
}