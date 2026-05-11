package controller;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dto.RepairOrderDTO;
import integration.CustomerRegistry;
import integration.Printer;
import integration.RepairOrderRegistry;
import model.DiagnosticResult;
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
    * to ensure that tests do not share state
    */
    @BeforeEach
    public void setUp() {
        registry = new RepairOrderRegistry();
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
    public void testCreateOrder() {
        RepairOrderDTO order = controller.createRepairOrder(
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
        controller.createRepairOrder(
                "test",
                new PhoneNumber("1"),
                new SerialNumber("1")
        );

        RepairOrderDTO acceptedOrder = controller.acceptRepair();

        assertEquals(OrderState.ACCEPTED, acceptedOrder.getState());
    }

    /**
     * Verifies that adding diagnostic result works.
     */
    @Test
    public void testAddDiagnosticResult() {
        controller.createRepairOrder(
                "test",
                new PhoneNumber("1"),
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
    public void testAddRepairTask() {
        controller.createRepairOrder(
                "test",
                new PhoneNumber("1"),
                new SerialNumber("1")
        );

        RepairOrderDTO order = controller.addRepairTask(
                new RepairTask("Task", "Desc", new Money(100))
        );

        assertEquals(1, order.getNumberOfTasks());
    }
}