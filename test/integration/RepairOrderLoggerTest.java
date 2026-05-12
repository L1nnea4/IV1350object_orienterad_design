package integration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dto.RepairOrderDTO;
import model.Bike;
import model.Customer;
import model.OrderId;
import model.PhoneNumber;
import model.RepairOrder;
import model.SerialNumber;

/**
 * Tests the RepairOrderLogger observer.
 */
public class RepairOrderLoggerTest {
    private RepairOrderLogger loggerObserver;
    private RepairOrderDTO dto;
    /**
     * Creates test data before each test.
     */
    @BeforeEach
    public void setUp() {
        loggerObserver = new RepairOrderLogger();
        Bike bike = new Bike("Brand","Model",new SerialNumber("1"));

        Customer customer = new Customer("Test", "a@b.com",new PhoneNumber("1"));
        RepairOrder order =new RepairOrder(new OrderId(),"Problem",customer,bike);
        dto = new RepairOrderDTO(order);
    }

    /**
     * Verifies that logger observer handles updates
     * without throwing exceptions.
     */
    @Test
    public void testLoggerObserverDoesNotThrow() {
        assertDoesNotThrow(() -> loggerObserver.orderUpdated(dto),"RepairOrderLogger should handle updates without throwing exceptions.");
    }
}