package integration;

import java.util.ArrayList;
import java.util.List;

import model.RepairOrder;

/**
 * Stores repair orders in memory.
 */
public class RepairOrderRegistry {
    private final List<RepairOrder> orders = new ArrayList<>();

    /**
     * Saves one repair order.
     *
     * @param order The order to store.
     */
    public void save(RepairOrder order) {
        orders.add(order);
    }

    /**
     * Returns all stored repair orders.
     *
     * @return A copy of all stored orders.
     */
    public List<RepairOrder> getAllOrders() {
        return new ArrayList<>(orders);
    }
}