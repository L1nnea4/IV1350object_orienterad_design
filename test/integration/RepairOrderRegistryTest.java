package integration;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dto.RepairOrderDTO;
import exception.RepairOrderNotFoundException;
import model.Bike;
import model.Customer;
import model.OrderId;
import model.PhoneNumber;
import model.RepairOrder;
import model.SerialNumber;


/**
 * Tests the RepairOrderRegistry class.
 */
public class RepairOrderRegistryTest {

    private RepairOrderRegistry registry;
    private RepairOrder order;

    /**
     * retrieves an singleton registry instance, clear stored orders and one sample repair order used by the tests.
     */
    @BeforeEach
    public void setUp() {
        registry = RepairOrderRegistry.getInstance();
        registry.clear();

        Bike bike = new Bike("Brand", "Model", new SerialNumber("1"));

        Customer customer = new Customer("Test", "a@b.com", new PhoneNumber("1"));
        order = new RepairOrder(
            new OrderId(),
            "Problem",
            customer,
            bike
        );
    }

    /**
     * Clears the registry and order references after each test.
     */
    @AfterEach
    public void tearDown() {
        registry = null;
        order = null;
    }

    /**
     * Verifies that save stores the order.
     */
    @Test
    public void testSaveStoresOrder() {
        registry.save(order);
        assertEquals(1, registry.getAllOrders().size(),    "save should store exactly one repair order in the registry.");
    }

    /**
     * Verifies that findById returns the correct order.
     */
    @Test
    public void testFindByIdReturnsOrder() throws RepairOrderNotFoundException {
        registry.save(order);
        RepairOrderDTO found = registry.findById(order.getId());

        assertNotNull(found,    "findById should return a RepairOrderDTO for an existing order.");
        assertEquals(order.getId().getValue(), found.getOrderId(),    "findById should return the repair order with the correct id.");
    }

    /**
     * Verifies that findById throws exception when order does not exist.
     */
    @Test
    public void testFindByIdThrowsExceptionWhenOrderMissing() {
        assertThrows(
                RepairOrderNotFoundException.class,
                () -> registry.findById(new OrderId()));
    }

    /**
     * Verifies that registry state does not change when exception is thrown.
     */
    @Test
    public void testStateNotChangedWhenExceptionThrown() {
        int before = registry.getAllOrders().size();
        try {
            registry.findById(new OrderId());
        } catch (RepairOrderNotFoundException exc) {
            // expected exception
        }
        int after = registry.getAllOrders().size();
        assertEquals(before, after,    "Registry state should not change when findById throws RepairOrderNotFoundException.");
    }

    /**
     * Verifies that RepairOrderNotFoundException
     * contains an informative message.
     */
    @Test
    public void testRepairOrderNotFoundExceptionMessage() {
        RepairOrderNotFoundException exc =assertThrows(RepairOrderNotFoundException.class,() -> registry.findById(new OrderId()) );
        assertNotNull(exc.getMessage(),"Exception should contain error message.");
    }
}