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
import exception.OperationFailedException;
import integration.CustomerRegistry;
import integration.Printer;
import integration.RepairOrderRegistry;
import model.DiagnosticResult;
import model.LoyalCustomerDiscountStrategy;
import model.Money;
import model.OrderId;
import model.OrderState;
import model.PhoneNumber;
import model.RepairTask;
import model.SerialNumber;
import model.TestObserver;

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
        assertNotNull(order, "createRepairOrder should return a RepairOrderDTO");
        assertEquals(OrderState.CREATED, order.getState(), "Newly created order should have state CREATED");
        assertEquals(1, registry.getAllOrders().size(), "Registry should contain exactly one order after creation");
    }

    /**
     * Verifies that acceptRepair changes the order state to ACCEPTED.
     */
    @Test
    public void testAcceptRepairChangesStateToAccepted() throws CustomerNotFoundException {
        controller.createRepairOrder(
                "test", new PhoneNumber("0701234567"), new SerialNumber("1"));
        RepairOrderDTO accepted = controller.acceptRepair();
        assertEquals(OrderState.ACCEPTED, accepted.getState(), "acceptRepair should change the order state to ACCEPTED.");
    }

    /**
     * Verifies that rejectRepair changes the order state to REJECTED.
     */
    @Test
    public void testRejectRepairChangesStateToRejected() throws CustomerNotFoundException {
        controller.createRepairOrder(
                "test", new PhoneNumber("0701234567"), new SerialNumber("1"));
        RepairOrderDTO rejected = controller.rejectRepair();
        assertEquals(OrderState.REJECTED, rejected.getState(), "rejectRepair should change the order state to REJECTED.");
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

        assertEquals(1, order.getNumberOfDiagnostics(), "Order should have one diagnostic result after adding it.");
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

        assertEquals(1, order.getNumberOfTasks(), "Order should have one repair task after adding it.");
    }

/**
     * Verifies that an unknown phone number throws CustomerNotFoundException.
     */
    @Test
    public void testUnknownPhoneThrowsCustomerNotFoundException() {
        assertThrows(CustomerNotFoundException.class, () -> {
            controller.findCustomer(new PhoneNumber("0000000000"));
        },"Unknown phone numbers should throw CustomerNotFoundException.");
    }

    /**
     * Verifies that the database failure number throws DatabaseFailureException.
     */
    @Test
    public void testDatabaseFailureThrowsDatabaseFailureException() {
        assertThrows(DatabaseFailureException.class, () -> {
            controller.findCustomer(new PhoneNumber("999999999"));
        },"Database failure number should throw DatabaseFailureException.");
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
        assertEquals(500, order.getTotalCost(), "Total cost should be 500 after adding two repair tasks.");
    }
    /**
     * Verifies that operations fail if no repair order exists.
    */
    @Test
    public void testAcceptRepairWithoutOrderThrowsException() {
        assertThrows(IllegalStateException.class, () -> {
            controller.acceptRepair();
        }, "Operations without an active repair order should throw IllegalStateException.");
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

        assertEquals(900, order.getTotalCost(), "Total cost should be 900 with loyal customer discount.");
    }

    /**
     * Verifies that current order is not changed
     * if creating a new order fails.
     */
    @Test
    public void testCurrentOrderNotChangedWhenExceptionThrown()throws CustomerNotFoundException {
        PhoneNumber validPhone = new PhoneNumber("0701234567");
        SerialNumber serial = new SerialNumber("BIKE1");
        controller.createRepairOrder("Brake issue", validPhone, serial);
        RepairOrderDTO before = controller.acceptRepair();
        try {
            controller.createRepairOrder("Problem", new PhoneNumber("0000000000"),serial);
        } catch (CustomerNotFoundException exc) {
            assertNotNull(exc, "Expected CustomerNotFoundException not thrown.");
        }
        RepairOrderDTO after = controller.acceptRepair();
        assertEquals(before.getOrderId(), after.getOrderId(), "Current order should not change if creating a new order fails.");
    }

    /**
     * Verifies that adding null task throws exception.
     */
    @Test
    public void testAddNullTaskThrowsException()throws CustomerNotFoundException {
        controller.createRepairOrder("test", new PhoneNumber("0701234567"), new SerialNumber("1"));
        assertThrows( IllegalArgumentException.class, () -> controller.addRepairTask(null), "Adding null task should throw IllegalArgumentException." );
    }

    /**
    * Verifies that adding null diagnostic throws exception.
    */
    @Test
    public void testAddNullDiagnosticThrowsException() throws CustomerNotFoundException {
        controller.createRepairOrder("test", new PhoneNumber("0701234567"), new SerialNumber("1") );
        assertThrows( IllegalArgumentException.class, () -> controller.addDiagnosticResult(null),"Adding null diagnostic should throw IllegalArgumentException." );
    }
    
    /**
    * Verifies that null discount strategy throws exception.
    */
    @Test
    public void testNullDiscountStrategyThrowsException() throws CustomerNotFoundException {
        controller.createRepairOrder("test",new PhoneNumber("0701234567"),new SerialNumber("1"));
        assertThrows(
                IllegalArgumentException.class,
                () -> controller.setDiscountStrategy(null),
                "Null strategy should throw IllegalArgumentException.");
    }

    /**
    * Verifies that observers are notified
    * when repair order changes.
    */
    @Test
    public void testObserverGetsUpdated()throws CustomerNotFoundException {
        TestObserver observer =new TestObserver();
        controller.createRepairOrder("test",new PhoneNumber("0701234567"),new SerialNumber("1"),observer);
        controller.addDiagnosticResult(new DiagnosticResult("diag"));
        assertEquals(1,observer.getCallCount(),"Observer should be notified when order changes.");
    }

    /**
     * Verifies that findOrder returns existing order.
    */
    @Test
    public void testFindOrderReturnsCorrectOrder()throws CustomerNotFoundException, OperationFailedException {
        RepairOrderDTO created = controller.createRepairOrder("test",new PhoneNumber("0701234567"),new SerialNumber("1"));
        RepairOrderDTO found =controller.findOrder(new model.OrderId(created.getOrderId()));
        assertEquals(created.getOrderId(),found.getOrderId(),"findOrder should return correct order." );
    }
    /**
     * Verifies that findOrder wraps lower level exception.
     */
    @Test
    public void testFindOrderThrowsOperationFailedException() {
        assertThrows(OperationFailedException.class,() -> controller.findOrder(new model.OrderId()),"Missing order should throw OperationFailedException.");
    }

    /**
     * Verifies that OperationFailedException has correct message.
     */
    @Test
    public void testFindOrderExceptionMessage() {
        OperationFailedException exc =assertThrows(OperationFailedException.class,() -> controller.findOrder(new OrderId()) );
        assertEquals("Could not find repair order.",exc.getMessage(),"OperationFailedException should have correct error message.");
    }
}
