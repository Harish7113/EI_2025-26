import exception.TaskConflictException;
import exception.TaskNotFoundException;
import manager.ScheduleManager;
import model.Priority;
import model.Task;
import observer.ConsoleNotifier;
import view.ConsoleView;

import java.io.IOException;
import java.util.List;
import java.util.logging.*;

/**
 * Main application class for the Astronaut Schedule Organizer.
 * Provides a console-based interface for managing daily schedules.
 */
public class AstronautSchedulerApp {
    
    private static final Logger logger = Logger.getLogger(AstronautSchedulerApp.class.getName());
    
    private final ScheduleManager scheduleManager;
    private final ConsoleView view;
    private final ConsoleNotifier notifier;
    private boolean running;
    
    public AstronautSchedulerApp() {
        this.scheduleManager = ScheduleManager.getInstance();
        this.view = new ConsoleView();
        this.notifier = new ConsoleNotifier();
        this.running = true;
        
        // Attach observer
        scheduleManager.addObserver(notifier);
    }
    
    /**
     * Main application entry point.
     */
    public static void main(String[] args) {
        setupLogging();
        
        try {
            AstronautSchedulerApp app = new AstronautSchedulerApp();
            app.run();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Fatal application error", e);
            System.err.println("A fatal error occurred. Check the logs for details.");
        }
    }
    
    /**
     * Main application loop.
     */
    public void run() {
        logger.info("Astronaut Schedule Organizer started");
        
        view.displayHeader();
        
        while (running) {
            try {
                int choice = view.displayMainMenu();
                processMenuChoice(choice);
                
                if (choice != 0 && running) {
                    view.waitForEnter();
                }
                
            } catch (Exception e) {
                logger.log(Level.WARNING, "Error processing menu choice", e);
                view.displayError(e.getMessage());
                view.waitForEnter();
            }
        }
        
        shutdown();
    }
    
    private void processMenuChoice(int choice) {
        switch (choice) {
            case 1:
                handleAddTask();
                break;
            case 2:
                handleRemoveTask();
                break;
            case 3:
                handleViewAllTasks();
                break;
            case 4:
                handleViewTasksByPriority();
                break;
            case 5:
                handleEditTask();
                break;
            case 6:
                handleMarkTaskCompleted();
                break;
            case 7:
                handleViewCompletedTasks();
                break;
            case 8:
                handleClearSchedule();
                break;
            case 9:
                view.displayHelp();
                break;
            case 0:
                handleExit();
                break;
            default:
                view.displayError("Invalid choice");
        }
    }
    
    private void handleAddTask() {
        try {
            ConsoleView.TaskInput input = view.getTaskInput();
            Task task = scheduleManager.addTask(input.description, input.startTime, 
                                              input.endTime, input.priority);
            logger.info("Task added: " + task.getDescription());
            
        } catch (TaskConflictException e) {
            logger.warning("Task conflict: " + e.getMessage());
            // Error message already shown by observer
            
        } catch (IllegalArgumentException e) {
            logger.warning("Invalid input for add task: " + e.getMessage());
            view.displayError(e.getMessage());
        }
    }
    
    private void handleRemoveTask() {
        List<Task> activeTasks = scheduleManager.getActiveTasks();
        if (activeTasks.isEmpty()) {
            view.displayInfo("No tasks to remove.");
            return;
        }
        
        view.displayTasks(activeTasks, "Active Tasks");
        
        try {
            String description = view.getStringInput("\nEnter task description to remove: ");
            Task removedTask = scheduleManager.removeTask(description);
            logger.info("Task removed: " + removedTask.getDescription());
            
        } catch (TaskNotFoundException e) {
            logger.warning("Task not found for removal: " + e.getMessage());
            view.displayError(e.getMessage());
        }
    }
    
    private void handleViewAllTasks() {
        List<Task> tasks = scheduleManager.getAllTasks();
        view.displayTasks(tasks, "All Scheduled Tasks");
        
        if (!tasks.isEmpty()) {
            displayStatistics();
        }
    }
    
    private void handleViewTasksByPriority() {
        try {
            String priorityStr = view.getStringInput("Enter priority (HIGH/MEDIUM/LOW): ");
            Priority priority = Priority.fromString(priorityStr);
            
            List<Task> tasks = scheduleManager.getTasksByPriority(priority);
            view.displayTasks(tasks, priority + " Priority Tasks");
            
        } catch (IllegalArgumentException e) {
            view.displayError(e.getMessage());
        }
    }
    
    private void handleEditTask() {
        List<Task> activeTasks = scheduleManager.getActiveTasks();
        if (activeTasks.isEmpty()) {
            view.displayInfo("No tasks to edit.");
            return;
        }
        
        view.displayTasks(activeTasks, "Active Tasks");
        
        try {
            ConsoleView.TaskUpdateInput input = view.getTaskUpdateInput();
            Task updatedTask = scheduleManager.updateTask(input.oldDescription, 
                                                        input.newDescription,
                                                        input.startTime, 
                                                        input.endTime, 
                                                        input.priority);
            
            logger.info("Task updated: " + updatedTask.getDescription());
            
        } catch (TaskNotFoundException e) {
            logger.warning("Task not found for update: " + e.getMessage());
            view.displayError(e.getMessage());
            
        } catch (TaskConflictException e) {
            logger.warning("Task conflict during update: " + e.getMessage());
            // Error message already shown by observer
            
        } catch (IllegalArgumentException e) {
            logger.warning("Invalid input for edit task: " + e.getMessage());
            view.displayError(e.getMessage());
        }
    }
    
    private void handleMarkTaskCompleted() {
        List<Task> activeTasks = scheduleManager.getActiveTasks();
        if (activeTasks.isEmpty()) {
            view.displayInfo("No active tasks to mark as completed.");
            return;
        }
        
        view.displayTasks(activeTasks, "Active Tasks");
        
        try {
            String description = view.getStringInput("\nEnter task description to mark as completed: ");
            Task completedTask = scheduleManager.markTaskCompleted(description);
            logger.info("Task completed: " + completedTask.getDescription());
            view.displaySuccess("Task marked as completed: " + completedTask.getDescription());
            
        } catch (TaskNotFoundException e) {
            logger.warning("Task not found for completion: " + e.getMessage());
            view.displayError(e.getMessage());
        }
    }
    
    private void handleViewCompletedTasks() {
        List<Task> completedTasks = scheduleManager.getCompletedTasks();
        view.displayTasks(completedTasks, "Completed Tasks");
    }
    
    private void handleClearSchedule() {
        if (scheduleManager.isEmpty()) {
            view.displayInfo("Schedule is already empty.");
            return;
        }
        
        boolean confirmed = view.getConfirmation("Are you sure you want to clear all tasks?");
        if (confirmed) {
            scheduleManager.clearSchedule();
            logger.info("Schedule cleared by user");
        } else {
            view.displayInfo("Clear operation cancelled.");
        }
    }
    
    private void handleExit() {
        boolean confirmed = view.getConfirmation("Are you sure you want to exit?");
        if (confirmed) {
            running = false;
            logger.info("Application exit requested");
        }
    }
    
    private void displayStatistics() {
        List<Task> allTasks = scheduleManager.getAllTasks();
        List<Task> completedTasks = scheduleManager.getCompletedTasks();
        List<Task> highPriorityTasks = scheduleManager.getTasksByPriority(Priority.HIGH);
        List<Task> mediumPriorityTasks = scheduleManager.getTasksByPriority(Priority.MEDIUM);
        List<Task> lowPriorityTasks = scheduleManager.getTasksByPriority(Priority.LOW);
        
        view.displayStatistics(
            allTasks.size(),
            completedTasks.size(),
            highPriorityTasks.size(),
            mediumPriorityTasks.size(),
            lowPriorityTasks.size()
        );
    }
    
    private void shutdown() {
        logger.info("Application shutting down");
        
        try {
            // Cleanup resources
            scheduleManager.removeObserver(notifier);
            view.close();
            
            System.out.println("\n" + "=".repeat(50));
            System.out.println("Thank you for using Astronaut Schedule Organizer!");
            System.out.println("Have a productive day! 🚀");
            System.out.println("=".repeat(50));
            
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error during shutdown", e);
        }
        
        logger.info("Application shutdown complete");
    }
    
    private static void setupLogging() {
        try {
            LogManager.getLogManager().reset();
            
            // File handler for logging to file
            FileHandler fileHandler = new FileHandler("astronaut-scheduler.log", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
            
            // Root logger setup
            Logger rootLogger = Logger.getLogger("");
            rootLogger.setLevel(Level.INFO);
            rootLogger.addHandler(fileHandler);
            
            // Console handler (only for severe errors)
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.SEVERE);
            rootLogger.addHandler(consoleHandler);
            
        } catch (IOException e) {
            System.err.println("Failed to setup logging: " + e.getMessage());
        }
    }
}
