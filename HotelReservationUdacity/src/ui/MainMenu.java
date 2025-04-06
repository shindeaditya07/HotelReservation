package ui;

import api.HotelResource;
import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import model.Reservation;
import utils.DateUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HotelResource hotelResource = HotelResource.getInstance();
    private static final AdminResource adminResource = AdminResource.getInstance();

    public static void main(String[] args) {
        runMainMenu();
    }

    public static void runMainMenu() {
        String choice;
        do {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin (Open Admin Menu)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1" -> findAndReserveRoom();
                case "2" -> viewMyReservations();
                case "3" -> createAccount();
                case "4" -> AdminMenu.runAdminMenu();
                case "5" -> System.out.println("Exiting application...");
                default -> System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        } while (!choice.equals("5"));
    }

    private static void findAndReserveRoom() {
        System.out.print("Enter check-in date (yyyy-mm-dd): ");
        String checkInStr = scanner.nextLine().trim();
        System.out.print("Enter check-out date (yyyy-mm-dd): ");
        String checkOutStr = scanner.nextLine().trim();

        Collection<IRoom> availableRooms = hotelResource.findARoom(checkInStr, checkOutStr);

        while (availableRooms.isEmpty()) {
            System.out.println("❌ No available rooms for selected dates.");
            System.out.print("Would you like to try different dates? (y/n): ");
            String tryNewDates = scanner.nextLine().trim().toLowerCase();
            if (!tryNewDates.equals("y")) {
                return;
            }
            System.out.print("Enter new check-in date (yyyy-mm-dd): ");
            checkInStr = scanner.nextLine().trim();
            System.out.print("Enter new check-out date (yyyy-mm-dd): ");
            checkOutStr = scanner.nextLine().trim();
            availableRooms = hotelResource.findARoom(checkInStr, checkOutStr);
        }

        System.out.println("✅ Available rooms:");
        availableRooms.forEach(System.out::println);

        IRoom selectedRoom = null;
        while (selectedRoom == null) {
            System.out.print("Enter room number to book: ");
            String roomId = scanner.nextLine().trim();
            selectedRoom = hotelResource.getARoom(roomId);

            if (selectedRoom == null || !availableRooms.contains(selectedRoom)) {
                System.out.print("❌ Invalid room number. Would you like to choose a different room? (y/n): ");
                String retryChoice = scanner.nextLine().trim().toLowerCase();
                if (!retryChoice.equals("y")) {
                    System.out.print("Would you like to change your booking dates? (y/n): ");
                    String changeDates = scanner.nextLine().trim().toLowerCase();
                    if (changeDates.equals("y")) {
                        findAndReserveRoom(); // Restart the booking process
                    }
                    return;
                }
                selectedRoom = null; // Reset selection
            }
        }

        System.out.print("Enter your email: ");
        String email = scanner.nextLine().trim().toLowerCase();

        while (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            System.out.print("❌ Invalid email format. Enter a valid email (e.g., example@domain.com): ");
            email = scanner.nextLine().trim().toLowerCase();
        }

        Customer customer = hotelResource.getCustomer(email);

        if (customer == null) {
            System.out.println("❌ No account found. Please create an account first.");
            return;
        }

        hotelResource.bookARoom(email, selectedRoom, checkInStr, checkOutStr);
        System.out.println("✅ Room booked successfully!");
    }

    private static void viewMyReservations() {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        Collection<Reservation> reservations = hotelResource.getCustomerReservations(email);

        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            reservations.forEach(System.out::println);
        }
    }

    private static void createAccount() {
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter your email (name@domain.com): ");
        String email = scanner.nextLine();

        hotelResource.createACustomer(email, firstName, lastName);
        System.out.println("Account created successfully!");
    }
}
