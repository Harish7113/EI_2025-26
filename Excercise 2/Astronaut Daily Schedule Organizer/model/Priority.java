package model;

/**
 * Priority levels for tasks.
 */
public enum Priority {
    HIGH("High Priority", "⚠"),
    MEDIUM("Medium Priority", "▪"),
    LOW("Low Priority", "○");
    
    private final String displayName;
    private final String symbol;
    
    Priority(String displayName, String symbol) {
        this.displayName = displayName;
        this.symbol = symbol;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getSymbol() {
        return symbol;
    }
    
    public static Priority fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Priority cannot be null");
        }
        
        try {
            return Priority.valueOf(value.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "Invalid priority: " + value + ". Valid values are: HIGH, MEDIUM, LOW");
        }
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
