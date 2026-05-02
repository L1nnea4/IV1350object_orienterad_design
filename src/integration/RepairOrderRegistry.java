package integration;

import java.util.ArrayList;
import java.util.List;

import model.OrderId;
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
     * Finds one order by id.
     *
     * @param id The id to search for.
     * @return The order with the given id, or null if not found.
     */
public RepairOrder findById(OrderId id) {
    for (RepairOrder order : orders) {
        if (order.getId().getValue() == id.getValue()) {
            return order;
        }
    }
    return null;
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