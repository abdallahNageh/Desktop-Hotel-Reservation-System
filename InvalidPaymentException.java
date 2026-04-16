public class InvalidPaymentException extends Exception{
    public InvalidPaymentException() {
        super("Payment failed: Insufficient balance or invalid payment method.");
    }

    public InvalidPaymentException(String message) {
        super(message);
    }
}
