import java.time.LocalDate;
import java.util.Date;
public class Reservation {
    private int id= 0;
    private Guest guest;
    private Room room;
    private LocalDate Checkindate ;
    private LocalDate Checkoutdate ;
    private ReservationStatus status;

    public Reservation(Guest guest, Room room, LocalDate checkindate, LocalDate checkoutdate) {
        this.guest = guest;
        this.room = room;
        Checkindate = checkindate;
        Checkoutdate = checkoutdate;
        this.id+=1;
    }

    public int getId() {
        return id;
    }


    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getCheckindate() {
        return Checkindate;
    }

    public void setCheckindate(LocalDate checkindate) {
        Checkindate = checkindate;
    }

    public LocalDate getCheckoutdate() {
        return Checkoutdate;
    }

    public void setCheckoutdate(LocalDate checkoutdate) {
        Checkoutdate = checkoutdate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
