import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReceptionistDashboardController {

    @FXML private Label totalGuestsLabel;
    @FXML private Label availableRoomsLabel;
    @FXML private Label activeReservationsLabel;

    @FXML
    public void initialize() {
        totalGuestsLabel.setText(String.valueOf(HotelDatabase.getGuests().size()));

        long availableRooms = HotelDatabase.getRooms().stream()
                .filter(Room::isAvailable).count();
        availableRoomsLabel.setText(String.valueOf(availableRooms));

        long activeReservations = HotelDatabase.getReservations().stream()
                .filter(r -> r.getStatus() == ReservationStatus.CONFIRMED).count();
        activeReservationsLabel.setText(String.valueOf(activeReservations));
    }
}
