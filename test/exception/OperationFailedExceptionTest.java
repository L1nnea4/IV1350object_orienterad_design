package exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

/**
 * Tests the OperationFailedException class.
 */
public class OperationFailedExceptionTest {

    /**
     * Verifies that the exception stores the message.
     */
    @Test
    public void testExceptionStoresMessage() {
        OperationFailedException exception =new OperationFailedException("Test message",new Exception());
        assertEquals("Test message",exception.getMessage(),"OperationFailedException should store the provided message.");
    }

    /**
     * Verifies that the wrapped cause is stored.
     */
    @Test
    public void testExceptionStoresCause() {
        Exception cause = new Exception("Original cause");
        OperationFailedException exception =new OperationFailedException("Wrapper message", cause);
        assertNotNull(exception.getCause(),"OperationFailedException should store the wrapped cause.");
        assertEquals( cause,exception.getCause(),"OperationFailedException should return the original wrapped cause.");
    }
}