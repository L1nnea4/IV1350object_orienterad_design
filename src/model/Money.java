package model;

/**
 * Represents an amount of money.
 */
public class Money {
    private final int amount;

    /**
     * Creates a money object.
     *
     * @param amount The amount in SEK.
     */
    public Money(int amount) {
        this.amount = amount;
    }

    /**
     * Returns the stored amount, not used in this solution but could be 
     * useful in a more complex implementation.
     *
     * @return The amount in SEK.
     */

    /*public int getAmount() {
        return amount;
    }*/

    /**
     * Returns amount as text.
     *
     * @return Text form of this money object.
     */
    @Override
    public String toString() {
        return amount + " SEK";
    }
}