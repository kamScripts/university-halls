package uni.universityhalls.exceptions;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(String RoomNumber) {
        super("Room " + RoomNumber + " not found.");
    }
}
