import java.util.List;

public class Room {

    private int roomNumber ;
    private double pricePerNight;  // basePrice from RoomType class
    private List<Amenity> amenities;
    private boolean isAvailable ;
    private RoomType roomType;

    public Room(){}

    public Room(int roomNumber, List<Amenity> amenities, boolean isAvailable, RoomType roomType) {
        this.roomNumber = roomNumber;
        // this.pricePerNight = pricePerNight;
        this.amenities = amenities;
        this.isAvailable = isAvailable;
        this.roomType = roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}