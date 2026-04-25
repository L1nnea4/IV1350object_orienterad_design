package model;

/**
 * Represents one repair task.
 */
public class RepairTask {
    private final String name;
    private final String description;
    private final Money cost;

    /**
     * Creates a repair task object.
     *
     * @param name Task name.
     * @param description Task description.
     * @param cost Task cost.
     */
    public RepairTask(String name, String description, Money cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    /**
     * Returns task as text.
     *
     * @return Text form of repair task.
     */
    public String toString() {
        return name + ": " + description + " (" + cost + ")";
    }
}