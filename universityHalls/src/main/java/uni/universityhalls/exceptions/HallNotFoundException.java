package uni.universityhalls.exceptions;

public class HallNotFoundException extends RuntimeException {
    public HallNotFoundException(String hallName) {
        super("Hall not found: " + hallName);
    }
}
