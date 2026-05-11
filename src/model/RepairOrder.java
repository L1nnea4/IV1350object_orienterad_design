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
    * Returns the total cost of all repair tasks in this order.
    *
    * @return Total cost in SEK.
    */
    public int getTotalCost() {
        int total = 0;
        for (RepairTask task : tasks) {
            total += task.getCost();
        }
        return total;
    }
    /**
     * Returns the reported problem for this order.
     *
     * @return The customer reported problem.
     */
    public String getProblem() {
        return problem;
    }

    /**
     * Returns the name of the customer that owns the bike in this order.
     *
     * @return The customer name.
     */
    public String getCustomerName() {
        return customer.getName();
    }
    /**
     * Returns a text description of the bike in this order.
     * @return The bike details as text.
     */
    public String getBikeInfo() {
        return bike.toString();
    }

    /**
    * Returns a list of text descriptions of the repair tasks in this order.
    * @return List of repair task descriptions.
    */
    public List<String> getTaskDescriptions() {
        List<String> result = new ArrayList<>();

        for (RepairTask task : tasks) {
        result.add(task.toString());
        }
         return result;
    }

    /**
     * Returns a list of text descriptions of the diagnostic results in this order.
     *
     * @return List of diagnostic result descriptions.
     */
    public List<String> getDiagnosticDescriptions() {
        List<String> result = new ArrayList<>();

        for (DiagnosticResult diagnostic : diagnostics) {
            result.add(diagnostic.toString());
        }
        return result;
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