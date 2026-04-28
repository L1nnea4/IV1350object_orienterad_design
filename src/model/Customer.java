package model;

/**
 * Represents a customer.
 */
public class Customer {
    private final String name;
    private final String email;
    private final PhoneNumber phone;

    /**
     * Creates a customer object.
     *
     * @param name Customer name.
     * @param email Customer email.
     * @param phone Customer phone number.
     */
    public Customer(String name, String email, PhoneNumber phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Returns customer as text.
     *
     * @return Text form of customer.
     */
    @Override
    public String toString() {
        return name + " (" + email + ", " + phone + ")";
    }
}