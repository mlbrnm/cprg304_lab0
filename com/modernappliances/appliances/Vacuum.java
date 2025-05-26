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
    }

    // getters
    public String getGrade() {
        return grade;
    }
    public String getVoltageDisplay() {
        return batteryVoltage + " V (" + (batteryVoltage == 18 ? "Low" : "High") + ")";
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
        return String.join(";",
            itemNumber,
            brand,
            String.valueOf(quantity),
            String.valueOf(wattage),
            color,
            String.valueOf(price),
            grade,
            String.valueOf(batteryVoltage)
        );
    }
}
