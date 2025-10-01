
// Client
public class StrategyPatternDemo {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Pay with Credit Card
        cart.setPaymentMethod(new CreditCardPayment("1234-5678-9876-5432"));
        cart.checkout(150);

        // Pay with PayPal
        cart.setPaymentMethod(new PayPalPayment("user@example.com"));
        cart.checkout(300);
    }
}
