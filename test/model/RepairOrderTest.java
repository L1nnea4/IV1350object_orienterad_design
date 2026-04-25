package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Tests the RepairOrder class.
 */
public class RepairOrderTest {

    /**
     * Verifies that accept changes state to ACCEPTED.
     */
    @Test
    public void testAcceptChangesState() {
        RepairOrder order = new RepairOrder(
            new OrderId(),
            "Problem",
            new PhoneNumber("123"),
            new SerialNumber("ABC"),
            new Customer("Test", "a@b.com", new PhoneNumber("123"))
        );

        order.accept();

        assertEquals(OrderState.ACCEPTED, order.getState());
    }

    /**
     * Verifies that diagnostics and tasks are stored.
     */
    @Test
    public void testAddDiagnosticAndTask() {
        RepairOrder order = new RepairOrder(
            new OrderId(),
            "Problem",
            new PhoneNumber("123"),
            new SerialNumber("ABC"),
            new Customer("Test", "a@b.com", new PhoneNumber("123"))
        );
        order.addDiagnosticResult(new DiagnosticResult("Flat tire"));
        order.addRepairTask(new RepairTask("Tire repair", "Patch rear tire", new Money(250)));
        assertEquals(1, order.getNumberOfDiagnostics());
        assertEquals(1, order.getNumberOfTasks());
    }
}