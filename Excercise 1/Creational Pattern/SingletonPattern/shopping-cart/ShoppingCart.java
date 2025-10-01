import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private static ShoppingCart instance;
    private List<Product> items;

    private ShoppingCart() {
        items = new ArrayList<>();
    }

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public void addProduct(Product product) {
        items.add(product);
        System.out.println(product.getName() + " added to cart.");
    }

    public void showCart() {
        System.out.println("\n🛒 Your Cart:");
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            for (Product item : items) {
                System.out.println("- " + item);
            }
        }
    }
}
