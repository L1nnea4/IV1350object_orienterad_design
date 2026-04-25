package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents one repair order.
 */
public class RepairOrder {
    private OrderState state;
    private List<RepairTask> tasks = new ArrayList<>();
    private List<DiagnosticResult> diagnostics = new ArrayList<>();

    /**
     * Creates a new repair order.
     */
    public RepairOrder(String problem, PhoneNumber phone, SerialNumber serial) {
        this.state = OrderState.CREATED;
    }

    public void addDiagnosticResult(DiagnosticResult result) {
        diagnostics.add(result);
    }

    public void addRepairTask(RepairTask task) {
        tasks.add(task);
    }

    public void accept() {
        state = OrderState.ACCEPTED;
    }

    public String toString() {
        return "RepairOrder state: " + state;
    }
}