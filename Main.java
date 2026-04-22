import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        HotelDatabase.initializeData();

        setupInitialUsers();

        while (true) {
            System.out.println("\n========================================");
            System.out.println("   WELCOME TO ASU HOTEL SYSTEM   ");
            System.out.println("========================================");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Receptionist");
            System.out.println("3. Login as Guest");
            System.out.println("4. Register as New Guest");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> adminMenu();
                case 2 -> receptionistMenu();
                case 3 -> guestMenu();
                case 4 -> registerGuest();
                case 5 -> {
                    System.out.println("Exiting... Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // --- Admin Menu ---
    private static void adminMenu() {

        System.out.print("Enter Admin Username: ");
        String user = scanner.nextLine();

        System.out.print("Enter Admin Password: ");
        String pass = scanner.nextLine();

        // ❌ لو فشل
        if (!Admin.login(user, pass)) {
            System.out.println("Login Failed! Invalid username or password.");
            return;
        }

        // ✅ لو نجح
        System.out.println("\nWelcome Admin, " + user);

        // 🔁 Loop يبدأ بس بعد login ناجح
        while (true) {

            System.out.println("\n========== ADMIN MENU ==========");
            System.out.println("1. View All Rooms");
            System.out.println("2. View All Guests");
            System.out.println("3. Add New Room");
            System.out.println("4. Delete Room");
            System.out.println("5. Logout");
            System.out.print("Choose: ");

            int choice = getIntInput();

            switch (choice) {

                case 1 -> {
                    for (Room r : HotelDatabase.getCurrentAmdin().viewRooms()) {
                        System.out.println("Room " + r.getRoomNumber()
                                + " | Type: " + r.getRoomType().getName()
                                + " | Price: " + r.getPricePerNight());
                    }
                }

                case 2 -> {
                    for (Guest g : HotelDatabase.getCurrentAmdin().viewGuests()) {
                        System.out.println("Guest: " + g.getUsername()
                                + " | Balance: " + g.getBalance());
                    }
                }

                case 3 -> {
                    addRoomFlow(); // ✨ فصلناها عشان الكود يبقى نضيف
                }

                case 4 -> {
                    System.out.print("Enter Room Number to delete: ");
                    int num = getIntInput();

                    Room r = HotelDatabase.findRoom(num);

                    if (r != null) {
                        HotelDatabase.getRooms().remove(r);
                        System.out.println("Room Deleted!");
                    } else {
                        System.out.println("Room not found!");
                    }
                }

                case 5 -> {
                    System.out.println("Logging out...");
                    return;
                }

                default -> System.out.println("Invalid choice!");
            }
        }
    }
    // --- Receptionist Menu ---
    private static void receptionistMenu() {
        System.out.print("Enter Username: ");
        String user = scanner.next();
        System.out.print("Enter Password: ");
        String pass = scanner.next();

        Receptionist recep = HotelDatabase.findReceptionist(user, pass);

        // ❌ Login failed case
        if (recep == null) {
            System.out.println("Login Failed! Incorrect username or password.");
            return;
        }

        // ✅ Login success
        System.out.println("\nWelcome Receptionist, " + user);

        while (true) {
            System.out.println("\n1. Manage Check-In");
            System.out.println("2. Manage Check-Out");
            System.out.println("3. View Reservations");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");

            int choice = getIntInput();

            if (choice == 1) {
                System.out.print("Enter Guest Username for Check-In: ");
                Guest g = HotelDatabase.findGuest(scanner.next());
                if (g != null) recep.manageCheckIn(g);
                else System.out.println("Guest not found.");
            }

            else if (choice == 2) {
                System.out.print("Enter Guest Username for Check-Out: ");
                Guest g = HotelDatabase.findGuest(scanner.next());
                if (g != null) recep.manageCheckOut(g);
                else System.out.println("Guest not found.");
            }

            else if (choice == 3) {
                for (Reservation r : HotelDatabase.getReservations()) {
                    System.out.println("Guest: " + r.getGuest().getUsername() +
                            " | Room: " + r.getRoom().getRoomNumber() +
                            " | Status: " + r.getStatus());
                }
            }

            else if (choice == 4) {
                System.out.println("Logging out...");
                break; // go back to main menu
            }

            else {
                System.out.println("Invalid choice!");
            }
        }
    }
     //--- Guest Menu ---
    private static void guestMenu() {
        System.out.print("Enter Username: ");
        String user = scanner.next();
        System.out.print("Enter Password: ");
        String pass = scanner.next();

        Guest guest = HotelDatabase.findGuest(user);

        if (guest != null && guest.getPassword().equals(pass) /*guest.login(user, pass)*/) {
            System.out.println("\nWelcome, " + user + " | Balance: " + guest.getBalance());
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. Checkout & Pay");

            int choice = getIntInput();

            if (choice == 1) {
                guest.viewAvailableRooms();
            } else if (choice == 2) {
                System.out.print("Enter Room Number: ");
                Room r = HotelDatabase.findRoom(getIntInput());
                if (r != null) {
                    try {
                        guest.makeReservation(r, LocalDate.now(), LocalDate.now().plusDays(2));
                        System.out.println("Room booked successfully!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else if (choice == 3) {
                ArrayList<Reservation> resList = guest.viewmyReservation();
                if (!resList.isEmpty()) {
                    try {
                        guest.checkout(resList.get(0));
                        System.out.println("Checked out successfully!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("No active reservations found.");
                }
            }

        } else {
            System.out.println("Login Failed!");
        }
    }

    // --- Helper Methods ---
    private static void registerGuest() {
        System.out.print("Enter New Username: ");
        String user = scanner.next();
        System.out.print("Enter Password: ");
        String pass = scanner.next();
        Guest g = new Guest(user, pass);
        g.setBalance(1000.0); 
        HotelDatabase.addGuest(g);
        System.out.println("Guest registered successfully! You can login now.");
    }

    private static void setupInitialUsers() {
//        HotelDatabase.getAdmins().add(new Admin("admin", "admin", LocalDate.now(), 8));
//        HotelDatabase.getReceptionists().add(new Receptionist("recep", "recep", LocalDate.now(), 8, "Front"));
//
//        RoomType type = new RoomType(1, RoomTypeName.SINGLE, "Single Room", 500);
//        HotelDatabase.addRoom(new Room(101, new ArrayList<>(), true, type));
//        HotelDatabase.addRoom(new Room(102, new ArrayList<>(), true, type));
//        HotelDatabase.findRoom(101).setPricePerNight(500);
//        HotelDatabase.findRoom(102).setPricePerNight(500);
//        
//        Guest testGuest = new Guest("g1", "123");
//        HotelDatabase.addGuest(testGuest);
//
//        Room room = HotelDatabase.findRoom(101);
//
//        // Create reservation for today
//        Reservation res = new Reservation(
//        testGuest,
//        room,
//        LocalDate.now(),
//        //LocalDate.now().minusDays(0)
//        LocalDate.now().plusDays(1)
//        );
//
//        res.setStatus(ReservationStatus.CONFIRMED);
//        HotelDatabase.addReservation(res);

    // 1. Setup Staff
    HotelDatabase.getAdmins().add(new Admin("admin", "admin", LocalDate.now(), 8));
    HotelDatabase.getReceptionists().add(new Receptionist("recep", "recep", LocalDate.now(), 8, "Front"));

    // 2. Setup Rooms
    RoomType single = new RoomType(RoomTypeName.SINGLE, "Single Room", 500);
    HotelDatabase.addRoom(new Room(101, new ArrayList<>(), true, single));
    HotelDatabase.addRoom(new Room(102, new ArrayList<>(), true, single));
    HotelDatabase.addRoom(new Room(103, new ArrayList<>(), true, single));
    
    // Set prices (since your Room constructor might not take price directly)
    HotelDatabase.findRoom(101).setPricePerNight(500);
    HotelDatabase.findRoom(102).setPricePerNight(500);
    HotelDatabase.findRoom(103).setPricePerNight(500);

    // --- TEST DATA FOR RECEPTIONIST ---

    // Scenario A: The Valid Guest (Ready for Check-In Today)
    Guest g1 = new Guest("valid_guest", "123");
    HotelDatabase.addGuest(g1);
    Reservation res1 = new Reservation(g1, HotelDatabase.findRoom(101), LocalDate.now(), LocalDate.now().plusDays(2));
    res1.setStatus(ReservationStatus.CONFIRMED);
    HotelDatabase.addReservation(res1);

    // Scenario B: The Early Guest (Reservation is for next week)
    Guest g2 = new Guest("early_guest", "123");
    HotelDatabase.addGuest(g2);
    Reservation res2 = new Reservation(g2, HotelDatabase.findRoom(102), LocalDate.now().plusDays(7), LocalDate.now().plusDays(9));
    res2.setStatus(ReservationStatus.CONFIRMED);
    HotelDatabase.addReservation(res2);

    // Scenario C: The Guest with No Reservation
    Guest g3 = new Guest("no_res_guest", "123");
    HotelDatabase.addGuest(g3);
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    private static void addRoomFlow() {

        System.out.print("Enter Room Number: ");
        int roomNumber = getIntInput();

        if (HotelDatabase.findRoom(roomNumber) != null) {
            System.out.println("Room already exists!");
            return;
        }

        System.out.print("Enter Price: ");
        double price = getDoubleInput();

        System.out.println("Choose Room Type:");
        System.out.println("1. SINGLE");
        System.out.println("2. DOUBLE");
        System.out.println("3. SUITE");

        int typeChoice = getIntInput();

        RoomTypeName typeName = switch (typeChoice) {
            case 1 -> RoomTypeName.SINGLE;
            case 2 -> RoomTypeName.DOUBLE;
            case 3 -> RoomTypeName.SUITE;
            default -> RoomTypeName.SINGLE;
        };

        RoomType type = new RoomType( typeName, typeName.name(), price);

        ArrayList<Amenity> selectedAmenities = new ArrayList<>();

        System.out.println("\nAvailable Amenities:");
        for (int i = 0; i < HotelDatabase.getAmenities().size(); i++) {
            System.out.println((i + 1) + ". " +
                    HotelDatabase.getAmenities().get(i).getName());
        }

        System.out.println("Enter numbers (0 to finish):");

        while (true) {
            int a = getIntInput();

            if (a == 0) break;

            if (a >= 1 && a <= HotelDatabase.getAmenities().size()) {

                Amenity selected = HotelDatabase.getAmenities().get(a - 1);

                if (selectedAmenities.contains(selected)) {
                    System.out.println("Already selected!");
                } else {
                    selectedAmenities.add(selected);
                    System.out.println(selected.getName() + " added.");
                }

            } else {
                System.out.println("Invalid choice!");
            }
        }

        Room newRoom = new Room(roomNumber, selectedAmenities, true, type);
        newRoom.setPricePerNight(price);

        HotelDatabase.addRoom(newRoom);

        System.out.println("Room Added Successfully!");
    }
    private static double getDoubleInput() {
        while (true) {
            try {
                double input = Double.parseDouble(scanner.nextLine());
                return input;
            } catch (Exception e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
