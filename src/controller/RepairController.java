package controller;

import integration.CustomerRegistry;
import integration.Printer;
import integration.RepairOrderRegistry;
import model.Customer;
import model.DiagnosticResult;
import model.PhoneNumber;
import model.RepairOrder;
import model.RepairTask;
import model.SerialNumber;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class RepairController {
    private RepairOrder currentOrder;
    private CustomerRegistry customerRegistry;
    private RepairOrderRegistry orderRegistry;
    private Printer printer;

    /**
     * Creates a new instance.
     */
    public RepairController(CustomerRegistry customerRegistry, RepairOrderRegistry orderRegistry, Printer printer) {
        this.customerRegistry = customerRegistry;
        this.orderRegistry = orderRegistry;
        this.printer = printer;
    }

    /**
     * Finds a customer.
     */
    public Customer findCustomer(PhoneNumber phone) {
        return customerRegistry.findCustomer(phone);
    }

    /**
     * Starts a new repair order.
     */
    public void createRepairOrder(String problem, PhoneNumber phone, SerialNumber serial) {
        currentOrder = new RepairOrder(problem, phone, serial);
        orderRegistry.save(currentOrder);
    }

    /**
     * Adds a diagnostic result.
     */
    public void addDiagnosticResult(DiagnosticResult result) {
        currentOrder.addDiagnosticResult(result);
    }

    /**
     * Adds a repair task.
     */
    public void addRepairTask(RepairTask task) {
        currentOrder.addRepairTask(task);
    }

    /**
     * Accepts repair order and prints receipt.
     */
    public void acceptRepair() {
        currentOrder.accept();
        printer.printReceipt(currentOrder);
    }
}