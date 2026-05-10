package integration;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dto.RepairOrderDTO;
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
     * Creates an empty registry and one sample repair order used by the tests.
     */
    @BeforeEach
    public void setUp() {
        registry = RepairOrderRegistry.getInstance();
        order = new RepairOrder(
            new OrderId(),
            "Problem",
            new PhoneNumber("1"),
            new SerialNumber("1"),
            new Customer("Test", "a@b.com", new PhoneNumber("1"))
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
        assertEquals(1, registry.getAllOrders().size());
    }

    /**
     * Verifies that findById returns the correct order.
     */
    @Test
    public void testFindByIdReturnsOrder() {
        registry.save(order);
        RepairOrderDTO found = registry.findById(order.getId());

        assertNotNull(found);
        assertEquals(order.getId().getValue(), found.getOrderId());
    }
}