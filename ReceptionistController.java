import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ReceptionistController {

    @FXML
    private StackPane contentArea;

    private void loadScreen(String fxmlFile) {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource(fxmlFile));
            contentArea.getChildren().setAll(pane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showDashboard() {
        loadScreen("dashboard.fxml");
    }

    @FXML
    private void showGuests() {
        loadScreen("guests.fxml");
    }

    @FXML
    private void showRooms() {
        loadScreen("rooms.fxml");
    }

    @FXML
    private void showReservations() {
        loadScreen("reservations.fxml");
    }

    @FXML
    private void showCheckIn() {
        loadScreen("checkin.fxml");
    }

    @FXML
    private void logout() {
        contentArea.getScene().getWindow().hide();
    }
}
