import java.util.Date;
import java.util.List;

public class Admin extends Staff {

    private List<String> permissions;

    public Admin(String username, String password, Date dateOfBirth, int workingHours, List<String> permissions) {
        super(username, password, dateOfBirth, workingHours);
        this.permissions = permissions;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
