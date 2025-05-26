package com.modernappliances.appliances;

// Base superclass for all appliances

public abstract class Appliance {
    // properties for all appliances
    private String itemNumber;
    private String brand;
    private int quantity;
    private int wattage;
    private String color;
    private double price;

    // constructor
    private Appliance(String itemNumber, String brand, int quantity, int wattage, String color, double price) {
        this.itemNumber = itemNumber;
        this.brand = brand;
        this.quantity = quantity;
        this.wattage = wattage;
        this.color = color;
        this.price = price;
    }

    // getters
    public String getItemNumber() {
        return itemNumber;
    }

    public String getBrand() {
        return brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getWattage() {
        return wattage;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    // setter for quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // check if the appliance is in stock
    public boolean isInStock() {
        return quantity > 0;
    }

    // decrement the quantity
    public void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
        } else {
            throw new IllegalStateException("Cannot decrement quantity below zero.");
        }
    }

    // string representation of the appliance, to be overridden by subclasses
    @Override
    public String toString() {
        return "ItemNumber: " + itemNumber + "\n" +
                "Brand: " + brand + "\n" +
                "Quantity: " + quantity + "\n" +
                "Wattage: " + wattage + "W\n" +
                "Color: " + color + "\n" +
                "Price: " + price;
    }

    // string representation of the appliance for saving to file
    public abstract String toFileString();

    // get appliance type from first character of item number
    public char getType() {
        if (itemNumber != null && !itemNumber.isEmpty()) {
            return itemNumber.charAt(0);
        } else {
            throw new IllegalArgumentException("Item number is not set or is empty.");
        }
    }
}
