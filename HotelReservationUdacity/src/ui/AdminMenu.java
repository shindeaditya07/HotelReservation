package ui;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AdminResource adminResource = AdminResource.getInstance();

    public static void runAdminMenu(){
        String choice;
        do {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. See all customers");
            System.out.println("2. See all rooms");
            System.out.println("3. See all reservations");
            System.out.println("4. Add a room");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1" -> viewAllCustomers();
                case "2" -> viewAllRooms();
                case "3" -> viewAllReservations();
                case "4" -> addRoom();
                case "5" -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        } while (!choice.equals("5"));
    }

    private static void viewAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            customers.forEach(System.out::println);
        }
    }

    private static void viewAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms available.");
        } else {
            rooms.forEach(System.out::println);
        }
    }

    private static void viewAllReservations() {
        adminResource.displayAllReservations();
    }

    private static void addRoom() {
        System.out.print("Enter room number: ");
        String roomNumber = scanner.nextLine().trim();

        double pricePerNight = -1;
        while (pricePerNight < 0) {
            System.out.print("Enter price per night: ");
            String priceInput = scanner.nextLine().trim();
            try {
                pricePerNight = Double.parseDouble(priceInput);
                if (pricePerNight < 0) {
                    System.out.println("❌ Price cannot be negative. Please enter a valid price.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a numeric value for the price.");
            }
        }

        RoomType roomType = null;
        while (roomType == null) {
            System.out.print("Enter room type (SINGLE/DOUBLE): ");
            String roomTypeInput = scanner.nextLine().trim().toUpperCase();
            try {
                roomType = RoomType.valueOf(roomTypeInput);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid room type. Please enter 'SINGLE' or 'DOUBLE'.");
            }
        }

        Room newRoom = new Room(roomNumber, pricePerNight, roomType, true);
        adminResource.addRoom(List.of(newRoom));
        System.out.println("✅ Room added successfully!");
    }

}
