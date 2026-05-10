package integration;

import java.util.ArrayList;
import java.util.List;

import dto.RepairOrderDTO;
import model.OrderId;
import model.RepairOrder;

/**
 * Stores repair orders in memory. This class is a Singleton because there must
 * only be one storage for orders in the application.
 */
public class RepairOrderRegistry {
    private static RepairOrderRegistry instance;
    private final List<RepairOrderDTO> orders = new ArrayList<>();

    /**
     * Returns the only instance of this class.
     *
     * @return The singleton instance of RepairOrderRegistry.
     */
    public static RepairOrderRegistry getInstance() {
        if (instance == null) {
            instance = new RepairOrderRegistry();
        }
        return instance;
    }

    /**
     * Saves a snapshot of the given repair order as a DTO.
     *
     * @param order The order to store.
     */
    public void save(RepairOrder order) {
        orders.add(new RepairOrderDTO(order));
    }

    /**
     * Finds one order by id.
     *
     * @param id The id to search for.
     * @return The matching order DTO, or null if not found.
     */
    public RepairOrderDTO findById(OrderId id) {
        for (RepairOrderDTO dto : orders) {
            if (dto.getOrderId() == id.getValue()) {
                return dto;
        }
    }
    return null;
}

    /**
     * Returns all stored repair order DTOs.
     *
     * @return A copy of all stored order DTOs.
     */
    public List<RepairOrderDTO> getAllOrders() {
        return new ArrayList<>(orders);
    }
}