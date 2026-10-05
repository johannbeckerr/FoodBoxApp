# FoodBoxApp

A Java console app that manages food boxes in a restaurant storage unit (max 8 boxes). It uses a **stack** (LIFO) and a **circular queue** (FIFO), both built from scratch on plain arrays, without `java.util` collections.

> 🚧 Work in progress. Developed for the *Algorithms and Constructs* module at CCT College Dublin (2026).

## What's done
- `FoodItem`: name, weight, best-before date and the time placed in storage.
- `StackStorage`: push, pop, peek, display, search.
- `QueueStorage`: enqueue, dequeue, peek, display. Uses `% capacity` to wrap around, so add and remove stay O(1) without shifting elements.

## Next steps
- An interactive menu with input validation.
- An interface and an abstract base class.

## Complexity
Add, remove and peek are **O(1)**. Display and search are **O(n)**.

## Run
Requires Java 21 and Maven. Open the project in an IDE and run `org.cct.Main`.

---
I wrote the data structure logic myself, using an AI assistant as a tutor to explain concepts and review my code.
