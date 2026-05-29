package view;

import dto.RepairOrderDTO;
import template.RepairOrderObserverTemplate;

/**
 * Observer that prints updated repair orders to the terminal.
 *
 * Technicians and receptionists receive repair order updates
 * through the Observer pattern.
 */
public class RepairOrderView extends RepairOrderObserverTemplate {

    private ErrorMessageHandler errorHandler = new ErrorMessageHandler();

    /**
     * Prints the updated repair order.
     *
     * @param orderDTO The updated repair order.
     */
    @Override
    protected void doHandleRepairOrderUpdate(RepairOrderDTO orderDTO) {
        System.out.println("[ORDER UPDATE] " + orderDTO);
    }

    /**
     * Handles observer related errors.
     *
     * @param exception The exception that occurred.
     */
    @Override
    protected void handleErrors(Exception exception) {
        errorHandler.showErrorMsg("Could not display repair order update.");
    }
}