package template;

import dto.RepairOrderDTO;
import model.RepairOrderObserver;

/**
 * Abstract template class for repair order observers.
 *
 * Uses the Template Method pattern to guarantee that
 * repair order updates are handled through the same flow.
 */
public abstract class RepairOrderObserverTemplate implements RepairOrderObserver {

    /**
     * Template method defined by the observer interface.
     *
     * @param orderDTO The updated repair order.
     */
    @Override
    public final void orderUpdated(RepairOrderDTO orderDTO) {
        processUpdate(orderDTO);
    }

    /**
     * Defines the fixed algorithm structure.
     * @param orderDTO The updated repair order.
     */
    private void processUpdate(RepairOrderDTO orderDTO) {
        try {
            doHandleRepairOrderUpdate(orderDTO);
        } catch (Exception exception) {
            handleErrors(exception);
        }
    }

    /**
     * Performs the observer specific update logic.
     *
     * @param orderDTO The updated repair order.
     * @throws Exception If update handling fails.
     */
    protected abstract void doHandleRepairOrderUpdate(RepairOrderDTO orderDTO)throws Exception;

    /**
     * Handles errors occurring during update processing.
     *
     * @param exception The exception that occurred.
     */
    protected abstract void handleErrors(Exception exception);
}