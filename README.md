# EI_Coding Exercises (Exercise 1 & Exercise 2)

A professional showcase of compact Java applications, exemplifying robust design patterns and principles of modular architecture.

## Contents

- Exercise 1: A set of mini Java application organized by design pattern categories:

  - Behavioural patterns
    - Observer pattern — `Exercise 1/Behavioural Pattern/ObserverPattern/Investor-app`
    - Strategy pattern — `Exercise 1/Behavioural Pattern/StrategPattern/payment-method`
  - Creational patterns
    - Prototype pattern — `Exercise 1/Creational Pattern/PrototypePattern/football-club`
    - Singleton pattern — `Exercise 1/Creational Pattern/SingletonPattern/shopping-cart`
  - Structural patterns
    - Composite pattern — `Exercise 1/Structural Pattern/CompositePattern/company-app`
    - Decorator pattern — `Exercise 1/Structural Pattern/DecoratorPattern/ChristmasTree`

- Exercise 2: A Java application under `Exercise 2/Astronaut Daily Schedule Organizer` with a simple task scheduling app showcasing modular design, factories, exceptions, and observer usage.

## Project goals

- Offer small, self-contained Java implementations of fundamental object oriented design patterns.
- Serve as a practical teaching resource and accessible reference for learners.
- Guarantee local ease-of-use, allowing for rapid execution and clear examination of pattern responsibilities and inter-object collaborations.

## Prerequisites

- Java Development Kit (JDK) 8 or later
- A Java build tool or IDE (javac/java on command line, or IntelliJ IDEA, Eclipse, VS Code + Java extensions)

> Note: Each example is a small standalone Java program. There is no top-level build system in this repository — run each sample from its `src` directory using javac/java or import the folder into your IDE as a Java project.

## How to run the examples (command line)

Below are sample commands you can run from Windows PowerShell. Adjust the paths if your workspace root differs.

1. Observer pattern (Investor app)

```powershell
cd Exercise 1\Behavioural Pattern\ObserverPattern\Investor-app\src
javac *.java
java Main
```

2. Strategy pattern (Payment methods)

```powershell
cd Exercise 1\Behavioural Pattern\StrategyPattern\payment-method\src
javac *.java
java Main
```

3. Prototype pattern (Football club)

```powershell
cd Exercise 1\Creational Pattern\PrototypePattern\football-club\src
javac *.java
java Main
```

4. Singleton pattern (Shopping cart)

```powershell
cd Exercise 1\Creational Pattern\SingletonPattern\shopping-cart\src
javac *.java
java Main
```

5. Composite pattern (Company)

```powershell
cd Exercise 1\Structural Pattern\CompositePattern\company-app\src
javac *.java
java Main
```

6. Decorator pattern (ChristmasTree)

```powershell
cd Exercise 1\Structural Pattern\DecoratorPattern\ChristmasTree\src
javac *.java
java Main
```

7. Exercise 2 (Astronaut Daily Schedule Organizer)

```powershell
cd Exercise 2\Astronaut Daily Schedule Organizer
javac AstronautSchedulerApp.java
java AstronautSchedulerApp
```

## Project structure

- Exercise 1 — grouped by pattern category with each example in its own folder and a simple `src` package-less or package-based layout. Good for quick inspection and manual compilation.
- Exercise 2 — a slightly more structured Java package layout under `AstronautSchedulerApp-main.AstronautSchedulerApp` showing a small app with controllers, managers, factories, exceptions, and observers.

## Notes on code quality and expectations

- These are educational examples: clarity and demonstrating the pattern are prioritized over production concerns.
- Feel free to refactor, add tests, or wire up a build system (Maven/Gradle) if you want to integrate them into larger projects.
