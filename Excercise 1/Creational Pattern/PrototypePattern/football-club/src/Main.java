import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PlayerRegistry registry = new PlayerRegistry();

        // Create default prototypes
        registry.addPrototype("Striker", new FootballPlayer("Default Striker", "Striker", 9));
        registry.addPrototype("Goalkeeper", new FootballPlayer("Default Keeper", "Goalkeeper", 1));

        System.out.println("Welcome to Football Club Application!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Clone a Striker");
            System.out.println("2. Clone a Goalkeeper");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            if (choice == 3) {
                System.out.println("Exiting application. Goodbye!");
                break;
            }

            String type = null;
            if (choice == 1) type = "Striker";
            else if (choice == 2) type = "Goalkeeper";
            else {
                System.out.println("Invalid choice! Try again.");
                continue;
            }

            Player clonedPlayer = registry.getPrototype(type);

            if (clonedPlayer instanceof FootballPlayer) {
                FootballPlayer player = (FootballPlayer) clonedPlayer;

                System.out.print("Enter player name: ");
                player.setName(sc.nextLine());

                System.out.print("Enter jersey number: ");
                player.setJerseyNumber(sc.nextInt());
                sc.nextLine(); // Consume newline

                System.out.println("\n--- Player Details ---");
                player.showDetails();
            }
        }

        sc.close();
    }
}
