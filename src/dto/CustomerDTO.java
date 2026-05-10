package dto;

import model.Customer;

/**
 * Data transfer object for customer information.
 * Used to pass customer data to the view without exposing model objects.
 */
public class CustomerDTO {
    private final String name;
    private final String email;
    private final String phone;

    /**
     * Creates a CustomerDTO from a Customer model object.
     *
     * @param customer The customer to copy data from.
     */
    public CustomerDTO(Customer customer) {
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
    }

    /**
     * Returns the customer name.
     *
     * @return Customer name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a text representation of the customer.
     *
     * @return Customer as text.
     */
    @Override
    public String toString() {
        return name + " (" + email + ", " + phone + ")";
    }
}
