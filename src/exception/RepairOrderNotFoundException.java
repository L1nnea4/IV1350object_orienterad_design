package exception;

import model.OrderId;

/**
 * Exception thrown when a repair order with the specified id does not exist.
 */
public class RepairOrderNotFoundException extends Exception {
    
        /**
        * Creates a new instance with the id of the missing order.
        *
        * @param id id The missing repair order id.
        */
    
    public RepairOrderNotFoundException(OrderId id) {
        super("Repair order " + id + " was not found.");
    }
}