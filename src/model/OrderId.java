package model;

public class OrderId {
    private static int counter = 0;
    private int id;

    public OrderId() {
        this.id = ++counter;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "" + id;
    }
}