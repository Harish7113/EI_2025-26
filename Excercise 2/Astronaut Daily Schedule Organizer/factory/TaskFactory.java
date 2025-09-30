package factory;

import model.Priority;
import model.Task;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Factory class for creating Task objects with validation.
 */
public class TaskFactory {
    
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    
    /**
     * Creates a new task with validation.
     */
    public static Task createTask(String description, String startTime, String endTime, String priority) {
        // Validate description
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be empty");
        }
        if (description.trim().length() > 200) {
            throw new IllegalArgumentException("Task description cannot exceed 200 characters");
        }
        
        // Parse and validate times
        LocalTime start = parseTime(startTime, "start time");
        LocalTime end = parseTime(endTime, "end time");
        
        // Validate time logic
        if (start.isAfter(end) || start.equals(end)) {
            throw new IllegalArgumentException("End time must be after start time");
        }
        
        // Validate duration constraints
        long minutes = java.time.Duration.between(start, end).toMinutes();
        if (minutes < 5) {
            throw new IllegalArgumentException("Task duration must be at least 5 minutes");
        }
        if (minutes > 480) { // 8 hours
            throw new IllegalArgumentException("Task duration cannot exceed 8 hours");
        }
        
        // Parse priority
        Priority taskPriority = Priority.fromString(priority);
        
        return new Task(description.trim(), start, end, taskPriority);
    }
    
    /**
     * Updates an existing task with new values.
     */
    public static Task updateTask(Task existingTask, String description, String startTime, 
                                 String endTime, String priority) {
        // Use existing values if new ones are not provided or empty
        String newDescription = (description == null || description.trim().isEmpty()) 
                              ? existingTask.getDescription() : description.trim();
        
        LocalTime newStart = (startTime == null || startTime.trim().isEmpty()) 
                           ? existingTask.getStartTime() : parseTime(startTime, "start time");
        
        LocalTime newEnd = (endTime == null || endTime.trim().isEmpty()) 
                         ? existingTask.getEndTime() : parseTime(endTime, "end time");
        
        Priority newPriority = (priority == null || priority.trim().isEmpty()) 
                             ? existingTask.getPriority() : Priority.fromString(priority);
        
        // Create new task with updated values
        Task updatedTask = createTask(newDescription, 
                                    newStart.format(TIME_FORMATTER),
                                    newEnd.format(TIME_FORMATTER), 
                                    newPriority.name());
        
        // Preserve completion status
        updatedTask.setCompleted(existingTask.isCompleted());
        
        return updatedTask;
    }
    
    private static LocalTime parseTime(String timeStr, String fieldName) {
        if (timeStr == null || timeStr.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        
        try {
            return LocalTime.parse(timeStr.trim(), TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid " + fieldName + " format: " + timeStr + 
                                             ". Expected format: HH:mm (e.g., 09:30)");
        }
    }
}
