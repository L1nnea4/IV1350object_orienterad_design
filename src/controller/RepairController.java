package controller;

import integration.CustomerNotFoundException;
import integration.CustomerRegistry;
import integration.Printer;
import integration.RepairOrderRegistry;
import model.Customer;
import model.DiagnosticResult;
import model.DiscountStrategy;
import model.OrderId;
import model.PhoneNumber;
import model.RepairOrder;
import model.RepairOrderObserver;
import model.RepairTask;
import model.SerialNumber;
import model.dto.CustomerDTO;
import model.dto.RepairOrderDTO;



/**
 * The application's only controller. All calls from the view go through here.
 * Observers are registered on each new repair order so they receive automatic updates.
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
     * @param orderRegistry    Used to store repair orders.
     * @param printer          Used to print accepted repair orders.
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
     * @return DTO with the found customer data.
     * @throws CustomerNotFoundException if no customer has the given phone number.
     */
    public CustomerDTO findCustomer(PhoneNumber phone) throws CustomerNotFoundException {
        Customer customer = customerRegistry.findCustomer(phone);
        return new CustomerDTO(customer);
    }

    /**
     * Creates and stores a new repair order. Registers any provided observers on the order.
     *
     * @param problem   Reported problem from customer.
     * @param phone     Customer phone number.
     * @param serial    Bike serial number.
     * @param observers Observers to attach to the new order.
     * @return DTO of the created repair order.
     * @throws CustomerNotFoundException if the phone number is not found.
     */
    public RepairOrderDTO createRepairOrder(String problem, PhoneNumber phone, SerialNumber serial,
                                            RepairOrderObserver... observers) throws CustomerNotFoundException {
        Customer customer = customerRegistry.findCustomer(phone);
        currentOrder = new RepairOrder(new OrderId(), problem, phone, serial, customer);
        for (RepairOrderObserver obs : observers) {
            currentOrder.addObserver(obs);
        }
        orderRegistry.save(currentOrder);
        return new RepairOrderDTO(currentOrder);
    }

    /**
     * Adds a diagnostic result to the current order.
     *
     * @param result The diagnostic result to add.
     * @return Updated order as DTO.
     */
    public RepairOrderDTO addDiagnosticResult(DiagnosticResult result) {
        currentOrder.addDiagnosticResult(result);
        return new RepairOrderDTO(currentOrder);
    }

    /**
     * Adds a repair task to the current order.
     *
     * @param task The repair task to add.
     * @return Updated order as DTO.
     */
    public RepairOrderDTO addRepairTask(RepairTask task) {
        currentOrder.addRepairTask(task);
        return new RepairOrderDTO(currentOrder);
    }

    /**
     * Accepts and prints the current repair order.
     *
     * @return The accepted order as DTO.
     */
    public RepairOrderDTO acceptRepair() {
        currentOrder.accept();
        RepairOrderDTO dto = new RepairOrderDTO(currentOrder);
        printer.printReceipt(dto);
        return dto;
    }

    /**
     * Rejects the current repair order.
     *
     * @return The rejected order as DTO.
     */
    public RepairOrderDTO rejectRepair() {
        currentOrder.reject();
        return new RepairOrderDTO(currentOrder);
    }

    /**
     * Sets the discount strategy to use for the current order.
     *
     * @param strategy The discount strategy to apply.
     */
    public void setDiscountStrategy(DiscountStrategy strategy) {
        currentOrder.setDiscountStrategy(strategy);
    }

    /**
     * Finds an order by its id.
     *
     * @param id The order id to search for.
     * @return The matching order as DTO, or null if not found.
     */
    public RepairOrderDTO findOrder(OrderId id) {
        return orderRegistry.findById(id);
    }
}
