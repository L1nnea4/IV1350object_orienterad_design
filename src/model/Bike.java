package model;

/**
 * Represents an electric bike.
 */
public class Bike {
    private final String brand;
    private final String model;
    private final SerialNumber serial;

    /**
     * Creates a bike object.
     *
     * @param brand Bike brand.
     * @param model Bike model.
     * @param serial Bike serial number.
     */
    public Bike(String brand, String model, SerialNumber serial) {
        this.brand = brand;
        this.model = model;
        this.serial = serial;
    }

    /**
     * Returns bike as text.
     *
     * @return Text form of bike.
     */
    public String toString() {
        return brand + " " + model + " " + serial;
    }
}