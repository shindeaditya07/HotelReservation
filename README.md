# Hotel Reservation Application

A console-based Java application designed to manage hotel room reservations, customer information, and administrative operations. It allows users to create accounts, search for available rooms based on dates, make reservations, and provides admins with options to view all customers, rooms, and reservation details through a simple menu-driven interface.

---

## Project Structure

```
HotelReservationUdacity/
├── src/
│   ├── api/
│   │   ├── AdminResource.java
│   │   └── HotelResource.java
│   ├── model/
│   │   ├── Customer.java
│   │   ├── Driver.java
│   │   ├── FreeRoom.java
│   │   ├── IRoom.java
│   │   ├── Reservation.java
│   │   ├── Room.java
│   │   └── RoomType.java
│   ├── service/
│   │   ├── CustomerService.java
│   │   ├── Driver.java
│   │   └── ReservationService.java
│   ├── ui/
│   │   ├── AdminMenu.java
│   │   ├── HotelApplication.java
│   │   └── MainMenu.java
│   └── utils/
│       └── DateUtils.java
├── .gitignore
├── HotelReservationUdacity.iml
└── HotelReservationUdacity.zip
```

---

## Package Overview

### `api`
- **AdminResource.java**  
  ➔ Provides admin-related operations like viewing all customers, all rooms, and all reservations.

- **HotelResource.java**  
  ➔ Provides customer-facing operations like finding and booking rooms.

---

### `model`
- **Customer.java**  
  ➔ Represents a customer with fields for email, first name, and last name.

- **Driver.java**  
  ➔ Entry point for testing or debugging parts of the project.

- **FreeRoom.java**  
  ➔ A subclass of Room, representing a room offered for free.

- **IRoom.java**  
  ➔ An interface defining the structure for Room classes (methods like getRoomNumber, getPrice).

- **Reservation.java**  
  ➔ Represents a reservation made by a customer, linking them to a room and reservation dates.

- **Room.java**  
  ➔ Concrete implementation of IRoom, representing a standard hotel room.

- **RoomType.java**  
  ➔ Enum for Room types (SINGLE, DOUBLE).

---

### `service`
- **CustomerService.java**  
  ➔ Manages customer-related operations like adding and retrieving customers.

- **Driver.java**  
  ➔ (Duplicate name) Used for testing service layer functionalities separately.

- **ReservationService.java**  
  ➔ Handles reservation operations like booking a room, finding available rooms, and retrieving customer reservations.

---

### `ui`
- **AdminMenu.java**  
  ➔ CLI menu for Admin users to manage rooms and view customer information.

- **HotelApplication.java**  
  ➔ Main class to run the entire Hotel Reservation Application (starting point).

- **MainMenu.java**  
  ➔ CLI menu for Customer users to search rooms, book rooms, and create accounts.

---

### `utils`
- **DateUtils.java**  
  ➔ Utility class for handling date manipulations (like date validation, suggesting alternative dates, etc.).

---

## How to Run

1. Open the project in IntelliJ IDEA (or any Java IDE).
2. Run the **HotelApplication** class (`src/ui/HotelApplication.java`).
3. Follow the console instructions to make reservations or access admin options.

---

## Features
- Add new customers.
- Search for available rooms by date.
- Make room reservations.
- Admin view to see all customers, rooms, and reservations.
- Basic error handling and date validations.
- Suggest alternative booking dates if no rooms are available.

---

---
