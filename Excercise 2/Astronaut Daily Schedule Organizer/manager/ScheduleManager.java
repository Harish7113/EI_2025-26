package manager;

import exception.TaskConflictException;
import exception.TaskNotFoundException;
import factory.TaskFactory;
import model.Priority;
import model.Task;
import observer.ScheduleObserver;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Singleton class that manages the astronaut schedule.
 * Provides all CRUD operations and conflict detection.
 */
public class ScheduleManager {
    
    private static ScheduleManager instance;
    private final List<Task> tasks;
    private final List<ScheduleObserver> observers;
    
    private ScheduleManager() {
        this.tasks = new ArrayList<>();
        this.observers = new ArrayList<>();
    }
    
    /**
     * Gets the singleton instance.
     */
    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }
    
    /**
     * Adds an observer to receive notifications.
     */
    public void addObserver(ScheduleObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }
    
    /**
     * Removes an observer.
     */
    public void removeObserver(ScheduleObserver observer) {
        observers.remove(observer);
    }
    
    /**
     * Adds a new task to the schedule.
     */
    public Task addTask(String description, String startTime, String endTime, String priority) {
        Task newTask = TaskFactory.createTask(description, startTime, endTime, priority);
        
        // Check for conflicts
        Task conflictingTask = findConflictingTask(newTask);
        if (conflictingTask != null) {
            notifyTaskConflict(newTask, conflictingTask);
            throw new TaskConflictException(
                "Task conflicts with existing task \"" + conflictingTask.getDescription() + "\"",
                newTask, conflictingTask);
        }
        
        tasks.add(newTask);
        sortTasks();
        notifyTaskAdded(newTask);
        
        return newTask;
    }
    
    /**
     * Removes a task by description.
     */
    public Task removeTask(String description) {
        Task task = findTaskByDescription(description);
        tasks.remove(task);
        notifyTaskRemoved(task);
        return task;
    }
    
    /**
     * Updates an existing task.
     */
    public Task updateTask(String oldDescription, String newDescription, 
                          String startTime, String endTime, String priority) {
        Task existingTask = findTaskByDescription(oldDescription);
        
        // Create updated task
        Task updatedTask = TaskFactory.updateTask(existingTask, newDescription, 
                                                startTime, endTime, priority);
        
        // Temporarily remove the old task to check for conflicts
        tasks.remove(existingTask);
        
        try {
            // Check for conflicts with other tasks
            Task conflictingTask = findConflictingTask(updatedTask);
            if (conflictingTask != null) {
                // Restore the old task
                tasks.add(existingTask);
                sortTasks();
                
                notifyTaskConflict(updatedTask, conflictingTask);
                throw new TaskConflictException(
                    "Updated task conflicts with existing task \"" + conflictingTask.getDescription() + "\"",
                    updatedTask, conflictingTask);
            }
            
            // Update the existing task's properties
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStartTime(updatedTask.getStartTime());
            existingTask.setEndTime(updatedTask.getEndTime());
            existingTask.setPriority(updatedTask.getPriority());
            
            tasks.add(existingTask);
            sortTasks();
            notifyTaskUpdated(updatedTask, existingTask);
            
            return existingTask;
            
        } catch (Exception e) {
            // Restore the old task in case of any error
            tasks.add(existingTask);
            sortTasks();
            throw e;
        }
    }
    
    /**
     * Marks a task as completed.
     */
    public Task markTaskCompleted(String description) {
        Task task = findTaskByDescription(description);
        Task oldTask = new Task(task.getDescription(), task.getStartTime(), 
                               task.getEndTime(), task.getPriority());
        oldTask.setCompleted(task.isCompleted());
        
        task.setCompleted(true);
        notifyTaskUpdated(oldTask, task);
        
        return task;
    }
    
    /**
     * Gets all tasks sorted by start time.
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }
    
    /**
     * Gets active (non-completed) tasks.
     */
    public List<Task> getActiveTasks() {
        return tasks.stream()
                   .filter(task -> !task.isCompleted())
                   .collect(Collectors.toList());
    }
    
    /**
     * Gets tasks by priority level.
     */
    public List<Task> getTasksByPriority(Priority priority) {
        return tasks.stream()
                   .filter(task -> task.getPriority() == priority)
                   .collect(Collectors.toList());
    }
    
    /**
     * Gets completed tasks.
     */
    public List<Task> getCompletedTasks() {
        return tasks.stream()
                   .filter(Task::isCompleted)
                   .collect(Collectors.toList());
    }
    
    /**
     * Clears all tasks from the schedule.
     */
    public void clearSchedule() {
        tasks.clear();
        notifyScheduleCleared();
    }
    
    /**
     * Gets the total number of tasks.
     */
    public int getTaskCount() {
        return tasks.size();
    }
    
    /**
     * Checks if the schedule is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
    
    private Task findTaskByDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be empty");
        }
        
        String searchDescription = description.trim().toLowerCase();
        return tasks.stream()
                   .filter(task -> task.getDescription().toLowerCase().equals(searchDescription))
                   .findFirst()
                   .orElseThrow(() -> new TaskNotFoundException("Task not found: " + description));
    }
    
    private Task findConflictingTask(Task newTask) {
        return tasks.stream()
                   .filter(existingTask -> !existingTask.isCompleted() && 
                                          existingTask.overlaps(newTask))
                   .findFirst()
                   .orElse(null);
    }
    
    private void sortTasks() {
        tasks.sort(Comparator.comparing(Task::getStartTime)
                            .thenComparing(task -> task.getPriority().ordinal()));
    }
    
    // Observer notification methods
    private void notifyTaskAdded(Task task) {
        observers.forEach(observer -> observer.onTaskAdded(task));
    }
    
    private void notifyTaskRemoved(Task task) {
        observers.forEach(observer -> observer.onTaskRemoved(task));
    }
    
    private void notifyTaskUpdated(Task oldTask, Task newTask) {
        observers.forEach(observer -> observer.onTaskUpdated(oldTask, newTask));
    }
    
    private void notifyTaskConflict(Task newTask, Task existingTask) {
        observers.forEach(observer -> observer.onTaskConflict(newTask, existingTask));
    }
    
    private void notifyScheduleCleared() {
        observers.forEach(observer -> observer.onScheduleCleared());
    }
}
