package integration;

import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

/**
 * Tests that RepairOrderRegistry is a Singleton.
 */
public class SingletonTest {

    /**
     * Verifies that two calls to getInstance return the same object.
     */
    @Test
    public void testGetInstanceReturnsSameObject() {
        RepairOrderRegistry first = RepairOrderRegistry.getInstance();
        RepairOrderRegistry second = RepairOrderRegistry.getInstance();
        assertSame(first, second,    "getInstance should always return the same RepairOrderRegistry instance.");
    }
}
