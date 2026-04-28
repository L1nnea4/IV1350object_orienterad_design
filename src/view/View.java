package view;

import controller.RepairController;
import model.Bike;
import model.Customer;
import model.DiagnosticResult;
import model.Money;
import model.OrderId;
import model.PhoneNumber;
import model.RepairOrder;
import model.RepairTask;
import model.SerialNumber;

/**
 * Simulates user interaction.
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
     * Runs one hardcoded basic flow
     */
    public void runFakeExecution() {
    PhoneNumber phone = new PhoneNumber("0701234567");
    SerialNumber serial = new SerialNumber("BIKE123");

    System.out.println("=== FIND CUSTOMER ===");
    System.out.println(contr.findCustomer(phone));

    System.out.println("\n=== CREATE ORDER ===");
    System.out.println(contr.createRepairOrder("Broken brake", phone, serial));

    System.out.println("\n=== ADD DIAGNOSTIC ===");
    System.out.println(contr.addDiagnosticResult(
        new DiagnosticResult("Brake worn out")));

    System.out.println("\n=== ADD TASK ===");
    System.out.println(contr.addRepairTask(
        new RepairTask("Replace brake", "Fix brake", new Money(500))));

    System.out.println("\n=== ACCEPT REPAIR ===");
    System.out.println(contr.acceptRepair());
}
    /*public void runFakeExecution() {
        PhoneNumber phone = new PhoneNumber("0701234567");
        SerialNumber serial = new SerialNumber("BIKE123");
        System.out.println(contr.findCustomer(phone));
        System.out.println(contr.createRepairOrder("Broken brake", phone, serial));
        System.out.println(contr.addDiagnosticResult(new DiagnosticResult("Brake worn out")));
        System.out.println(contr.addRepairTask(new RepairTask("Replace brake", "Fix brake", new Money(500))));
        System.out.println(contr.acceptRepair());
    }*/
    public void debugPrintAll() {
    PhoneNumber phone = new PhoneNumber("0701234567");
    SerialNumber serial = new SerialNumber("BIKE123");

    Customer customer = new Customer("Linnea", "test@mail.com", phone);
    Bike bike = new Bike("Brand", "Model", serial);
    Money money = new Money(500);
    DiagnosticResult diagnostic = new DiagnosticResult("Brake worn out");
    RepairTask task = new RepairTask("Replace brake", "Fix brake", money);

    RepairOrder order = new RepairOrder(
        new OrderId(),
        "Broken brake",
        phone,
        serial
        , customer
    );

    order.addDiagnosticResult(diagnostic);
    order.addRepairTask(task);
    order.accept();

    System.out.println("==== DEBUG OUTPUT ====");
    System.out.println(customer);
    System.out.println(bike);
    System.out.println(money);
    System.out.println(diagnostic);
    System.out.println(task);
    System.out.println(order);
}

}