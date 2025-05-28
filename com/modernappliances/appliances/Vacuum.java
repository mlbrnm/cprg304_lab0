package com.modernappliances.appliances;

public class Vacuum extends Appliance {

    // vacuum-specific properties
    private String grade;
    private int batteryVoltage;

    // constructor
    public Vacuum(String itemNumber, String brand, int quantity, int wattage, String color, double price, String grade, int batteryVoltage) {
        super(itemNumber, brand, quantity, wattage, color, price);
        this.grade = grade;
        this.batteryVoltage = batteryVoltage;

        // validate battery voltage
        if (!(batteryVoltage == 18 || batteryVoltage == 24)) {
            throw new IllegalArgumentException("Invalid battery voltage. Must be either 18V or 24V.");
        }
    }

    // getters
    public String getGrade() {
        return grade;
    }

    public int getBatteryVoltage() {
        return batteryVoltage;
    }

    // Display friendly battery voltage
    public String getVoltageDisplay() {
        return batteryVoltage == 18 ? "Low" : "High";
    }

    // override toString method for display
    @Override
    public String toString() {
        return super.toString() +
        "Grade: " + grade + "\n" +
        "Battery Voltage: " + getVoltageDisplay() + "\n";
    }

    // override toFileString method for file output
    @Override
    public String toFileString() {
        String formattedPrice = (price == (int) price) ? String.valueOf((int) price) : String.valueOf(price);
        return String.join(";",
            itemNumber,
            brand,
            String.valueOf(quantity),
            String.valueOf(wattage),
            color,
            formattedPrice,
            grade,
            String.valueOf(batteryVoltage)
        );
    }
}
