package integration;

import model.*;

public class Printer {

    public void printReceipt(RepairOrder order) {
        System.out.println("---- RECEIPT ----");
        System.out.println(order);
    }
}