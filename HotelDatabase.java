import java.util.ArrayList;
import java.util.List;

public class HotelDatabase {

    // 🔹 Data Storage (Static)
    public static List<Guest> guests = new ArrayList<>();
    public static List<Room> rooms = new ArrayList<>();
    public static List<Reservation> reservations = new ArrayList<>();
    public static List<Invoice> invoices = new ArrayList<>();
    public static List<RoomType> roomTypes = new ArrayList<>();
    public static List<Amenity> amenities = new ArrayList<>();


    // 🔹 Add Methods
    public static void addGuest(Guest guest) {
        guests.add(guest);
    }

    public static void addRoom(Room room) {
        rooms.add(room);
    }

    public static void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public static void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public static void addRoomType(RoomType type) {
        roomTypes.add(type);
    }

    public static void addAmenity(Amenity amenity) {
        amenities.add(amenity);
    }


    // 🔹 Find Methods
    public static Guest findGuest(String username) {
        for (Guest g : guests) {
            if (g.getUsername().equals(username)) {
                return g;
            }
        }
        return null;
    }

    public static Room findRoom(int roomNumber) {
        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber) {
                return r;
            }
        }
        return null;
    }

    public static Amenity findAmenity(int id) {
        for (Amenity a : amenities) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }


    // 🔹 Remove Methods
    public static void removeGuest(Guest guest) {
        guests.remove(guest);
    }

    public static void removeRoom(Room room) {
        rooms.remove(room);
    }

    public static void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public static void removeAmenity(Amenity amenity) {
        amenities.remove(amenity);
    }


    // 🔹 Dummy Data (مهم جدًا للتجربة)
    public static void initializeData() {

        // Amenities
        Amenity wifi = new Amenity(1, "WiFi", "High-speed internet");
        Amenity tv = new Amenity(2, "TV", "Smart TV");
        amenities.add(wifi);
        amenities.add(tv);

        // Room Types
        RoomType single = new RoomType(1, "Single", "Single bed room", 500);
        RoomType doubleRoom = new RoomType(2, "Double", "Double bed room", 800);
        roomTypes.add(single);
        roomTypes.add(doubleRoom);

        // Rooms
        List<Amenity> room1Amenities = new ArrayList<>();
        room1Amenities.add(wifi);
        room1Amenities.add(tv);

        Room room1 = new Room(101, 500, single, room1Amenities);
        rooms.add(room1);

        // Guest
        Guest guest1 = new Guest("abdullah", "1234", null, 1000, "Cairo", Gender.MALE, "Single");
        guests.add(guest1);
    }
}