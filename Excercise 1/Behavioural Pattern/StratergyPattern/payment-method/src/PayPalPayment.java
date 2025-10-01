
// Concrete Strategy
public class PayPalPayment implements PaymentStrategy {
    private final String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void processPayment(int amount) {
        System.out.println(amount + " paid via PayPal account: " + email);
    }
}
