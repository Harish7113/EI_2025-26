
// Context
public class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentMethod(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int totalAmount) {
        if (paymentStrategy == null) {
            System.out.println("No payment method selected!");
        } else {
            paymentStrategy.processPayment(totalAmount);
        }
    }
}
