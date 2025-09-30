package view;

import model.Priority;
import model.Task;

import java.util.List;
import java.util.Scanner;

/**
 * Simple console view for displaying information and getting user input.
 */
public class ConsoleView {
    
    private final Scanner scanner;
    
    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Displays the application header.
     */
    public void displayHeader() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("    ASTRONAUT DAILY SCHEDULE ORGANIZER - SIMPLE");
        System.out.println("=".repeat(60));
    }
    
    /**
     * Displays the main menu and returns user choice.
     */
    public int displayMainMenu() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("MAIN MENU");
        System.out.println("-".repeat(40));
        System.out.println("1. Add Task");
        System.out.println("2. Remove Task");
        System.out.println("3. View All Tasks");
        System.out.println("4. View Tasks by Priority");
        System.out.println("5. Edit Task");
        System.out.println("6. Mark Task as Completed");
        System.out.println("7. View Completed Tasks");
        System.out.println("8. Clear Schedule");
        System.out.println("9. Help");
        System.out.println("0. Exit");
        System.out.println("-".repeat(40));
        
        return getIntInput("Enter your choice (0-9): ", 0, 9);
    }
    
    /**
     * Gets task details from user for adding a new task.
     */
    public TaskInput getTaskInput() {
        System.out.println("\n=== ADD NEW TASK ===");
        String description = getStringInput("Enter task description: ");
        String startTime = getStringInput("Enter start time (HH:mm): ");
        String endTime = getStringInput("Enter end time (HH:mm): ");
        String priority = getStringInput("Enter priority (HIGH/MEDIUM/LOW): ");
        
        return new TaskInput(description, startTime, endTime, priority);
    }
    
    /**
     * Gets task details from user for updating a task.
     */
    public TaskUpdateInput getTaskUpdateInput() {
        System.out.println("\n=== EDIT TASK ===");
        String oldDescription = getStringInput("Enter description of task to edit: ");
        
        System.out.println("Enter new values (leave empty to keep current values):");
        String newDescription = getOptionalStringInput("New description: ");
        String startTime = getOptionalStringInput("New start time (HH:mm): ");
        String endTime = getOptionalStringInput("New end time (HH:mm): ");
        String priority = getOptionalStringInput("New priority (HIGH/MEDIUM/LOW): ");
        
        return new TaskUpdateInput(oldDescription, newDescription, startTime, endTime, priority);
    }
    
    /**
     * Displays a list of tasks.
     */
    public void displayTasks(List<Task> tasks, String title) {
        System.out.println("\n" + "-".repeat(60));
        System.out.println(title.toUpperCase());
        System.out.println("-".repeat(60));
        
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.printf("%2d. %s\n", i + 1, task.toString());
            }
            System.out.println("-".repeat(60));
            System.out.printf("Total: %d tasks\n", tasks.size());
        }
    }
    
    /**
     * Displays schedule statistics.
     */
    public void displayStatistics(int total, int completed, int high, int medium, int low) {
        System.out.println("\n" + "-".repeat(30));
        System.out.println("SCHEDULE STATISTICS");
        System.out.println("-".repeat(30));
        System.out.printf("Total Tasks:     %d\n", total);
        System.out.printf("Completed:       %d\n", completed);
        System.out.printf("High Priority:   %d\n", high);
        System.out.printf("Medium Priority: %d\n", medium);
        System.out.printf("Low Priority:    %d\n", low);
        System.out.println("-".repeat(30));
    }
    
    /**
     * Displays help information.
     */
    public void displayHelp() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("HELP - USER GUIDE");
        System.out.println("=".repeat(50));
        System.out.println("FEATURES:");
        System.out.println("• Add, edit, remove, and view tasks");
        System.out.println("• Automatic conflict detection");
        System.out.println("• Priority-based organization");
        System.out.println("• Mark tasks as completed");
        System.out.println();
        System.out.println("TIME FORMAT:");
        System.out.println("• Use 24-hour format: HH:mm (e.g., 09:30, 14:00)");
        System.out.println("• Minimum task duration: 5 minutes");
        System.out.println("• Maximum task duration: 8 hours");
        System.out.println();
        System.out.println("PRIORITY LEVELS:");
        System.out.println("• HIGH   - Critical tasks");
        System.out.println("• MEDIUM - Important tasks");
        System.out.println("• LOW    - Optional tasks");
        System.out.println("=".repeat(50));
    }
    
    public String getStringInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.print("Input cannot be empty. " + prompt);
            input = scanner.nextLine().trim();
        }
        return input;
    }
    
    public String getOptionalStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    public int getIntInput(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Please enter a number between %d and %d.\n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    public boolean getConfirmation(String message) {
        System.out.print(message + " (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }
    
    public void displayError(String message) {
        System.err.println("✗ Error: " + message);
    }
    
    public void displaySuccess(String message) {
        System.out.println("✓ " + message);
    }
    
    public void displayInfo(String message) {
        System.out.println("ℹ " + message);
    }
    
    public void waitForEnter() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    public void close() {
        scanner.close();
    }
    
    // Inner classes for input data
    public static class TaskInput {
        public final String description;
        public final String startTime;
        public final String endTime;
        public final String priority;
        
        public TaskInput(String description, String startTime, String endTime, String priority) {
            this.description = description;
            this.startTime = startTime;
            this.endTime = endTime;
            this.priority = priority;
        }
    }
    
    public static class TaskUpdateInput {
        public final String oldDescription;
        public final String newDescription;
        public final String startTime;
        public final String endTime;
        public final String priority;
        
        public TaskUpdateInput(String oldDescription, String newDescription, 
                             String startTime, String endTime, String priority) {
            this.oldDescription = oldDescription;
            this.newDescription = newDescription;
            this.startTime = startTime;
            this.endTime = endTime;
            this.priority = priority;
        }
    }
}
