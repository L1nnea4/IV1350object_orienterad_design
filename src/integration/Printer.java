package integration;

import dto.RepairOrderDTO;

/**
 * Prints information to standard output.
 */
public class Printer {
    /**
     * Prints one repair order receipt.
     *
     * @param order The order to print.
     */
    public void printReceipt(RepairOrderDTO order) {
        System.out.println("=== RECEIPT ===");
        System.out.println(order);
    }
}