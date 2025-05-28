package com.modernappliances.appliances;

public class Dishwasher extends Appliance {

    // dishwasher-specific properties
    private String soundRating;
    private String feature;

    // constructor
    public Dishwasher(String itemNumber, String brand, int quantity, int wattage, String color, double price, String feature, String soundRating) {
        super(itemNumber, brand, quantity, wattage, color, price);
        this.feature = feature;
        this.soundRating = soundRating;

        // validate sound rating
        if (!soundRating.matches("Qt|Qr|Qu|M")) {
            throw new IllegalArgumentException("Invalid sound rating. Must be one of: Qt, Qr, Qu, M.");
        }
    }
    
    // getters
    public String getSoundRating() {
        return soundRating;
    }

    public String getFeature() {
        return feature;
    }

    // display friendly sound rating
    public String getSoundRatingDisplay() {
        switch (soundRating) {
            case "Qt": return "Quietest";
            case "Qr": return "Quieter";
            case "Qu": return "Quiet";
            case "M": return "Moderate";
            default: return soundRating;
        }
    }

    // override toString method for display
    @Override
    public String toString() {
        return super.toString() +
            "Feature: " + feature + "\n" +
            "Sound Rating: " + getSoundRatingDisplay() + "\n";
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
            feature,
            soundRating
        );
    }

}
