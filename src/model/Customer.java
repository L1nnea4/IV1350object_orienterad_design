package model;

public class Customer {
    private String name;
    private String email;
    private PhoneNumber phone;

    public Customer(String name, String email, PhoneNumber phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String toString() {
        return name;
    }
}