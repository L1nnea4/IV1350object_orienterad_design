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
     * Verifies that all scenarios and information
     * printouts in the View class are executed.
     */
    @Test
    public void testRunFakeExecutionPrintsAllScenarios() {

        RepairOrderRegistry registry = RepairOrderRegistry.getInstance();
        registry.clear();
        RepairController controller = new RepairController(new CustomerRegistry(), registry, new Printer());
        View view = new View(controller);
        view.runFakeExecution();
        String result = output.toString();

        /*
         * Scenario headings.
         */
        assertTrue(result.contains("SCENARIO 1"),"Scenario 1 should be printed.");
        assertTrue(result.contains("SCENARIO 2"),"Scenario 2 should be printed.");
        assertTrue(result.contains("SCENARIO 3"),"Scenario 3 should be printed.");
        assertTrue(result.contains("SCENARIO 4"),"Scenario 4 should be printed.");

        /*
         * Successful flow.
         */
        assertTrue(result.contains("Find customer"),"Customer lookup should be printed.");
        assertTrue(result.contains("Created order"),"Order creation should be printed.");
        assertTrue(result.contains("After diagnostic"),"Diagnostic update should be printed.");
        assertTrue(result.contains("After task"),"Repair task update should be printed.");
        assertTrue(result.contains("Accepted order total"),"Accepted order total should be printed.");

        /*
         * Unknown customer scenario.
         */
        assertTrue(result.contains("Could not find a customer with that phone number."),"Unknown customer error should be printed.");

        /*
         * Database failure scenario.
         */
        assertTrue(result.contains("The system is temporarily unavailable"), "Database failure error should be printed.");

        /*
         * Discount scenario.
         */
        assertTrue(result.contains( "Order total with loyal customer discount"),"Discount result should be printed.");
    }
}