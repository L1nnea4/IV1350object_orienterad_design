package model;

import java.util.ArrayList;
import java.util.List;

import dto.RepairOrderDTO;

/**
 * Represents a repair order. This is the observed object in the Observer pattern.
 * It notifies all registered observers whenever it is updated.
 */
public class RepairOrder {
    private final OrderId id;
    private final String problem;
    private OrderState state;
    private final Customer customer;
    private final Bike bike;
    private final List<RepairTask> tasks = new ArrayList<>();
    private final List<DiagnosticResult> diagnostics = new ArrayList<>();
    private DiscountStrategy discountStrategy;
    private final List<RepairOrderObserver> observers = new ArrayList<>();

    /**
     * Creates a new repair order with no discount.
     *
     * @param id The order id.
     * @param problem The reported customer problem.
     * @param customer The customer that owns the bike.
     * @param bike The bike associated with the repair order.
     */
    public RepairOrder(OrderId id, String problem,
                        Customer customer, Bike bike) {
        this.id = id;
        this.problem = problem;
        this.customer = customer;
        this.bike = bike;
        this.state = OrderState.CREATED;
        this.discountStrategy = new NoDiscountStrategy();
    }

    /**
     * Registers an observer that will be notified on every change to this order.
     *
     * @param observer The observer to add.
     */
    public void addObserver(RepairOrderObserver observer) {
        observers.add(observer);
    }

    /**
     * Sets the discount strategy to use when accepting this order.
     *
     * @param strategy The discount strategy to apply.
     * @throws IllegalArgumentException if the strategy is null.
     */
    public void setDiscountStrategy(DiscountStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Discount strategy cannot be null.");
        }
        this.discountStrategy = strategy;
    }

    /**
     * Adds one diagnostic result to this order and notifies observers.
     *
     * @param result The result to add.
     * @throws IllegalStateException IllegalArgumentException if the result is null.
     */
    public void addDiagnosticResult(DiagnosticResult result) {
        if (result == null) {
            throw new IllegalArgumentException("Diagnostic result cannot be null.");
        }
        diagnostics.add(result);
        notifyObservers();
    }

    /**
     * Adds one repair task to this order and notifies observers.
     *
     * @param task The task to add.
     * @throws IllegalStateException IllegalArgumentException if the task is null.
     */
    public void addRepairTask(RepairTask task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }
        tasks.add(task);
        notifyObservers();
    }

    /**
     * Accepts this repair order and notifies observers.
     */
    public void accept() {
        state = OrderState.ACCEPTED;
        notifyObservers();
    }

    /**
     * Rejects this repair order and notifies observers.
     */
    public void reject() {
        state = OrderState.REJECTED;
        notifyObservers();
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
     * Returns the order id.
     *
     * @return The order id for this repair order.
     */
    public OrderId getId() {
        return id;
    }
    

    /**
     * Returns the problem description.
     *
     * @return The reported problem.
     */
    public String getProblem() {
        return problem;
    }

    /**
     * Returns the customer name.
     *
     * @return Customer name.
     */
    public String getCustomerName() {
        return customer.getName();
    }

    /**
     * Returns basic bike information.
     *
     * @return Bike description.
     */
    public String getBikeInfo() {
        return bike.toString();
    }

    /**
     * Returns the total cost of all repair tasks after applying the discount strategy.
     *
     * @return Total cost in SEK.
     */
    public int getTotalCost() {
        int total = 0;
        for (RepairTask task : tasks) {
            total += task.getCost();
        }
        return discountStrategy.applyDiscount(total);
    }

    /**
     * Returns how many diagnostic results have been added.
     *
     * @return Number of diagnostic results.
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
     * Returns text descriptions of all diagnostic results.
    *
     * @return List of diagnostic descriptions.
     */
    public List<String> getDiagnosticDescriptions() {
        List<String> result = new ArrayList<>();
        for (DiagnosticResult d : diagnostics) {
            result.add(d.toString());
        }
        return result;
    }

    /**
     * Returns text descriptions of all repair tasks including their cost.
     *
     * @return List of task descriptions.
    */
    public List<String> getTaskDescriptions() {
        List<String> result = new ArrayList<>();
        for (RepairTask t : tasks) {
            result.add(t.toString());
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
        return "Order " + id + " customer: " + customer + " bike: " + bike
                + " problem: " + problem + " state: " + state + " diagnostics: " + diagnostics + " tasks: " + tasks + " total cost: " + getTotalCost() + " SEK";
    }

    /**
     * Notifies all registered observers about the latest order state.
     */
    private void notifyObservers() {
        RepairOrderDTO dto = new RepairOrderDTO(this);
        for (RepairOrderObserver observer : observers) {
            try {
                observer.orderUpdated(dto);
            } catch (Exception exc) {
                System.err.println("Failed to notify observer.");
                exc.printStackTrace();
            }
        }
    }
}