package integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import model.Customer;
import model.OrderId;
import model.PhoneNumber;
import model.RepairOrder;
import model.SerialNumber;


public class RepairOrderRegistryTest {

@Test
public void testSaveStoresOrder() {
    RepairOrderRegistry registry = new RepairOrderRegistry();

    RepairOrder order = new RepairOrder(
        new OrderId(),
        "Problem",
        new PhoneNumber("1"),
        new SerialNumber("1"),
        new Customer("Test", "a@b.com", new PhoneNumber("1"))
    );

    registry.save(order);

    assertEquals(1, registry.getAllOrders().size());
}
}