package view;

import dto.RepairOrderDTO;
import model.RepairOrderObserver;

/**
 * Observer that prints updated repair orders to the terminal.
 * Technicians and receptionists see live updates this way.
 * This class never calls the controller; it only receives updates through the Observer pattern.
 */
public class RepairOrderView implements RepairOrderObserver {

    /**
     * Called automatically when a repair order is updated.
     * Prints the current state of the order to System.out.
     *
     * @param orderDTO A snapshot of the updated order.
     */
    @Override
    public void orderUpdated(RepairOrderDTO orderDTO) {
        System.out.println("[ORDER UPDATE] " + orderDTO);
    }
}
