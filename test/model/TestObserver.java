package model;

import dto.RepairOrderDTO;

/**
 * A simple test observer that counts how many times it has been notified.
 */
public class TestObserver implements RepairOrderObserver {

    private int callCount = 0;
    /**
     * Called when a repair order is updated.
     *
     * @param orderDTO The updated repair order.
     */
    @Override
    public void orderUpdated(RepairOrderDTO orderDTO) {
        callCount++;
    }
    /**
     * Returns how many times this observer has been notified.
     *
     * @return Number of received updates.
     */
    public int getCallCount() {
        return callCount;
    }
}