package model;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the RepairOrder class.
 */
public class RepairOrderTest {

    private RepairOrder order;

    /**
     * Creates a fresh RepairOrder object before each test so tests do not share state.
     */
    @BeforeEach
    public void setUp() {

        Bike bike = new Bike("Brand", "Model", new SerialNumber("ABC"));
        Customer customer = new Customer("Test", "a@b.com", new PhoneNumber("123"));
        order = new RepairOrder(
            new OrderId(),
            "Problem",
            customer,
            bike
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
        assertEquals(OrderState.ACCEPTED, order.getState(),    "accept should change the order state to ACCEPTED.");
    }

    /**
     * Verifies that reject changes state to REJECTED.
     */
    @Test
    public void testRejectChangesState() {
        order.reject();
        assertEquals(OrderState.REJECTED, order.getState(),    "reject should change the order state to REJECTED.");
    }

    /**
     * Checks that adding one diagnostic increases the count to one.
    */
    @Test
    public void testAddDiagnosticResult() {
        order.addDiagnosticResult(new DiagnosticResult("Flat tire"));
        assertEquals(1, order.getNumberOfDiagnostics(),    "Adding one diagnostic result should increase the diagnostic count to one.");
    }

    /**
     * Checks that adding one repair task increases the task count to one.
     */
    @Test
    public void testAddRepairTask() {
        order.addRepairTask(new RepairTask("Tire repair", "Patch rear tire", new Money(250)));
        assertEquals(1, order.getNumberOfTasks(),    "Adding one repair task should increase the task count to one.");
    }
    /**
     * Verifies that multiple tasks are stored correctly.
     */
    @Test
    public void testMultipleTasks() {
        order.addRepairTask(new RepairTask("Task1", "Desc", new Money(100)));
        order.addRepairTask(new RepairTask("Task2", "Desc", new Money(200)));

        assertEquals(2, order.getNumberOfTasks(),    "Adding two repair tasks should result in two stored tasks.");
    }
    /**
    * Verifies that a new repair order has total cost zero.
    */
    @Test
    public void testEmptyOrderHasZeroTotalCost() {
        assertEquals(0, order.getTotalCost(),    "A new repair order should have total cost zero.");
    }

    /**
     * Verifies that getTotalCost returns the sum of all task costs.
     */
    @Test
    public void testTotalCostWithNoDiscount() {
        order.addRepairTask(new RepairTask("T1", "Desc", new Money(100)));
        order.addRepairTask(new RepairTask("T2", "Desc", new Money(200)));
        assertEquals(300, order.getTotalCost(),    "getTotalCost should return the sum of all repair task costs.");
    }

    /**
     * Verifies that a loyal customer discount strategy reduces the total.
     */
    @Test
    public void testLoyalCustomerDiscountReducesCost() {
        order.addRepairTask(new RepairTask("T1", "Desc", new Money(1000)));
        order.setDiscountStrategy(new LoyalCustomerDiscountStrategy());
        assertEquals(900, order.getTotalCost(),    "Loyal customer discount should reduce the total cost by 10 percent.");
    }

    /**
     * Verifies that an observer is notified when a task is added.
     */
    @Test
    public void testObserverIsNotified() {
        TestObserver observer = new TestObserver();
        order.addObserver(observer);
        order.addRepairTask(new RepairTask("Task", "Desc", new Money(100)));
        assertEquals(1, observer.getCallCount(),    "Observer should be notified when a repair task is added.");
    }
    /**
    * Verifies that an observer is notified when the order is accepted.
    */
    @Test
    public void testObserverIsNotifiedOnAccept() {
        TestObserver observer = new TestObserver();
        order.addObserver(observer);
        order.accept();
        assertEquals(1, observer.getCallCount(),    "Observer should be notified when the order is accepted.");
    }
    /**
    * Verifies that an observer is notified when a diagnostic result is added.
    */
    @Test
    public void testObserverIsNotifiedOnDiagnostic() {
        TestObserver observer = new TestObserver();
        order.addObserver(observer);
        order.addDiagnosticResult(
            new DiagnosticResult("Flat tire"));
        assertEquals(1, observer.getCallCount(),    "Observer should be notified when a diagnostic result is added.");
    }

    /**
     * Verifies that state does not change if no operation is called.
     */
    @Test
    public void testInitialStateIsCreated() {
        assertEquals(OrderState.CREATED, order.getState(),    "A newly created repair order should have state CREATED.");
    }

    /**
     * Verifies that adding null repair task throws exception.
     */
    @Test
    public void testAddNullRepairTaskThrowsException() {
        assertThrows(IllegalArgumentException.class,() -> order.addRepairTask(null),"Adding null repair task should throw IllegalArgumentException.");
    }

    /**
     * Verifies that adding null diagnostic throws exception.
    */
    @Test
    public void testAddNullDiagnosticThrowsException() {
        assertThrows(IllegalArgumentException.class,() -> order.addDiagnosticResult(null),"Adding null diagnostic should throw IllegalArgumentException.");
    }

    /**
     * Verifies that null discount strategy throws exception.
    */
    @Test
    public void testNullDiscountStrategyThrowsException() {
        assertThrows(IllegalArgumentException.class,() -> order.setDiscountStrategy(null), "Null discount strategy should throw IllegalArgumentException." );
    }

    /**
     * Verifies that repair order state does not change
     * when exception is thrown.
     */
    @Test
    public void testStateNotChangedWhenExceptionThrown() {
        int before = order.getNumberOfTasks();
        try {
            order.addRepairTask(null);
        } catch (IllegalArgumentException exc) {
            // expected exception
        }
        int after = order.getNumberOfTasks();
        assertEquals(before, after, "Order state should not change when exception is thrown.");
    }
}
