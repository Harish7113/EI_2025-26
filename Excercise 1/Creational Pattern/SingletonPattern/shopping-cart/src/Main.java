import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = ShoppingCart.getInstance();

        System.out.println("Welcome to Shopping Cart Application!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Product");
            System.out.println("2. View Cart");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    Product product = new Product(name, price);
                    cart.addProduct(product);
                    break;

                case 2:
                    cart.showCart();
                    break;

                case 3:
                    System.out.println("Thank you for shopping!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
