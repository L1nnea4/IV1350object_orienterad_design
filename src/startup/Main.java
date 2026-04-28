package startup;

import controller.RepairController;
import integration.CustomerRegistry;
import integration.Printer;
import integration.RepairOrderRegistry;
import view.View;

/**
 * Starts the application, contains the main method used to start the application.
 */
public class Main {
    /**
     * Starts the hardcoded program flow.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        CustomerRegistry customerRegistry = new CustomerRegistry();
        RepairOrderRegistry orderRegistry = new RepairOrderRegistry();
        Printer printer = new Printer();
        RepairController controller = new RepairController(customerRegistry, orderRegistry, printer);
        View view = new View(controller);
        view.runFakeExecution();
        //view.debugPrintAll();
    }
}