package model;

import model.dto.RepairOrderDTO;

/**
 * Observer interface for receiving repair order updates.
 * Implement this interface to be notified when a repair order changes.
 */
public interface RepairOrderObserver {

    /**
     * Called when a repair order has been updated.
     *
     * @param orderDTO A snapshot of the updated repair order.
     */
    void orderUpdated(RepairOrderDTO orderDTO);
}
