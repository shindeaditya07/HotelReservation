package model;

public interface IRoom {
    String getRoomNumber();
    double getPrice();
    RoomType getRoomType();
    boolean isAvailable();  // You may already have this

    // Add this method to the IRoom interface
    boolean isFree();
}
