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
     * Returns the amount value.
     *
     * @return Amount in SEK.
     */
    public int getAmount() {
        return amount;
    }

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