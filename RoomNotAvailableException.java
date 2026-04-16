public class RoomNotAvailableException extends Exception{
    public RoomNotAvailableException() {
        super("The requested room is currently not available for these dates.");
    }

    public RoomNotAvailableException(String message) {
        super(message);
    }
}
