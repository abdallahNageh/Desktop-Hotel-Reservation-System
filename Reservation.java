import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private int id;
    private Guest guest;
    private Room room;
    private LocalDate checkInDate ;
    private LocalDate checkOutDate ;
    private ReservationStatus status;


public Reservation(int id, Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
    this.id = id;
    this.guest = guest;
    this.room = room;

    if (checkOutDate.isBefore(checkInDate) || checkOutDate.isEqual(checkInDate)) {
        throw new IllegalArgumentException("Check out date must be after check in date.");
    }

    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;

    this.status = ReservationStatus.PENDING;
}

public void confirm() {
    if (this.status == ReservationStatus.PENDING) {
        this.status = ReservationStatus.CONFIRMED;
    } else {
        throw new IllegalStateException("Only pending reservations can be confirmed.");
    }
}

public void cancel() {
    if (this.status != ReservationStatus.COMPLETED && this.status != ReservationStatus.CANCELLED) {
        this.status = ReservationStatus.CANCELLED;
    } else {
        throw new IllegalStateException("Cannot cancel a completed or already cancelled reservation.");
    }
}

public double getTotalPrice() {
    long daysStayed = ChronoUnit.DAYS.between(checkInDate, checkOutDate);

    return daysStayed * room.getPricePerNight();
}

public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
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

public LocalDate getCheckInDate() {
    return checkInDate;
}
public void setCheckInDate(LocalDate checkInDate) {
    if (checkInDate.isAfter(this.checkOutDate)) {
        throw new IllegalArgumentException("Check in date cannot be after check out date.");
    }
    this.checkInDate = checkInDate;
}

public LocalDate getCheckOutDate() {
    return checkOutDate;
}
public void setCheckOutDate(LocalDate checkOutDate) {
    if (checkOutDate.isBefore(this.checkInDate) || checkOutDate.isEqual(this.checkInDate)) {
        throw new IllegalArgumentException("Check out date must be after check in date.");
    }
    this.checkOutDate = checkOutDate;
}

public ReservationStatus getStatus() {
    return status;
}
public void setStatus(ReservationStatus status) {
    this.status = status;
}
}