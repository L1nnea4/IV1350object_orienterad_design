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
     * Returns the cost of this task in SEK.
     *
     * @return Cost amount.
     */
    public int getCost() {
        return cost.getAmount();
    }

    /**
     * Returns task as text including the cost.
     *
     * @return Text form of repair task.
     */
    @Override
    public String toString() {
        return name + ": " + description + " (" + cost + ")";
    }
}