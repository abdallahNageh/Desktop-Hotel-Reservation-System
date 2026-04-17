import java.util.ArrayList;
import java.util.List;

public class HotelDatabase {

    // 🔹 Data Storage
    private static ArrayList<Receptionist> receptionists = new ArrayList<>();
    private static ArrayList<Admin> admins = new ArrayList<>();
    private static ArrayList<Guest> guests = new ArrayList<>();
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();
    private static ArrayList<Invoice> invoices = new ArrayList<>();
    private static ArrayList<RoomType> roomTypes = new ArrayList<>();
    private static ArrayList<Amenity> amenities = new ArrayList<>();
    private static Admin currentAdmin = null ;
    private static Guest currentGuest = null ;
    private static Receptionist currentReceptionist = null ;

    public static Guest getCurrentGuest() {
        return currentGuest;
    }

    public static void setCurrentGuest(Guest currentGuest) {
        HotelDatabase.currentGuest = currentGuest;
    }

    public static Admin getCurrentAmdin() {
        return currentAdmin;
    }

    public  static void setCurrentAmdin(Admin currentAmdin) {
        HotelDatabase.currentAdmin = currentAmdin;
    }

    public static Receptionist getCurrentReceptionist() {
        return currentReceptionist;
    }

    public static void setCurrentReceptionist(Receptionist currentReceptionist) {
        HotelDatabase.currentReceptionist = currentReceptionist;
    }

    // 🔹 ADD
    public static void addGuest(Guest guest) {
        if (findGuest(guest.getUsername()) != null) {
            System.out.println("Guest with this username already exists.");
            return;
            }
        guests.add(guest);
    }

    public static void addRoom(Room room) {
        if (findRoom(room.getRoomNumber()) != null) {
            System.out.println("Room with this number already exists.");
            return;
            }
        rooms.add(room);
    }

    public static void addReservation(Reservation reservation) {
        if (reservation == null) {
            System.out.println("Invalid reservation.");
            return;
            }

        for (Reservation r : reservations) {

            if (r.getRoom().equals(reservation.getRoom()) &&
                r.getStatus() != ReservationStatus.CANCELLED) {

                if (!(reservation.getCheckOutDate().isBefore(r.getCheckInDate()) ||
                reservation.getCheckInDate().isAfter(r.getCheckOutDate()))) {

                    System.out.println("Room already booked for these dates.");
                    return;
                    }
            }
        }

        reservations.add(reservation);
    }

    public static void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public static void addRoomType(RoomType type) {
        for (RoomType rt : roomTypes) {
            if (rt.getId() == type.getId()) {
                System.out.println("RoomType with this ID already exists.");
                return;
                }
        }
        roomTypes.add(type);
    }

    public static void addAmenity(Amenity amenity) {
        if (findAmenity(amenity.getId()) != null) {
            System.out.println("Amenity with this ID already exists.");
            return;
        }
        amenities.add(amenity);
    }
    public static void addAdmin(Admin admin){admins.add(admin);}
    public static void addReceptionist(Receptionist receptionist){receptionists.add(receptionist);}

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
    public static Admin findAdmin(String name,String password){
        for (Admin a:admins){
            if(a.getUsername()== name && a.getPassword() == password)
                return a ;
        }
        return null ;
    }
    public static Receptionist findReceptionist(String name,String password){
        for (Receptionist a:receptionists){
            if(a.getUsername() == name && a.getPassword()==password)
                return a;
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

    public static ArrayList<Admin> getAdmins() {
        return admins;
    }
    public static ArrayList<Receptionist> getReceptionists() {
        return receptionists;
    }
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

        RoomType single = new RoomType(1, RoomTypeName.SINGLE, "Single room", 500);
        RoomType doubleRoom = new RoomType(2, RoomTypeName.DOUBLE, "Double room", 800);

        roomTypes.add(single);
        roomTypes.add(doubleRoom);

        List<Amenity> roomAmenities = new ArrayList<>();
        roomAmenities.add(wifi);
        roomAmenities.add(tv);

    }
}