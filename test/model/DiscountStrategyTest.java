package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Tests the discount strategies.
 */
public class DiscountStrategyTest {

    /**
     * Verifies that NoDiscountStrategy returns the original cost unchanged.
     */
    @Test
    public void testNoDiscountReturnsFullCost() {
        DiscountStrategy strategy = new NoDiscountStrategy();
        assertEquals(1000, strategy.applyDiscount(1000),    "NoDiscountStrategy should return the original cost unchanged.");
    }

    /**
     * Verifies that LoyalCustomerDiscountStrategy reduces the cost by 10 percent.
     */
    @Test
    public void testLoyalCustomerDiscountReducesByTenPercent() {
        DiscountStrategy strategy = new LoyalCustomerDiscountStrategy();
        assertEquals(900, strategy.applyDiscount(1000),    "LoyalCustomerDiscountStrategy should reduce 1000 SEK to 900 SEK.");
    }

    /**
     * Verifies that loyal discount on 300 gives 270, so test it applies the 10 percent discount correctly.
     */
    @Test
    public void testLoyalCustomerDiscountOnThreeHundred() {
        DiscountStrategy strategy = new LoyalCustomerDiscountStrategy();
        assertEquals(270, strategy.applyDiscount(300),    "LoyalCustomerDiscountStrategy should reduce 300 SEK to 270 SEK.");
    }
}
