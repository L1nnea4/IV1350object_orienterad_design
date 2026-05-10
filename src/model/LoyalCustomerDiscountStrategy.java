package model;

/**
 * A discount strategy that gives loyal customers a 10 percent discount.
 * This is applied when a customer has had three or more repairs.
 */
public class LoyalCustomerDiscountStrategy implements DiscountStrategy {
    private static final double DISCOUNT_RATE = 0.10;

    /**
     * Returns the total cost reduced by ten percent.
     *
     * @param totalCost The original total cost.
     * @return The cost after a 10% reduction.
     */
    @Override
    public int applyDiscount(int totalCost) {
        return (int) (totalCost * (1 - DISCOUNT_RATE));
    }
}