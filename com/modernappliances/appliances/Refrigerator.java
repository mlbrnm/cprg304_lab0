package com.modernappliances.appliances;

// subclass of Appliance for Refrigerator

public class Refrigerator extends Appliance {

    // refrigerator-specific properties
    private int numberOfDoors;
    private int height;
    private int width;

    // constructor
    public Refrigerator(String itemNumber, String brand, int quantity, int wattage, String color, double price, int numberOfDoors, int height, int width){
        super(itemNumber, brand, quantity, wattage, color, price);
        this.numberOfDoors = numberOfDoors;
        this.height = height;
        this.width = width;
    }
    
    // getters
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    // convert door count to human-readable string
    public String getNumberOfDoorsDisplay() {
        switch (numberOfDoors) {
            case 2: return "Double Door";
            case 3: return "Three Doors";
            case 4: return "Four Doors";
            default: return String.valueOf(numberOfDoors) + " Doors";
        }
    }

    // override toString method for display
    @Override
    public String toString() {
        return super.toString() +
        "Number of Doors: " + getNumberOfDoorsDisplay() + "\n" +
        "Height: " + height + "\n" +
        "Width: " + width + "\n";
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
        String.valueOf(numberOfDoors),
        String.valueOf(height),
        String.valueOf(width)
        );
    }
}
