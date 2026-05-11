package integration;

import java.util.ArrayList;
import java.util.List;

import dto.RepairOrderDTO;
import model.OrderId;

/**
 * Stores repair orders in memory.
 */
public class RepairOrderRegistry {
    private final List<RepairOrderDTO> orders = new ArrayList<>();

    /**
     * Saves a snapshot of the given repair order as a DTO.
     *
     * @param order The order to store.
     */
    public void save(RepairOrderDTO order) {
        orders.add(order);
    }

    /**
     * Finds one order by id.
     *
     * @param id The id to search for.
     * @return The matching order DTO, or null if not found.
     */
public RepairOrderDTO findById(OrderId id) {
    for (RepairOrderDTO order : orders) {
        if (order.getOrderId() == id.getValue()) {
            return order;
        }
    }
    return null;
}

    /**
     * Returns all stored repair orders.
     *
     * @return A copy of all stored order DTOs.
     */
    public List<RepairOrderDTO> getAllOrders() {
        return new ArrayList<>(orders);
    }
}