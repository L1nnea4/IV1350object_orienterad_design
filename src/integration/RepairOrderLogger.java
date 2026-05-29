package integration;

import dto.RepairOrderDTO;
import template.RepairOrderObserverTemplate;

/**
 * Observer that writes repair order updates to a log file.
 */
public class RepairOrderLogger extends RepairOrderObserverTemplate {

    private final LogHandler logger = new LogHandler();

    /**
     * Writes the updated repair order to the log file.
     *
     * @param orderDTO The updated repair order.
     */
    @Override
    protected void doHandleRepairOrderUpdate(RepairOrderDTO orderDTO) {
        logger.logMessage(orderDTO.toString());
    }

    /**
     * Logs observer related failures.
     *
     * @param exception The exception that occurred.
     */
    @Override
    protected void handleErrors(Exception exception) {
        logger.logException(exception);
    }
}