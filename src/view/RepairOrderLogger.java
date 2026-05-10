package view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import model.RepairOrderObserver;
import model.dto.RepairOrderDTO;

/**
 * Observer that writes repair order updates to a log file.
 * This class never calls the controller; it only receives updates through the Observer pattern.
 */
public class RepairOrderLogger implements RepairOrderObserver {
    private static final String LOG_FILE = "repairorder.log";

    /**
     * Called automatically when a repair order is updated.
     * Writes the current order state to the log file.
     *
     * @param orderDTO A snapshot of the updated order.
     */
    @Override
    public void orderUpdated(RepairOrderDTO orderDTO) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println("[" + LocalDateTime.now() + "] " + orderDTO);
        } catch (IOException e) {
            System.err.println("Could not write to repair order log: " + e.getMessage());
        }
    }
}
