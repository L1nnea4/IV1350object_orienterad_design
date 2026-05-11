package integration;

import org.junit.jupiter.api.AfterEach;
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
 * Tests the Printer class.
 */
public class PrinterTest {

    private Printer printer;
    private RepairOrder order;

    /**
     * Sets up test data before each test.
     */
    @BeforeEach
    public void setUp() {
        printer = new Printer();

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
     * Cleans up after each test.
     */
    @AfterEach
    public void tearDown() {
        printer = null;
        order = null;
    }

    /**
     * Verifies that printReceipt can be called without causing errors/ does not crash.
     */
    @Test
    public void testPrintReceiptDoesNotCrash() {
        RepairOrderDTO orderDTO = new RepairOrderDTO(order);
        printer.printReceipt(orderDTO);
    }
}