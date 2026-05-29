package view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.RepairController;
import integration.CustomerRegistry;
import integration.Printer;
import integration.RepairOrderRegistry;

/**
 * Tests output printed by the View class.
 */
public class ViewOutputTest {

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
* Verifies that the View prints all information output that matter
* during the hardcoded execution flow, like succesful repair flow and customer lookup failures.
*/
    @Test
    public void testRunFakeExecutionPrintsInformation() {
        RepairOrderRegistry registry = RepairOrderRegistry.getInstance();
        registry.clear();
        RepairController controller = new RepairController(new CustomerRegistry(),registry,new Printer());
        View view = new View(controller);
        view.runFakeExecution();
        String result = output.toString();

        assertTrue(result.contains("Linnea"),"Customer information should be printed.");
        assertTrue(result.contains("Broken brake"),"Repair order information should be printed.");
        assertTrue(result.contains("Brake worn out"),"Diagnostic information should be printed.");
        assertTrue(result.contains("Replace brake"),"Repair task information should be printed.");
        assertTrue(result.contains("500"),"Repair cost should be printed.");

        assertTrue(result.contains("Could not find a customer with that phone number."),"Customer not found message should be printed.");

        assertTrue(result.contains("The system is temporarily unavailable"),"Database failure message should be printed.");

        assertTrue(result.contains("Order total with loyal customer discount"),"Discount information should be printed.");
        assertTrue(result.contains("270"),"Discounted total cost should be printed.");
}
}