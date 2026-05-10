package dto;

import java.util.ArrayList;
import java.util.List;

import model.OrderState;
import model.RepairOrder;

/**
 * Data transfer object for repair order information.
 * Used to pass order data between layers without exposing model objects.
 */
public class RepairOrderDTO {
    private final int orderId;
    private final String problem;
    private final OrderState state;
    private final String customerName;
    private final String bikeInfo;
    private final List<String> tasks;
    private final List<String> diagnostics;
    private final int totalCost;

    /**
     * Creates a RepairOrderDTO from a RepairOrder model object.
     *
     * @param order The repair order to copy data from.
     */
    public RepairOrderDTO(RepairOrder order) {
        this.orderId = order.getId().getValue();
        this.problem = order.getProblem();
        this.state = order.getState();
        this.customerName = order.getCustomerName();
        this.bikeInfo = order.getBikeInfo();
        this.tasks = new ArrayList<>(order.getTaskDescriptions());
        this.diagnostics = new ArrayList<>(order.getDiagnosticDescriptions());
        this.totalCost = order.getTotalCost();
    }

    /**
     * Returns the order id value.
     *
     * @return The order id.
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Returns the current order state.
     *
     * @return The order state.
     */
    public OrderState getState() {
        return state;
    }

    /**
     * Returns the total cost of all repair tasks.
     *
     * @return Total cost in SEK.
     */
    public int getTotalCost() {
        return totalCost;
    }

    /**
     * Returns the number of diagnostic results.
     *
     * @return Number of diagnostics.
     */
    public int getNumberOfDiagnostics() {
        return diagnostics.size();
    }

    /**
     * Returns the number of repair tasks.
     *
     * @return Number of repair tasks.
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }
    /**
     * Returns a text representation of this repair order.
     *
     * @return Order details as text.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ").append(orderId);
        sb.append(" | Customer: ").append(customerName);
        sb.append(" | Bike: ").append(bikeInfo);
        sb.append(" | Problem: ").append(problem);
        sb.append(" | State: ").append(state);
        if (!diagnostics.isEmpty()) {
            sb.append(" | Diagnostics: ").append(diagnostics);
        }
        if (!tasks.isEmpty()) {
            sb.append(" | Tasks: ").append(tasks);
        }
        sb.append(" | Total cost: ").append(totalCost).append(" SEK");
        return sb.toString();
    }
}
