import java.time.LocalDate;
import java.util.Date;

public class Invoice {
    private int id;
    private double totalAmount;
    private PaymentMethod paymentMethod;
    private LocalDate paymentdate ;

    public Invoice(double totalAmount, PaymentMethod paymentMethod, LocalDate paymentdate) {
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.paymentdate = paymentdate;
        this.id +=1 ;
    }
}
