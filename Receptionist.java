//import java.time.LocalDate;
//import java.util.ArrayList;
//public class Receptionist extends Staff{
//    private String department;
//
//    public Receptionist(String username, String password, LocalDate dateOfBirth, int workingHours, String department) {
//        super(username, password,dateOfBirth,workingHours,Role.RECEPTIONIST);
//        this.department = department;
//    }
//
//    public String getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(String department) {
//        this.department = department;
//    }
//    
//    public void manageCheckIn(Guest guest) {
//    for (Reservation r : Database.reservations) {
//        if (r.getGuest().equals(guest)) {
//            
//            if (r.getStatus() == ReservationStatus.CONFIRMED) {
//                System.out.println("Guest checked in successfully.");
//                // optional: mark room unavailable
//                r.getRoom().setAvailable(false);
//                return;
//            }
//        }
//    }
//    System.out.println("No valid reservation found for check-in.");
//}
//    
//    public void manageCheckOut(Guest guest) {
//    for (Reservation r : Database.reservations) {
//        if (r.getGuest().equals(guest) && 
//            r.getStatus() == ReservationStatus.CONFIRMED) {
//
//            // Generate invoice
//            Invoice invoice = new Invoice(r);
//            Database.invoices.add(invoice);
//
//            // Complete reservation
//            r.setStatus(ReservationStatus.COMPLETED);
//
//            // Make room available again
//            r.getRoom().setAvailable(true);
//
//            System.out.println("Check-out successful. Invoice generated.");
//            return;
//        }
//    }
//    System.out.println("No active reservation found.");
//}
//    
//    
//    
//    
//    
//    
//    
//}

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

//    // ✅ View Guests
//    public ArrayList<Guest> viewGuests() {
//        return HotelDatabase.getGuests();
//    }
//
//    // ✅ View Reservations
//    public ArrayList<Reservation> viewReservations() {
//        return HotelDatabase.getReservations();
//    }

    // ✅ Check-In
    public void manageCheckIn(Guest guest) {

        for (Reservation r : HotelDatabase.getReservations()) {

            if (r.getGuest().equals(guest)) {

                if (r.getStatus() == ReservationStatus.CONFIRMED) {

                    if (LocalDate.now().isBefore(r.getCheckindate())) {
                        System.out.println("Too early for check-in.");
                        return;
                    }

                    r.getRoom().setAvailable(false);

                    System.out.println("Guest checked in successfully.");
                    return;
                }
            }
        }

        System.out.println("No valid reservation found for check-in.");
    }

    // ✅ Check-Out
    public void manageCheckOut(Guest guest) {

        for (Reservation r : HotelDatabase.getReservations()) {

            if (r.getGuest().equals(guest) &&
                r.getStatus() == ReservationStatus.CONFIRMED) {

                long days = java.time.temporal.ChronoUnit.DAYS.between(
                        r.getCheckindate(),
                        r.getCheckoutdate()
                );

                double total = days * r.getRoom().getPricePerNight();

                Invoice invoice = new Invoice(total, PaymentMethod.CASH, LocalDate.now());

                HotelDatabase.addInvoice(invoice);

                r.setStatus(ReservationStatus.COMPLETED);

                r.getRoom().setAvailable(true);

                System.out.println("Check-out successful. Invoice generated.");
                return;
            }
        }

        System.out.println("No active reservation found.");
    }
}