package startup;

import controller.RepairController;
import integration.CustomerRegistry;
import integration.Printer;
import integration.RepairOrderRegistry;
import view.View;

/**
 * Application entry point. Creates integration objects, the controller, and the view,
 * then starts the hardcoded program flow.
 */
public class Main {
    
    /**
     * Starts the hardcoded program flow.
     *
     * @param args Command line arguments, unused in this version.
     */
    public static void main(String[] args) {
        CustomerRegistry customerRegistry = new CustomerRegistry();
        RepairOrderRegistry orderRegistry = new RepairOrderRegistry();
        Printer printer = new Printer();
        RepairController controller = new RepairController(customerRegistry, orderRegistry, printer);
        View view = new View(controller);
        view.runFakeExecution();
    }
}