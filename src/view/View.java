package view;

import controller.RepairController;
import model.DiagnosticResult;
import model.Money;
import model.PhoneNumber;
import model.RepairTask;
import model.SerialNumber;

/**
 * Simulates user interaction, runs a fixed sequence of controller calls and prints results to the console.
 *
*/
public class View {
    private final RepairController contr;

    /**
     * Creates a new view.
     *
     * @param contr The controller used by this view.
     */
    public View(RepairController contr) {
        this.contr = contr;
    }

    /**
     * Runs one hardcoded basic flow, find customer, create order, add diagnostic, add task, accept repair, each controller result is printed out.
     *
    */
    public void runFakeExecution() {
        PhoneNumber phone = new PhoneNumber("0701234567");
        SerialNumber serial = new SerialNumber("BIKE123");
        System.out.println("=== FIND CUSTOMER ===");
        System.out.println(contr.findCustomer(phone));
        System.out.println("=== CREATE REPAIR ORDER ===");
        System.out.println(contr.createRepairOrder("Broken brake", phone, serial));
        System.out.println("=== ADD DIAGNOSTIC RESULT ===");
        System.out.println(contr.addDiagnosticResult(new DiagnosticResult("Brake worn out")));
        System.out.println("=== ADD REPAIR TASK ===");
        System.out.println(contr.addRepairTask(new RepairTask("Replace brake", "Fix brake", new Money(500))));
        System.out.println(contr.acceptRepair());
    }
}