package view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
 * Tests the RepairOrderView class.
 */
public class RepairOrderViewTest {

    private ByteArrayOutputStream output;
    private PrintStream originalOut;

    /**
     * Redirects System.out before each test.
     */
    @BeforeEach
    public void setUp() {
        originalOut = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    /**
     * Restores System.out after each test.
     */
    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    /**
     * Verifies that repair order updates are printed.
     */
    @Test
    public void testRepairOrderUpdatePrintsOrder() {
        Bike bike = new Bike( "Brand", "Model", new SerialNumber("ABC") );
        Customer customer = new Customer( "Test", "test@test.com", new PhoneNumber("123"));
        RepairOrder order = new RepairOrder( new OrderId(), "Problem", customer, bike);
        RepairOrderDTO dto = new RepairOrderDTO(order);
        RepairOrderView view = new RepairOrderView();
        view.orderUpdated(dto);
        String result = output.toString();
        assertTrue( result.contains("ORDER UPDATE"), "RepairOrderView should print update header.");
        assertTrue( result.contains("Problem"), "RepairOrderView should print repair order information.");
    }
}