package observer;

import model.Task;

/**
 * Console implementation of ScheduleObserver that prints notifications to console.
 */
public class ConsoleNotifier implements ScheduleObserver {
    
    @Override
    public void onTaskAdded(Task task) {
        System.out.println("✓ Task added successfully: " + task.getDescription());
    }
    
    @Override
    public void onTaskRemoved(Task task) {
        System.out.println("✓ Task removed successfully: " + task.getDescription());
    }
    
    @Override
    public void onTaskUpdated(Task oldTask, Task newTask) {
        System.out.println("✓ Task updated successfully: " + newTask.getDescription());
    }
    
    @Override
    public void onTaskConflict(Task newTask, Task existingTask) {
        System.err.println("✗ Error: Task conflicts with existing task \"" + 
                          existingTask.getDescription() + "\" scheduled from " + 
                          existingTask.getStartTimeString() + " to " + 
                          existingTask.getEndTimeString());
    }
    
    @Override
    public void onScheduleCleared() {
        System.out.println("✓ Schedule cleared successfully");
    }
}
