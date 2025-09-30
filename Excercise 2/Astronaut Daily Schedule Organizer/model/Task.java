package model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

/**
 * Simple Task model representing a scheduled task.
 * Contains all essential task information.
 */
public class Task implements Comparable<Task> {
    
    private final String id;
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private Priority priority;
    private boolean completed;
    
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    
    public Task(String description, LocalTime startTime, LocalTime endTime, Priority priority) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.completed = false;
    }
    
    // Validation method
    public static void validateTimeSlot(LocalTime start, LocalTime end) {
        if (start.isAfter(end) || start.equals(end)) {
            throw new IllegalArgumentException("End time must be after start time");
        }
    }
    
    // Check if this task overlaps with another
    public boolean overlaps(Task other) {
        return !(this.endTime.isBefore(other.startTime) || 
                 this.startTime.isAfter(other.endTime));
    }
    
    // Getters
    public String getId() { return id; }
    public String getDescription() { return description; }
    public LocalTime getStartTime() { return startTime; }
    public LocalTime getEndTime() { return endTime; }
    public Priority getPriority() { return priority; }
    public boolean isCompleted() { return completed; }
    
    // Setters
    public void setDescription(String description) { 
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        this.description = description.trim(); 
    }
    
    public void setStartTime(LocalTime startTime) { 
        this.startTime = startTime; 
        validateTimeSlot(this.startTime, this.endTime);
    }
    
    public void setEndTime(LocalTime endTime) { 
        this.endTime = endTime; 
        validateTimeSlot(this.startTime, this.endTime);
    }
    
    public void setPriority(Priority priority) { this.priority = priority; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    
    // Helper methods
    public String getStartTimeString() {
        return startTime.format(TIME_FORMATTER);
    }
    
    public String getEndTimeString() {
        return endTime.format(TIME_FORMATTER);
    }
    
    @Override
    public int compareTo(Task other) {
        // Sort by start time first, then by priority (HIGH first)
        int timeComparison = this.startTime.compareTo(other.startTime);
        if (timeComparison != 0) {
            return timeComparison;
        }
        return Integer.compare(other.priority.ordinal(), this.priority.ordinal());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return Objects.equals(id, task.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        String statusMark = completed ? " ✓" : "";
        return String.format("%s - %s: %s [%s]%s", 
            getStartTimeString(), getEndTimeString(), 
            description, priority, statusMark);
    }
}
