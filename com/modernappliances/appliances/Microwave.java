package com.modernappliances.appliances;

public class Microwave extends Appliance {

    private double capacity;
    private String roomType;

    public Microwave(String itemNumber, String brand, int quantity, int wattage, String color, double price,
            double capacity, String roomType) {
        super(itemNumber, brand, quantity, wattage, color, price);
        this.capacity = capacity;
        this.roomType = roomType;

        if (!roomType.equalsIgnoreCase("K") && !roomType.equalsIgnoreCase("W")) {
            throw new IllegalArgumentException("Invalid room type. Must be either K (kitchen) or W (work site).");
        }
    }

    public double getCapacity() {
        return capacity;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomTypeDisplay() {
        return roomType.equalsIgnoreCase("K") ? "Kitchen" : "Work Site";
    }

    @Override
    public String toString() {
        return super.toString() +
                "Capacity: " + capacity + "\n" +
                "Room Type: " + getRoomTypeDisplay() + "\n";
    }

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
                String.valueOf(capacity),
                roomType);
    }
}
