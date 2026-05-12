package integration;

import dto.RepairOrderDTO;
import model.RepairOrderObserver;

/**
 * Observer that writes repair order updates to a log file.
 * This class never calls the controller, it only receives updates through the Observer pattern.
 */
public class RepairOrderLogger implements RepairOrderObserver {
    private final LogHandler logger;

   /**
     * Creates a new logger observer.
     */
    public RepairOrderLogger() {
        logger = new LogHandler();
    }

    /**
     * Called automatically when a repair order is updated.
     * Writes the current order state to the log file.
     *
     * @param orderDTO A snapshot of the updated order.
     */
    @Override
    public void orderUpdated(RepairOrderDTO orderDTO) {
        logger.logMessage(orderDTO.toString());
    }
}
