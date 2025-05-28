// Lab 0: Introduction to Java (Modern Appliances)
// Created by:
//    Aryan Bhanot
//    Melbourne Marsden
//    Amrit Reddy
//    Harsimar Singh
//    Jasmeet Singh

package com.modernappliances.application;

import com.modernappliances.appliances.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class ModernAppliances {
    private static final String FILE_NAME = "appliances.txt";
    private static List<Appliance> appliances = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadAppliances();
        displayMainMenu();
        scanner.close();
    }

    /**
     * Loads appliances from the specified file.
     * If the file does not exist, it initializes an empty list.
     */
    private static void loadAppliances() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Appliance appliance = parseAppliance(line);
                if (appliance != null) {
                    appliances.add(appliance);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading appliances: " + e.getMessage());
        }
    }


    /**
     * Parses a line from the file and creates an Appliance object.
     * Returns null if the line is invalid or does not match any appliance type.
     */
    private static Appliance parseAppliance(String line) {
        String[] parts = line.split(";");
        if (parts.length < 7)
            return null;

        try {
            String itemNumber = parts[0];
            String brand = parts[1];
            int quantity = Integer.parseInt(parts[2]);
            int wattage = Integer.parseInt(parts[3]);
            String color = parts[4];
            double price = Double.parseDouble(parts[5]);

            switch (itemNumber.charAt(0)) {
                case '1': // Refrigerator
                    if (parts.length == 9) {
                        return new Refrigerator(itemNumber, brand, quantity, wattage, color, price,
                                Integer.parseInt(parts[6]), Integer.parseInt(parts[7]), Integer.parseInt(parts[8]));
                    }
                    break;
                case '2': // Vacuum
                    if (parts.length == 8) {
                        return new Vacuum(itemNumber, brand, quantity, wattage, color, price,
                                parts[6], Integer.parseInt(parts[7]));
                    }
                    break;
                case '3': // Microwave
                    if (parts.length == 8) {
                        return new Microwave(itemNumber, brand, quantity, wattage, color, price,
                                Double.parseDouble(parts[6]), parts[7]);
                    }
                    break;
                case '4': // Dishwasher
                case '5':
                    if (parts.length == 8) {
                        return new Dishwasher(itemNumber, brand, quantity, wattage, color, price,
                                parts[6], parts[7]);
                    }
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error parsing appliance: " + e.getMessage());
        }
        return null;
    }

    /**
     * Displays the main menu and handles user input.
     * Provides options to check out appliances, find by brand, display by type,
     * produce a random list, or save and exit.
     */
    private static void displayMainMenu() {
        while (true) {
            System.out.println("\nWelcome to Modern Appliances!");
            System.out.println("How may we assist you?\n");
            System.out.println("1 - Check out appliance");
            System.out.println("2 - Find appliances by brand");
            System.out.println("3 - Display appliances by type");
            System.out.println("4 - Produce random appliance list");
            System.out.println("5 - Save & exit");
            System.out.print("\nEnter option: ");

            try {
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        checkoutAppliance();
                        break;
                    case 2:
                        findAppliancesByBrand();
                        break;
                    case 3:
                        displayAppliancesByType();
                        break;
                    case 4:
                        produceRandomApplianceList();
                        break;
                    case 5:
                        saveAppliances();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    /**
     * Checks out an appliance by item number.
     * If the appliance is in stock, it decrements the quantity and confirms checkout.
     * If not found or out of stock, it informs the user.
     */
    private static void checkoutAppliance() {
        System.out.print("\nEnter the item number of an appliance: ");
        String itemNumber = scanner.nextLine();

        Optional<Appliance> appliance = appliances.stream()
                .filter(a -> a.getItemNumber().equals(itemNumber))
                .findFirst();

        if (appliance.isPresent()) {
            Appliance found = appliance.get();
            if (found.isInStock()) {
                found.decrementQuantity();
                System.out.println("\nAppliance \"" + itemNumber + "\" has been checked out.");
            } else {
                System.out.println("\nThe appliance is not available to be checked out.");
            }
        } else {
            System.out.println("\nNo appliances found with that item number.");
        }
    }

    /**
     * Finds appliances by brand and displays them.
     * If no appliances match the brand, it informs the user.
     */
    private static void findAppliancesByBrand() {
        System.out.print("\nEnter brand to search for: ");
        String brand = scanner.nextLine();

        List<Appliance> matching = appliances.stream()
                .filter(a -> a.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());

        if (matching.isEmpty()) {
            System.out.println("\nNo appliances found for brand: " + brand);
        } else {
            System.out.println("\nMatching Appliances:");
            matching.forEach(System.out::println);
        }
    }

    /**
     * Displays appliances by type and allows filtering based on specific criteria.
     * The user can choose from refrigerators, vacuums, microwaves, or dishwashers.
     */
    private static void displayAppliancesByType() {
        System.out.println("\nAppliance Types");
        System.out.println("1 - Refrigerators");
        System.out.println("2 - Vacuums");
        System.out.println("3 - Microwaves");
        System.out.println("4 - Dishwashers");
        System.out.print("\nEnter type of appliance: ");

        try {
            int type = Integer.parseInt(scanner.nextLine());
            switch (type) {
                case 1:
                    filterRefrigerators();
                    break;
                case 2:
                    filterVacuums();
                    break;
                case 3:
                    filterMicrowaves();
                    break;
                case 4:
                    filterDishwashers();
                    break;
                default:
                    System.out.println("Invalid appliance type.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    /**
     * Filters refrigerators based on the number of doors.
     * Prompts the user for input and displays matching refrigerators.
     */
    private static void filterRefrigerators() {
        System.out.print("\nEnter number of doors: 2 (double door), 3 (three doors) or 4 (four doors): ");
        try {
            int doors = Integer.parseInt(scanner.nextLine());
            List<Appliance> matching = appliances.stream()
                    .filter(a -> a instanceof Refrigerator)
                    .filter(a -> ((Refrigerator) a).getNumberOfDoors() == doors)
                    .collect(Collectors.toList());

            if (matching.isEmpty()) {
                System.out.println("\nNo refrigerators found with " + doors + " doors.");
            } else {
                System.out.println("\nMatching refrigerators:");
                matching.forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    /**
     * Filters vacuums based on battery voltage.
     * Prompts the user for input and displays matching vacuums.
     */
    private static void filterVacuums() {
        System.out.print("\nEnter battery voltage value. 18 V (low) or 24 V (high): ");
        try {
            int voltage = Integer.parseInt(scanner.nextLine());
            List<Appliance> matching = appliances.stream()
                    .filter(a -> a instanceof Vacuum)
                    .filter(a -> ((Vacuum) a).getBatteryVoltage() == voltage)
                    .collect(Collectors.toList());

            if (matching.isEmpty()) {
                System.out.println("\nNo vacuums found with " + voltage + "V battery.");
            } else {
                System.out.println("\nMatching vacuums:");
                matching.forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    /**
     * Filters microwaves based on the room type.
     * Prompts the user for input and displays matching microwaves.
     */
    private static void filterMicrowaves() {
        System.out.print("\nRoom where the microwave will be installed: K (kitchen) or W (work site): ");
        String roomType = scanner.nextLine().toUpperCase();

        List<Appliance> matching = appliances.stream()
                .filter(a -> a instanceof Microwave)
                .filter(a -> ((Microwave) a).getRoomType().equalsIgnoreCase(roomType))
                .collect(Collectors.toList());

        if (matching.isEmpty()) {
            System.out.println("\nNo microwaves found for room type: " + roomType);
        } else {
            System.out.println("\nMatching microwaves:");
            matching.forEach(System.out::println);
        }
    }

    /**
     * Filters dishwashers based on the sound rating.
     * Prompts the user for input and displays matching dishwashers.
     */
    private static void filterDishwashers() {
        System.out.print(
                "\nEnter the sound rating of the dishwasher: Qt (Quietest), Qr (Quieter), Qu (Quiet) or M (Moderate): ");
        String soundRating = scanner.nextLine();

        List<Appliance> matching = appliances.stream()
                .filter(a -> a instanceof Dishwasher)
                .filter(a -> ((Dishwasher) a).getSoundRating().equalsIgnoreCase(soundRating))
                .collect(Collectors.toList());

        if (matching.isEmpty()) {
            System.out.println("\nNo dishwashers found with sound rating: " + soundRating);
        } else {
            System.out.println("\nMatching dishwashers:");
            matching.forEach(System.out::println);
        }
    }

    /**
     * Produces a random list of appliances based on user input.
     * Prompts the user for the number of appliances to display.
     * If the input is invalid, it informs the user.
     */
    private static void produceRandomApplianceList() {
        System.out.print("\nEnter number of appliances: ");
        try {
            int count = Integer.parseInt(scanner.nextLine());
            if (count <= 0) {
                System.out.println("Please enter a positive number.");
                return;
            }

            Collections.shuffle(appliances);
            System.out.println("\nRandom appliances:");
            appliances.stream()
                    .limit(count)
                    .forEach(System.out::println);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    /**
     * Saves the current list of appliances to the specified file.
     * Each appliance is saved in a specific format for later retrieval.
     */
    private static void saveAppliances() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            appliances.forEach(appliance -> writer.println(appliance.toFileString()));
            System.out.println("\nAppliances saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving appliances: " + e.getMessage());
        }
    }
}