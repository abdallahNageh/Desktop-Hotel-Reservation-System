import java.time.LocalDate;
import java.util.ArrayList;

public class Receptionist extends Staff {

    private String department;

    public Receptionist(String username, String password, LocalDate dateOfBirth, int workingHours, String department) {
        super(username, password, dateOfBirth, workingHours, Role.RECEPTIONIST);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // Check-In
    public void manageCheckIn(Guest guest) {

        for (Reservation r : HotelDatabase.getReservations()) {
        if (r.getGuest().equals(guest)) {
            // Check if they are already checked in
            if (r.getStatus() == ReservationStatus.CHECKED_IN) {
                System.out.println("Guest is already checked in to room " + r.getRoom().getRoomNumber());
                return;
            }

            if (r.getStatus() == ReservationStatus.CONFIRMED) {
                if (LocalDate.now().isBefore(r.getCheckInDate())) {
                    System.out.println("Too early for check-in.");
                    return;
                }

                // Update both the room and the reservation status
                r.getRoom().setAvailable(false);
                r.setStatus(ReservationStatus.CHECKED_IN); // Key fix here

                System.out.println("Guest checked in successfully.");
                return;
            }
        }
    }
    System.out.println("No valid reservation found for check-in.");
    }

    // Check-Out
    public void manageCheckOut(Guest guest) {
    for (Reservation r : HotelDatabase.getReservations()) {
        if (r.getGuest().equals(guest) && r.getStatus() == ReservationStatus.CHECKED_IN) {

            long days = java.time.temporal.ChronoUnit.DAYS.between(
                    r.getCheckInDate(),
                    r.getCheckOutDate()
            );

            // FIX: If check-in and check-out are the same day, charge for 1 night
            if (days <= 0) {
                days = 1;
            }

            double total = days * r.getRoom().getPricePerNight();

            Invoice invoice = new Invoice(total, PaymentMethod.CASH, LocalDate.now());
            HotelDatabase.addInvoice(invoice);

            r.setStatus(ReservationStatus.COMPLETED);
            r.getRoom().setAvailable(true);

            System.out.println("Check-out successful. Total Charge: " + total);
            System.out.println("Invoice generated for " + days + " night(s).");
            return;
            }
        }
        System.out.println("No active stay found for this guest.");
    }
    public static boolean login(String name ,String password){
        HotelDatabase.setCurrentReceptionist(HotelDatabase.findReceptionist(name,password));
        if (HotelDatabase.getCurrentReceptionist() == null)
            return false ;
        return true ;
    }
}