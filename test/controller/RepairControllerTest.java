package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import integration.CustomerRegistry;
import integration.Printer;
import integration.RepairOrderRegistry;
import model.OrderState;
import model.PhoneNumber;
import model.RepairOrder;
import model.SerialNumber;

/**
 * Tests the RepairController class.
 */
public class RepairControllerTest {

    /**
     * Verifies that createRepairOrder returns a created order.
     */
    @Test
    public void testCreateOrder() {
        RepairOrderRegistry registry = new RepairOrderRegistry();
        RepairController controller = new RepairController(
                new CustomerRegistry(),
                registry,
                new Printer()
        );

        RepairOrder order = controller.createRepairOrder(
                "test",
                new PhoneNumber("1"),
                new SerialNumber("1")
        );

        assertNotNull(order);
        assertEquals(OrderState.CREATED, order.getState());
        assertEquals(1, registry.getAllOrders().size());
    }

    /**
     * Verifies that acceptRepair changes state to ACCEPTED.
     */
    @Test
    public void testAcceptRepair() {
        RepairController controller = new RepairController(
                new CustomerRegistry(),
                new RepairOrderRegistry(),
                new Printer()
        );
        controller.createRepairOrder(
                "test",
                new PhoneNumber("1"),
                new SerialNumber("1")
        );
        RepairOrder acceptedOrder = controller.acceptRepair();
        assertEquals(OrderState.ACCEPTED, acceptedOrder.getState());
    }
}