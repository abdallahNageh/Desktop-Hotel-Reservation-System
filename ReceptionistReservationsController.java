import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;

public class ReceptionistReservationsController {

    @FXML private TableView<Reservation> reservationsTable;
    @FXML private TableColumn<Reservation, String> guestCol;
    @FXML private TableColumn<Reservation, String> roomCol;
    @FXML private TableColumn<Reservation, String> checkInCol;
    @FXML private TableColumn<Reservation, String> checkOutCol;
    @FXML private TableColumn<Reservation, String> statusCol;

    @FXML
    public void initialize() {
        guestCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getGuest().getUsername()));
        roomCol.setCellValueFactory(data ->
                new SimpleStringProperty(String.valueOf(data.getValue().getRoom().getRoomNumber())));
        checkInCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getCheckInDate().toString()));
        checkOutCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getCheckOutDate().toString()));
        statusCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getStatus().toString()));

        reservationsTable.setItems(FXCollections.observableArrayList(HotelDatabase.getReservations()));
    }
}
