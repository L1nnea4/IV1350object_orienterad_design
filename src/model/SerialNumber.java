package model;

/**
 * Represents a bike serial number.
 */
public class SerialNumber {
    private final String serial;

    /**
     * Creates a serial number object.
     *
     * @param serial The serial number value.
     */
    public SerialNumber(String serial) {
        this.serial = serial;
    }

    /**
     * Returns serial number as text.
     *
     * @return The serial number text.
     */
    public String toString() {
        return serial;
    }
}