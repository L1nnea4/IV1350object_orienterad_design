package model;

/**
 * Strategy interface for calculating repair discounts.
 * Different discount strategies can be swapped without changing the order logic.
 */
public interface DiscountStrategy {

    /**
     * Calculates the discounted price from the given total cost.
     *
     * @param totalCost The original total cost in SEK.
     * @return The price after applying the discount, in SEK.
     */
    int applyDiscount(int totalCost);
}
