import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChristmasTree tree = new ChristmasTreeImpl();

        System.out.println("Choose decorations to add to the Christmas Tree:");
        System.out.println("1. Garland");
        System.out.println("2. Lights");
        System.out.println("3. Star");
        System.out.println("4. Done");

        boolean done = false;
        while (!done) {
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    tree = new Garland(tree);
                    break;
                case 2:
                    tree = new Lights(tree);
                    break;
                case 3:
                    tree = new Star(tree);
                    break;
                case 4:
                    done = true;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }

        System.out.println("\n Final Tree Decoration: " + tree.decorate());
        sc.close();
    }
}
