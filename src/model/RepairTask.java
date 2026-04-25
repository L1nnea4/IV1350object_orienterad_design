package model;

public class RepairTask {
    private String name;
    private String description;
    private Money cost;

    public RepairTask(String name, String description, Money cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }
}