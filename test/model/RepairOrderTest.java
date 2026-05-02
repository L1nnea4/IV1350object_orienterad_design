package model;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the RepairOrder class.
 */
public class RepairOrderTest {

    private RepairOrder order;

    /**
     * creates a fresh RepairOrder object before each test so tests do not share state
     */
    @BeforeEach
    public void setUp() {
        order = new RepairOrder(
            new OrderId(),
            "Problem",
            new PhoneNumber("123"),
            new SerialNumber("ABC"),
            new Customer("Test", "a@b.com", new PhoneNumber("123"))
        );
    }

    /**
     * Clears references after each test so the next test starts clean.
     */
    @AfterEach
    public void tearDown() {
        order = null;
    }

    /**
     * Verifies that accept changes state to ACCEPTED.
     */
    @Test
    public void testAcceptChangesState() {
        order.accept();
        assertEquals(OrderState.ACCEPTED, order.getState());
    }

    /**
    * Checks that adding one diagnostic increases the diagnostic count to one.
    */
    @Test
    public void testAddDiagnosticResult() {
        order.addDiagnosticResult(new DiagnosticResult("Flat tire"));
        assertEquals(1, order.getNumberOfDiagnostics());
    }

    /**
     *Checks that adding one repair task increases the task count to one.
     */
    @Test
    public void testAddRepairTask() {
        order.addRepairTask(new RepairTask("Tire repair", "Patch rear tire", new Money(250)));
        assertEquals(1, order.getNumberOfTasks());
    }
    /**
     * Verifies that multiple tasks are stored correctly.
     */
    @Test
    public void testMultipleTasks() {
        order.addRepairTask(new RepairTask("Task1", "Desc", new Money(100)));
        order.addRepairTask(new RepairTask("Task2", "Desc", new Money(200)));

        assertEquals(2, order.getNumberOfTasks());
    }
}