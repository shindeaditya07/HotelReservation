package model;

public class FreeRoom extends Room {
    public FreeRoom(String roomNumber, RoomType roomType) {
        super(roomNumber, 0.0, roomType, true); // Free rooms always have price = 0.0 and are available
    }

    @Override
    public String toString() {
        return "Free Room\nRoom Number: " + getRoomNumber() +
                "\nRoom Type: " + getRoomType() +
                "\nPrice: Free";
    }
}
