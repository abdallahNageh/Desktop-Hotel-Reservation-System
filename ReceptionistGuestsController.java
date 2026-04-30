import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReceptionistGuestsController {

    @FXML private TableView<Guest> guestsTable;
    @FXML private TableColumn<Guest, String> usernameCol;
    @FXML private TableColumn<Guest, String> dobCol;
    @FXML private TableColumn<Guest, String> genderCol;
    @FXML private TableColumn<Guest, String> addressCol;
    @FXML private TableColumn<Guest, Double> balanceCol;

    @FXML
    public void initialize() {
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        balanceCol.setCellValueFactory(new PropertyValueFactory<>("balance"));

        guestsTable.setItems(FXCollections.observableArrayList(HotelDatabase.getGuests()));
    }
}
