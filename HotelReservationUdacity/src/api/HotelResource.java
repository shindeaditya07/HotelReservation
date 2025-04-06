package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class HotelResource {
    private static final HotelResource INSTANCE = new HotelResource();
    private final CustomerService customerService = CustomerService.getInstance();
    private final ReservationService reservationService = ReservationService.getInstance();

    private HotelResource() {}

    public static HotelResource getInstance() {
        return INSTANCE;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    public Collection<IRoom> findARoom(String checkInStr, String checkOutStr) {
        Date checkIn = parseDate(checkInStr);
        Date checkOut = parseDate(checkOutStr);
        if (checkIn == null || checkOut == null) {
            System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
            return Collections.emptyList();
        }

        Collection<IRoom> availableRooms = reservationService.findRooms(checkIn, checkOut);

        if (availableRooms.isEmpty()) {
            System.out.println("❌ No rooms available. Suggesting new dates...");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(checkIn);
            calendar.add(Calendar.DAY_OF_MONTH, 3);  // Suggest a check-in 3 days later
            String newCheckIn = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

            calendar.setTime(checkOut);
            calendar.add(Calendar.DAY_OF_MONTH, 3);  // Suggest a check-out 3 days later
            String newCheckOut = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

            Collection<IRoom> alternativeRooms = reservationService.findRooms(parseDate(newCheckIn), parseDate(newCheckOut));

            if (!alternativeRooms.isEmpty()) {
                System.out.println("✅ Alternative rooms available for " + newCheckIn + " to " + newCheckOut + ": " + alternativeRooms);
            } else {
                System.out.println("❌ No alternative rooms available.");
            }
        }

        return availableRooms;
    }

    public Reservation bookARoom(String customerEmail, IRoom room, String checkInStr, String checkOutStr) {
        Customer customer = getCustomer(customerEmail);
        Date checkIn = parseDate(checkInStr);
        Date checkOut = parseDate(checkOutStr);

        if (customer == null || checkIn == null || checkOut == null) {
            System.out.println("Invalid input. Make sure customer exists and dates are correct.");
            return null;
        }
        Collection<Reservation> existingReservations = reservationService.getCustomerReservations(customer);
        for (Reservation res : existingReservations) {
            if (res.getRoom().equals(room) && res.getCheckinDate().equals(checkIn) && res.getCheckoutDate().equals(checkOut)) {
                System.out.println("❌ You already have a reservation for this room on these dates.");
                return null;
            }
        }

        return reservationService.reserveARoom(customer, room, checkIn, checkOut);
    }


    public Collection<Reservation> getCustomerReservations(String customerEmail) {
        Customer customer = getCustomer(customerEmail);
        return reservationService.getCustomerReservations(customer);
    }

    private Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    public IRoom getARoom(String roomId) {
        return reservationService.getARoom(roomId);
    }


}
