import java.util.Date;

public class Guest {
    private String username ;
    private String password;
    private Date dateOfBirth ;
    private Gender gender;
    private double balance;
    private String address ;
    // private Gender gender ;
    private String roomPreferences ;
    public static int noOfguest = 0;

    public Guest(String username, String password, Date dateOfBirth, Gender gender, double balance, String address, String roomPreferences) {
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.balance = balance;
        this.address = address;
        this.roomPreferences = roomPreferences;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public double getBalance() {
        return balance;
    }

    public String getAddress() {
        return address;
    }

    public String getRoomPreferences() {
        return roomPreferences;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRoomPreferences(String roomPreferences) {
        this.roomPreferences = roomPreferences;
    }


    public void register(String username, String password, Date dateOfBirth, double balance, String address, String roomPreferences){
        if (noOfguest<256) {
            HotelDatabase.guests[noOfguest] = new Guest(username, password, dateOfBirth, balance, address, roomPreferences);
        }
        else {
            System.out.println("Storage completed");
        }
    }

    public boolean login(String username, String password) {
        boolean log = false;
        for (int i = 0; i < 256; i++) {
            if (HotelDatabase.guests[i].username.equals(username) && HotelDatabase.guests[i].password.equals(password)) {
                log = true;
                break;
            } else {
                log = false;
            }
        }
        return log;
    }




}
