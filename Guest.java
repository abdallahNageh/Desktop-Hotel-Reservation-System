import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Guest {
    private String username;
    private String password;
    private Date dateOfBirth;
    private Gender gender;
    private double balance;
    private String address;
    // private Gender gender ;
    private String roomPreferences;
    public static int noOfguest = 0;

    public boolean equals(Object o){
        if(o==null || !(o instanceof Guest)) return false;
        Guest guest = (Guest)o;
        return (this.username.equals(guest.getUsername())&& this.password.equals(guest.password));
    }



    public Guest(String username, String password) {
        this.username = username;
        this.password = password;
        noOfguest += 1;
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


    public void register(String username, String password, Gender gender, Date dataofbirth, String address, double balance, String roomPreferences) {
        Guest newobj = new Guest(username, password);
        newobj.setDateOfBirth(dataofbirth);
        newobj.setGender(gender);
        newobj.setAddress(address);
        newobj.setBalance(balance);
        newobj.setRoomPreferences(roomPreferences);
        HotelDatabase.addGuest(newobj);
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public ArrayList<Room> viewAvailableRooms() {
        ArrayList<Room> rooms = (ArrayList<Room>) HotelDatabase.getRooms();
        ArrayList<Room> Available = new ArrayList<>();
        for (Room r : rooms) {
            if (r.isAvailable() == true) {
                Available.add(r);
                System.out.println("room number : "+ r.getRoomNumber() +"\n" +  " room type : " +r.getRoomType()+"\n" +"Price / Night "+ r.getPricePerNight());

                for(int i = 0 ;i < r.getAmenities().size()  ; i++){
                    System.out.println(r.getAmenities().get(i).getName());
                }
            }
        }
        return Available;
    }


    public Reservation makeReservation(Room room,LocalDate checkin,  LocalDate checkout){
        Reservation newRev = new Reservation( this, room ,  checkin,  checkout);
        HotelDatabase.addReservation(newRev);
        return newRev ;
    }

    public ArrayList<Reservation> viewmyReservation(){
        ArrayList<Reservation> myReservation = new ArrayList<Reservation>();
        for (Reservation R : HotelDatabase.getReservations()) {
            if (R.getGuest().equals(this)) {
                 myReservation.add(R) ;
            }

        }
        return myReservation ;
    }

    public void  cancelReservation (Reservation reservation){
        reservation.setStatus(ReservationStatus.CANCELLED);
    }


    public Invoice checkout(Reservation reservation) {

        long days = java.time.temporal.ChronoUnit.DAYS.between(reservation.getCheckInDate(), reservation.getCheckOutDate());


        double total = days * reservation.getRoom().getPricePerNight();


        Invoice invoice = new Invoice(total, PaymentMethod.CASH , LocalDate.now());


        HotelDatabase.addInvoice(invoice);


        reservation.setStatus(ReservationStatus.COMPLETED);

        return invoice;
    }
}


