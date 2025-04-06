package service;

import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.Date;

public class Driver {
    public static void main(String[] args) {
        CustomerService customerService = CustomerService.getInstance();
        ReservationService reservationService = ReservationService.getInstance();

        // Adding Customers
        customerService.addCustomer("shindeaditya07@gmail.com", "Aditya", "Shinde");
        customerService.addCustomer("test@example.com", "John", "Doe");

        System.out.println("Customers: ");
        for (Customer customer : customerService.getAllCustomers()) {
            System.out.println(customer);
        }

        // ✅ Adding 10 Rooms
        // Ensure rooms are added
        IRoom room1 = new Room("101", 200.0, RoomType.SINGLE, true);
        IRoom room2 = new Room("102", 300.0, RoomType.DOUBLE, true);
        reservationService.addRoom(room1);
        reservationService.addRoom(room2);


        // ✅ Print available rooms at startup
        System.out.println("Rooms in system: " + reservationService.getAllRooms());

        // Reserving a room
        Customer customer = customerService.getCustomer("shindeaditya07@gmail.com");
        Date checkinDate = new Date();
        Date checkoutDate = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 3)); // 3 days later

        reservationService.reserveARoom(customer, reservationService.getARoom("101"), checkinDate, checkoutDate);

        System.out.println("\nReservations:");
        reservationService.printAllReservation();
    }
}
