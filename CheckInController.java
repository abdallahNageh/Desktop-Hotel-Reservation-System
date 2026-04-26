import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CheckInController {

    @FXML private TextField usernameField;
    @FXML private Label messageLabel;

    @FXML
    private void handleCheckIn() {
        String username = usernameField.getText().trim();
        Guest guest = HotelDatabase.findGuest(username);

        if (guest == null) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Guest not found!");
            return;
        }

        Receptionist receptionist = HotelDatabase.getCurrentReceptionist();
        if (receptionist == null) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("No receptionist logged in!");
            return;
        }

        receptionist.manageCheckIn(guest);
        messageLabel.setStyle("-fx-text-fill: green;");
        messageLabel.setText("Check-in successful for " + username);
    }

    @FXML
    private void handleCheckOut() {
        String username = usernameField.getText().trim();
        Guest guest = HotelDatabase.findGuest(username);

        if (guest == null) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("Guest not found!");
            return;
        }

        Receptionist receptionist = HotelDatabase.getCurrentReceptionist();
        if (receptionist == null) {
            messageLabel.setStyle("-fx-text-fill: red;");
            messageLabel.setText("No receptionist logged in!");
            return;
        }

        receptionist.manageCheckOut(guest);
        messageLabel.setStyle("-fx-text-fill: green;");
        messageLabel.setText("Check-out successful for " + username);
    }
}