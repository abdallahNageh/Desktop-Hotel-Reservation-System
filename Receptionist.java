import java.util.Date;

public class Receptionist extends Staff{
    private String department;

    public Receptionist(String username, String password, Date dateOfBirth, int workingHours, String department) {
        super(username, password, dateOfBirth, workingHours);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
