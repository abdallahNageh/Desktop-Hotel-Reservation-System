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
        loadScreen("ManageReceptionistDashboard.fxml");
    }

    @FXML
    private void showGuests() {
        loadScreen("ManageReceptionistGuests.fxml");
    }

    @FXML
    private void showRooms() {
        loadScreen("ManageReceptionistRooms.fxml");
    }

    @FXML
    private void showReservations() {
        loadScreen("ManageReceptionistReservations.fxml");
    }

    @FXML
    private void showCheckIn() {
        loadScreen("ManageReceptionistCheckIn.fxml");
    }

    @FXML
    private void logout() {
        contentArea.getScene().getWindow().hide();
    }
}
