package startup;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the Main class output.
 */
public class MainTest {

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
     * Verifies that the application prints output from the hardcoded execution flow.
     */
    @Test
    public void testMainPrintsOutput() {

        Main.main(new String[]{});
        String result = output.toString();
        assertTrue(result.length() > 0,"Main should produce output.");

        assertTrue(
                result.contains("Find customer")
                || result.contains("Created order")
                || result.contains("Accepted order total"),
                "Main should print application output."
        );
    }
}