package com.example.hotel_project;

import BackEnd.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReceptionistCheckInController {

    @FXML private TextField usernameField;
    @FXML private ComboBox<PaymentMethod> paymentMethodBox;
    @FXML private Label messageLabel;
    @FXML private VBox invoiceBox;
    @FXML private Label invoiceGuestLabel;
    @FXML private Label invoiceRoomLabel;
    @FXML private Label invoiceDaysLabel;
    @FXML private Label invoiceTotalLabel;
    @FXML private Label invoicePaymentLabel;
    @FXML private Label invoiceDateLabel;

    @FXML
    public void initialize() {
        paymentMethodBox.setItems(FXCollections.observableArrayList(PaymentMethod.values()));
        paymentMethodBox.setValue(PaymentMethod.CASH);
        invoiceBox.setVisible(false);
        invoiceBox.setManaged(false);
    }

    @FXML
    private void handleCheckIn() {
        String username = usernameField.getText().trim();
        Guest guest = HotelDatabase.findGuest(username);

        if (guest == null) {
            showError("Guest not found!");
            return;
        }

        Receptionist receptionist = HotelDatabase.getCurrentReceptionist();
        if (receptionist == null) {
            showError("No receptionist logged in!");
            return;
        }

        invoiceBox.setVisible(false);
        invoiceBox.setManaged(false);
        receptionist.manageCheckIn(guest);
        showSuccess("Check-in successful for " + username);
    }

    @FXML
    private void handleCheckOut() {
        String username = usernameField.getText().trim();
        Guest guest = HotelDatabase.findGuest(username);

        if (guest == null) {
            showError("Guest not found!");
            return;
        }

        Receptionist receptionist = HotelDatabase.getCurrentReceptionist();
        if (receptionist == null) {
            showError("No receptionist logged in!");
            return;
        }

        PaymentMethod method = paymentMethodBox.getValue();
        if (method == null) {
            showError("Please select a payment method!");
            return;
        }

        // find confirmed reservation
        Reservation activeReservation = null;
        for (Reservation r : HotelDatabase.getReservations()) {
            if (r.getGuest().equals(guest) && r.getStatus() == ReservationStatus.CONFIRMED) {
                activeReservation = r;
                break;
            }
        }

        if (activeReservation == null) {
            showError("No confirmed reservation found for this guest!");
            return;
        }

        // calculate total
        long days = ChronoUnit.DAYS.between(activeReservation.getCheckInDate(), activeReservation.getCheckOutDate());
        double total = days * activeReservation.getRoom().getPricePerNight();

        // check balance
        if (guest.getBalance() < total) {
            showError("Payment failed! Insufficient balance. Balance: " + guest.getBalance() + ", Total: " + total);
            return;
        }

        // process payment
        guest.setBalance(guest.getBalance() - total);
        Invoice invoice = new Invoice(total, method, LocalDate.now());
        HotelDatabase.addInvoice(invoice);
        activeReservation.setStatus(ReservationStatus.COMPLETED);
        activeReservation.getRoom().setAvailable(true);

        // show invoice
        invoiceGuestLabel.setText("Guest: " + guest.getUsername());
        invoiceRoomLabel.setText("Room: " + activeReservation.getRoom().getRoomNumber());
        invoiceDaysLabel.setText("Nights stayed: " + days);
        invoiceTotalLabel.setText("Total Paid: $" + total);
        invoicePaymentLabel.setText("Payment Method: " + method);
        invoiceDateLabel.setText("Date: " + LocalDate.now());
        invoiceBox.setVisible(true);
        invoiceBox.setManaged(true);

        showSuccess("Check-out successful for " + username);
    }

    private void showError(String msg) {
        messageLabel.setStyle("-fx-text-fill: red;");
        messageLabel.setText(msg);
    }

    private void showSuccess(String msg) {
        messageLabel.setStyle("-fx-text-fill: green;");
        messageLabel.setText(msg);
    }
}