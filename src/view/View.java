package view;

import controller.RepairController;
import dto.RepairOrderDTO;
import exception.CustomerNotFoundException;
import exception.DatabaseFailureException;
import integration.LogHandler;
import integration.RepairOrderLogger;
import model.DiagnosticResult;
import model.LoyalCustomerDiscountStrategy;
import model.Money;
import model.PhoneNumber;
import model.RepairTask;
import model.SerialNumber;

/**
 * Simulates user interaction, runs a fixed sequence of controller calls and prints results to the console.
 *All exceptions from the controller are caught here.
*/
public class View {
    private final RepairController contr;
    //private final ExceptionLogger exceptionLogger = new ExceptionLogger();
    private final RepairOrderView orderView = new RepairOrderView();
    private final RepairOrderLogger orderLogger = new RepairOrderLogger();
    private final LogHandler logger = new LogHandler();
    private final ErrorMessageHandler errorHandler = new ErrorMessageHandler();
    /**
     * Creates a new view.
     *
     * @param contr The controller used by this view.
     */
    public View(RepairController contr) {
        this.contr = contr;
    }

    /**
     * Runs several hardcoded scenarios to demonstrate the program flow:
     * successful flow, unknown customer, database failure, and discount.
    */
    public void runFakeExecution() {
        
        System.out.println("=== SCENARIO 1: SUCCESSFUL REPAIR ===");
        runSuccessfulFlow();

        System.out.println("\n=== SCENARIO 2: UNKNOWN CUSTOMER ===");
        runUnknownCustomerFlow();

        System.out.println("\n=== SCENARIO 3: DATABASE FAILURE ===");
        runDatabaseFailureFlow();

        System.out.println("\n=== SCENARIO 4: LOYAL CUSTOMER DISCOUNT ===");
        runDiscountFlow();
        }  
    
    /**
     * Simulates a successful repair flow.
     */
    private void runSuccessfulFlow() {
        PhoneNumber phone = new PhoneNumber("0701234567");
        SerialNumber serial = new SerialNumber("BIKE123");

        try {
            System.out.println("Find customer: " + contr.findCustomer(phone));
            RepairOrderDTO order = contr.createRepairOrder("Broken brake", phone, serial,
                    orderView, orderLogger);
            System.out.println("Created order: " + order);

            order = contr.addDiagnosticResult(new DiagnosticResult("Brake worn out"));
            System.out.println("After diagnostic: " + order);

            order = contr.addRepairTask(new RepairTask("Replace brake", "Fix brake", new Money(500)));
            System.out.println("After task: " + order);

            order = contr.acceptRepair();
            System.out.println("Accepted order total: " + order.getTotalCost() + " SEK");
        } catch (CustomerNotFoundException e) {
            errorHandler.showErrorMsg("Could not find a customer with that phone number.");
        } catch (IllegalStateException exc) {
            errorHandler.showErrorMsg("Operation could not be completed.");
            logger.logException(exc);   
        }
    }
    /**
     * Simulates searching for an unknown customer.
     * CustomerNotFoundException business rule violation so no need for logging
     */
    private void runUnknownCustomerFlow() {
        PhoneNumber unknownPhone = new PhoneNumber("0000000000");
        try {
            contr.findCustomer(unknownPhone);
        } catch (CustomerNotFoundException exc) {
            errorHandler.showErrorMsg("Could not find a customer with that phone number.");
        }
    }
    /**
     * Simulates a database failure.
     */
    private void runDatabaseFailureFlow() {
        PhoneNumber badPhone = new PhoneNumber("999999999");
        try {
            contr.findCustomer(badPhone);
        } catch (CustomerNotFoundException exc) {
            errorHandler.showErrorMsg("Could not find a customer with that phone number."); 
        } catch (DatabaseFailureException exc) {
            errorHandler.showErrorMsg("The system is temporarily unavailable, please try again later.");
            logger.logException(exc);
        }
    }
    /**
     * Simulates a repair flow with discount.
     */
    private void runDiscountFlow() {
        PhoneNumber phone = new PhoneNumber("0701234567");
        SerialNumber serial = new SerialNumber("BIKE456");
        try {
            contr.createRepairOrder("Flat tire", phone, serial, orderView, orderLogger);
            contr.addDiagnosticResult(new DiagnosticResult("Rear tire puncture"));
            contr.addRepairTask(new RepairTask("Replace tire", "New rear tire", new Money(300)));
            contr.setDiscountStrategy(new LoyalCustomerDiscountStrategy());
            RepairOrderDTO order = contr.acceptRepair();
            System.out.println("Order total with loyal customer discount: " + order.getTotalCost() + " SEK");
        } catch (CustomerNotFoundException e) {
            errorHandler.showErrorMsg("Could not find a customer with that phone number.");
        } catch (IllegalStateException exc) {
            errorHandler.showErrorMsg("Operation could not be completed.");
            logger.logException(exc);
        }
}
}