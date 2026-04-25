package integration;

import model.*;
import java.util.*;

public class RepairOrderRegistry {

    private List<RepairOrder> orders = new ArrayList<>();

    public void save(RepairOrder order) {
        orders.add(order);
    }

    public RepairOrder findById(OrderId id) {
        for (RepairOrder o : orders) {
            if (o.getId().toString().equals(id.toString())) {
                return o;
            }
        }
        return null;
    }
}