package controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import model.*;
import integration.*;

public class RepairControllerTest {

    @Test
    public void testCreateOrder() {
        RepairController controller = new RepairController(
                new CustomerRegistry(),
                new RepairOrderRegistry(),
                new Printer()
        );

        RepairOrder order = controller.createRepairOrder(
                "test",
                new PhoneNumber("1"),
                new SerialNumber("1")
        );

        assertNotNull(order);
    }
}