package model;

/**
 * Represents a repair order id.
 */
public class OrderId {
    private static int counter = 0;
    private final int id;

    /**
     * Creates a unique order id.
     */
    public OrderId() {
        this.id = ++counter;
    }
    /**
     * Returns the id value.
     *
     * @return The id value.
     */
public int getValue() {
    return id;
}

    /**
     * Returns the id as text.
     *
     * @return The id value as string.
     */
    @Override
    public String toString() {
        return String.valueOf(id);
    }
}