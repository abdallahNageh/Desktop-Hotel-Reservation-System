import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class ReceptionistRoomsController {

    @FXML private TableView<Room> roomsTable;
    @FXML private TableColumn<Room, Integer> roomNumberCol;
    @FXML private TableColumn<Room, String> roomTypeCol;
    @FXML private TableColumn<Room, Double> priceCol;
    @FXML private TableColumn<Room, Boolean> availableCol;

    @FXML
    public void initialize() {
        roomNumberCol.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        roomTypeCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getRoomType().getName().toString()));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("pricePerNight"));
        availableCol.setCellValueFactory(data ->
                new SimpleBooleanProperty(data.getValue().isAvailable()).asObject());

        roomsTable.setItems(FXCollections.observableArrayList(HotelDatabase.getRooms()));
    }
}
