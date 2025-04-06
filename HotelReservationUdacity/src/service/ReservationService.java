package service;

import model.*;

import java.util.*;

public class ReservationService {
    private static final ReservationService INSTANCE = new ReservationService();
    private Map<String, IRoom> rooms;
    private List<Reservation> reservations;

    private ReservationService() {
        rooms = new HashMap<>();
        reservations = new ArrayList<>();

        // Initialize rooms here (example)
        rooms.put("101", new Room("101", 200.0, RoomType.SINGLE,true));
        rooms.put("102", new Room("102", 300.0, RoomType.DOUBLE,true));
        rooms.put("103", new Room("103", 250.0, RoomType.SINGLE,true));
        rooms.put("104", new Room("104", 350.0, RoomType.DOUBLE,true));

        // Optionally, print out all the rooms
        System.out.println("Rooms initialized: " + rooms);
    }

    public static ReservationService getInstance() {
        return INSTANCE;
    }

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
        System.out.println("Room added: " + room);
    }

    public IRoom getARoom(String roomNumber) {
        return rooms.get(roomNumber);
    }

    public Collection<IRoom> getAllRooms() {
        return rooms.values();
    }

    public Collection<IRoom> findRooms(Date checkIn, Date checkOut) {
        System.out.println("Checking rooms for dates: " + checkIn + " to " + checkOut);

        Collection<IRoom> availableRooms = new ArrayList<>(rooms.values());
        System.out.println("Rooms before filtering: " + availableRooms);

        for (Reservation reservation : reservations) {
            Date existingCheckIn = reservation.getCheckinDate();
            Date existingCheckOut = reservation.getCheckoutDate();

            if (!(checkOut.before(existingCheckIn) || checkIn.after(existingCheckOut))) {
                availableRooms.remove(reservation.getRoom());
            }
        }

        System.out.println("Available rooms after filtering: " + availableRooms);
        return availableRooms;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkIn, Date checkOut) {
        // Check if room is available before reserving it
        if (!(room instanceof Room) || !((Room) room).isAvailable()) {
            System.out.println("Sorry, the room is not available.");
            return null; // Room cannot be reserved because it's not available
        }

        Reservation reservation = new Reservation(customer, room, checkIn, checkOut);
        reservations.add(reservation);

        // Mark the room as reserved
        ((Room) room).setAvailable(false);

        return reservation;
    }

    public Collection<Reservation> getCustomerReservations(Customer customer) {
        List<Reservation> customerReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }

    public void printAllReservation() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}

