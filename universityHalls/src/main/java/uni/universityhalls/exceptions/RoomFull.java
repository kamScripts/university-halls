package uni.universityhalls.exceptions;

public class RoomFull extends RuntimeException {
    public RoomFull(String RoomNumber) {
        super("Room " + RoomNumber + " reached maximum capacity");
    }
}
