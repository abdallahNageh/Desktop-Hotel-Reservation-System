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
        String user = scanner.next();
        System.out.print("Enter Admin Password: ");
        String pass = scanner.next();

        Admin admin = HotelDatabase.findAdmin(user, pass);
        if (admin != null) {
            System.out.println("\nWelcome Admin, " + user);
            System.out.println("1. View All Rooms");
            System.out.println("2. View All Guests");
            System.out.println("3. Add New Room");
            System.out.println("4. Back to Main Menu");
            int choice = getIntInput();

            if (choice == 1) {
                for (Room r : admin.viewRooms()) System.out.println("Room " + r.getRoomNumber() + " | Type: " + r.getRoomType().getName());
            } else if (choice == 2) {
                for (Guest g : admin.viewGuests()) System.out.println("Guest: " + g.getUsername());
            }
        } else {
            System.out.println("Login Failed! Admin not found.");
        }
    }

    // --- Receptionist Menu ---
    private static void receptionistMenu() {
        System.out.print("Enter Username: ");
        String user = scanner.next();
        System.out.print("Enter Password: ");
        String pass = scanner.next();

        Receptionist recep = HotelDatabase.findReceptionist(user, pass);
        if (recep != null) {
            System.out.println("\nWelcome Receptionist, " + user);
            System.out.println("1. Manage Check-In");
            System.out.println("2. Manage Check-Out");
            System.out.println("3. View Reservations");
            int choice = getIntInput();

            if (choice == 1) {
                System.out.print("Enter Guest Username for Check-In: ");
                Guest g = HotelDatabase.findGuest(scanner.next());
                if (g != null) recep.manageCheckIn(g);
                else System.out.println("Guest not found.");
            }
        } else {
            System.out.println("Login Failed!");
        }
    }

    // --- Guest Menu ---
    private static void guestMenu() {
        System.out.print("Enter Username: ");
        String user = scanner.next();
        System.out.print("Enter Password: ");
        String pass = scanner.next();

        Guest guest = HotelDatabase.findGuest(user);
        if (guest != null && guest.login(user, pass)) {
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
                    } catch (Exception e) { System.out.println(e.getMessage()); }
                }
            } else if (choice == 3) {
                var resList = guest.viewmyReservation();
                if (!resList.isEmpty()) {
                    try {
                        guest.checkout(resList.get(0));
                        System.out.println("Checked out successfully!");
                    } catch (Exception e) { System.out.println(e.getMessage()); }
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
        HotelDatabase.getAdmins().add(new Admin("admin", "admin", LocalDate.now(), 8));
        HotelDatabase.getReceptionists().add(new Receptionist("recep", "recep", LocalDate.now(), 8, "Front"));

        RoomType type = new RoomType(1, RoomTypeName.SINGLE, "Single Room", 500);
        HotelDatabase.addRoom(new Room(101, new ArrayList<>(), true, type));
        HotelDatabase.addRoom(new Room(102, new ArrayList<>(), true, type));
        HotelDatabase.findRoom(101).setPricePerNight(500);
        HotelDatabase.findRoom(102).setPricePerNight(500);
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}