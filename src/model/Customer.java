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
     * Returns the customer name.
     *
     * @return Customer name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the customer email.
     *
     * @return Customer email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the customer phone number as text.
     *
     * @return Phone number string.
     */
    public String getPhone() {
        return phone.toString();
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