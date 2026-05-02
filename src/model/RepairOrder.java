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
     * Returns how many diagnositc results have been added to this order.
     *
     * @return Number of diagnostics results in this order

     */
    public int getNumberOfDiagnostics() {
        return diagnostics.size();
    }

    /**
     * Returns how mant repair tasks have been added to this order
     *
     * @return the number of repair tasks
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }
    /**
    * Returns the order id.
    *
    * @return The order id for this repair order.
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