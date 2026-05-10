package model;

/**
 * A discount strategy that applies no discount.
 * Used as the default strategy when no discount is given.
 */
public class NoDiscountStrategy implements DiscountStrategy {

    /**
     * Returns the total cost unchanged.
     *
     * @param totalCost The original total cost.
     * @return The same total cost with no reduction.
     */
    @Override
    public int applyDiscount(int totalCost) {
        return totalCost;
    }
}
