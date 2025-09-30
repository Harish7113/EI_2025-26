package exception;

import model.Task;

/**
 * Exception thrown when a task conflicts with an existing task.
 */
public class TaskConflictException extends RuntimeException {
    
    private final Task conflictingTask;
    private final Task existingTask;
    
    public TaskConflictException(String message, Task conflictingTask, Task existingTask) {
        super(message);
        this.conflictingTask = conflictingTask;
        this.existingTask = existingTask;
    }
    
    public Task getConflictingTask() {
        return conflictingTask;
    }
    
    public Task getExistingTask() {
        return existingTask;
    }
}
