# Astronaut Daily Schedule Organizer

## 📋 Overview

A simplified, console-based Java application for managing astronaut daily schedules. This version provides all the core functionality with a cleaner, more straightforward implementation while maintaining professional coding standards.

## Class Diagram
<img src="Class diagram.png">

## 🚀 Features

### Core Functionality
- **Task Management**: Complete CRUD operations for daily tasks
- **Conflict Detection**: Automatic detection and prevention of overlapping tasks
- **Priority Organization**: Three-tier priority system (HIGH, MEDIUM, LOW)
- **Task Completion**: Mark tasks as completed and track progress
- **Time Validation**: Comprehensive validation of time formats and constraints
- **Schedule Views**: View all tasks sorted by time or filtered by priority

### Design Patterns
- **Singleton Pattern**: ScheduleManager ensures single instance
- **Factory Pattern**: TaskFactory handles task creation and validation
- **Observer Pattern**: Real-time console notifications for schedule events

## 🏗️ Architecture

### Package Structure

```
├── AstronautSchedulerApp.java     # Main application
├── exception/                     # Custom exceptions
│   ├── TaskConflictException      # Task conflict handling
│   └── TaskNotFoundException      # Missing task handling
├── factory/                       # Factory pattern
│   └── TaskFactory               # Task creation and validation
├── manager/                       # Business logic
│   └── ScheduleManager           # Schedule management (Singleton)
├── model/                         # Domain models
│   ├── Task                      # Task entity
│   └── Priority                  # Priority enumeration
├── observer/                      # Observer pattern
│   ├── ScheduleObserver          # Observer interface
│   └── ConsoleNotifier           # Console notifications
└── view/                          # User interface
    └── ConsoleView               # Console UI utilities
```

## 📦 Quick Start

## 🎮 Usage

### Main Menu Options

```
MAIN MENU
----------------------------------------
1. Add Task
2. Remove Task
3. View All Tasks
4. View Tasks by Priority
5. Edit Task
6. Mark Task as Completed
7. View Completed Tasks
8. Clear Schedule
9. Help
0. Exit
```

### Example Usage

**Adding a Task:**
```
=== ADD NEW TASK ===
Enter task description: Morning Exercise
Enter start time (HH:mm): 07:00
Enter end time (HH:mm): 08:00
Enter priority (HIGH/MEDIUM/LOW): HIGH
✓ Task added successfully: Morning Exercise
```

**Viewing Tasks:**
```
ALL SCHEDULED TASKS
------------------------------------------------------------
 1. 07:00 - 08:00: Morning Exercise [High Priority]
 2. 09:00 - 10:00: Team Meeting [Medium Priority]
 3. 14:00 - 15:00: Equipment Check [High Priority]
------------------------------------------------------------
Total: 3 tasks
```

### Input Requirements

- **Time Format**: 24-hour format (HH:mm) - e.g., `07:30`, `14:00`, `23:45`
- **Priority Levels**: `HIGH`, `MEDIUM`, `LOW` (case-insensitive)
- **Description**: 1-200 characters, cannot be empty
- **Duration**: Minimum 5 minutes, maximum 8 hours
- **Conflicts**: Overlapping tasks are automatically detected and prevented

## 📁 Logging

The application logs activities to `astronaut-scheduler.log` in the project directory:
- Task operations (add, remove, update, complete)
- Errors and exceptions
- Application lifecycle events

### Maintained Functionality
- ✅ All core CRUD operations
- ✅ Complete conflict detection algorithm
- ✅ Priority-based task organization
- ✅ Observer pattern for notifications
- ✅ Singleton and Factory patterns
- ✅ Comprehensive unit test coverage
- ✅ Input validation and error handling
- ✅ Logging mechanism

## 📈 Code Quality

- **Clean Architecture**: Clear separation of concerns with logical package structure
- **SOLID Principles**: Single responsibility, open/closed, and dependency inversion
- **Design Patterns**: Proper implementation of Singleton, Factory, and Observer patterns
- **Error Handling**: Graceful handling of all error scenarios
- **Testing**: Comprehensive unit test coverage with JUnit 5
- **Documentation**: Well-documented code with clear method signatures

## 🔍 Example Test Cases

The application includes positive and negative test cases covering:

**Positive Cases:**
- Adding valid tasks without conflicts
- Viewing tasks in sorted order
- Updating task details
- Marking tasks as completed
- Filtering by priority levels

**Negative Cases:**
- Adding overlapping tasks (conflict detection)
- Removing non-existent tasks
- Invalid time formats (25:00, 9:60, etc.)
- Invalid priority levels
- Tasks with invalid durations

---
