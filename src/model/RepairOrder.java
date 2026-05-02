package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a repair order.
 */
public class RepairOrder {
    private final OrderId id;
    private final String problem;
    private OrderState state;
    private final Customer customer;
    private final Bike bike;
    private final List<RepairTask> tasks = new ArrayList<>();
    private final List<DiagnosticResult> diagnostics = new ArrayList<>();

    /**
     * Creates a new repair order.
     *
     * @param id The order id.
     * @param problem The reported customer problem.
     * @param phone The customer phone number.
     * @param serial The bike serial number.
     * @param customer The customer that owns the bike.
     */
    public RepairOrder(OrderId id, String problem, PhoneNumber phone,
                       SerialNumber serial, Customer customer) {
        this.id = id;
        this.problem = problem;
        this.customer = customer;
        this.bike = new Bike("Brand", "Model", serial);
        this.state = OrderState.CREATED;
    }

    /**
     * Adds one diagnostic result to this order.
     *
     * @param result The result to add.
     */
    public void addDiagnosticResult(DiagnosticResult result) {
        diagnostics.add(result);
    }

    /**
     * Adds one repair task to this order.
     *
     * @param task The task to add.
     */
    public void addRepairTask(RepairTask task) {
        tasks.add(task);
    }

    /**
     * Accepts this repair order.
     */
    public void accept() {
        state = OrderState.ACCEPTED;
    }

    /**
     * Returns the current state.
     *
     * @return Current order state.
     */
    public OrderState getState() {
        return state;
    }

    /**
     * Returns diagnostic count.
     *
     * @return Number of diagnostics in this order, is used for verification in unit tests.

     */
    public int getNumberOfDiagnostics() {
        return diagnostics.size();
    }

    /**
     * Returns task count, is used for verification in unit tests.
     *
     * @return Number of tasks in this order.
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }
/**
 * Returns the order id.
 *
 * @return The order id.
 */
public OrderId getId() {
    return id;
}

    /**
     * Returns a text version of this repair order.
     *
     * @return Order details as text.
     */
    @Override
    public String toString() {
        return "Order " + id + " customer: " + customer + " bike: " + bike + " problem: " + problem + " state: " + state;
    }
}