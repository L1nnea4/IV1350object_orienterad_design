package integration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import integration.LogHandler;

/**
 * Tests the LogHandler class.
 */
public class LogHandler {

    /**
     * Verifies that logException writes to log
     * without throwing exceptions.
     */
    @Test
    public void testLogExceptionDoesNotThrow() {

        LogHandler logger =
                new LogHandler();

        assertDoesNotThrow(
                () -> logger.logException(
                        new Exception("Test exception")
                ),
                "logException should not throw exceptions."
        );
    }

    /**
     * Verifies that logMessage writes to log
     * without throwing exceptions.
     */
    @Test
    public void testLogMessageDoesNotThrow() {

        LogHandler logger =
                new LogHandler();

        assertDoesNotThrow(
                () -> logger.logMessage(
                        "Test log message"
                ),
                "logMessage should not throw exceptions."
        );
    }
}