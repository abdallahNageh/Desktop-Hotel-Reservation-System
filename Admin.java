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
    public static boolean login(String name ,String password){
        HotelDatabase.setCurrentAmdin(HotelDatabase.findAdmin(name,password));
        if (HotelDatabase.getCurrentAmdin() == null)
            return false ;
        return true ;
    }
    public boolean updateRoomPrize(int roomNumber , double newPrice) {
        Room room = HotelDatabase.findRoom(roomNumber);
        if (room == null)
            return false;
        else {
            room.setPricePerNight(newPrice);
            return true ;
        }
    }
    public boolean updateRoomAvailability(int roomNumber, boolean status) {
        Room room = HotelDatabase.findRoom(roomNumber);
        if (room != null) {
            room.setAvailable(status);
            return true ;
        }
        return false ;
    }
    public boolean updateRoomType(int roomNumber, RoomType newType) {
        Room room = HotelDatabase.findRoom(roomNumber);
        if (room != null) {
            room.setRoomType(newType);
            return true ;
        }
        return false ;
    }
    public boolean updateRoomAmenities(int roomNumber, List<Amenity> newAmenities) {
        Room room = HotelDatabase.findRoom(roomNumber);
        if (room != null) {
            room.setAmenities(newAmenities);
            return true;
        }
        return false;
    }
}









