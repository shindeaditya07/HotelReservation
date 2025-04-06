package model;

public class Room implements IRoom {
    private String roomNumber;
    private double price;
    private RoomType roomType;
    private boolean isAvailable; // This tracks the availability of the room

    public Room(String roomNumber, double price, RoomType roomType, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
        this.isAvailable = isAvailable;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public boolean isFree() {
        return isAvailable;  // Room is free if it is available
    }

    // Add the setAvailable method to set the availability of the room
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public void bookRoom() {
        setAvailable(false);  // Mark the room as booked (unavailable)
    }

    public void releaseRoom() {
        setAvailable(true);  // Mark the room as available
    }

    @Override
    public String toString() {
        return "Room: " + roomNumber + "\nPrice: " + price + "\nRoom Type: " + roomType + "\nAvailable: " + isAvailable;
    }
}
