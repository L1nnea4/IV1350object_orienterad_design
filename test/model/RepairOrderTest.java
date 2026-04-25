package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RepairOrderTest {

    @Test
    public void testAccept() {
        RepairOrder order = new RepairOrder("test",
                new PhoneNumber("1"),
                new SerialNumber("1"));

        order.accept();

        assertEquals(OrderState.ACCEPTED, order.getState());
    }
}