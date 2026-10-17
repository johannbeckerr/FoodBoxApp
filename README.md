# FoodBoxApp

A Java console app that manages food boxes in a restaurant storage unit (max 8 boxes). It uses a **stack** (LIFO) and a **circular queue** (FIFO), both built from scratch on plain arrays, without `java.util` collections.

Developed for the *Algorithms and Constructs* module (CA1) at CCT College Dublin, 2026.

**Try it in your browser:** https://johannbeckerr.github.io/FoodBoxApp/

## How it works
At the start, the chef chooses a mode:
- **1 - Stack:** the same side is used to add and remove, so the last box in is the first box out.
- **2 - Queue:** boxes go in at the front and leave from the opposite side, so the first box in is the first box out.

Menu: `1-add, 2-peek, 3-display, 4-search, 5-remove, 6-exit`

## Classes
- `FoodItem`: name, weight (grams), best-before date and the time placed in storage.
- `StorageActions` (interface): add, remove, peek, display, search, isFull, isEmpty.
- `FoodStorage` (abstract class): shared capacity (8) and the `FoodItem[]` array.
- `StackStorage`: `top` index, push and pop.
- `QueueStorage`: `front`, `rear` and `count`. Uses `% capacity` to wrap around, so add and remove stay O(1) without shifting elements.
- `Main`: the menu. One `FoodStorage` variable holds a stack or a queue (polymorphism).

## Input validation
- Mode and menu accept numbers only; text does not crash the app.
- Food is chosen from a numbered list (1-5): Burger, Pizza, Fries, Sandwich, Hotdog.
- Weight must be a whole number greater than 0.
- Best-before date must be `dd/MM/yyyy`, from today up to 14 days later.
- A full storage is checked before asking for data.
- Peek, remove, display and search show a message when the storage is empty.

## Complexity
| Operation | Stack | Queue |
|---|---|---|
| add | O(1) | O(1) |
| remove | O(1) | O(1) |
| peek | O(1) | O(1) |
| display | O(n) | O(n) |
| search | O(n) | O(n) |

Space: O(n), with n ≤ 8.

## Run
Requires Java 21 and Maven. Open the project in NetBeans or IntelliJ and run `org.cct.Main`.

---
I wrote the core data structure logic (stack and circular queue) myself, using Gemini (Google) as a tutor to explain concepts and review my code. Parts of the input validation, the interface/abstract class and the polymorphism in `Main` were written with AI help, then reviewed and tested by me. All AI use is declared in the AI declaration document submitted with this assessment.

All exact prompts are listed in this link: https://share.gemini.google/xkp3TJmSTn5f