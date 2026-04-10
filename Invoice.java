import java.time.LocalDate;
import java.util.Date;

public class Invoice {
    private static int idCounter = 0;
    private int id;
    private double totalAmount;
    private PaymentMethod paymentMethod;  //Enum
    private LocalDate paymentDate;


    public Invoice() {
    }

    public Invoice ( double totalAmount, PaymentMethod paymentMethod,  LocalDate paymentDate) {
        this.id = idCounter++;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDate getPaymentdate() {
        return paymentDate;
    }

    public void setPaymentdate(LocalDate paymentdate) {
        this.paymentDate = paymentdate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double calculateFinalTotal(double basePrice, int numberOfNights){
        this.totalAmount = basePrice * numberOfNights;
        return totalAmount;
    }

    public void pay(){
        System.out.println("Payment Receipt");
        System.out.println("Invoice ID: " + id);
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Payment Date: " + paymentDate);
        System.out.println("Payment successful.");
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", totalAmount=" + totalAmount +
                ", paymentMethod=" + paymentMethod +
                ", paymentDate=" + paymentDate +
                '}';
    }

}