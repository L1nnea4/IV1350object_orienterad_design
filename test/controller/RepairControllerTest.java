package controller;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dto.RepairOrderDTO;
import exception.CustomerNotFoundException;
import exception.DatabaseFailureException;
import integration.CustomerRegistry;
import integration.Printer;
import integration.RepairOrderRegistry;
import model.DiagnosticResult;
import model.LoyalCustomerDiscountStrategy;
import model.Money;
import model.OrderState;
import model.PhoneNumber;
import model.RepairTask;
import model.SerialNumber;

/**
 * Tests the RepairController class.
 */
public class RepairControllerTest {

    private RepairController controller;
    private RepairOrderRegistry registry;

    /**
     * Creates a new RepairController and its dependencies before each test
     * to ensure that tests do not share state.
     */
    @BeforeEach
    public void setUp() {
        registry = RepairOrderRegistry.getInstance();
        registry.clear();

        controller = new RepairController(
                new CustomerRegistry(),
                registry,
                new Printer()
        );
    }

    /**
     * Drops references to the controller and registry after each test.
     */
    @AfterEach
    public void tearDown() {
        controller = null;
        registry = null;
    }

    /**
     * Verifies that createRepairOrder returns a created order.
     */
    @Test
    public void testCreateOrderReturnsCreatedOrder() throws CustomerNotFoundException {
        RepairOrderDTO order = controller.createRepairOrder(
                "test", new PhoneNumber("0701234567"), new SerialNumber("1"));
        assertNotNull(order);
        assertEquals(OrderState.CREATED, order.getState());
        assertEquals(1, registry.getAllOrders().size());
    }

    /**
     * Verifies that acceptRepair changes the order state to ACCEPTED.
     */
    @Test
    public void testAcceptRepairChangesStateToAccepted() throws CustomerNotFoundException {
        controller.createRepairOrder(
                "test", new PhoneNumber("0701234567"), new SerialNumber("1"));
        RepairOrderDTO accepted = controller.acceptRepair();
        assertEquals(OrderState.ACCEPTED, accepted.getState());
    }

    /**
     * Verifies that rejectRepair changes the order state to REJECTED.
     */
    @Test
    public void testRejectRepairChangesStateToRejected() throws CustomerNotFoundException {
        controller.createRepairOrder(
                "test", new PhoneNumber("0701234567"), new SerialNumber("1"));
        RepairOrderDTO rejected = controller.rejectRepair();
        assertEquals(OrderState.REJECTED, rejected.getState());
    }

    /**
     * Verifies that adding diagnostic result works.
     */
    @Test
    public void testAddDiagnosticResult() throws CustomerNotFoundException {
        controller.createRepairOrder(
                "test",
                new PhoneNumber("0701234567"),
                new SerialNumber("1")
        );

        RepairOrderDTO order = controller.addDiagnosticResult(
                new DiagnosticResult("Test diag")
        );

        assertEquals(1, order.getNumberOfDiagnostics());
    }

    /**
     * Verifies that adding repair task works.
     */
    @Test
    public void testAddRepairTask() throws CustomerNotFoundException {
        controller.createRepairOrder(
                "test",
                new PhoneNumber("0701234567"),
                new SerialNumber("1")
        );

        RepairOrderDTO order = controller.addRepairTask(
                new RepairTask("Task", "Desc", new Money(100))
        );

        assertEquals(1, order.getNumberOfTasks());
    }

/**
     * Verifies that an unknown phone number throws CustomerNotFoundException.
     */
    @Test
    public void testUnknownPhoneThrowsCustomerNotFoundException() {
        assertThrows(CustomerNotFoundException.class, () -> {
            controller.findCustomer(new PhoneNumber("0000000000"));
        });
    }

    /**
     * Verifies that the database failure number throws DatabaseFailureException.
     */
    @Test
    public void testDatabaseFailureThrowsDatabaseFailureException() {
        assertThrows(DatabaseFailureException.class, () -> {
            controller.findCustomer(new PhoneNumber("999999999"));
        });
    }

    /**
     * Verifies that total cost is calculated correctly.
     */
    @Test
    public void testTotalCostAfterAddingTasks() throws CustomerNotFoundException {
        controller.createRepairOrder(
                "test", new PhoneNumber("0701234567"), new SerialNumber("1"));
        controller.addRepairTask(new RepairTask("T1", "Desc", new Money(200)));
        controller.addRepairTask(new RepairTask("T2", "Desc", new Money(300)));
        RepairOrderDTO order = controller.acceptRepair();
        assertEquals(500, order.getTotalCost());
    }
    /**
     * Verifies that operations fail if no repair order exists.
    */
    @Test
    public void testAcceptRepairWithoutOrderThrowsException() {
        assertThrows(IllegalStateException.class, () -> {
            controller.acceptRepair();
        });
    }

    /**
     * Verifies that loyal customer discount reduces the total cost.
     */
    @Test
    public void testDiscountStrategyReducesTotalCost()
            throws CustomerNotFoundException {

        controller.createRepairOrder(
                "test",
                new PhoneNumber("0701234567"),
                new SerialNumber("1")
        );

        controller.addRepairTask(
                new RepairTask("Task", "Desc", new Money(1000))
        );

        controller.setDiscountStrategy(
                new LoyalCustomerDiscountStrategy()
        );

        RepairOrderDTO order = controller.acceptRepair();

        assertEquals(900, order.getTotalCost());
    }
}
