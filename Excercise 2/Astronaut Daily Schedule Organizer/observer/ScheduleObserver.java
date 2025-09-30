package observer;

import model.Task;

/**
 * Observer interface for schedule events.
 */
public interface ScheduleObserver {
    
    void onTaskAdded(Task task);
    
    void onTaskRemoved(Task task);
    
    void onTaskUpdated(Task oldTask, Task newTask);
    
    void onTaskConflict(Task newTask, Task existingTask);
    
    void onScheduleCleared();
}
