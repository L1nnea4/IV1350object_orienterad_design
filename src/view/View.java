package view;

import controller.*;
import model.*;

public class View {

    private RepairController controller;

    public View(RepairController controller) {
        this.controller = controller;
    }

    public void start() {

        PhoneNumber phone = new PhoneNumber("123");

        Customer c = controller.findCustomer(phone);
        System.out.println("Customer: " + c);

        RepairOrder order = controller.createRepairOrder(
                "Broken chain",
                phone,
                new SerialNumber("ABC")
        );

        controller.addDiagnosticResult(order.getId(),
                new DiagnosticResult("Chain broken"));

        controller.addRepairTask(order.getId(),
                new RepairTask("Fix chain", "Replace chain", new Money(100)));

        controller.acceptRepairOrder(order.getId());
    }
}