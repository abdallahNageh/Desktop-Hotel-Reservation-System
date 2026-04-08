import java.util.Date;
public abstract class Staff {

    private String username ;
    private String password;
    private Date dateOfBirth;
    private int workingHours ;

    Staff(String username ,String password, Date dateOfBirth ,int workingHours ){
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.workingHours = workingHours;

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

    public int getWorkingHours() {
        return workingHours;
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

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }

    public boolean login(){
        return false;
    }
}

// abdallah nageh
