import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Staff {

    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private int workingHours;
    private Role role;

    Staff(String username, String password, LocalDate dateOfBirth, int workingHours, Role role) {
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.workingHours = workingHours;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public boolean login(String user, String pass) {
        return username.equals(user) && password.equals(pass);
    }

    public ArrayList<Guest> viewGuests() {
        return HotelDatabase.getGuests();
    }

    public ArrayList<Room> viewRooms() {
        return HotelDatabase.getRooms();
    }

    public ArrayList<Reservation> viewReservations() {
        return HotelDatabase.getReservations();
    }
}