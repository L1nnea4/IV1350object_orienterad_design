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
     * Verifies that the application prints output.
     */
    @Test
    public void testMainPrintsOutput() {

        Main.main(new String[]{});
        String result = output.toString();
        assertTrue(
                result.contains("SCENARIO")
                || result.contains("Find customer")
                || result.contains("Created order"),
                "Main should print application output."
        );
    }
}