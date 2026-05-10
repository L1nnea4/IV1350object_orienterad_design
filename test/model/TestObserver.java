package model;

import model.dto.RepairOrderDTO;

/**
 * A simple test observer that counts how many times it has been called.
 */
public class TestObserver implements RepairOrderObserver {
    private int callCount = 0;

    /**
     * Records that an update was received.
     *
     * @param orderDTO The updated order DTO.
     */
    @Override
    public void orderUpdated(RepairOrderDTO orderDTO) {
        callCount++;
    }

    /**
     * Returns how many times orderUpdated has been called.
     *
     * @return The call count.
     */
    public int getCallCount() {
        return callCount;
    }
}
