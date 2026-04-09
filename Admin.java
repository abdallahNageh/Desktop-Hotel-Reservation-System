import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
public class Admin extends Staff {
    public Admin(String username, String password, LocalDate dateOfBirth, int workingHours) {
        super(username, password, dateOfBirth, workingHours,Role.ADMIN);
    }
    public void addRoom(Room room) {
        HotelDatabase.addRoom(room);
    }

    public void removeRoom(Room room) {
        HotelDatabase.removeRoom(room);
    }

    // 🔹 CRUD RoomTypes
    public void addRoomType(RoomType type) {
        HotelDatabase.addRoomType(type);
    }

    public void removeRoomType(RoomType type) {
        HotelDatabase.getRoomTypes().remove(type);
    }
    public void addAmenity(Amenity amenity) {
        HotelDatabase.addAmenity(amenity);
    }

    public void removeAmenity(Amenity amenity) {
        HotelDatabase.removeAmenity(amenity);
    }

    }








