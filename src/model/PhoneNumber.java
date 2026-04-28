package model;

/**
 * Represents a phone number.
 */
public class PhoneNumber {
    private final String number;

    /**
     * Creates a phone number object.
     *
     * @param number The phone number value.
     */
    public PhoneNumber(String number) {
        this.number = number;
    }

    /**
     * Returns phone number as text.
     *
     * @return The phone number text.
     */
    @Override
    public String toString() {
        return number;
    }
}