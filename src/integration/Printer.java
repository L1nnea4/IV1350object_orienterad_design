package integration;

import dto.RepairOrderDTO;

/**
 * Prints information to standard output.
 */
public class Printer {
    /**
     * Prints one repair order receipt.
     *
     * @param orderDTO The order data to print.
     */
    public void printReceipt(RepairOrderDTO orderDTO) {
        System.out.println("=== RECEIPT ===");
        System.out.println(orderDTO);
    }
}