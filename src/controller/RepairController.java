package controller;

import dto.CustomerDTO;
import dto.RepairOrderDTO;
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
     * @return DTO with the found customer.
     */
    public CustomerDTO findCustomer(PhoneNumber phone) {
        return new CustomerDTO(customerRegistry.findCustomer(phone));
    }

    /**
     * Creates and stores a new repair order.
     *
     * @param problem Reported problem from customer.
     * @param phone Customer phone number.
     * @param serial Bike serial number.
     * @return DTO with the created repair order.
     */
    public RepairOrderDTO createRepairOrder(String problem, PhoneNumber phone, SerialNumber serial) {
        Customer customer = customerRegistry.findCustomer(phone);
        currentOrder = new RepairOrder(new OrderId(), problem, phone, serial, customer);
        orderRegistry.save(new RepairOrderDTO(currentOrder));
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
     * Finds a repair order by id.
     *
     * @param id The order id to search for.
     * @return Matching repair order DTO
     */
    public RepairOrderDTO findRepairOrder(OrderId id) {
        return orderRegistry.findById(id);
    }

}