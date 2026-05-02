package controller;

import integration.CustomerRegistry;
import integration.Printer;
import integration.RepairOrderRegistry;
import model.Customer;
import model.DiagnosticResult;
import model.OrderId;
import model.PhoneNumber;
import model.RepairOrder;
import model.RepairTask;
import model.SerialNumber;

/**
 * This is the applications only controller. All calls from the view to the model and integraion layer pass through here
 */
public class RepairController {
    private final CustomerRegistry customerRegistry;
    private final RepairOrderRegistry orderRegistry;
    private final Printer printer;
    private RepairOrder currentOrder;

    /**
     * Creates a new controller.
     *
     * @param customerRegistry Used to find customers.
     * @param orderRegistry Used to store repair orders.
     * @param printer Used to print accepted repair orders.
     */
    public RepairController(CustomerRegistry customerRegistry,
                            RepairOrderRegistry orderRegistry,
                            Printer printer) {
        this.customerRegistry = customerRegistry;
        this.orderRegistry = orderRegistry;
        this.printer = printer;
    }

    /**
     * Finds a customer from phone number.
     *
     * @param phone Customer phone number.
     * @return The found customer.
     */
    public Customer findCustomer(PhoneNumber phone) {
        return customerRegistry.findCustomer(phone);
    }

    /**
     * Creates and stores a new repair order.
     *
     * @param problem Reported problem from customer.
     * @param phone Customer phone number.
     * @param serial Bike serial number.
     * @return The created repair order.
     */
    public RepairOrder createRepairOrder(String problem, PhoneNumber phone, SerialNumber serial) {
        Customer customer = customerRegistry.findCustomer(phone);
        currentOrder = new RepairOrder(new OrderId(), problem, phone, serial, customer);
        orderRegistry.save(currentOrder);
        return currentOrder;
    }

    /**
     * Adds a diagnostic result to the current order.
     *
     * @param result The diagnostic result to add.
     * @return The updated repair order.
     */
    public RepairOrder addDiagnosticResult(DiagnosticResult result) {
        currentOrder.addDiagnosticResult(result);
        return currentOrder;
    }

    /**
     * Adds a repair task to the current order.
     *
     * @param task The repair task to add.
     * @return The updated repair order.
     */
    public RepairOrder addRepairTask(RepairTask task) {
        currentOrder.addRepairTask(task);
        return currentOrder;
    }

    /**
     * Accepts and prints the current repair order.
     *
     * @return The accepted repair order.
     */
    public RepairOrder acceptRepair() {
        currentOrder.accept();
        printer.printReceipt(currentOrder);
        return currentOrder;
    }
}