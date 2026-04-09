import java.util.ArrayList;
import java.util.List;

public class HotelDatabase {

    // 🔹 Data Storage
    private static ArrayList<Guest> guests = new ArrayList<>();
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();
    private static ArrayList<Invoice> invoices = new ArrayList<>();
    private static ArrayList<RoomType> roomTypes = new ArrayList<>();
    private static ArrayList<Amenity> amenities = new ArrayList<>();

    // 🔹 ADD
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

    // 🔹 FIND
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

    // 🔹 REMOVE
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

    // 🔹 GETTERS (optional)
    public static ArrayList<Guest> getGuests() {
        return guests;
    }

    public static ArrayList<Room> getRooms() {
        return rooms;
    }

    public static ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public static ArrayList<Amenity> getAmenities() {
        return amenities;
    }

    public static ArrayList<RoomType> getRoomTypes() {
        return roomTypes;
    }

    // 🔹 Dummy Data
    public static void initializeData() {

        Amenity wifi = new Amenity(1, "WiFi", "High speed internet");
        Amenity tv = new Amenity(2, "TV", "Smart TV");

        amenities.add(wifi);
        amenities.add(tv);

        RoomType single = new RoomType(1, "Single", "Single room", 500);
        RoomType doubleRoom = new RoomType(2, "Double", "Double room", 800);

        roomTypes.add(single);
        roomTypes.add(doubleRoom);

        List<Amenity> roomAmenities = new ArrayList<>();
        roomAmenities.add(wifi);
        roomAmenities.add(tv);

    }
}